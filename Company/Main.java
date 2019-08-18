import java.util.*;
import java.io.*;

class App {
	public App() throws java.io.IOException {
		Company c = new Company();
		c.startCompany();
	}
}

class Company {
	private ArrayList<Users> merchants = new ArrayList<Users>();
	private ArrayList<Users> customers = new ArrayList<Users>();
	private static float account_balance = 0;

	Company() throws java.io.IOException {
		merchants.add(new Merchant("jack"));
		merchants.add(new Merchant("john"));
		merchants.add(new Merchant("james"));
		merchants.add(new Merchant("jeff"));
		merchants.add(new Merchant("joseph"));

		customers.add(new Customer("ali"));
		customers.add(new Customer("nobby"));
		customers.add(new Customer("bruno"));
		customers.add(new Customer("borat"));
		customers.add(new Customer("aladeen"));
	}

	float getBalance() {
		return this.account_balance;
	}

	void select_merchants() {
		for(Users s : merchants) {
			s.print_comb();
		}
	}

	Users select_merchants(int id) {
		for(Users s : merchants) {
			if(s.ID() == id) {
				return s;
			}
		}
		return null;
	}

	void select_customers() {
		for(Users s : customers) {
			s.print_comb();
		}
	}

	Users select_customers(int id) {
		for(Users s : customers) {
			if(s.ID() == id) {
				return s;
			}
		}
		return null;
	}

 	void options_display() throws java.io.IOException {
		System.out.println("Welcome to Mercury");
		System.out.println("1) Enter as Merchant");
		System.out.println("2) Enter as customer");
		System.out.println("3) See user details");
		System.out.println("4) Company account balance");
		System.out.println("5) Exit");
	}

	void startCompany() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		options_display();
		int option = Integer.parseInt(buffer.readLine());
		while(option != 5) {
			switch(option) {
				case 1 : 
					select_merchants();
					int temp = Integer.parseInt(buffer.readLine());
					while(temp != 5){
						switch(temp) {
							case 1 : 
								Users s1 = select_merchants(1);
								System.out.println("Welcome "+((Merchant)s1).getName());
								s1.choose_option();
								int option1 = Integer.parseInt(buffer.readLine());
								while(option1 != 6) {
									switch(option1) {
										case 1 :
											((Merchant)s1).addItem();
											break;
										case 2 :
											((Merchant)s1).editItem();
											break;
										case 3 :
											//s1.search_category();
											break;
										case 4 :
											((Merchant)s1).add_offer();
											break;
										case 5 : 
											break;
									}
									System.out.println("Welcome "+((Merchant)s1).getName());
									s1.choose_option();
									option1 = Integer.parseInt(buffer.readLine());
								}
								break;
							case 2 :
								Users s2 = select_merchants(2);
								System.out.println("Welcome "+((Merchant)s2).getName());
								s2.choose_option();
								break;
							case 3 :
								Users s3 = select_merchants(3);
								System.out.println("Welcome "+((Merchant)s3).getName());
								s3.choose_option();
								break;
							case 4 :
								Users s4 = select_merchants(4);
								System.out.println("Welcome "+((Merchant)s4).getName());
								s4.choose_option();
								break;
							case 5 :
								Users s5 = select_merchants(5);
								System.out.println("Welcome "+((Merchant)s5).getName());
								s5.choose_option();
								break;
						}
						temp = Integer.parseInt(buffer.readLine());
					}
					break;
				case 2 :
					select_customers();
					break;
				case 3 :
					String[] query = buffer.readLine().split(" ");
					if(query[0].equals("M")) {
						Users s = select_merchants(Integer.parseInt(query[1]));
						s.getDetails();
					}	
					else {
						Users s = select_customers(Integer.parseInt(query[1]));
						s.getDetails();
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
	
	ArrayList<Item> items = new ArrayList<Item>();

	static int merchant_count = 0;
	private int id;
	private final String name;
	private final String address;
	static float total_contribution = 0; 

	Merchant(String name) {
		merchant_count++;
		this.name = name;
		this.id = merchant_count;
		this.address = "";
	}

	public void choose_option() {
		System.out.println("Merchant Menu");
		System.out.println("1) Add item");
		System.out.println("2) Edit item");
		System.out.println("3) Search by category");
		System.out.println("4) Add offer");
		System.out.println("5) Rewards won");
		System.out.println("6) Exit");
	}
	

	// public void search_category() throws java.io.IOException {
	// 	BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	// 	System.out.println("Choose a category");
		
	// 	// int choice = Integer.parseInt(buffer.readLine());
	// 	// items.get(choice).display_item_details();
	// }

	void add_offer() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("choose item by code");
		for(Item item : items) {
			item.display_item_details();
		}
		int choice = Integer.parseInt(buffer.readLine());
		for(Item item:items) {
			if(item.getItem_code() == choice) {
				String offer1 = "buy one get one free";
				String offer2 = "25% off";
				System.out.println("choose offer");
				System.out.println("1) "+offer1);
				System.out.println("2) "+offer2);
				int temp = Integer.parseInt(buffer.readLine());
				if(temp == 1) {
					item.setOffer(offer1);
				}
				else{
					item.setOffer(offer2);
				}
				item.display_item_details();
			}
		}
	}

	String getName() {
		return this.name;
	}

	int getId() {
		return this.id;
	}

	public int ID() {
		return this.getId();
	}

	String comb() {
		return this.id+" "+this.name;
	}

	public void print_comb() {
		System.out.println(this.comb());
	}

	void merchant_details() {
		System.out.println(this.name+" "+this.address+" "+this.total_contribution);
	}

	public void getDetails() {
		this.merchant_details();
	}

	void display_items() throws java.io.IOException {
		for(Item item : items) {
			item.display_item_details();
		}
	}

	void addItem() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter item details");
		System.out.println("item name:");
		String n = buffer.readLine();
		System.out.println("item price:");
		float p = Integer.parseInt(buffer.readLine());
		System.out.println("item quantity:");
		int q = Integer.parseInt(buffer.readLine());
		System.out.println("item cateogry:");
		String category = buffer.readLine();
		Item item = new Item(n,p,q,category);
		if(items.size()<11) {
			items.add(item);
		}
		item.display_item_details();
	}
	
	void editItem() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("choose item by code");
		for(Item i : items) {
			i.display_item_details();
		}
		int choice = Integer.parseInt(buffer.readLine());
		for(Item i : items) {
			if(i.getItem_code() == choice) {
				i.edit();
			}
		}
	}
}

class Customer implements Users {

	static int customers_count = 0;
	static int orders = 0;
	private final String name;
	private final int id;
	private final String address;


	Customer(String name) {
		customers_count++;
		this.name = name;
		this.id = customers_count;
		this.address = "";
	}

	public void choose_option() {

	}

	int getId() {
		return this.id;
	}

	public int ID() {
		return this.getId();
	}

	String comb() {
		return this.id+" "+this.name;
	}

	public void print_comb() {
		System.out.println(this.comb());
	}

	void customer_details() {
		System.out.println(this.name+" "+this.address+" "+this.orders);
	}

	public void getDetails() {
		this.customer_details();
	}

	// public void choose_category() {

	// }

}

class Item {
	static int item_count = 0;
	private String name;
	private int item_code;
	private float price;
	private int quantity;
	private String category;
	private String offer;

	Item(String name,float price,int quantity,String category) {
		item_count++;
		this.name = name;
		this.item_code = item_count;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.offer = "None";
	}

	String getCategory() {
		return this.category;
	}

	int getItem_code() {
		return this.item_code;
	}

	void setOffer(String offer) {
		this.offer = offer;
	}

	void display_item_details() throws java.io.IOException {
		System.out.println(this.item_code+" "+this.name+" "+this.price+" "+this.quantity+" "+this.offer+" "+this.category);
	}

	void edit() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));		
		System.out.println("Enter edit details");
		System.out.println("item price:");
		float p = Float.parseFloat(buffer.readLine());
		System.out.println("item quantity:");
		int q = Integer.parseInt(buffer.readLine());
		this.price = p;
		this.quantity = q;
		this.display_item_details();
	}

}

class Main {
	public static void main(String[] args) throws java.io.IOException {
		App app = new App();
	}
}