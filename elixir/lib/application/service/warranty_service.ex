defmodule LoanHandler.Application.Service.WarrantyService do

    def is_warranties_valid?(warranties_list, proposal) do

        is_all_warranties_valid?(warranties_list) and
        is_warranty_quantity_sufficient?(warranties_list) and
        is_warranty_values_sum_sufficient?(warranties_list, proposal)
    
    end

    defp is_all_warranties_valid?(warranties_list) do
        warranties_list
    end

    defp is_warranty_quantity_sufficient?(warranties_list) do
        warranties_list
    end

    defp is_warranty_values_sum_sufficient?(warranties_list, proposal) do
        warranties_list
    end
end