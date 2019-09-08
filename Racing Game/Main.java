import java.util.*;
import java.io.*;

class Game {

	private ArrayList<GenericTile> _tiles;
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
	private String _name;

	Game() throws Exception {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		Boolean done = false;
		int n = 0;
		while(!done){
			try {
				System.out.println("Enter total number of tiles on the race track(length)");
				n = Integer.parseInt(buffer.readLine());
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
		EnterPlayer();
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

	void EnterPlayer() throws Exception {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Player Name");
		_name = buffer.readLine();
		System.out.println("Starting the game with "+_name+" at Tile-1");
		System.out.println("Control transferred to Computer transfer to Computer for rolling the Dice for "+_name);
		System.out.println("Hit enter to start the game");
		String n = buffer.readLine();
		StartGame();
	}

	void StartGame() throws Exception {
		Random random = new Random();
		int tile_number = 1;
		int count = 1;
		while(tile_number < _tiles.size()) {
			int dice_roll = 1+random.nextInt(6);
			if(tile_number == 1 && dice_roll != 6) {
				System.out.println("[Roll-"+count+"] "+_name+" rolled at "+dice_roll+" at Tile-1, OOPs you need 6 to start");
			}

			else if(tile_number == 1 && dice_roll == 6) {
				System.out.println("[Roll-"+count+"] "+_name+" rolled at "+dice_roll+" at Tile-1. You are out of the cage! You get a free roll.");
				dice_roll = 1+random.nextInt(6);
				count++;
				System.out.print("[Roll-"+count+"] "+_name+" rolled at "+dice_roll+" at Tile-"+tile_number+", landed on Tile ");
				tile_number+=dice_roll;
				System.out.println(tile_number);
				System.out.println("Trying to shake the Tile-"+tile_number);
				_tiles.get(tile_number-1).shake();
				int penalty = _tiles.get(tile_number-1).getmoves();
				tile_number=tile_number - penalty;
				if(tile_number<=0) {
					System.out.println(_name+" moved to Tile 1 as it cant go back further.");
					tile_number = 1;
				}
				else {
					System.out.println(_name+" moved to Tile-"+tile_number);
				}

			}

			else {
				System.out.print("[Roll-"+count+"] "+_name+" rolled at "+dice_roll+" at Tile-"+tile_number+", landed on Tile ");
				tile_number+=dice_roll;
				System.out.println(tile_number);
				try {
					if(tile_number >= _tiles.size()) {
						throw new GameWinnerException(_name+" wins the race "+count+" rolls");
					}
					System.out.println("Trying to shake the Tile-"+tile_number);
					_tiles.get(tile_number-1).shake();
					int penalty = _tiles.get(tile_number-1).getmoves();
					tile_number = tile_number - penalty;
					if(tile_number<=0) {
						System.out.println(_name+" moved to Tile 1 as it cant go back further.");
						tile_number = 1;
					}
					else {
						System.out.println(_name+" moved to Tile-"+tile_number);
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
		}
	}
}

class Tile {
	
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
		Game game = new Game();
	}
}