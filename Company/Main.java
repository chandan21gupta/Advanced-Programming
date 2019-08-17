import java.util.*;
import java.io.*;

class App {
	public App() throws java.io.IOException {
		Company c = new Company();
		c.startCompany();
	}
}

class Company {

	private ArrayList<Merchant> merchants = new ArrayList<Merchant>();
	private ArrayList<Customer> customers = new ArrayList<Customer>();

	public Company() {
		merchants.add(new Merchant("Jack"));
		merchants.add(new Merchant("John"));
		merchants.add(new Merchant("James"));
		merchants.add(new Merchant("Jeff"));
		merchants.add(new Merchant("Joseph"));

		customers.add(new Customer("Ali"));
		customers.add(new Customer("Nobby"));
		customers.add(new Customer("Bruno"));
		customers.add(new Customer("Borat"));
		customers.add(new Customer("Aladeen"));
	}

	void displayCompany() throws java.io.IOException {
		System.out.println("Welcome to Mercury");
		System.out.println("1) Enter as Merchant");
		System.out.println("2) Enter as Customer");
		System.out.println("3) See user details");
		System.out.println("4) Company account balance");
		System.out.println("5) Exit");
	}

	void choose_merchants() {
		System.out.println("choose merchants");
		for(Merchant m : merchants) {
			System.out.println(Integer.toString(m.getCode())+" "+m.getName());
		}
	}

	void choose_customers() {
		System.out.println("choose customers");
		for(Customer c : customers) {
			System.out.println(Integer.toString(c.getCode())+" "+c.getName());
		} 
	}

	public void startCompany() throws java.io.IOException {
		displayCompany();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		int option = Integer.parseInt(buffer.readLine());
		while(option != 5) {
			switch(option) {
				case 1 :
					choose_merchants();  
					break;
				case 2 :
					choose_customers();
					break;
				case 3 :
					break;
				case 4 :
					break;
				case 5 :
					return;
			}
			option = Integer.parseInt(buffer.readLine());
		}
		
	}
}

class Merchant implements Users {

	static int merchant_count = 0;
	private String name;
	private int code;

	Merchant(String name) {
		merchant_count++;
		this.name = name;
		this.code = merchant_count;
	}	

	String getName() {
		return this.name;
	}

	int getCode() {
		return this.code;
	}


}

class Customer implements Users {
	static int customer_count = 0;
	String name;
	int code;
	Customer(String name) {
		customer_count++;
		this.name = name;
		this.code = customer_count;
	}

	public String getName() {
		return this.name;
	}

	public int getCode() {
		return this.code;
	}
	
}

class Main {

	public static void main(String[] main) throws java.io.IOException {
		App app = new App();
	}
}