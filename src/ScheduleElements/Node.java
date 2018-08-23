package ScheduleElements;

import java.util.ArrayList;


public class Node {
	
	private Job nodeJob;
	private int nodeLevel;
	private int previousNode;
	private int nextNode;
	private int lowerBound;
	private int upperBound;
	private String noteStatus;
	
	public Node(Job nodeJob, int nodeLevel, int previousNode) {
		this.nodeJob = nodeJob;
		this.nodeLevel = nodeLevel;
		this.previousNode = previousNode;
	}

	public Job getNodeJob() {
		return nodeJob;
	}

	public void setNodeJob(Job nodeJob) {
		this.nodeJob = nodeJob;
	}

	public int getNodeLevel() {
		return nodeLevel;
	}

	public void setNodeLevel(int nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	public int getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(int previousNode) {
		this.previousNode = previousNode;
	}

	public int getNextNode() {
		return nextNode;
	}

	public void setNextNode(int nextNode) {
		this.nextNode = nextNode;
	}

	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public String getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	

}
