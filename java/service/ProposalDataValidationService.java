package service;

import java.util.ArrayList;
import java.util.List;

import dto.ProponentDTO;
import dto.ProposalDTO;
import dto.WarrantyDTO;

public class ProposalDataValidationService {
	
	private static final String PARANA = "PR";
	private static final String SANTA_CATARINA = "SC";
	private static final String RIO_GRANDE_DO_SUL = "RS";

	/**
	 * Executa as valida��es necess�rias para as mensagens que sejam the Proposal
	 * @param ProposalDTO
	 * */
	public boolean processProposalMessage(ProposalDTO proposalDTO) {
		
		if(proposalDTO.getProposalLoanValue() < 30000 || proposalDTO.getProposalLoanValue() > 3000000) { 
			return false; //� O valor do empr�stimo deve estar entre R$ 30.000,00 e R$ 3.000.000,00
		}
		if(proposalDTO.getNumberOfMonthlyInstallments() < 24 || proposalDTO.getNumberOfMonthlyInstallments() > 180) {
			return false; //� O empr�stimo deve ser pago em no m�nimo 2 anos e no m�ximo 15 anos
		}
		
		boolean isValid = true;

		isValid = processWarrantyMessages(proposalDTO);
		
		if(!isValid) {
			return false;
		}
		
		isValid = processProponentMessages(proposalDTO);
		
		return isValid;
	}
	
	/**
	 * Executa as valida��es necess�rias para as mensagens que sejam the Proponent
	 * @param String
	 * */
	private boolean processProponentMessages(ProposalDTO proposalDTO) {
		if(!(proposalDTO.getProponentList().size() == 2)) {
			return false; //�Deve haver no m�nimo 2 proponentes por proposta
		}else {
			List<ProponentDTO> mainProponent = new ArrayList<ProponentDTO>();
			for(ProponentDTO dto : proposalDTO.getProponentList()) {
				if (dto.getProponentAge() < 18) {
					return false; //�Todos os proponentes devem ser maiores de 18 anos
				}
				if(dto.isMainProponent()) {
					mainProponent.add(dto);
				}
			}
			
			if(mainProponent.isEmpty() ||mainProponent.size() > 1) {
				return false; //�Deve haver exatamente 1 proponente principal por proposta
			}else {
				ProponentDTO dto = mainProponent.get(0);
				double parcelaMensal = proposalDTO.getProposalLoanValue() / proposalDTO.getNumberOfMonthlyInstallments();
				
				if(dto.getProponentAge() >= 18 && dto.getProponentAge() <= 24 && !(dto.getProponentMonthlyIncome() >= parcelaMensal*4)) {
					return false; // renda do proponente deve ser 4 vezes o valor da parcela do empr�stimo, se a idade dele for entre 18 e 24 anos
				}
				if(dto.getProponentAge() >= 24 && dto.getProponentAge() <= 50 && !(dto.getProponentMonthlyIncome() >= parcelaMensal*3)) {
					return false; // renda do proponente deve ser 4 vezes o valor da parcela do empr�stimo, se a idade dele for entre 24 e 50 anos
				}
				
				if(dto.getProponentAge() >= 50 && !(dto.getProponentMonthlyIncome() >= parcelaMensal*2)) {
					return false; // renda do proponente deve ser 4 vezes o valor da parcela do empr�stimo, se a idade dele for acima de 50 anos
				}
			}
		}
		return true;
	}
	
	/**
	 * Executa as valida��es necess�rias para as mensagens que sejam the Warranty
	 * @param String
	 * */
	private boolean processWarrantyMessages(ProposalDTO proposalDTO) {
		
		if(proposalDTO.getWarrantyList().isEmpty()) {
			return false; //�Dever haver no m�nimo 1 garantia de im�vel por proposta
		}else {
			double valorGarantias = 0;
			for(WarrantyDTO dto : proposalDTO.getWarrantyList()) {
				if(!dto.getEventAction().equalsIgnoreCase("removed")) {
					valorGarantias += dto.getWarrantyValue();
				
					if(dto.getWarrantyProvince().equalsIgnoreCase(PARANA) || dto.getWarrantyProvince().equalsIgnoreCase(SANTA_CATARINA) 
							|| dto.getWarrantyProvince().equalsIgnoreCase(RIO_GRANDE_DO_SUL)) {
						return false; //�As garantias de im�vel dos estados PR, SC e RS n�o s�o aceitas
					}
				}
			}
			
			if(!(valorGarantias >= (proposalDTO.getProposalLoanValue()*2))) {
				return false; //�O valor das garantias deve ser maior ou igual ao dobro do valor do empr�stimo
			}
		}
		return true;
	}
}
