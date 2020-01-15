defmodule LoanHandler.Application.Service.ProponentService do

    def is_proponents_valid?(proponents_list, proposal) do

        is_proposal_proponents_valid?(proponents_list, proposal) and
        is_proponents_quantity_sufficient?(proponents_list) and
        is_only_one_main_proponent?(proponents_list)
    
    end

    defp is_proponents_quantity_sufficient?(proponents_list) do
        proponents_list
    end

    defp is_only_one_main_proponent?(proponents_list) do
        proponents_list
    end

    defp is_proposal_proponents_valid?(proponents_list, proposal) do
        proponents_list
    end
end