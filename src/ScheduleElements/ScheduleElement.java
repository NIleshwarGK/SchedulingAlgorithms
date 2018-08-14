package ScheduleElements;

public class ScheduleElement {
	
	private final Job jobElement;
	private final int startingTime;
	private final int completionTime;
	private boolean preemptionStatus;
	
	public ScheduleElement(Job jobElement, int startingTime, int completionTime) {
		this.jobElement = jobElement;
		this.startingTime = startingTime;
		this.completionTime = completionTime;
		this.preemptionStatus = false;
	}

	public Job getJobElement() {
		return jobElement;
	}

	public int getStartingTime() {
		return startingTime;
	}

	public int getCompletionTime() {
		return completionTime;
	}
	
	public void setPreemptionStatus(boolean preemptionStatus) {
		this.preemptionStatus = preemptionStatus;
	}
	
	public boolean getPreemptionStatus() {
		return preemptionStatus;
	}
}
