package sjf_preemptive;

////// this algorithm also called Shortest Remaining
//Time First with different arrival time

//it can be used with same arrival time by adding a array which is initialized with 0.

class Process {
	int pid; // Process ID
	int bt; // Burst Time
	int art; // Arrival Time

	public Process(int pid, int bt, int art) {
		this.pid = pid;
		this.bt = bt;
		this.art = art;
	}
}

public class SJFPreemptive {

	static void findWaitingTime(Process proc[], int n, int wt[]) {

		int rt[] = new int[n];
		for (int i = 0; i < n; i++)
			rt[i] = proc[i].bt;

		int complete = 0, t = 0, minm = Integer.MAX_VALUE;
		int shortest = 0, finish_time;
		boolean check = false;

		while (complete != n) {

			for (int j = 0; j < n; j++) {
				if ((proc[j].art <= t) && (rt[j] < minm) && rt[j] > 0) {
					minm = rt[j];
					shortest = j;
					check = true;
				}
			}

			if (check == false) {
				t++;
				continue;
			}

			rt[shortest]--;
			minm = rt[shortest];

			if (minm == 0)
				minm = Integer.MAX_VALUE;

			if (rt[shortest] == 0) {
				complete++;

				finish_time = t + 1;
				wt[shortest] = finish_time - proc[shortest].bt - proc[shortest].art;

				if (wt[shortest] < 0)
					wt[shortest] = 0;
			}
			t++;
		}
	}

	static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) {
		
		for (int i = 0; i < n; i++)
			tat[i] = proc[i].bt + wt[i];
	}

	static void findavgTime(Process proc[], int n) {
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0, total_tat = 0;

		findWaitingTime(proc, n, wt);

		findTurnAroundTime(proc, n, wt, tat);

		System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
			System.out.println(" " + proc[i].pid + "\t\t" + proc[i].bt + "\t\t " + wt[i] + "\t\t" + tat[i]);
		}

		System.out.println("Average waiting time = " + (float) total_wt / (float) n);
		System.out.println("Average turn around time = " + (float) total_tat / (float) n);
	}

	public static void main(String[] args) {

		Process proc[] = { new Process(1, 6, 1), new Process(2, 8, 1), new Process(3, 7, 2), new Process(4, 3, 3) };
		findavgTime(proc, proc.length);

	}

}


/*
Implementation:
1- Traverse until all process gets completely
   executed.
   a) Find process with minimum remaining time at
     every single time lap.
   b) Reduce its time by 1.
   c) Check if its remaining time becomes 0 
   d) Increment the counter of process completion.
   e) Completion time of current process = 
     current_time +1;
   e) Calculate waiting time for each completed 
     process.
   wt[i]= Completion time - arrival_time-burst_time
   f)Increment time lap by one.
2- Find turnaround time (waiting_time+burst_time).*/