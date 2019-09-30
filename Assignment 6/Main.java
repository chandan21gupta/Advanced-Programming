import java.io.*;

class Demo implements java.io.Serializable {

	int a;
	String b;

	Demo(int a, String b) {
		this.a = a;
		this.b = b;
	}

}


class Main {
	public static void main(String[] args) {
		Demo object = new Demo(1,"Hello");
		String filename = "file.ser";
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(object);

			out.close();
			file.close();
			System.out.println("Object has been serialized.");
		}
		catch(IOException e) {
			System.out.println("IOException occured!!");
		}

		Demo object1 = null;

		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			object1 = (Demo)in.readObject();

			Boolean t = object1 == object;

			System.out.println(t);

			in.close();
			file.close();

			System.out.println("Object has been desearlized.");
		}
		catch(IOException e) {
			System.out.println("IOException occured!!");
		}
		catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException is caught!!");
		}
	}
}