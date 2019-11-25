import java.util.ArrayList;
import java.util.List;

import dto.ProponentDTO;
import dto.ProposalDTO;
import dto.WarrantyDTO;
import service.DTOPopulationService;

public class Solution {

	private static DTOPopulationService dtoPopulationService = new DTOPopulationService();
	private static final String PARANA = "PR";
	private static final String SANTA_CATARINA = "SC";
	private static final String RIO_GRANDE_DO_SUL = "RS";
  
	/**
	 * Método que inicia o processo the validação das mensagens
	 * @param List<String>
	 * */
	public static String processMessages(List<String> messages) {
		
		List<String> listValidIDs = new ArrayList<String>();
		
		for (String message : messages) {
			if(message.contains("proposal")) {
				ProposalDTO proposalDTO = dtoPopulationService.populateProposalDTO(message, messages);
				boolean isValid = processProposalMessage(proposalDTO);
				
				if(isValid) {
					listValidIDs.add(proposalDTO.getProposalId());
				}
			}
			
		}
	  
		String response = listValidIDs.toString().replace("[", "").replace("]", "").replaceAll(" ", "");

		return response; //Lista de IDs das PROPOSTAS válidas
	}

	
	/**
	 * Executa as validações necessárias para as mensagens que sejam the Proposal
	 * @param ProposalDTO
	 * */
	private static boolean processProposalMessage(ProposalDTO proposalDTO) {
		
		if(proposalDTO.getProposalLoanValue() < 30000 || proposalDTO.getProposalLoanValue() > 3000000) { 
			return false; //• O valor do empréstimo deve estar entre R$ 30.000,00 e R$ 3.000.000,00
		}
		if(proposalDTO.getNumberOfMonthlyInstallments() < 24 || proposalDTO.getNumberOfMonthlyInstallments() > 180) {
			return false; //• O empréstimo deve ser pago em no mínimo 2 anos e no máximo 15 anos
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
	 * Executa as validações necessárias para as mensagens que sejam the Proponent
	 * @param String
	 * */
	private static boolean processProponentMessages(ProposalDTO proposalDTO) {
		if(!(proposalDTO.getProponentList().size() == 2)) {
			return false; //•Deve haver no mínimo 2 proponentes por proposta
		}else {
			List<ProponentDTO> mainProponent = new ArrayList<ProponentDTO>();
			for(ProponentDTO dto : proposalDTO.getProponentList()) {
				if (dto.getProponentAge() < 18) {
					return false; //•Todos os proponentes devem ser maiores de 18 anos
				}
				if(dto.isMainProponent()) {
					mainProponent.add(dto);
				}
			}
			
			if(mainProponent.isEmpty() ||mainProponent.size() > 1) {
				return false; //•Deve haver exatamente 1 proponente principal por proposta
			}else {
				ProponentDTO dto = mainProponent.get(0);
				double parcelaMensal = proposalDTO.getProposalLoanValue() / proposalDTO.getNumberOfMonthlyInstallments();
				
				if(dto.getProponentAge() >= 18 && dto.getProponentAge() <= 24 && !(dto.getProponentMonthlyIncome() >= parcelaMensal*4)) {
					return false; // renda do proponente deve ser 4 vezes o valor da parcela do empréstimo, se a idade dele for entre 18 e 24 anos
				}
				if(dto.getProponentAge() >= 24 && dto.getProponentAge() <= 50 && !(dto.getProponentMonthlyIncome() >= parcelaMensal*3)) {
					return false; // renda do proponente deve ser 4 vezes o valor da parcela do empréstimo, se a idade dele for entre 24 e 50 anos
				}
				
				if(dto.getProponentAge() >= 50 && !(dto.getProponentMonthlyIncome() >= parcelaMensal*2)) {
					return false; // renda do proponente deve ser 4 vezes o valor da parcela do empréstimo, se a idade dele for acima de 50 anos
				}
			}
		}
		return true;
	}
	
	/**
	 * Executa as validações necessárias para as mensagens que sejam the Warranty
	 * @param String
	 * */
	private static boolean processWarrantyMessages(ProposalDTO proposalDTO) {
		
		if(proposalDTO.getWarrantyList().isEmpty()) {
			return false; //•Dever haver no mínimo 1 garantia de imóvel por proposta
		}else {
			double valorGarantias = 0;
			for(WarrantyDTO dto : proposalDTO.getWarrantyList()) {
				if(!dto.getEventAction().equalsIgnoreCase("removed")) {
					valorGarantias += dto.getWarrantyValue();
				
					if(dto.getWarrantyProvince().equalsIgnoreCase(PARANA) || dto.getWarrantyProvince().equalsIgnoreCase(SANTA_CATARINA) 
							|| dto.getWarrantyProvince().equalsIgnoreCase(RIO_GRANDE_DO_SUL)) {
						return false; //•As garantias de imóvel dos estados PR, SC e RS não são aceitas
					}
				}
			}
			
			if(!(valorGarantias >= (proposalDTO.getProposalLoanValue()*2))) {
				return false; //•O valor das garantias deve ser maior ou igual ao dobro do valor do empréstimo
			}
		}
		return true;
	}
}