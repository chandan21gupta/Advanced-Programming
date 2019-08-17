import java.util.*;
import java.io.*;

class App {
	public App() throws java.io.IOException {
		Company c = new Company();
		c.startCompany();
	}
}

class Company {
	private static float account_balance = 0;
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

	void search_merchant(int id) {
		for(Merchant m : merchants) {
			if(m.getCode() == id) {
				m.getDetails();
			}
		}
	}

	void search_customers(int id) {
		for(Customer c : customers) {
			if(c.getCode() == id) {
				c.getDetails();
			}
		}
	}

	float getBalance() {
		return this.account_balance;
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
					String[] user = buffer.readLine().split(" ");
					if(user[0].equals("M")){
						search_merchant(Integer.parseInt(user[1]));
					}
					else{
						search_customers(Integer.parseInt(user[1]));
					}
					break;
				case 4 :
					System.out.println(getBalance());
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
	final private String name;
	final private int code;
	static float contribution = 0;
	final String address = "Sample";

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

	String getAddress() {
		return this.address;
	}

	float getContribution() {
		return this.contribution;
	}

	@Override
	public void getDetails() {
		System.out.println(this.getName());
		System.out.println(this.getAddress());
		System.out.println(this.getContribution());
	}

}

class Customer implements Users {
	static int customer_count = 0;
	static int orders = 0;
	final String name;
	final String address = "Sample";
	final int code;
	
	public Customer(String name) {
		customer_count++;
		this.name = name;
		this.code = customer_count;
	}

	String getName() {
		return this.name;
	}

	int getCode() {
		return this.code;
	}

	String getAddress() {
		return this.address;
	}

	int getOrders() {
		return this.orders;
	}

	@Override
	public void getDetails() {
		System.out.println(this.getName());
		System.out.println(this.getAddress());
		System.out.println(this.getOrders());
	}
}

class Main {

	public static void main(String[] main) throws java.io.IOException {
		App app = new App();
	}
}