defmodule LoanHandler.Application.Service.ProponentService do

    @doc """
        Method that calls the validations for the proponents
    """
    def is_proponents_valid?(proponents_list, proposal) do

        is_only_one_main_proponent?(proponents_list) and
        is_number_of_proponents_enough?(proponents_list) and
        is_proponent_age_enough?(proponents_list) and
        is_proposal_proponents_valid?(proponents_list, proposal)

    end

    @doc """
        There must be at least 2 proponents per loan proposal
    """
    defp is_number_of_proponents_enough?(proponents_list) do
        length(proponents_list) >= 2
    end

    @doc """
        There must be only 1 main proponent per loan proposal
    """
    defp is_only_one_main_proponent?(proponents_list) do

        main_prop = []

        for prop <- proponents_list  do
            unless prop.proponent_is_main == false do
                main_prop ++ [prop]
            end
        end

        length(main_prop) > 1
    end

    @doc """
        The necessary monthly income changes depending on the proponent age.
        Ages:
        18 - 24  = 4x the loan monthly payment
        25 - 50  = 3x the loan monthly payment
        50 - any = 2x the loan monthly payment
    """
    defp is_proposal_proponents_valid?(proponents_list, proposal) do
        main_proponent = Enum.map(proponents_list, fn prop -> prop.proponent_is_main == true end )
        proposal_monthly_payment = proposal.proposal_loan_value / proposal.proposal_number_of_monthly_installments

        case main_proponent do
            proponent_age when proponent_age >= 18 and proponent_age <= 24 ->
                (proposal_monthly_payment * 4) <= main_proponent.proponent_monthly_income

            proponent_age when proponent_age > 24 and proponent_age <= 50 ->
                (proposal_monthly_payment * 3) <= main_proponent.proponent_monthly_income

            proponent_age when proponent_age > 50 ->
                (proposal_monthly_payment * 2) <= main_proponent.proponent_monthly_income

        end
    end

    @doc """
        The proponents must be at least 18y old to make a loan proposal
    """
    defp is_proponent_age_enough?(proponents_list) do
        Enum.map(proponents_list, fn prop -> prop.proponent_age > 18 end )
    end
end
