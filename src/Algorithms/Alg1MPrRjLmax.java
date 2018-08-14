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
		
		Collections.sort(clonedJobList, Job.processingTimeAscending);
		Collections.sort(clonedJobList, Job.releaseDateAscending);
		Collections.sort(clonedJobList, Job.dueDateAscending);
		
		while(!clonedJobList.isEmpty()) {
			//Sort jobs according to their descending order of duedates
			//Collections.sort(clonedJobList, Job.dueDateAscending);
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
			
			if(machineStartTime < jobStartTime) {
				for(int j=2; j<clonedJobList.size(); j++) {
					int startTime = Integer.max(machineStartTime, clonedJobList.get(j).getReleaseDate());
					int completionTime = startTime + clonedJobList.get(j).getProcessingTime(); 
					if(completionTime > jobStartTime) {
						ScheduleElement fillerJob = new ScheduleElement(clonedJobList.get(j), startTime, jobStartTime);
						fillerJob.setPreemptionStatus(true);
						clonedJobList.get(j).changeParameters(jobStartTime - startTime, jobStartTime + 1, clonedJobList.get(j).getDueDate());
						machine.addScheduleElement(fillerJob);
						machine.addScheduleElement(priorityJob);
						break;
					}else {
						ScheduleElement fillerJob = new ScheduleElement(clonedJobList.get(j), startTime, completionTime);
						machine.addScheduleElement(fillerJob);
						clonedJobList.remove(j);
						machineStartTime = machine.getMachineSchedule().getLast().getCompletionTime();
						if(machineStartTime == jobStartTime) {
							break;
						}
					}
				}		
			}else {
				machine.addScheduleElement(priorityJob);
			}
			clonedJobList.removeFirst();
		}
	}
}
