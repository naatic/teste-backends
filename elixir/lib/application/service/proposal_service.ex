defmodule LoanHandler.Application.Service.ProposalService do
  alias LoanHandler.Application.Service.ProponentService
  alias LoanHandler.Application.Service.WarrantyService


  def is_proposal_valid?(proposal) do

    is_proposal_loan_value_valid?(proposal) and
    is_proposal_number_of_monthly_installments_valid?(proposal)
  
  end

  defp is_proposal_loan_value_valid?(proposal) do
    proposal
  end

  defp is_proposal_number_of_monthly_installments_valid?(proposal) do
    proposal
  end
end
