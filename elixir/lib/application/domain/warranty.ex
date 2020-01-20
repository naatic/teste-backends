defmodule LoanHandler.Application.Domain.Warranty do
  @moduledoc false
  defstruct [
    :proposal_id,
    :warranty_id,
    :warranty_value,
    :warranty_province
  ]
end
