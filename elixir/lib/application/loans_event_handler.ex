defmodule LoanHandler.Application.LoansEventHandler do

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

  end

  defp update_action(loans, event) do

  end

  defp delete_action(loans, event) do

  end

end
