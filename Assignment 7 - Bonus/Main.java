import java.io.*;
import java.util.*;

class Fibonaachi {

	Producer producer;

	public Fibonaachi() throws java.io.IOException {
		producer = new Producer();
		Thread prod = new Thread(producer);
		prod.start();
	}

}

class Producer implements Runnable {
	final private int consumers;
	private ArrayList<Thread> consumer_threads = new ArrayList<Thread>(); 
	Producer() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		this.consumers = Integer.parseInt(buffer.readLine());
		for(int i=0;i<this.consumers; i++) {
			this.consumer_threads.add(new Thread(new Consumer()));
		}
	}

	@Override
	public void run() {
		
	}
}

class Consumer implements Runnable {

	@Override
	public void run() {

	}
}

class Main {

	public static void main(String[] args) throws java.io.IOException {
		Fibonaachi fibo = new Fibonaachi();
	}
}