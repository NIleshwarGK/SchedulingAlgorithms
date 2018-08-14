package Algorithms;

import java.util.Collections;
import java.util.LinkedList;

import ScheduleElements.Job;
import ScheduleElements.Machine;
import ScheduleElements.ScheduleElement;

public class Alg1MSumCj {
	
	public Alg1MSumCj() {
	}
	
	public void runAlgorithm(LinkedList<Job> jobList, Machine machine) {
		//First, sort the jobs based on their processing time
		Collections.sort(jobList, Job.processingTimeAscending);
		
		//Schedule the jobs on the machine
		//Go through the jobs in schedule order, update schedule element and attach to the machine
		for(int j=0; j<jobList.size(); j++) {
			int machineStartTime;
			if(machine.getMachineSchedule().isEmpty()) {
				machineStartTime = 0;
			}else {
				machineStartTime = machine.getMachineSchedule().getLast().getCompletionTime();
			}
			
			int startTime = Integer.max(machineStartTime, jobList.get(j).getReleaseDate());
			int endTime = startTime + jobList.get(j).getProcessingTime();
			
			ScheduleElement scheduleElement = new ScheduleElement(jobList.get(j), startTime, endTime);
			
			machine.addScheduleElement(scheduleElement);
		}
		
	}

}
