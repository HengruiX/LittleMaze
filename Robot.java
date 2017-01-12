import com.sun.tools.javac.util.Pair;

import java.awt.*;

/**
 * A robot can navigate a given maze with a number of supported operations
 * 
 * @author Hengrui Xing
 */
public class Robot {
	private int x;
	private int y;
	private Direction direction;

	/**
	 * Constructs a Robot facing east, at location (1,1)
	 */
	@Deprecated
	public Robot() {
		x = 1;
		y = 1;
		direction = Direction.East;
	}

	/**
	 * Constructs a Robot facing east, at location (x,y)
	 *
	 * @param x
	 *            the initial horizontal location of the Robot
	 * @param y
	 *            the initial vertical location of the Robot
	 */
	@Deprecated
	public Robot(int x, int y) {
		this.x = x;
		this.y = y;
		direction = Direction.East;
	}

	/**
	 * Constructs a Robot facing East, at a random location in the maze
	 *
	 * @param maze
	 */
	public Robot(Maze maze) {
		Pair<Integer, Integer> location = maze.getRandomLocation();
		this.x = location.fst;
		this.y = location.snd;
		direction = Direction.East;
	}

	/**
	 * move forward in the Maze in the direction of this Robot
	 *
	 * @param maze
	 *            the Maze in which this Robot runs
	 * @precondition the current location of the Maze is clear, the location of
	 *               the Maze to which this Robot advances is clear
	 */
	public void advance(Maze maze) {
		int next_x = x;
		int next_y = y;
		switch (direction) {
		case East:
			next_x += 1;
			break;
		case West:
			next_x -= 1;
			break;
		case North:
			next_y -= 1;
			break;
		case South:
			next_y += 1;
		}
		if (maze.isClear(x, y, direction)) {
			x = next_x;
			y = next_y;
		}
	}

	/**
	 * test whether the next location of the Maze along this Robot's direction
	 * is clear
	 *
	 * @param maze
	 *            the Maze in which this Robot runs
	 * @return whether the next location of the Maze along this Robot's
	 *         direction is clear
	 */
	public boolean isClear(Maze maze) {
		return maze.isClear(x, y, direction);
	}

	/**
	 * let this Robot turns to its left
	 */
	public void turnLeft() {
		switch (direction) {
		case East:
			direction = Direction.North;
			break;
		case West:
			direction = Direction.South;
			break;
		case North:
			direction = Direction.West;
			break;
		case South:
			direction = Direction.East;
		}
	}

	/**
	 * put a beeper at the current location of the Maze
	 *
	 * @param maze
	 *            the Maze in which this Robot runs
	 * @precondition the current location of the Maze is clear
	 */
	public void putBeeper(Maze maze) {
		maze.addBeeper(x, y);
	}

	/**
	 * collect a beeper at the current location of the Maze
	 *
	 * @param maze
	 *            the Maze in which this Robot runs
	 * @precondition the current location of the Maze is clear, hasBeeper(maze)
	 */
	public void collectBeeper(Maze maze) {
		if (detectBeeper(maze))
			maze.removeBeeper(x, y);
	}

	/**
	 * test whether there is beeper present at the current location of the Maze
	 *
	 * @param maze
	 *            the Maze in which this Robot runs
	 * @return whether there is beeper present at the current location of the
	 *         Maze
	 * @precondition the current location of the Maze is clear
	 */
	public boolean detectBeeper(Maze maze) {
		return !maze.isEmpty(x, y);
	}

	/**
	 * test whether the Robot has moved out of the maze successfully
	 *
	 * @param maze
	 *            the Maze in which this Robot runs
	 * @return whether the Robot has moved out of the maze successfully
	 */
	public boolean checkVictory(Maze maze) {
		return maze.isOut(x, y);
	}

	/**
	 * paint the robot on the graphics context as a red triangle pointing at
	 * this robot's direction
	 *
	 * @param graphics
	 *            the graphics context on which the robot is painted
	 */
	public void paint(Graphics graphics) {
		int[] xPoints = null;
		int[] yPoints = null;
		switch (direction) {
		case East:
			xPoints = new int[] { x * Maze.UNIT + 2, x * Maze.UNIT + 2, x * Maze.UNIT + Maze.UNIT - 2 };
			yPoints = new int[] { y * Maze.UNIT + 8, y * Maze.UNIT + Maze.UNIT - 8, y * Maze.UNIT + Maze.UNIT / 2 };
			break;
		case West:
			xPoints = new int[] { x * Maze.UNIT + Maze.UNIT - 2, x * Maze.UNIT + Maze.UNIT - 2, x * Maze.UNIT + 2 };
			yPoints = new int[] { y * Maze.UNIT + 8, y * Maze.UNIT + Maze.UNIT - 8, y * Maze.UNIT + Maze.UNIT / 2 };
			break;
		case North:
			xPoints = new int[] { x * Maze.UNIT + 8, x * Maze.UNIT + Maze.UNIT - 8, x * Maze.UNIT + Maze.UNIT / 2 };
			yPoints = new int[] { y * Maze.UNIT + Maze.UNIT - 2, y * Maze.UNIT + Maze.UNIT - 2, y * Maze.UNIT + 2 };
			break;
		case South:
			xPoints = new int[] { x * Maze.UNIT + 8, x * Maze.UNIT + Maze.UNIT - 8, x * Maze.UNIT + Maze.UNIT / 2 };
			yPoints = new int[] { y * Maze.UNIT + 2, y * Maze.UNIT + 2, y * Maze.UNIT + Maze.UNIT - 2 };
		}
		graphics.setColor(Color.RED);
		graphics.fillPolygon(xPoints, yPoints, 3);
	}
}
