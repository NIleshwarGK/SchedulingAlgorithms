package ScheduleElements;

import java.util.Comparator;

public class Job {
	
	//Declare class variables
	private final String jobID;
	private int processingTime;
	private int releaseDate;
	private int dueDate;
	private final float jobWeight;
	
	//Set the job properties
	public Job(String jobID, int processingTime, int releaseDate, int dueDate, float jobWeight) {
		this.jobID = jobID;
		this.processingTime = processingTime;
		this.releaseDate = releaseDate;
		this.dueDate = dueDate;
		this.jobWeight = jobWeight;
	}
	
	public void changeParameters(int processingTime, int releaseDate, int dueDate) {
		this.processingTime = processingTime;
		this.releaseDate = releaseDate;
		this.dueDate = dueDate;
	}

	//Retrieve individual job properties
	public String getJobID() {
		return jobID;
	}
	public int getProcessingTime() {
		return processingTime;
	}
	public int getReleaseDate() {
		return releaseDate;
	}
	public int getDueDate() {
		return dueDate;
	}
	public float getJobWeight() {
		return jobWeight;
	}
	
	//Basic sorting options based on processing time, release date, due date and smith ratio
	public static Comparator<Job> processingTimeAscending = new Comparator<Job>(){
		@Override
        public int compare(Job J1, Job J2) {
			return Integer.compare(J1.getProcessingTime(), J2.getProcessingTime());
        }
    };
    public static Comparator<Job> processingTimeDescending = new Comparator<Job>(){
		@Override
        public int compare(Job J1, Job J2) {
			return Integer.compare(J2.getProcessingTime(), J1.getProcessingTime());
        }
    };
    
    public static Comparator<Job> releaseDateAscending = new Comparator<Job>(){
		@Override
        public int compare(Job J1, Job J2) {
			return Integer.compare(J1.getReleaseDate(), J2.getReleaseDate());
        }
    };
    public static Comparator<Job> releaseDateDescending = new Comparator<Job>(){
		@Override
        public int compare(Job J1, Job J2) {
			return Integer.compare(J2.getReleaseDate(), J1.getReleaseDate());
        }
    };
	
	public static Comparator<Job> dueDateAscending = new Comparator<Job>(){
		@Override
        public int compare(Job J1, Job J2) {
			return Integer.compare(J1.getDueDate(), J2.getDueDate());
        }
    };
    public static Comparator<Job> dueDateDescending = new Comparator<Job>(){
		@Override
        public int compare(Job J1, Job J2) {
			return Integer.compare(J2.getDueDate(), J1.getDueDate());
        }
    };
    
    public static Comparator<Job> smithRatioAscending = new Comparator<Job>(){
		@Override
        public int compare(Job J1, Job J2) {
			return Float.compare(J1.getJobWeight()/J1.getProcessingTime(), J2.getJobWeight()/J2.getProcessingTime());
        }
    };
    public static Comparator<Job> smithRatioDescending = new Comparator<Job>(){
		@Override
        public int compare(Job J1, Job J2) {
			return Float.compare(J2.getJobWeight()/J2.getProcessingTime(), J1.getJobWeight()/J1.getProcessingTime());
        }
    };
    
    public Job clone(){
		Job clonedJob =  new Job(jobID, processingTime, releaseDate, dueDate, jobWeight);
		return clonedJob;
	}

}
