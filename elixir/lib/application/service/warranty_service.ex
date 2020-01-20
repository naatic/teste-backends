defmodule LoanHandler.Application.Service.WarrantyService do
  @moduledoc false
  @doc """
      Method that calls the validations for the warranty
  """
  def is_warranties_valid?(warranties_list, proposal) do
    validations = [
      is_warranties_provinces_valid?(warranties_list),
      is_warranty_quantity_sufficient?(warranties_list),
      is_warranty_price_sum_sufficient?(warranties_list, proposal)
    ]

    Enum.all?(validations, fn response -> response == true end)
  end

  @doc """
      The warranty given cannot be placed in PR, SC neither in RS
  """
  defp is_warranties_provinces_valid?(warranties_list) do
    Enum.filter(
      warranties_list,
      fn w ->
        w.warranty_province == "PR" or
          w.warranty_province == "SC" or
          w.warranty_province == "RS"
      end
    )
    |> length() == 0
  end

  @doc """
      There must be at least one warranty per loan proposal
  """
  defp is_warranty_quantity_sufficient?(warranties_list) do
    length(warranties_list) >= 1
  end

  @doc """
      The price of the warranty must be at least twice of the value of the loan proposed
  """
  defp is_warranty_price_sum_sufficient?(warranties_list, proposal) do
    warranty_price_amount =
      Enum.map(warranties_list, fn w -> w.warranty_value end)
      |> Enum.sum()

    warranty_price_amount >= proposal.proposal_loan_value * 2
  end
end
