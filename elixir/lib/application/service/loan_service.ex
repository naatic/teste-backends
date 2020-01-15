defmodule LoanHandler.Application.Service.LoanService do
  alias LoanHandler.Application.Repository.ProponentRepository
  alias LoanHandler.Application.Repository.WarrantyRepository
  alias LoanHandler.Application.Repository.ProposalRepository


  def handle_event(loans, event) do
    case event.event_action do
      "added" -> add_action(loans, event)
      "created" -> add_action(loans, event)
      "updated" -> update_action(loans, event)
      "deleted" -> delete_action(loans, event)
      "removed" -> delete_action(loans, event)
    end
  end

  defp add_action(loans, event) do
    case event.event_schema do
      "proposal"  -> %{ loans | proposals:  ProposalRepository.add_proposal(loans.proposals, event.event_properties)}
      "proponent" -> %{ loans | proponents: ProponentRepository.add_proponent(loans.proponents, event.event_properties)}
      "warranty"  -> %{ loans | warranties: WarrantyRepository.add_warranty(loans.warranties, event.event_properties)}
    end
  end

  defp update_action(loans, event) do
    case event.event_schema do
      "proposal"  -> %{ loans | proposals:  ProposalRepository.update_proposal(loans.proposals, event.event_properties)}
      "proponent" -> %{ loans | proponents: ProponentRepository.update_proponent(loans.proponents, event.event_properties)}
      "warranty"  -> %{ loans | warranties: WarrantyRepository.update_warranty(loans.warranties, event.event_properties)}
    end
  end

  defp delete_action(loans, event) do
    case event.event_schema do
      "proposal"  -> %{ loans | proposals:  ProposalRepository.delete_proposal(loans.proposals, event.event_properties)}
      "proponent" -> %{ loans | proponents: ProponentRepository.delete_proponent(loans.proponents, event.event_properties)}
      "warranty"  -> %{ loans | warranties: WarrantyRepository.delete_warranty(loans.warranties, event.event_properties)}
    end
  end

end
