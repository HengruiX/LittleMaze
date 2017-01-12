/**
 * The Repaint Manager maintains the current displayer of the GUI and repaints
 * the GUI when needed
 * 
 * @author Hengrui Xing
 */
public class RepaintManager {

	private Displayer displayer;

	public RepaintManager(Displayer displayer) {
		this.displayer = displayer;
	}

	/**
	 * repaints the GUI
	 */
	public void repaint() {
		displayer.repaint();
	}
}
