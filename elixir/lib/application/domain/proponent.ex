defmodule LoanHandler.Application.Domain.Proponent do

  defstruct [
    :proposal_id,
    :proponent_id,
    :proponent_name,
    :proponent_age,
    :proponent_monthly_income,
    :proponent_is_main
  ]

end
