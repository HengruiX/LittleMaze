import javax.swing.*;

/**
 * @author Hengrui Xing
 */
public class Main {

	public static void main(String[] args) {
		String message = "please enter the name of maze generator\n";
		String choices = "the choices are";
		for (int i = 0; i < MazeGenerator.choices.length; i++) {
			if (i == MazeGenerator.choices.length - 1) {
				if (MazeGenerator.choices.length > 1) {
					choices += " and " + MazeGenerator.choices[i];
				} else {
					choices += " " + MazeGenerator.choices[i];
				}
			} else {
				choices += " " + MazeGenerator.choices[i] + ",";
			}
		}
		String name = JOptionPane.showInputDialog(null, message + choices);
		while (!contains(MazeGenerator.choices, name)) {
			name = JOptionPane.showInputDialog(null, "please enter the correct name of maze generator\n" + choices);
		}
		String trialsString = JOptionPane.showInputDialog(null, "how many trials do you want?");
		int trials = 0;
		boolean isInt = false;
		while (!isInt) {
			try {
				trials = Integer.parseInt(trialsString);
				isInt = true;
			} catch (Exception e) {
				trialsString = JOptionPane.showInputDialog(null,
						"how many trials do you want?\n(please enter a correct integer)");
			}
		}
		int process = JOptionPane.showConfirmDialog(null, "do you wish to see the process?");
		if (process == JOptionPane.YES_OPTION) {
			Executor.setWAIT(1);
			MazeGenerator.setWAIT(50);
		}
		double avg = Experiment.averageStep("DFSSolver", name + "Generator", trials);
		String result = "it takes our robot ";
		if (trials > 1) {
			result += "on average ";
		}
		result += avg + " steps to finish the maze";
		if (trials > 1) {
			result += "s";
		}
		result += " generated by the " + name + " maze generator";
		JOptionPane.showConfirmDialog(null, result);
	}

	/**
	 * test whether key equals any of the objects in ts
	 *
	 * @param ts
	 *            the array of objects to test
	 * @param key
	 *            the key object to look for
	 * @param <T>
	 *            the type of the array object
	 * @return whether key equals any of the objects in ts
	 */
	private static <T> boolean contains(T[] ts, T key) {
		for (T t : ts) {
			if (t.equals(key)) {
				return true;
			}
		}
		return false;
	}
}
