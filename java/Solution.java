import java.util.ArrayList;
import java.util.List;

import dto.ProposalDTO;
import service.DTOPopulationService;
import service.ProposalDataValidationService;

public class Solution {

	private static DTOPopulationService dtoPopulationService = new DTOPopulationService();
	private static ProposalDataValidationService proposalDataValidationService = new ProposalDataValidationService();
  
	/**
	 * Método que inicia o processo the validação das mensagens
	 * @param List<String>
	 * */
	public static String processMessages(List<String> messages) {
		
		List<String> listValidIDs = new ArrayList<String>();
		
		for (String message : messages) {
			if(message.contains("proposal")) {
				ProposalDTO proposalDTO = dtoPopulationService.populateProposalDTO(message, messages);
				boolean isValid = proposalDataValidationService.processProposalMessage(proposalDTO);
				
				if(isValid) {
					listValidIDs.add(proposalDTO.getProposalId());
				}
			}
		}
	  
		String response = listValidIDs.toString().replace("[", "").replace("]", "").replaceAll(" ", "");

		return response; //Lista de IDs das PROPOSTAS válidas
	}
}