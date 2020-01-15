defmodule LoanHandler.Application.Utils.EventParser do
  alias LoanHandler.Application.Domain.Warranty
  alias LoanHandler.Application.Domain.Proponent
  alias LoanHandler.Application.Domain.Proposal

  @doc """
    parses the received event, not yet divided by event type
  """
  def parse_event(event) do
    event_as_list = String.split(event, ",")

    event = %{
      event_id:         Enum.at(event_as_list, 0),
      event_schema:     Enum.at(event_as_list, 1),
      event_action:     Enum.at(event_as_list, 2),
      event_timestamp:  Enum.at(event_as_list, 3),
      event_properties: nil
    }

    #Enum.drop vai dividir minha lista, formando uma nova a partir da posição 4 da atual
    event_properties_list = Enum.drop(event_as_list, 4)

    case event.event_schema do
      "proposal"  -> %{event | event_properties: parse_proposal(event_properties_list)}
      "proponent" -> %{event | event_properties: parse_proponent(event_properties_list)}
      "warranty"  -> %{event | event_properties: parse_warranty(event_properties_list)}
      _           -> {:error, "Not a valid event"}
    end
  end

  @doc """
    parses the received proposal to be inserted in the event properties
  """
  def parse_proposal(proposal_values) do
    %Proposal{
      proposal_id: Enum.at(proposal_values, 0),
      proposal_loan_value: String.to_float(Enum.at(proposal_values, 1)),
      proposal_number_of_monthly_installments: String.to_integer(Enum.at(proposal_values, 2))
    }
  end

  @doc """
    parses the received warranty to be inserted in the event properties
  """
  def parse_warranty(warranty_values) do
    %Warranty{
      proposal_id: Enum.at(warranty_values, 0),
      warranty_id: Enum.at(warranty_values, 1),
      warranty_value: String.to_float(Enum.at(warranty_values, 2)),
      warranty_province: Enum.at(warranty_values, 3)
    }
  end

  @doc """
    parses the received proponent to be inserted in the event properties
  """
  def parse_proponent(proponent_values) do
    %Proponent{
      proposal_id: Enum.at(proponent_values, 0),
      proponent_id: Enum.at(proponent_values, 1),
      proponent_name: Enum.at(proponent_values, 2),
      proponent_age: String.to_integer(Enum.at(proponent_values, 3)),
      proponent_monthly_income: String.to_float(Enum.at(proponent_values, 4)),
      proponent_is_main: String.to_atom(Enum.at(proponent_values, 5)) == true
    }
  end

end
