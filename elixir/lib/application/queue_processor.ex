defmodule LoanHandler.Application.QueueProcessor do
  alias LoanHandler.Application.EventParser

  def process([head | tail]) do
    process([head | tail], %{proposals: [], warranties: [], proponents: []}, [])
  end

  def process([], loans, _processed_events) do
    loans
  end

  def process([head | tail], loans, processed_events) do

    parsed_event = EventParser.parse_event(head)
    processed_events ++ [parsed_event]

  end

end
