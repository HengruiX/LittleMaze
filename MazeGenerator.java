/**
 * MazeGenerator generates a maze with some specified variables
 * 
 * @author Hengrui Xing
 */
public abstract class MazeGenerator {

	protected int length;
	protected int width;
	protected int exit_x;
	protected int exit_y;
	private static int WAIT = 0;
	public static final String[] choices = { "DFS", "RD", "RK" };

	/**
	 * set the waiting time before each step of maze generation
	 *
	 * @param WAIT
	 */
	public static void setWAIT(int WAIT) {
		MazeGenerator.WAIT = WAIT;
	}

	/**
	 * set the various feature variables for the generator
	 *
	 * @param length
	 *            the length of the maze to generate
	 * @param width
	 *            the width of the maze to generate
	 * @param exit_x
	 *            the x location of the cell which maintains the exit of the
	 *            maze
	 * @param exit_y
	 *            the y location of the cell which maintains the exit of the
	 *            maze
	 */
	public void setFeatures(int length, int width, int exit_x, int exit_y) {
		this.length = length;
		this.width = width;
		this.exit_x = exit_x;
		this.exit_y = exit_y;
	}

	/**
	 * generate a maze
	 *
	 * @return a configuration of maze
	 */
	public Cell[][] generate() {
		Cell[][] result = new Cell[width][length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				result[i][j] = new Cell(j, i);
			}
		}
		if (exit_x == 0) {
			result[exit_y][exit_x].removeWall(Direction.West);
		} else if (exit_x == length - 1) {
			result[exit_y][exit_x].removeWall(Direction.East);
		} else if (exit_y == 0) {
			result[exit_y][exit_x].removeWall(Direction.North);
		} else if (exit_y == width - 1) {
			result[exit_y][exit_x].removeWall(Direction.South);
		}
		return result;
	}

	/**
	 * generate a maze and repaint the GUI at every step of generation
	 *
	 * @param manager
	 *            the repaint manager to repaint the GUI
	 * @param result
	 *            the maze configuration for generation
	 */
	public void generate(RepaintManager manager, Cell[][] result) {
	}

	/**
	 * repiant the GUI
	 *
	 * @param manager
	 *            the repaint manager to repaint the GUI
	 */
	public void repaint(RepaintManager manager) {
		manager.repaint();
		try {
			Thread.sleep(WAIT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
