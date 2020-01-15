defmodule LoanHandler.Application.MessageProcessor do
  alias LoanHandler.Application.EventParser
  alias LoanHandler.Application.LoansEventHandler

  def process([head | tail]) do
    process([head | tail], %{proposals: [], warranties: [], proponents: []}, [])
  end

  def process([], loans, _processed_events) do
    loans
  end

  def process([head | tail], loans, processed_events) do

    parsed_event = EventParser.parse_event(head)
    processed_events ++ [parsed_event]

    unless is_event_already_processed?(parsed_event, processed_events) and
            is_update_request_late?(parsed_event, processed_events) do
      process(tail, LoansEventHandler.handle_event(loans, parsed_event), processed_events)
    else
      #se o evento já tiver sido processado ou estiver atrasado, chama-se o process novamente
      #utilizando a tail da lista
      process(tail, loans, processed_events)
    end
  end

  defp is_event_already_processed?(event, processed_events) do
    unless Enum.empty?(processed_events) do
      Enum.any?(processed_events,
                fn p_event ->
                      p_event.event_id == event.event_id
                end
      )
    else
      false
    end
  end

  defp is_update_request_late?(event, processed_events) do
    unless Enum.empty?(processed_events) do
      Enum.fetch(processed_events,
                fn p_event ->
                      p_event.proposal_id == event.proposal_id
                end
      )

      |> Enum.any?(
            fn p_event ->
                p_event.event_timestamp > event.event_timestamp
            end
      )
    else
      false
    end
  end
end
