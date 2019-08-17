import java.util.*;
import java.io.*;
class App {
	public App() {
		Company c = new Company();
		c.startCompany();
	}
}

class Company {

	ArrayList<Users> merchants = new ArrayList<Users>();
	ArrayList<Users> customers = new ArrayList<Users>();

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

	void displayCompany() {
		System.out.println("Welcome to Mercury");
		System.out.println("1) Enter as Merchant");
		System.out.println("2) Enter as Customer");
		System.out.println("3) See user details");
		System.out.println("4) Company account balance");
		System.out.println("5) Exit");
	}

	public void startCompany() {
		displayCompany();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		int option = Integer.parseInt(buffer.readLine());
		while(option != 5) {
			switch(option) {
				case 1 : 
					break;
				case 2 :
					break;
				case 3 :
					break;
				case 4 :
					break;
				case 5 :
					break;
			}
		}
	}
}

class Merchant implements Users {

	static int merchant_count = 0;
	String name;
	int code;
	Merchant(String name) {
		merchant_count++;
		this.name = name;
		this.code = merchant_count;
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
}

class Main {

	public static void main(String[] main) throws java.io.IOException{
		App app = new App();
	}
}