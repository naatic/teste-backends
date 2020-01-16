defmodule LoanHandler.Application.Service.LoanService do

  alias LoanHandler.Application.Repository.DataRepository

  alias LoanHandler.Application.Service.ProponentService
  alias LoanHandler.Application.Service.ProposalService
  alias LoanHandler.Application.Service.WarrantyService

  @doc """
    inserts the event in the proposal list based on type
  """
  def handle_event(loans, event) do
    case event.event_action do
      "added" -> add_action(loans, event)
      "created" -> add_action(loans, event)
      "updated" -> update_action(loans, event)
      "deleted" -> delete_action(loans, event)
      "removed" -> delete_action(loans, event)
    end
  end

  @doc """
    does the complete validation of the data received
  """
  def is_data_valid?(loans, proposal) do

    proponents_list = Enum.filter(loans.proponents, fn proponent ->
                        proponent.proposal_id == proposal.proposal_id
                      end)

    warranties_list = Enum.filter(loans.warranties, fn warranty ->
                        warranty.proposal_id == proposal.proposal_id
                      end)

      proponent_validation = ProponentService.is_proponents_valid?(proponents_list, proposal)
      warranty_validation = WarrantyService.is_warranties_valid?(warranties_list, proposal)
      proposal_validation = ProposalService.is_proposal_valid?(proposal)

      Enum.all?(proponent_validation, fn response -> response == true end) and
      Enum.all?(warranty_validation, fn response -> response == true end) and
      Enum.all?(proposal_validation, fn response -> response == true end)
  end

  @doc """
    adds the action to the loans list based on the event_schema received
  """
  defp add_action(loans, event) do
    case event.event_schema do
      "proposal"  -> %{ loans | proposals:  DataRepository.add(loans.proposals, event.event_properties)}
      "proponent" -> %{ loans | proponents: DataRepository.add(loans.proponents, event.event_properties)}
      "warranty"  -> %{ loans | warranties: DataRepository.add(loans.warranties, event.event_properties)}
    end
  end

  @doc """
    updates the action inside the loans list based on the event_schema received
  """
  defp update_action(loans, event) do
    case event.event_schema do
      "proposal"  -> %{ loans | proposals:  DataRepository.update(loans.proposals, event.event_properties)}
      "proponent" -> %{ loans | proponents: DataRepository.update(loans.proponents, event.event_properties)}
      "warranty"  -> %{ loans | warranties: DataRepository.update(loans.warranties, event.event_properties)}
    end
  end

  @doc """
    deletes the action from the loans list based on the event_schema received
  """
  defp delete_action(loans, event) do
    case event.event_schema do
      "proposal"  -> %{ loans | proposals:  DataRepository.delete(loans.proposals, event.event_properties)}
      "proponent" -> %{ loans | proponents: DataRepository.delete(loans.proponents, event.event_properties)}
      "warranty"  -> %{ loans | warranties: DataRepository.delete(loans.warranties, event.event_properties)}
    end
  end


end
