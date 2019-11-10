import java.io.*;
import java.util.*;

class Fionacchi {

	Producer producer;

	public Fibonaachi() {
		producer = new Producer();
		Thread prod = new Thread(producer);
		prod.start();
	}

}

class Producer extends Thread {
	final private int consumers;
	private ArrayList<Consumer> consumer_threads = new ArrayList<Consumer>(); 
	Producer() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		this.consumers = Integer.parseInt(buffer.readLine());
		for(int i=0;i<this.consumers; i++) {
			this.consumer_threads.add(new Thread(new Consumer));
		}
	}

	@Override
	public void run() {
		
	}
}

class Consumer {

}

class Main {

	public static void main(String[] args) {
		Fibonaachi fibo = new Fibonaachi();
	}
}