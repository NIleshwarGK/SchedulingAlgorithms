package Algorithms;

import java.util.LinkedList;

import ScheduleElements.Job;
import ScheduleElements.Node;

public class Alg1MRjLmax {
	public Alg1MRjLmax() {
		
	}
	
	public void runAlgorithm(LinkedList<Job> jobList, LinkedList<LinkedList<Node>> bbTree) {
		//Determining the bounds of the tree
		int treeStartLevel = 0;
		int treeEndLevel = jobList.size();
		//Initializing the BBTree
		for(int level = treeStartLevel; level <= treeEndLevel; level++) {
			//System.out.println(level);
			//At the beginning of the algorithm, this makes sure we have the root node initialized
			LinkedList<Node> currentLevelNodes = new LinkedList<Node>();
			LinkedList<Job> scheduledJobs = new LinkedList<Job>();
			LinkedList<Job> toBeScheduledJobs = new LinkedList<Job>();
			if(level == treeStartLevel) {
				Job dummyJob = new Job("Root", 0, 0, 0, 0);
				Node rootNode = new Node(dummyJob, 0, -1);
				rootNode.setNoteStatus("Root");
				currentLevelNodes.add(rootNode);
			}else {
				//we first have to access each and every node of the previous tree level
				//System.out.println("Size is " + bbTree.get(level-1).size());
				for(int node=0; node<bbTree.get(level-1).size(); node++) {
					//For every node, we first clone the original job list
					LinkedList<Job> clonedJobList = (LinkedList<Job>) jobList.clone();
					scheduledJobs = new LinkedList<Job>();
					toBeScheduledJobs = new LinkedList<Job>();
					//Now we have to remove the jobs which have already been scheduled
					//For that we loop through the previous nodes and remove those job indices from the cloned list
					int l = level - 1;
					int refNode = node;
					if(bbTree.get(l).get(refNode).getPreviousNode() == -1) {
						for(int j=0; j<clonedJobList.size(); j++) {
							toBeScheduledJobs = (LinkedList<Job>) clonedJobList.clone();
							scheduledJobs.add(clonedJobList.get(j));
							toBeScheduledJobs.remove(j);
							Node currentNode = new Node(clonedJobList.get(j), level, node);
							
							currentLevelNodes.add(currentNode);
						}
					}else {
						while(bbTree.get(l).get(refNode).getPreviousNode() != -1) {
							for(int j=0;j<clonedJobList.size();j++) {
								if(bbTree.get(l).get(refNode).getNodeJob().getJobID() == clonedJobList.get(j).getJobID()) {
									clonedJobList.remove(j);
									refNode = bbTree.get(l).get(refNode).getPreviousNode();
									l = l-1;
									break;
								}
							}
							
						}
						
						for(int j=0; j<clonedJobList.size(); j++) {
							Node currentNode = new Node(clonedJobList.get(j), level, node);
							currentLevelNodes.add(currentNode);
						}
					}
				}
			}
			
			for(int node=0; node<bbTree.get(level-1).size(); node++) {
				//For every node, we first clone the original job list
				LinkedList<Job> clonedJobList = (LinkedList<Job>) jobList.clone();
				//Now we have to remove the jobs which have already been scheduled
				//For that we loop through the previous nodes and remove those job indices from the cloned list
				int l = level - 1;
				int refNode = node;
				if(bbTree.get(l).get(refNode).getPreviousNode() == -1) {
					for(int j=0; j<clonedJobList.size(); j++) {
						Node currentNode = new Node(clonedJobList.get(j), level, node);
						currentLevelNodes.add(currentNode);
					}
				}else {
					while(bbTree.get(l).get(refNode).getPreviousNode() != -1) {
						for(int j=0;j<clonedJobList.size();j++) {
							if(bbTree.get(l).get(refNode).getNodeJob().getJobID() == clonedJobList.get(j).getJobID()) {
								clonedJobList.remove(j);
								refNode = bbTree.get(l).get(refNode).getPreviousNode();
								l = l-1;
								break;
							}
						}
						
					}
					
					for(int j=0; j<clonedJobList.size(); j++) {
						Node currentNode = new Node(clonedJobList.get(j), level, node);
						currentLevelNodes.add(currentNode);
					}
				}
			}
			
			
			
			
			bbTree.add(currentLevelNodes);
		}
		System.out.println("Process exitted successfully");
	}
	
	private int calculatePrDefLmax(LinkedList<Job> scheduledJobs) {
		int machineStartTime = 0;
		int maxLateness = Integer.MIN_VALUE;
		for(int j=0; j<scheduledJobs.size();j++) {
			int startTime = Integer.max(scheduledJobs.get(j).getReleaseDate(), machineStartTime);
			int completionTime = startTime + scheduledJobs.get(j).getProcessingTime();
			machineStartTime = completionTime;
			maxLateness = Integer.max(maxLateness, completionTime - scheduledJobs.get(j).getDueDate());
		}
		return maxLateness;
	}
}
