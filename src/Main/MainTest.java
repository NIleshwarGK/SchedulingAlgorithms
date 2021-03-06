package Main;

import java.util.Collections;
import java.util.LinkedList;

import Algorithms.Alg1MLmax;
import Algorithms.Alg1MPrRjLmax;
import Algorithms.Alg1MRjLmax;
import Algorithms.Alg1MSumCj;
import Algorithms.Alg1MSumWjCj;
import ScheduleElements.Job;
import ScheduleElements.Machine;
import ScheduleElements.Node;
import ScheduleElements.ScheduleElement;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Job J1 = new Job("J1", 3, 3, 6, 1);
		Job J2 = new Job("J2", 2, 2, 7, 1);
		Job J3 = new Job("J3", 5, 0, 10, 1);
		Job J4 = new Job("J4", 5, 11, 18, 1);
		
		
		LinkedList<Job> jobList = new LinkedList<Job>();
		
		jobList.add(J1);
		jobList.add(J2);
		jobList.add(J3);
		jobList.add(J4);
		
		Machine machine = new Machine("M1");
		
		LinkedList<LinkedList<Node>> bbTree = new LinkedList<LinkedList<Node>>();
		
		//Alg1MSumCj algVar = new Alg1MSumCj();
		//Alg1MLmax algVar = new Alg1MLmax();
		//Alg1MSumWjCj algVar = new Alg1MSumWjCj();
		//Alg1MPrRjLmax algVar = new Alg1MPrRjLmax();
		//algVar.runAlgorithm(jobList, machine);
		Alg1MRjLmax algVar = new Alg1MRjLmax();
		algVar.runAlgorithm(jobList, bbTree);
		
		System.out.println("Tree Size : " + bbTree.size());
		
		
		for(int j=0; j<bbTree.size(); j++) {
			System.out.println("Level : " + j + " - Elements : "+ bbTree.get(j).size());
			for(int i=0; i<bbTree.get(j).size();i++) {
				//System.out.println(bbTree.get(j).get(i).getNodeJob().getJobID());
			}
		}

	}

}
