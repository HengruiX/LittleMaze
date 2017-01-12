/**
 * A solver using depth-first-search derived method
 * 
 * @author Hengrui Xing
 */
public class DFSSolver extends Solver {

	@Override
	public void solve() {
		executor.turnLeft();
		executor.turnLeft();
		executor.advance();
		int last = countBeeper();
		executor.turnLeft();
		executor.turnLeft();
		executor.advance();
		int current;
		if (!executor.detectBeeper()) {
			for (int i = 0; i <= last; i++) {
				executor.putBeeper();
			}
			current = last + 1;
		} else {
			current = last - 1;
		}
		boolean hasUnexplored = false;
		for (int i = 0; i < 4; i++) {
			executor.turnLeft();
			if (executor.isClear() && countNext() == 0) {
				executor.advance();
				hasUnexplored = true;
				break;
			}
		}
		if (!hasUnexplored) {
			for (int i = 0; i < 4; i++) {
				executor.turnLeft();
				if (executor.isClear() && countNext() == current - 1) {
					break;
				}
			}
			executor.advance();
		}

	}

	private int countBeeper() {
		int result = 0;
		while (executor.detectBeeper()) {
			executor.collectBeeper();
			result++;
		}
		for (int i = 0; i < result; i++) {
			executor.putBeeper();
		}
		return result;
	}

	private int countNext() {
		int result;
		executor.advance();
		result = countBeeper();
		executor.turnLeft();
		executor.turnLeft();
		executor.advance();
		executor.turnLeft();
		executor.turnLeft();
		return result;
	}
}
