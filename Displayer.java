import javax.swing.*;
import java.awt.*;

/**
 * The displayer manages the GUI and paints the maze, robot and beepers when
 * needed
 * 
 * @author Hengrui Xing
 */
public class Displayer {
	private Robot robot;
	private Maze maze;
	private JPanel jPanel;
	private JFrame frame;

	public Displayer(Robot robot, Maze maze) {
		this.robot = robot;
		this.maze = maze;
	}

	/**
	 * initialize the GUI displayer (always call before repainting)
	 */
	public void init() {
		jPanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				maze.paint(g);
				robot.paint(g);
			}
		};
		jPanel.setBackground(Color.WHITE);
		jPanel.setPreferredSize(maze.getSize());
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(jPanel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * repaint the GUI
	 */
	public void repaint() {
		jPanel.repaint();
	}

	/**
	 * close the GUI
	 */
	public void shut() {
		if (frame != null) {
			frame.dispose();
			frame = null;
		}
	}

}
