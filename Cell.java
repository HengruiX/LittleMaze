import java.util.HashSet;
import java.util.Set;

/**
 * A cell is an elementary component of the maze structure which has a specified
 * location and maintains a set of walls and some number of beepers
 * 
 * @author Hengrui Xing
 */
public class Cell {
	private int x;
	private int y;
	private int beeper;
	private Set<Direction> walls;

	/**
	 * Constructs a cell in the maze with the specified location and with walls
	 * on all directions
	 *
	 * @param x
	 *            the x location of the cell
	 * @param y
	 *            the y location of the cell
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		beeper = 0;
		walls = new HashSet<>(4);
		walls.add(Direction.East);
		walls.add(Direction.North);
		walls.add(Direction.West);
		walls.add(Direction.South);
	}

	/**
	 * @param direction
	 *            the direction to test for clearance
	 * @return whether the specified direction of the cell is clear, i.e.,
	 *         without a wall
	 */
	public boolean isClear(Direction direction) {
		return !walls.contains(direction);
	}

	/**
	 * removes the wall on the specified direction
	 *
	 * @param direction
	 *            the direction to remove the wall
	 */
	public void removeWall(Direction direction) {
		walls.remove(direction);
	}

	/**
	 * add a beeper the cell
	 */
	public void addBeeper() {
		beeper++;
	}

	/**
	 * remove a beeper from the cell
	 */
	public void removeBeeper() {
		if (beeper > 0) {
			beeper--;
		}
	}

	/**
	 * @return whether there is a beeper in the cell
	 */
	public boolean hasBeeper() {
		return beeper > 0;
	}

	/**
	 * Returns the set of walls that this cell maintains (for drawing purpose)
	 *
	 * @return the set of walls that this cell maintains
	 */
	public Set<Direction> getWalls() {
		return walls;
	}

	/**
	 * @return the number of beepers in the cell as a String object
	 */
	@Override
	public String toString() {
		return Integer.toString(beeper);
	}
}