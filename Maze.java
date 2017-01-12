import com.sun.tools.javac.util.Pair;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Set;

/**
 * A maze maintains a configuration of cells and form a navigable rectangle
 * 
 * @author Hengrui Xing
 */
public class Maze {

	private Cell[][] config;
	public static final int UNIT = 25;
	public static final int BEEPER_RADIUS = 10;
	public static final int MAZE_LENGTH = 30;
	public static final int MAZE_WIDTH = 20;
	public static final int EXIT_X = 0;
	public static final int EXIT_Y = 0;

	/**
	 * constructs an empty maze
	 */
	public Maze() {
	}

	/**
	 * generate the configuration of the maze by the specified generator
	 *
	 * @param generator
	 *            the maze generator responsible for generating maze
	 *            configuration
	 */
	public void generateMaze(MazeGenerator generator) {
		generator.setFeatures(MAZE_LENGTH, MAZE_WIDTH, EXIT_X, EXIT_Y);
		config = generator.generate();
	}

	/**
	 * generate the configuration of the maze by the specified generator,
	 * repaints the GUI for every step the generator takes
	 *
	 * @param generator
	 *            the maze generator responsible for generating maze
	 *            configuration
	 * @param repaintManager
	 *            the repaint manager responsible for repainting the GUI
	 */
	public void generateMaze(MazeGenerator generator, RepaintManager repaintManager) {
		generator.setFeatures(MAZE_LENGTH, MAZE_WIDTH, EXIT_X, EXIT_Y);
		config = new Cell[MAZE_WIDTH][MAZE_LENGTH];
		for (int i = 0; i < MAZE_WIDTH; i++) {
			for (int j = 0; j < MAZE_LENGTH; j++) {
				config[i][j] = new Cell(j, i);
			}
		}
		if (EXIT_X == 0) {
			config[EXIT_Y][EXIT_X].removeWall(Direction.West);
		} else if (EXIT_X == MAZE_LENGTH - 1) {
			config[EXIT_Y][EXIT_X].removeWall(Direction.East);
		} else if (EXIT_Y == 0) {
			config[EXIT_Y][EXIT_X].removeWall(Direction.North);
		} else if (EXIT_Y == MAZE_WIDTH - 1) {
			config[EXIT_Y][EXIT_X].removeWall(Direction.South);
		}
		generator.generate(repaintManager, config);
	}

	/**
	 * Select a random location in the maze
	 *
	 * @return a pair of integers specifying the x and y location respectively
	 *         of a random location
	 */
	public Pair<Integer, Integer> getRandomLocation() {
		int x = config[0].length;
		int y = config.length;
		return new Pair<>((int) (Math.random() * x), (int) (Math.random() * y));
	}

	/**
	 * @return a Dimension Object the specifies the size of the maze
	 */
	public Dimension getSize() {
		int x = 0;
		int y = config.length * UNIT;
		for (Cell[] row : config) {
			if (row.length > x)
				x = row.length;
		}
		x *= UNIT;
		return new Dimension(x, y);
	}

	/**
	 * Add a beeper in the specified location
	 *
	 * @param x
	 *            the x location to add beeper
	 * @param y
	 *            the y location to add beeper
	 */
	public void addBeeper(int x, int y) {
		config[y][x].addBeeper();
	}

	/**
	 * Remove a beeper in the specified location
	 *
	 * @param x
	 *            the x location to remove beeper
	 * @param y
	 *            the y location to remove beeper
	 */
	public void removeBeeper(int x, int y) {
		config[y][x].removeBeeper();
	}

	/**
	 * Test whether the specified location is empty (of beepers)
	 *
	 * @param x
	 *            the x location to test
	 * @param y
	 *            the y location to test
	 * @return whether the specified location is empty (of beepers)
	 */
	public boolean isEmpty(int x, int y) {
		if (y >= 0 && y < config.length && x >= 0 && x < config[y].length) {
			return !config[y][x].hasBeeper();
		} else
			return true;
	}

	/**
	 * Test whether a specified location is out of the range of the maze
	 *
	 * @param x
	 *            the x location to test
	 * @param y
	 *            the y location to test
	 * @return whether a specified location is out of the range of the maze
	 */
	public boolean isOut(int x, int y) {
		if (y >= 0 && y < config.length && x >= 0 && x < config[y].length) {
			return false;
		} else
			return true;
	}

	/**
	 * Test whether the specified direction of the specified location is clear
	 *
	 * @param x
	 *            the x location to test
	 * @param y
	 *            the y location to test
	 * @param direction
	 *            the y location to test
	 * @return whether the specified direction of the specified location is
	 *         clear
	 */
	public boolean isClear(int x, int y, Direction direction) {
		if (y >= 0 && y < config.length && x >= 0 && x < config[y].length) {
			return config[y][x].isClear(direction);
		}
		return true;
	}

	/**
	 * paint the maze configuration on the GUI graphics context
	 *
	 * @param graphics
	 *            the graphics context to paint on
	 */
	public synchronized void paint(Graphics graphics) {
		for (int i = 0; i < config.length; i++) {
			for (int j = 0; j < config[i].length; j++) {
				if (config[i][j].hasBeeper()) {
					graphics.setColor(Color.orange);
					graphics.fillOval(j * UNIT + UNIT / 2 - BEEPER_RADIUS, i * UNIT + UNIT / 2 - BEEPER_RADIUS,
							2 * BEEPER_RADIUS, 2 * BEEPER_RADIUS);
					graphics.setColor(Color.BLACK);
					Font font = graphics.getFont();
					Graphics2D g2 = (Graphics2D) graphics;
					FontRenderContext context = g2.getFontRenderContext();
					Rectangle2D bounds = font.getStringBounds(config[i][j].toString(), context);
					int width = (int) bounds.getWidth();
					int height = (int) bounds.getHeight();
					int descent = height + (int) bounds.getY();
					int x = j * UNIT + UNIT / 2 - BEEPER_RADIUS + UNIT / 2 - width / 2;
					int y = i * UNIT + UNIT / 2 - BEEPER_RADIUS + UNIT / 2 - descent + height / 2;
					graphics.drawString(config[i][j].toString(), x, y);
				}
				Set<Direction> walls = config[i][j].getWalls();
				int x1 = j * UNIT;
				int x2 = j * UNIT + UNIT;
				int y1 = i * UNIT;
				int y2 = i * UNIT + UNIT;
				graphics.setColor(Color.BLACK);
				for (Direction direction : walls) {
					switch (direction) {
					case North:
						graphics.drawLine(x1, y1, x2, y1);
						break;
					case South:
						graphics.drawLine(x1, y2, x2, y2);
						break;
					case West:
						graphics.drawLine(x1, y1, x1, y2);
						break;
					case East:
						graphics.drawLine(x2, y1, x2, y2);
					}
				}
				if (walls.size() == 4) {
					graphics.fillRect(x1, y1, UNIT, UNIT);
				}
			}
		}
	}

}
