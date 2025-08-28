package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.Scanner;
 


public class Main {

	public static void main(String[] args) throws InterruptedException{
		ArrayList<PrimeFinderThread> threads = makeThreads(3, 1, 500_000_000);
		startThreads(threads);
		Thread.sleep(5000);
		pauseThreads(threads);

		enterInput();
		resumeThreads(threads);

	}


	public static ArrayList<PrimeFinderThread> makeThreads(int numberOfThreads, int start, int end ){
		ArrayList<PrimeFinderThread> threads = new ArrayList<>();
		int range = (end - start) / numberOfThreads;
		for (int i = 0; i < numberOfThreads; i++) {
			int threadStart = start + (i * range);
			int threadEnd = (i == numberOfThreads - 1) ? end : threadStart + range;
			PrimeFinderThread pft = new PrimeFinderThread(threadStart, threadEnd);
			threads.add(pft);
		}
		return threads;
	}

	public static void startThreads(ArrayList<PrimeFinderThread> threads){
		for (PrimeFinderThread thread : threads) {
			thread.start();
		}
	}

	public static void pauseThreads(ArrayList<PrimeFinderThread> threads) throws InterruptedException{
		for(PrimeFinderThread thread : threads){
			thread.pauseThread();
			System.out.println("Thread Pause");
			System.out.println("The last prime found was: " + thread.getPrimes().get(thread.getPrimes().size() - 1));

		}
		
	}

		private static void enterInput(){
		System.out.println("Press the ENTER key to continue");
		Scanner sc = new Scanner(System.in);
		String i = "enter";
		while(true){
			if (i.equals("")){
				break;
			}
			i = sc.nextLine();
		}
		sc.close();
	}


	public static void resumeThreads(ArrayList<PrimeFinderThread> threads){
		for(PrimeFinderThread thread : threads){
			thread.resumeThread();
			System.out.println("Thread Resume");
		}
	}
	
}
