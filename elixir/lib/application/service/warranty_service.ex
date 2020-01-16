defmodule LoanHandler.Application.Service.WarrantyService do

    def is_warranties_valid?(warranties_list, proposal) do

        is_warranties_provinces_valid?(warranties_list) and
        is_warranty_quantity_sufficient?(warranties_list) and
        is_warranty_price_sum_sufficient?(warranties_list, proposal)

    end

    defp is_warranties_provinces_valid?(warranties_list) do
       Enum.any?(warranties_list,
            fn w ->
                w.warranty_province != "PR" and
                w.warranty_province != "SC" and
                w.warranty_province != "RS"
            end
        )
    end

    defp is_warranty_quantity_sufficient?(warranties_list) do
        length(warranties_list) >= 1
    end

    defp is_warranty_price_sum_sufficient?(warranties_list, proposal) do
        warranty_price_amount = 0

        for warranty <- warranties_list do
            warranty_price_amount ++ warranty.warranty_value
        end

        warranty_price_amount >= proposal.proposal_loan_value * 2
    end
end
