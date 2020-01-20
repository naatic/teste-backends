defmodule LoanHandler.Application.Service.MessageProcessorService do
  @moduledoc false
  alias LoanHandler.Application.Utils.EventParser
  alias LoanHandler.Application.Service.LoanService

  def process(messages) do
    do_process(messages, %{proposals: [], warranties: [], proponents: []}, [])
  end

  defp do_process([], loans, _processed_events) do
    loans
  end

  defp do_process([head | tail], loans, processed_events) do
    parsed_event = EventParser.parse_event(head)
    processed_events ++ [parsed_event]

    unless is_event_already_processed?(parsed_event, processed_events) and
             is_update_request_late?(parsed_event, processed_events) do
      do_process(tail, LoanService.handle_event(loans, parsed_event), processed_events)
    else
      # se o evento já tiver sido processado ou estiver atrasado, chama-se o process novamente
      # sem adicionar na lista de processed events
      do_process(tail, loans, processed_events)
    end
  end

  defp is_event_already_processed?(event, processed_events) do
      Enum.any?(
        processed_events,
        fn p_event ->
          p_event.event_id == event.event_id
        end
      )
  end

  defp is_event_already_processed?(event, []), do: false

  defp is_update_request_late?(event, processed_events) do
    Enum.fetch(
      processed_events,
      fn p_event ->
        p_event.proposal_id == event.proposal_id
      end
    )
    |> Enum.any?(
        fn p_event ->
          p_event.event_timestamp > event.event_timestamp
        end
      )
  end

  defp is_update_request_late?(event, []), do: false
end
