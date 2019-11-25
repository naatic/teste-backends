package dto;

public class ProponentDTO {
	
	private String eventID;
	private String eventSchema;
	private String eventAction;
	private String eventTimestamp;
	private String proposalId;
	private String proponentId;
	private String proponentName;
	private int proponentAge;
	private double proponentMonthlyIncome;
	private boolean isMainProponent;
	
	
	public ProponentDTO(String[] proponentValues) {
		this.setEventID(proponentValues[0]);
		this.setEventSchema(proponentValues[1]);
		this.setEventAction(proponentValues[2]);
		this.setEventTimestamp(proponentValues[3]);
		this.setProposalId(proponentValues[4]);
		this.setProponentId(proponentValues[5]);
		this.setProponentName(proponentValues[6]);
		this.setProponentAge(Integer.parseInt(proponentValues[7]));
		this.setProponentMonthlyIncome(Double.parseDouble(proponentValues[8]));
		this.setMainProponent(Boolean.parseBoolean(proponentValues[9]));
	}
	public ProponentDTO() {}
	
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
	public String getProponentId() {
		return proponentId;
	}
	public void setProponentId(String proponentId) {
		this.proponentId = proponentId;
	}
	public String getProponentName() {
		return proponentName;
	}
	public void setProponentName(String proponentName) {
		this.proponentName = proponentName;
	}
	public int getProponentAge() {
		return proponentAge;
	}
	public void setProponentAge(int proponentAge) {
		this.proponentAge = proponentAge;
	}
	public double getProponentMonthlyIncome() {
		return proponentMonthlyIncome;
	}
	public void setProponentMonthlyIncome(double proponentMonthlyIncome) {
		this.proponentMonthlyIncome = proponentMonthlyIncome;
	}
	public boolean isMainProponent() {
		return isMainProponent;
	}
	public void setMainProponent(boolean isMainProponent) {
		this.isMainProponent = isMainProponent;
	}
}
