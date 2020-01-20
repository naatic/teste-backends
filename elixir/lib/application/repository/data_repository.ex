defmodule LoanHandler.Application.Repository.DataRepository do
  @moduledoc false
  @doc """
    Generic method to add the event in the event list
  """
  def add(event_list, event_properties) do
    event_list ++ [event_properties]
  end

  @doc """
    Generic method to delete the event in the event list
  """
  def delete(event_list, event_properties) do
    event = Enum.find(event_list, fn e -> e.proposal_id == event_properties.proposal_id end)
    List.delete(event_list, event)
  end

  @doc """
    Generic method to update the event in the event list
  """
  def update(event_list, event_properties) do
    delete(event_list, event_properties)
    add(event_list, event_properties)
  end
end
