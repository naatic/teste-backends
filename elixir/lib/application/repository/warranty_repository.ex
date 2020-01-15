defmodule LoanHandler.Application.Repository.WarrantyRepository do

  def add_warranty(warranties, event_properties) do
    warranties ++ [event_properties]
  end

  def delete_warranty(warranties, event_properties) do
    List.delete(warranties, event_properties)
  end

  def update_warranty(warranties, event_properties) do
    delete_warranty(warranties, event_properties)
    add_warranty(warranties, event_properties)
  end

end
