package dto;

public class WarrantyDTO {
	
	private String eventID;
	private String eventSchema;
	private String eventAction;
	private String eventTimestamp;
	private String proposalId;
	private String warrantyId;
	private double warrantyValue;
	private String warrantyProvince;
	
	
	public WarrantyDTO(String[] warrantyValues) {
		this.setEventID(warrantyValues[0]);
		this.setEventSchema(warrantyValues[1]);
		this.setEventAction(warrantyValues[2]);
		this.setEventTimestamp(warrantyValues[3]);
		this.setProposalId(warrantyValues[4]);
		this.setWarrantyId(warrantyValues[5]);
		
		if(!warrantyValues[2].toString().contains("removed")) { //campos adicionados apenas quando a warranty não é removida
			this.setWarrantyValue(Double.parseDouble(warrantyValues[6]));
			this.setWarrantyProvince(warrantyValues[7]);
		}
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
	public String getWarrantyId() {
		return warrantyId;
	}
	public void setWarrantyId(String warrantyId) {
		this.warrantyId = warrantyId;
	}
	public double getWarrantyValue() {
		return warrantyValue;
	}
	public void setWarrantyValue(double warrantyValue) {
		this.warrantyValue = warrantyValue;
	}
	public String getWarrantyProvince() {
		return warrantyProvince;
	}
	public void setWarrantyProvince(String warrantyProvince) {
		this.warrantyProvince = warrantyProvince;
	}
}
