package Algorithms;

import java.util.Collections;
import java.util.LinkedList;

import ScheduleElements.Job;
import ScheduleElements.Machine;
import ScheduleElements.ScheduleElement;

public class Alg1MPrRjLmax {

	public Alg1MPrRjLmax() {

	}

	public void runAlgorithm(LinkedList<Job> jobList, Machine machine) {
		LinkedList<Job> clonedJobList = (LinkedList<Job>) jobList.clone();
		
		while(!clonedJobList.isEmpty()) {
			//Sort jobs according to their descending order of duedates
			Collections.sort(clonedJobList, Job.dueDateAscending);
			//Integer for machine start time
			int machineStartTime;
			if(machine.getMachineSchedule().isEmpty()) {
				machineStartTime = 0;
			}else {
				machineStartTime = machine.getMachineSchedule().getLast().getCompletionTime();
			}
			
			int jobStartTime = Integer.max(machineStartTime, clonedJobList.getFirst().getReleaseDate()); 
			int jobCompletionTime = jobStartTime + clonedJobList.getFirst().getProcessingTime();
			 
			ScheduleElement priorityJob = new ScheduleElement(clonedJobList.getFirst(), jobStartTime, jobCompletionTime);

			clonedJobList.removeFirst();
			//Sort jobs based on release dates, with deadlines as second category
			Collections.sort(clonedJobList, Job.dueDateAscending);
			Collections.sort(clonedJobList, Job.releaseDateAscending);

			int j=0;
			label_break_while:
			while(machineStartTime < jobStartTime) {
				if(clonedJobList.isEmpty()) {
					break label_break_while;
				}else if(clonedJobList.get(j).getReleaseDate() >= jobStartTime) {
					break label_break_while;
				}else {
					boolean preemptionStatus = false;
					//What are the three possible completion times for the slot
					//1. The job completes without any preemption
					int fjobProcessingTime = clonedJobList.get(j).getProcessingTime();
					int fjobReleaseDate = clonedJobList.get(j).getReleaseDate();
					int fjobDueDate = clonedJobList.get(j).getDueDate();
					
					int fjobStartTime = Integer.max(machineStartTime, fjobReleaseDate);
					int fjobCompletionTimeA = fjobStartTime + fjobProcessingTime;
					
					//2. The job gets interrupted by the priority job
					int fjobCompletionTimeB = jobStartTime;
				
					//3. The job gets interrupted by a third job of higher priority
					int jID = -2;
					int nextDueDate = fjobDueDate;
					for(int k=j+1; k<clonedJobList.size(); k++) {
						if(clonedJobList.get(k).getDueDate() < nextDueDate) {
							nextDueDate = clonedJobList.get(k).getDueDate();
							jID = k;
						}
					}
					int fjobCompletionTimeC = Integer.MAX_VALUE;
					if(jID > 0) {
						fjobCompletionTimeC = clonedJobList.get(jID).getReleaseDate();
					}
					//The final completion time would be the minimum of all three possibilities 
					int fjobCompletionTime = Integer.min(fjobCompletionTimeA, Integer.min(fjobCompletionTimeB, fjobCompletionTimeC));
					//Updating the new time at which machine becomes available
					machineStartTime = fjobCompletionTime;
					//Creating filler schedule element and addint it onto the machine
					ScheduleElement fillerJob = new ScheduleElement(clonedJobList.get(j), fjobStartTime, fjobCompletionTime);
					machine.addScheduleElement(fillerJob);
					//Defining proceeding conditions based on the scenario which led to the following completion time
					if(fjobCompletionTime == fjobCompletionTimeA) { 
						//Means the job can be completely scheduled before the priority job
						clonedJobList.remove(j);
					}else if(fjobCompletionTime == fjobCompletionTimeB) {
						//The current job has to be terminated by the priority job
						int fjobProcessedTime = fjobCompletionTime - fjobStartTime;
						clonedJobList.get(j).changeParameters(fjobProcessingTime - fjobProcessedTime, jobStartTime + 1, fjobDueDate);
						preemptionStatus = true;
					}else if(fjobCompletionTime == fjobCompletionTimeC) {
						int fjobProcessedTime = fjobCompletionTime - fjobStartTime;
						clonedJobList.get(j).changeParameters(fjobProcessingTime - fjobProcessedTime, jobStartTime + 1, fjobDueDate);
						preemptionStatus = true;
						j=j+1;
					}
					machine.getMachineSchedule().getLast().setPreemptionStatus(preemptionStatus);
				}
			}
			machine.addScheduleElement(priorityJob);			
		}
	}
}
