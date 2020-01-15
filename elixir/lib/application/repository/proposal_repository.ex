defmodule LoanHandler.Application.Repository.ProposalRepository do

  def add_proposal(proposals, event_properties) do
    proposals ++ [event_properties]
  end

  def delete_proposal(proposals, event_properties) do
    List.delete(proposals, event_properties)
  end

  def update_proposal(proposals, event_properties) do
    delete_proposal(proposals, event_properties)
    add_proposal(proposals, event_properties)
  end

end
