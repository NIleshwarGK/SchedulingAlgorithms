package ScheduleElements;

import java.util.LinkedList;

public class Machine {
	private final String machineID;
	private final LinkedList<ScheduleElement> machineSchedule;
	
	public Machine(String machineID) {
		this.machineID = machineID;
		this.machineSchedule = new LinkedList<ScheduleElement>();
	}
	
	public String getMachineID() {
		return machineID;
	}

	public LinkedList<ScheduleElement> getMachineSchedule() {
		return machineSchedule;
	}

	public void addScheduleElement(ScheduleElement scheduleElement) {
		machineSchedule.add(scheduleElement);
	}

}
