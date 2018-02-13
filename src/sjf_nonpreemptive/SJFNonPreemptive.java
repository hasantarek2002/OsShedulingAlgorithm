package sjf_nonpreemptive;

import java.util.Arrays;

//////////////////////////////////
//this is SJF Non-preemptive with same arrival time

class Process implements Comparable<Process> {
	int pid; // Process ID
	int bt; // Burst Time

	public Process(int pid, int bt) {
		this.pid = pid;
		this.bt = bt;
	}

	public int compareTo(Process proc) {
		int compareBt = ((Process) proc).bt;

		// ascending order
		return this.bt - compareBt;

		// descending order
		// return compareBt - this.bt;
	}

}

public class SJFNonPreemptive {

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
		System.out.println("Processes "+ " Burst time "+ " Waiting time " + " Turn around time");
		
		for (int i = 0; i < n; i++)
	    {
	        total_wt = total_wt + wt[i];
	        total_tat = total_tat + tat[i];
	        System.out.println(" "+proc[i].pid+"\t\t"+proc[i].bt +"\t "+ wt[i]+ "\t\t " + tat[i]);
	    }
		
		System.out.println("Average waiting time = "+(float)total_wt / (float)n);
		System.out.println("Average turn around time = "+(float)total_tat / (float)n);

	}

	

	public static void main(String[] args) {
		Process proc[] = { new Process(1, 6), new Process(2, 8), new Process(3, 7), new Process(4, 3) };

		Arrays.sort(proc);
		/*
		 * for(int i=0;i<4;i++){
		 * System.out.println(proc[i].pid+"\t\t"+proc[i].bt); }
		 */
		findavgTime(proc, 4);

	}

}

/*
 Algorithm
 
 * 1- Sort all the processes in increasing order 
   according to burst time.
2- Then simply, apply FCFS.
*/
