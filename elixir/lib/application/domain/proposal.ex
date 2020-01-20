defmodule LoanHandler.Application.Domain.Proposal do
  @moduledoc false
  defstruct [
    :proposal_id,
    :proposal_loan_value,
    :proposal_number_of_monthly_installments
  ]
end
