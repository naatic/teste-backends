defmodule LoanHandler.Application.Repository.ProponentRepository do

  def add_proponent(proponents, event_properties) do
    proponents ++ [event_properties]
  end

  def delete_proponent(proponents, event_properties) do
    List.delete(proponents, event_properties)
  end

  def update_proponent(proponents, event_properties) do
    delete_proponent(proponents, event_properties)
    add_proponent(proponents, event_properties)
  end

end
