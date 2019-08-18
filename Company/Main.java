import java.util.*;
import java.io.*;

class App {
	public App() throws java.io.IOException {
		Company c = new Company();
		c.startCompany();
	}
}

class Company {
	static public ArrayList<Users> merchants = new ArrayList<Users>();
	private ArrayList<Users> customers = new ArrayList<Users>();
	static public ArrayList<String> Categories = new ArrayList<String>();
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

	public static boolean category_contains(String category) {
		for(String s : Categories) {
			if(s.equals(category)) {
				return true;
			}
		}
		return false;
	}

	static ArrayList<Item> display_merchant_items (String category) throws java.io.IOException {
		ArrayList<Item> i = new ArrayList<Item>();
		for(Users m : merchants) {
			for(Item item : ((Merchant)m).getItems()) {
				if(item.getCategory().equals(category)) {
					i.add(item);					
				}
			}
		}
		return i;
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
					while(temp != 6){
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
											s1.search_category();
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
								int option2 = Integer.parseInt(buffer.readLine());
								while(option2 != 6) {
									switch(option2) {
										case 1 :
											((Merchant)s2).addItem();
											break;
										case 2 :
											((Merchant)s2).editItem();
											break;
										case 3 :
											s2.search_category();
											break;
										case 4 :
											((Merchant)s2).add_offer();
											break;
										case 5 : 
											break;
									}
									System.out.println("Welcome "+((Merchant)s2).getName());
									s2.choose_option();
									option2 = Integer.parseInt(buffer.readLine());
								}
								break;
							case 3 :
								Users s3 = select_merchants(3);
								System.out.println("Welcome "+((Merchant)s3).getName());
								s3.choose_option();
								int option3 = Integer.parseInt(buffer.readLine());
								while(option3 != 6) {
									switch(option3) {
										case 1 :
											((Merchant)s3).addItem();
											break;
										case 2 :
											((Merchant)s3).editItem();
											break;
										case 3 :
											s3.search_category();
											break;
										case 4 :
											((Merchant)s3).add_offer();
											break;
										case 5 : 
											break;
									}
									System.out.println("Welcome "+((Merchant)s3).getName());
									s3.choose_option();
									option2 = Integer.parseInt(buffer.readLine());
								}
								break;
							case 4 :
								Users s4 = select_merchants(4);
								System.out.println("Welcome "+((Merchant)s4).getName());
								s4.choose_option();
								int option4 = Integer.parseInt(buffer.readLine());
								while(option4 != 6) {
									switch(option4) {
										case 1 :
											((Merchant)s4).addItem();
											break;
										case 2 :
											((Merchant)s4).editItem();
											break;
										case 3 :
											s4.search_category();
											break;
										case 4 :
											((Merchant)s4).add_offer();
											break;
										case 5 : 
											break;
									}
									System.out.println("Welcome "+((Merchant)s4).getName());
									s4.choose_option();
									option4 = Integer.parseInt(buffer.readLine());
								}
								break;
							case 5 :
								Users s5 = select_merchants(5);
								System.out.println("Welcome "+((Merchant)s5).getName());
								s5.choose_option();
								int option5 = Integer.parseInt(buffer.readLine());
								while(option5 != 6) {
									switch(option5) {
										case 1 :
											((Merchant)s5).addItem();
											break;
										case 2 :
											((Merchant)s5).editItem();
											break;
										case 3 :
											s5.search_category();
											break;
										case 4 :
											((Merchant)s5).add_offer();
											break;
										case 5 : 
											break;
									}
									System.out.println("Welcome "+((Merchant)s5).getName());
									s5.choose_option();
									option5 = Integer.parseInt(buffer.readLine());
								}
								break;
						}
						select_merchants();
						temp = Integer.parseInt(buffer.readLine());
					}
					break;
				case 2 :
					select_customers();
					int temp2 = Integer.parseInt(buffer.readLine());
					while(temp2 != 6) {
						switch(temp2) {
							case 1 :
								Users c1 = select_customers(1);
								System.out.println("Welcome "+((Customer)c1).getName());
								c1.choose_option();
								int option6 = Integer.parseInt(buffer.readLine());
								while(option6 != 5) {
									switch(option6) {
										case 1 :
											c1.search_category();
											break;
										case 2 :
											break;
										case 3 :
											break;
										case 4 :
											((Customer)c1).recent_orders();
											break;
									}
									System.out.println("Welcome "+((Customer)c1).getName());
									c1.choose_option();
									option6 = Integer.parseInt(buffer.readLine());
								}
								break;
							case 2 :
								Users c2 = select_customers(2);
								System.out.println("Welcome "+((Customer)c2).getName());
								c2.choose_option();
								int option7 = Integer.parseInt(buffer.readLine());
								while(option7 != 5) {
									switch(option7) {
										case 1 :
											c2.search_category();
											break;
										case 2 :
											break;
										case 3 :
											break;
										case 4 :
											break;
									}
									System.out.println("Welcome "+((Customer)c2).getName());
									c2.choose_option();
									option7 = Integer.parseInt(buffer.readLine());
								}
								break;
							case 3 :
								Users c3 = select_customers(3);
								System.out.println("Welcome "+((Customer)c3).getName());
								c3.choose_option();
								int option8 = Integer.parseInt(buffer.readLine());
								while(option8 != 5) {
									switch(option8) {
										case 1 :
											c3.search_category();
											break;
										case 2 :
											break;
										case 3 :
											break;
										case 4 :
											break;
									}
									System.out.println("Welcome "+((Customer)c3).getName());
									c3.choose_option();
									option8 = Integer.parseInt(buffer.readLine());
								}
								break;
							case 4 :
								Users c4 = select_customers(4);
								System.out.println("Welcome "+((Customer)c4).getName());
								c4.choose_option();
								int option9 = Integer.parseInt(buffer.readLine());
								while(option9 != 5) {
									switch(option9) {
										case 1 :
											c4.search_category();
											break;
										case 2 :
											break;
										case 3 :
											break;
										case 4 :
											break;
									}
									System.out.println("Welcome "+((Customer)c4).getName());
									c4.choose_option();
									option9 = Integer.parseInt(buffer.readLine());
								}
								break;
							case 5 :
								Users c5 = select_customers(5);
								System.out.println("Welcome "+((Customer)c5).getName());
								c5.choose_option();
								int option10 = Integer.parseInt(buffer.readLine());
								while(option10 != 5) {
									switch(option10) {
										case 1 :
											c5.search_category();
											break;
										case 2 :
											break;
										case 3 :
											break;
										case 4 :
											break;
									}
									System.out.println("Welcome "+((Customer)c5).getName());
									c5.choose_option();
									option10 = Integer.parseInt(buffer.readLine());
								}
								break;
						}
						select_customers();
						temp2 = Integer.parseInt(buffer.readLine());
					}
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
			options_display();
			option = Integer.parseInt(buffer.readLine());
		}
	}
}

class Merchant implements Users {
	
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<String> categories = new ArrayList<String>();

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

	ArrayList<Item> getItems() {
		return this.items;
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
	
	boolean contains(String category) {
		for(String s : categories) {
			if(s.equals(category)) {
				return true;
			}
		}
		return false;
	}

	public void search_category() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Choose a category");
		for(int i=0;i<categories.size();i++) {
			System.out.println(i+1 + " "+ categories.get(i));
		}
		int option = Integer.parseInt(buffer.readLine());
		String category = categories.get(option-1);
		for(Item item : items) {
			if(item.getCategory().equals(category)){
				item.display_item_details();
			}
		}
	}

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
		System.out.println("item category:");
		String category = buffer.readLine();
		if(!contains(category)){
			categories.add(category);
		}
		if(!Company.category_contains(category)) {
			Company.Categories.add(category);
		}
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
	//static int orders = 0;
	static private ArrayList<Item> orders = new ArrayList<Item>();
	static private ArrayList<Integer> quantities = new ArrayList<Integer>();
	static private ArrayList<Integer> quantities_cart = new ArrayList<Integer>();
	static private ArrayList<Item> cart = new ArrayList<Item>();
	private final String name;
	private final int id;
	private final String address;
	private static float customer_balance = 100;

	Customer(String name) {
		customers_count++;
		this.name = name;
		this.id = customers_count;
		this.address = "";
	}

	public void choose_option() {
		System.out.println("Customer menu");
		System.out.println("1) Search item");
		System.out.println("2) checkout cart");
		System.out.println("3) Reward won");
		System.out.println("4) print latest orders");
		System.out.println("5) Exit");
	}

	void setBalance(float p) {
		this.customer_balance = p;
	}

	float getBalance() {
		return this.customer_balance;
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

	void customer_details() {
		System.out.println(this.name+" "+this.address+" "+this.orders);
	}

	public void getDetails() {
		this.customer_details();
	}

	public void search_category() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("choose a category");
		for(int i=0;i<Company.Categories.size();i++) {
			System.out.println(i+1+" "+Company.Categories.get(i));
		}
		int choice = Integer.parseInt(buffer.readLine());
		String category = Company.Categories.get(choice-1);
		ArrayList<Item> i = Company.display_merchant_items(category);
		System.out.println("choose item by code");
		for(Item item : i) {
			item.display_item_details();
		}
		System.out.println("Enter item code");
		choice = Integer.parseInt(buffer.readLine());
		System.out.println("Enter item quantity");
		int q = Integer.parseInt(buffer.readLine());
		System.out.println("Choose method of abstraction");
		System.out.println("1) Buy item");
		System.out.println("2) Add item to cart");
		System.out.println("3) Exit");
		int option = Integer.parseInt(buffer.readLine());
		if(option == 1) {
			if(i.get(choice-1).getQuantity()>=q && this.getBalance()>=i.get(choice-1).getPrice()*q) {
				i.get(choice-1).setQuantity(i.get(choice-1).getQuantity()-q);
				this.setBalance(this.getBalance()-i.get(choice-1).getPrice()*q);
				orders.add(i.get(choice-1));
				quantities.add(q);
				System.out.println("Item Successfully bought");
			}
		}	
		else if(option == 2) {
			cart.add(i.get(choice-1));
			quantities_cart.add(q);
			System.out.println("Successfully added to cart");
		}
		else{
			return;
		}
	}

	void recent_orders() {
		int i = this.orders.size()-1;
		int k = 10;
		while(i>=0 && k>0) {
			System.out.print("Bought item ");
			System.out.print(orders.get(i).getName()+" "+quantities.get(i)+"for Rs"+orders.get(i).getPrice()*quantities.get(i)+"from");
			k--;
			i--;
		}
	}

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

	String getName() {
		return this.name;
	}

	float getPrice() {
		return this.price;
	}

	void setQuantity(int p) {
		this.quantity = p;
	}

	int getQuantity() {
		return this.quantity;
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