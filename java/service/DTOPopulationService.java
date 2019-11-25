package service;
import java.util.List;

import dto.ProponentDTO;
import dto.ProposalDTO;
import dto.WarrantyDTO;

public class DTOPopulationService {

	/**
	 * Popula o DTO de proposal
	 * @param String
	 * */
	public ProposalDTO populateProposalDTO(String proposal, List<String> messages) {
		
		String[] proposalValues = returnSplitedEvent(proposal);
		ProposalDTO proposalDTO = new ProposalDTO(proposalValues);
				
		for(String message: messages) {
			if(message.contains(proposalDTO.getProposalId())) {
				if(message.contains("warranty")) {
					WarrantyDTO warrantyDTO = populateWarrantyDTO(message);
					proposalDTO.getWarrantyList().add(warrantyDTO);
				}else if(message.contains("proponent")){
					ProponentDTO proponentDTO = populateProponentDTO(message);
					proposalDTO.getProponentList().add(proponentDTO);
				}
			}
		}
		
		return proposalDTO;
	}
	
	/**
	 * Popula o DTO de warranty para ser vinculado a proposta
	 * @param String
	 * */
	private WarrantyDTO populateWarrantyDTO(String warranty) {
		String[] warrantyValues = returnSplitedEvent(warranty);
		WarrantyDTO warrantyDTO = new WarrantyDTO(warrantyValues);
		
		return warrantyDTO;
	}

	/**
	 * Popula o DTO de proponent para ser vinculado a proposta
	 * @param String
	 * */
	private ProponentDTO populateProponentDTO(String proponent) {
		String[] proponentValues = returnSplitedEvent(proponent);
		ProponentDTO proponentDTO = new ProponentDTO(proponentValues);
		
		return proponentDTO;
	}
	
	/**Splitta os eventos para serem convertidos a DTOs
	 * @param String
	 * */
	private String[] returnSplitedEvent(String event) {
		return event.split(",");
	}

}
