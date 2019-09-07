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

	Game() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter total number of tiles on the race track(length)");
		int n = Integer.parseInt(buffer.readLine());
		_tiles = new ArrayList<GenericTile>(n);
		System.out.println("Setting up the race track...");
		
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
		_trampolines_moves = 1+random.nextInt(10);
		_white_moves = 0;

		for(int i=0;i<n;i++) {
			_tiles.add(null);
		}

		random_genrator(n,_snakes,"snakes");
		random_genrator(n,_crickets,"crickets");
		random_genrator(n,_vultures,"vultures");
		random_genrator(n,_trampolines,"trampolines");
		random_genrator(n,_whites,"whites");
		System.out.println("Danger : There are "+_snakes+", "+_crickets+", "+_vultures+" numbers of Snakes, Cricket and Vultures respectively on your track!");
		System.out.println("Danger : Each Snake, Cricket and Vultures can throw you back by "+_snakes_moves+", "+_crickets_moves+", "+_vultures_moves+" number of Tiles respectively!");
		System.out.println("Good news : There are "+_trampolines+" number of Trampolines on your track!");
		System.out.println("Good news : Each Trampoline can help you advance by "+_trampolines_moves+" number of Tiles");

		EnterPlayer();
	}

	void random_genrator(int n,int _obstacles,String str) {
		Random random = new Random();
		int k = _obstacles;
		while(k>0) {
			for(int i=0;i<n;i++) {
				if(_tiles.get(i) == null) {
					int x = random.nextInt(2);
					if(x == 1) {
						if(str.equals("snakes")) {
							_tiles.add(new Snake(_snakes_moves));
						}
						else if(str.equals("crickets")) {
							_tiles.add(new Cricket(_crickets_moves));
						}
						else if(str.equals("vultures")) {
							_tiles.add(new Vulture(_vultures_moves));
						}
						else if(str.equals("trampolines")) {
							_tiles.add(new Trampoline(_trampolines_moves));
						}
						else if(str.equals("whites")) {
							_tiles.add(new White(_white_moves));
						}
						k--;
					}
				}
			}
		}
	}

	void EnterPlayer() throws java.io.IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Player Name");
		_name = buffer.readLine();
		System.out.println("Starting the game with "+_name+" at Tile-1");
		System.out.println("Control transferred to Computer transfer to Computer for rolling the Dice for "+_name);
		System.out.println("Hit enter to start the game");
		String n = buffer.readLine();
		StartGame();
	}

	void StartGame() {
		Random random = new Random();
		int tile_number = 1;
		int count = 1;
		while(tile_number != _tiles.size()) {
			int dice_roll = 1+random.nextInt(6);
			if(tile_number == 1 && dice_roll != 6) {
				System.out.println("[Roll-"+count+"] "+_name+" rolled at "+dice_roll+" at Tile-1, OOPs you need 6 to start");
			}

			else if(tile_number == 1 && dice_roll == 6) {
				System.out.println("[Roll-"+count+"] "+_name+" rolled at "+dice_roll+" at Tile-1. You are out of the cage! You get a free roll.");
				dice_roll = 1+random.nextInt();
				count++;
				System.out.print("[Roll-"+count+"] "+_name+" rolled at "+dice_roll+" at Tile-"+tile_number+", landed on Tile ");
				tile_number+=dice_roll;
				System.out.print(tile_number);
				System.out.println("Trying to shake the Tile-"+tile_number);
				_tiles.get(tile_number-1).shake();			
			}

			else {
				System.out.print("[Roll-"+count+"] "+_name+" rolled at "+dice_roll+" at Tile-"+tile_number+", landed on Tile ");
				tile_number+=dice_roll;
				System.out.print(tile_number);
				System.out.println("Trying to shake the Tile-"+tile_number);
				_tiles.get(tile_number-1).shake();
			}

			count++;
		}
	}
}

class Tile {
	
	private int moves;

	Tile(int moves) {
		this.moves = moves;
	}
}

class Snake extends Tile implements GenericTile {

	Snake(int moves) {
		super(moves);
	}

	public void shake() {

	}
}

class Vulture extends Tile implements GenericTile {
	
	Vulture(int moves) {
		super(moves);
	}

	public void shake() {

	}
}

class Cricket extends Tile implements GenericTile {
	
	Cricket(int moves) {
		super(moves);
	}

	public void shake() {

	}
}

class Trampoline extends Tile implements GenericTile {
	
	Trampoline(int moves) {
		super(moves);
	}

	public void shake() {

	}
}

class White extends Tile implements GenericTile {
	
	White(int moves) {
		super(moves);
	}

	public void shake() {

	}
}

class Main {
	public static void main(String[] args) throws java.io.IOException {
		Game game = new Game();
	}
}