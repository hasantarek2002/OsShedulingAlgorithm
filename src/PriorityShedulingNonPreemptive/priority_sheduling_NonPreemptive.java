package PriorityShedulingNonPreemptive;

import java.util.Arrays;


// Here Arrival time is same and it is done by the help of using FCFS with same arrival time

//it can be done with different arrival time using FCFS with different arrival time

class Process implements Comparable<Process> {
	int pid; // Process ID
	int bt; // Burst Time
	int priority;

	public Process(int pid, int bt, int priority) {
		this.pid = pid;
		this.bt = bt;
		this.priority = priority;
	}

	public int compareTo(Process proc) {
		int comparePriority = ((Process) proc).priority;

		// ascending order
		// return this.priority - comparePriority;

		// descending order
		return comparePriority - this.priority;
	}

}

public class priority_sheduling_NonPreemptive {

	static void findWaitingTime(Process proc[], int n, int wt[]) {

		wt[0] = 0;
		for (int i = 1; i < n; i++)
			wt[i] = proc[i - 1].bt + wt[i - 1];
	}

	static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) {
		for (int i = 0; i < n; i++)
			tat[i] = proc[i].bt + wt[i];
	}

	static void findavgTime(Process proc[], int n) {
		int[] wt = new int[n];
		int[] tat = new int[n];
		int total_wt = 0, total_tat = 0;

		findWaitingTime(proc, n, wt);
		findTurnAroundTime(proc, n, wt, tat);

		System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
			System.out.println(" " + proc[i].pid + "\t\t" + proc[i].bt + "\t " + wt[i] + "\t\t " + tat[i]);
		}

		System.out.println("Average waiting time = " + (float) total_wt / (float) n);
		System.out.println("Average turn around time = " + (float) total_tat / (float) n);
	}

	static void priorityScheduling(Process proc[], int n) {
		Arrays.sort(proc);

		System.out.println("Order in which processes gets executed");
		for (int i = 0; i < n; i++)
			System.out.print(proc[i].pid + " ");
		System.out.println();

		findavgTime(proc, n);
	}

	public static void main(String[] args) {
		Process proc[] = { new Process(1, 10, 2), new Process(2, 5, 0), new Process(3, 8, 1) };
		priorityScheduling(proc, proc.length);

	}

}


/*
 IMPLEMENTATION
 
	 1- First input the processes with their burst time 
	   and priority.
	2- Sort the processes, burst time and priority
	   according to the priority.
	3- Now simply apply FCFS algorithm.

*/

