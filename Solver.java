/**
 * The solver gives instructions to an executor to solve the maze using the
 * robot
 * 
 * @author Hengrui Xing
 */
public abstract class Solver {

	protected Executor executor;

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	/**
	 * solve the maze using the executor for one step
	 */
	public abstract void solve();

	/**
	 * iteratively solve the maze by running solve(), terminates after 1000
	 * steps
	 *
	 * @return the number of steps used to solve the maze, 0 if fails to solve
	 *         the maze
	 */
	public int runSolver() {
		int i = 0;
		while (!executor.hasWin() && i < 5000) {
			solve();
			i++;
		}
		if (i == 1000) {
			return 0;
		}
		return i;
	}
}
