defmodule Solution do
  alias LoanHandler.Application.Service.MessageProcessorService
  alias LoanHandler.Application.Service.LoanService
  @moduledoc false

  @spec solution(list) :: String.t()
  def solution(messages) do
    loans = MessageProcessorService.process(messages)
    validate_data(loans)
  end

  defp validate_data(loans) do
    Enum.filter(loans.proposals,
    fn proposal ->
      LoanService.is_data_valid?(loans, proposal)
    end
    )
    |> Enum.map(fn prop -> prop.proposal_id end)
    |> Enum.join(",")
  end

  defp validate_data(_), do: ""

end
