import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * A Runner is a Runnable that use a thread to generate a maze and solve it
 * 
 * @author Hengrui Xing
 */
public class Runner implements Runnable {

	private List<Integer> result;
	private CyclicBarrier barrier;
	private Solver solver;
	private MazeGenerator generator;

	/**
	 * Constructs a Runner Object
	 *
	 * @param result
	 *            the list of steps for solving each maze
	 * @param barrier
	 *            the CyclicBarrier to notify when the maze solving is done
	 * @param solverName
	 *            the name of the solver that is being used
	 * @param generatorName
	 *            the name of the generator that is being used
	 */
	public Runner(List<Integer> result, CyclicBarrier barrier, String solverName, String generatorName) {
		this.result = result;
		this.barrier = barrier;
		try {
			solver = (Solver) Class.forName(solverName).newInstance();
			generator = (MazeGenerator) Class.forName(generatorName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * run the maze solving using the specified solver and maze generator, add
	 * the number of steps needed to complete in the result list
	 */
	@Override
	public void run() {
		Executor executor = new Executor();
		solver.setExecutor(executor);
		executor.init(generator);
		int i = solver.runSolver();
		result.add(i);
		executor.close();
		try {
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
