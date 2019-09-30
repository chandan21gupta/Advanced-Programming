import java.util.*;
import java.io.*;

class Game {

	//private ArrayList<String> _users;
	private User current;
	Game(String user_name) throws java.io.IOException,Exception {
		loadgame(user_name);
	}

	void loadgame(String user) throws java.io.IOException,Exception {
		try {
			FileInputStream file = new FileInputStream(user+".ser");
			ObjectInputStream in = new ObjectInputStream(file);

			current = (User)in.readObject();
			in.close();
			file.close();

			System.out.println("Game loaded succesfully!!!");
		}
		catch(IOException e) {
			System.out.println("IOException is caught!!!");
			current = new User(user);
		}

		catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException is caught!!!");
		}
		if(current != null) {
			current.startGame();
		}
	}

	public static void saveGame(User u) {
		try {
			
			FileOutputStream file = new FileOutputStream(u.getName()+".ser");
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(u);

			out.close();
			file.close();

			System.out.println("Game has been saved succesfully!!!");
		}

		catch(IOException e) {
			System.out.println("IOException caught!!!");
		}

	}
}

class User_Game implements Serializable {

	private ArrayList<GenericTile> _tiles;
	private int n = 100;
	final private int _snakes;
	final private int _vultures;
	final private int _crickets;
	final private int _trampolines;
	final private int _whites;
	final private int _snakes_moves;
	final private int _crickets_moves;
	final private int _trampolines_moves;
	final private int _vultures_moves;
	final private int _white_moves;
	
	private GenericTile checkpoint_1;
	private GenericTile checkpoint_2;
	private GenericTile checkpoint_3;
	private GenericTile checkpoint;
	private User _u;

	User_Game(User u) throws java.io.IOException {
		_u = u;
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		Boolean done = false;
		while(!done){
			try {
				System.out.println("Enter total number of tiles on the race track(length)");
				this.n = Integer.parseInt(buffer.readLine());
				_tiles = new ArrayList<GenericTile>(n);
				System.out.println("Setting up the race track...");
				done = true;
			}
			catch(NumberFormatException e) {
				System.out.println("The user should enter an integer number");
				System.out.println("Please enter again");
			}
		}

		for(int i=0;i<n;i++) {
			_tiles.add(null);
		}

		Random random = new Random();
		_snakes = (n/5)-9+random.nextInt((n/5)-((n/5)-9+1));
		_vultures = (n/5)-9+random.nextInt((n/5)-((n/5)-9+1));
		_crickets = (n/5)-9+random.nextInt((n/5)-((n/5)-9+1));
		_trampolines = (n/5)-9+random.nextInt((n/5)-((n/5)-9+1));
		_whites = n - (_snakes+_vultures+_crickets+_trampolines);

		_snakes_moves = 1+random.nextInt(10);
		_crickets_moves = 1+random.nextInt(10);
		_vultures_moves = 1+random.nextInt(10);
		_trampolines_moves = -(1+random.nextInt(10));
		_white_moves = 0;

		random_generator(n,_snakes,"snakes");
		random_generator(n,_crickets,"crickets");
		random_generator(n,_vultures,"vultures");
		random_generator(n,_trampolines,"trampolines");
		random_generator(n,_whites,"whites");
		System.out.println("Danger : There are "+_snakes+", "+_crickets+", "+_vultures+" numbers of Snakes, Cricket and Vultures respectively on your track!");
		System.out.println("Danger : Each Snake, Cricket and Vultures can throw you back by "+_snakes_moves+", "+_crickets_moves+", "+_vultures_moves+" number of Tiles respectively!");
		System.out.println("Good news : There are "+_trampolines+" number of Trampolines on your track!");
		System.out.println("Good news : Each Trampoline can help you advance by "+-_trampolines_moves+" number of Tiles");
		
		int checkpoint_counter = 0;
		for(GenericTile tile : _tiles) {
			checkpoint_counter++;
			if(checkpoint_counter == 0.25*n) {
				checkpoint_1 = tile;
			}
			else if(checkpoint_counter == 0.5*n) {
				checkpoint_2 = tile;
			}

			else if(checkpoint_counter == 0.75*n) {
				checkpoint_3 = tile;
			}
		}
	}

	void random_generator(int n,int _obstacles,String str) {
		Random random = new Random();
		int k = _obstacles;
		while(k>0) {
			for(int i=0;i<n;i++) {
				if(_tiles.get(i) == null) {
					int x = random.nextInt(2);
					if(k>0 && x == 1) {
						if(str.equals("snakes")) {
						_tiles.set(i,new Snake(_snakes_moves));
							//System.out.println("Snakes called");
						}
						else if(str.equals("crickets")) {
							_tiles.set(i,new Cricket(_crickets_moves));
							//System.out.println("Crickets called");

						}
						else if(str.equals("vultures")) {
							_tiles.set(i,new Vulture(_vultures_moves));
							//System.out.println("Vultures called");

						}
						else if(str.equals("trampolines")) {
							_tiles.set(i,new Trampoline(_trampolines_moves));
							//System.out.println("Trampolines called");

						}
						else if(str.equals("whites")) {
							_tiles.set(i,new White(_white_moves));
							//System.out.println("Whites called");

						}
						k--;
					}
				}
			}
		}
	}

	// void EnterPlayer() throws Exception {
	// 	User user = new User(n);
	// 	_users.add(user);
	// 	StartGame(user);
	// }

	int getCheckpoint() {
		int k = -1;
		if(checkpoint == null) {
			return -1;
		}
		for(int i=0;i<_tiles.size();i++) {
			if(_tiles.get(i) == checkpoint) {
				k = i;
				return k;
			}	
		}
		return k;
	}

	void StartGame() throws java.io.IOException, Exception {
		Random random = new Random();
		int tile_number = getCheckpoint();
		if(tile_number == -1) {
			tile_number = 1;
		}
		int count = 1;
		while(tile_number < _tiles.size()) {
			int dice_roll = 1+random.nextInt(6);
			if(tile_number == 1 && dice_roll != 6) {
				System.out.println("[Roll-"+count+"]"+" rolled at "+dice_roll+" at Tile-1, OOPs you need 6 to start");				
				checkpoint = _tiles.get(tile_number-1);
			}

			else if(tile_number == 1 && dice_roll == 6) {
				System.out.println("[Roll-"+count+"]"+" rolled at "+dice_roll+" at Tile-1. You are out of the cage! You get a free roll.");
				dice_roll = 1+random.nextInt(6);
				count++;
				System.out.print("[Roll-"+count+"]"+" rolled at "+dice_roll+" at Tile-"+tile_number+", landed on Tile ");
				tile_number+=dice_roll;
				System.out.println(tile_number);
				System.out.println("Trying to shake the Tile-"+tile_number);
				_tiles.get(tile_number-1).shake();
				int penalty = _tiles.get(tile_number-1).getmoves();
				tile_number=tile_number - penalty;
				if(tile_number<=0) {
					System.out.println("moved to Tile 1 as it cant go back further.");
					tile_number = 1;
					checkpoint = _tiles.get(tile_number-1);
				}
				else {
					System.out.println("moved to Tile-"+tile_number);
					checkpoint = _tiles.get(tile_number-1);
				}
			}

			else {
				System.out.print("[Roll-"+count+"]"+" rolled at "+dice_roll+" at Tile-"+tile_number+", landed on Tile ");
				tile_number+=dice_roll;
				System.out.println(tile_number);
				try {
					if(tile_number >= _tiles.size()) {
						throw new GameWinnerException("You won the race with"+count+" rolls");
					}
					System.out.println("Trying to shake the Tile-"+tile_number);
					_tiles.get(tile_number-1).shake();
					int penalty = _tiles.get(tile_number-1).getmoves();
						tile_number = tile_number - penalty;
					if(tile_number<=0) {
						System.out.println("moved to Tile 1 as it cant go back further.");
						tile_number = 1;
						checkpoint = _tiles.get(tile_number-1);

					}

					else {
						System.out.println("moved to Tile-"+tile_number);
						checkpoint = _tiles.get(tile_number-1);
					}
				}
				catch(GameWinnerException e) {
					System.out.println(e.getMessage());
					System.out.println("Total Snake Bites="+Snake.bites);
					System.out.println("Total Vulture Bites="+Vulture.bites);
					System.out.println("Total Cricket Bites="+Cricket.bites);
					System.out.println("Total Trampolines="+Trampoline.bites);
				}
				finally {
					count++;
				}
			}

			if(checkpoint == checkpoint_1 || checkpoint == checkpoint_2 || checkpoint == checkpoint_3) {
					BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Press 1 to save the game or 2 to continue...");
					int op = Integer.parseInt(buffer.readLine());
					if(op == 1) {
						Game.saveGame(_u);
						return;
					}
				}
		}
	}
}

class User implements Serializable {

	private User_Game _game;
	private final String _name;
	
	User() throws java.io.IOException,Exception {
		_game = new User_Game(this);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Player Name");
		_name = buffer.readLine();
		System.out.println("Starting the game with "+_name+" at Tile-1");
		System.out.println("Control transferred to Computer transfer to Computer for rolling the Dice for "+_name);
		System.out.println("Hit enter to start the game");
		_game.StartGame();
	}

	User(String name) throws java.io.IOException,Exception {
		_game = new User_Game(this);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println("Enter the Player Name");
		_name = name;
		System.out.println("Starting the game with "+_name+" at Tile-1");
		System.out.println("Control transferred to Computer transfer to Computer for rolling the Dice for "+_name);
		System.out.println("Hit enter to start the game");
		_game.StartGame();
	}

	String getName() {
		return _name;
	}

	void startGame() throws java.io.IOException,Exception {
		_game.StartGame();
	}

}

class Tile implements Serializable {
	
	private int moves;

	Tile(int moves) {
		this.moves = moves;
	}

	int getMoves() {
		return this.moves;
	}
}

class Snake extends Tile implements GenericTile {

	public static int bites = 0;

	Snake(int moves) {
		super(moves);
	}

	@Override
	public int getmoves() {
		return getMoves();
	}

	@Override
	public void shake() throws SnakeBiteException {
		try {
			bites++;
			throw new SnakeBiteException("Hiss...! I am a Snake, you go back "+getmoves()+" tiles");
		}
		catch (SnakeBiteException e) {
			System.out.println(e.getMessage());
		}
	}
}

class Vulture extends Tile implements GenericTile {
	
	public static int bites = 0;

	Vulture(int moves) {
		super(moves);
	}

	@Override
	public int getmoves() {
		return getMoves();
	}

	@Override
	public void shake() throws VultureBiteException {
		
		try {
			bites++;
			throw new VultureBiteException("Yapping...! I am a Vulture, you go back "+getmoves()+" tiles");
		}
		catch(VultureBiteException e) {
			System.out.println(e.getMessage());
		}
	}
}

class Cricket extends Tile implements GenericTile {
	
	public static int bites = 0;

	Cricket(int moves) {
		super(moves);
	}

	@Override
	public int getmoves() {
		return getMoves();
	}

	@Override
	public void shake() throws CricketBiteException {
		try {
			bites++;
			throw new CricketBiteException("Chirp...! I am a Cricket, you go back "+getmoves()+" tiles");
		}

		catch(CricketBiteException e) {
			System.out.println(e.getMessage());
		}
	}
}

class Trampoline extends Tile implements GenericTile {
	
	public static int bites = 0;

	Trampoline(int moves) {
		super(moves);
	}

	@Override
	public int getmoves() {
		return getMoves();
	}

	@Override
	public void shake() throws TrampolineException {
		try {
			bites++;
			throw new TrampolineException("Ping-Pong! I am a Trampoline, you advance "+getmoves()+" tiles");
		}
		catch(TrampolineException e) {
			System.out.println(e.getMessage());
		}
	}
}

class White extends Tile implements GenericTile {
	
	White(int moves) {
		super(moves);
	}

	@Override
	public int getmoves() {
		return getMoves();
	}

	@Override
	public void shake() {
		System.out.println("I am a White tile!");
	}

}

class GameWinnerException extends Exception {
	public GameWinnerException(String message) {
		super(message);
	}
 }

class SnakeBiteException extends Exception {
	public SnakeBiteException(String message) {
		super(message);
	}
}

class CricketBiteException extends Exception {
	public CricketBiteException(String message) {
		super(message);
	}
}

class VultureBiteException extends Exception {
	public VultureBiteException(String message) {
		super(message);
	}
}

class TrampolineException extends Exception {
	public TrampolineException(String message) {
		super(message);
	}
}

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter username");
		String name = buffer.readLine();
		Game game = new Game(name);
	}
}