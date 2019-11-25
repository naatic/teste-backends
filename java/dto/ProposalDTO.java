package dto;
import java.util.HashSet;
import java.util.Set;

public class ProposalDTO {
	
	private String eventID;
	private String eventSchema;
	private String eventAction;
	private String eventTimestamp;
	private String proposalId;
	private double proposalLoanValue;
	private int numberOfMonthlyInstallments;
	private Set<WarrantyDTO> warrantyList = new HashSet<>(); //• Em caso de eventos repetidos, considere o primeiro evento
	private Set<ProponentDTO> proponentList = new HashSet<>(); //• Em caso de eventos repetidos, considere o primeiro evento
	
	
	public ProposalDTO(String[] proposalValues) {
		this.setEventID(proposalValues[0]);
		this.setEventSchema(proposalValues[1]);
		this.setEventAction(proposalValues[2]);
		this.setEventTimestamp(proposalValues[3]);
		this.setProposalId(proposalValues[4]);
		this.setProposalLoanValue(Double.parseDouble(proposalValues[5]));
		this.setNumberOfMonthlyInstallments(Integer.parseInt(proposalValues[6]));
	}
	
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getEventSchema() {
		return eventSchema;
	}
	public void setEventSchema(String eventSchema) {
		this.eventSchema = eventSchema;
	}
	public String getEventAction() {
		return eventAction;
	}
	public void setEventAction(String eventAction) {
		this.eventAction = eventAction;
	}
	public String getEventTimestamp() {
		return eventTimestamp;
	}
	public void setEventTimestamp(String eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}
	public String getProposalId() {
		return proposalId;
	}
	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}
	public double getProposalLoanValue() {
		return proposalLoanValue;
	}
	public void setProposalLoanValue(double proposalLoanValue) {
		this.proposalLoanValue = proposalLoanValue;
	}
	public int getNumberOfMonthlyInstallments() {
		return numberOfMonthlyInstallments;
	}
	public void setNumberOfMonthlyInstallments(int numberOfMonthlyInstallments) {
		this.numberOfMonthlyInstallments = numberOfMonthlyInstallments;
	}
	public Set<WarrantyDTO> getWarrantyList() {
		return warrantyList;
	}
	public void setWarrantyList(Set<WarrantyDTO> warrantyList) {
		this.warrantyList = warrantyList;
	}
	public Set<ProponentDTO> getProponentList() {
		return proponentList;
	}
	public void setProponentList(Set<ProponentDTO> proponentList) {
		this.proponentList = proponentList;
	}
	

}
