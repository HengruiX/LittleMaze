
/**
 * A generator using recursive-division derived method
 * 
 * @author Hengrui Xing
 */
public class RDGenerator extends MazeGenerator {

	@Override
	public Cell[][] generate() {
		Cell[][] result = super.generate();
		divideChamber(result, 0, width - 1, 0, length - 1, width >= length, null);
		return result;
	}

	@Override
	public void generate(RepaintManager manager, Cell[][] result) {
		super.generate(manager, result);
		divideChamber(result, 0, width - 1, 0, length - 1, width >= length, null, manager);
	}

	private void divideChamber(Cell[][] result, int i1, int i2, int j1, int j2, boolean horizontal, Boolean isUp) {
		if (!(i1 > i2 || j1 > j2)) {
			if (horizontal) {
				int rand = (int) (Math.random() * (i2 - i1 + 1)) + i1;
				if (j1 < j2) {
					result[rand][j1].removeWall(Direction.East);
					result[rand][j2].removeWall(Direction.West);
				}
				if (isUp != null) {
					if (isUp) {
						result[rand][j1].removeWall(Direction.West);
						result[rand][j1 - 1].removeWall(Direction.East);
					} else {
						result[rand][j2].removeWall(Direction.East);
						result[rand][j2 + 1].removeWall(Direction.West);
					}
				}
				for (int k = j1 + 1; k < j2; k++) {
					result[rand][k].removeWall(Direction.East);
					result[rand][k].removeWall(Direction.West);
				}
				divideChamber(result, i1, rand - 1, j1, j2, !horizontal, false);
				divideChamber(result, rand + 1, i2, j1, j2, !horizontal, true);
			} else {
				int rand = (int) (Math.random() * (j2 - j1 + 1)) + j1;
				if (i1 < i2) {
					result[i1][rand].removeWall(Direction.South);
					result[i2][rand].removeWall(Direction.North);
				}
				if (isUp != null) {
					if (isUp) {
						result[i1][rand].removeWall(Direction.North);
						result[i1 - 1][rand].removeWall(Direction.South);
					} else {
						result[i2][rand].removeWall(Direction.South);
						result[i2 + 1][rand].removeWall(Direction.North);
					}
				}
				for (int k = i1 + 1; k < i2; k++) {
					result[k][rand].removeWall(Direction.South);
					result[k][rand].removeWall(Direction.North);
				}
				divideChamber(result, i1, i2, j1, rand - 1, !horizontal, false);
				divideChamber(result, i1, i2, rand + 1, j2, !horizontal, true);
			}
		}
	}

	private void divideChamber(Cell[][] result, int i1, int i2, int j1, int j2, boolean horizontal, Boolean isUp,
			RepaintManager manager) {
		if (!(i1 > i2 || j1 > j2)) {
			if (horizontal) {
				int rand = (int) (Math.random() * (i2 - i1 + 1)) + i1;
				if (j1 < j2) {
					result[rand][j1].removeWall(Direction.East);
					result[rand][j2].removeWall(Direction.West);
					repaint(manager);
				}
				if (isUp != null) {
					if (isUp) {
						result[rand][j1].removeWall(Direction.West);
						result[rand][j1 - 1].removeWall(Direction.East);
					} else {
						result[rand][j2].removeWall(Direction.East);
						result[rand][j2 + 1].removeWall(Direction.West);
					}
					repaint(manager);
				}
				for (int k = j1 + 1; k < j2; k++) {
					result[rand][k].removeWall(Direction.East);
					result[rand][k].removeWall(Direction.West);
					repaint(manager);
				}
				divideChamber(result, i1, rand - 1, j1, j2, !horizontal, false, manager);
				divideChamber(result, rand + 1, i2, j1, j2, !horizontal, true, manager);
			} else {
				int rand = (int) (Math.random() * (j2 - j1 + 1)) + j1;
				if (i1 < i2) {
					result[i1][rand].removeWall(Direction.South);
					result[i2][rand].removeWall(Direction.North);
					repaint(manager);
				}
				if (isUp != null) {
					if (isUp) {
						result[i1][rand].removeWall(Direction.North);
						result[i1 - 1][rand].removeWall(Direction.South);
					} else {
						result[i2][rand].removeWall(Direction.South);
						result[i2 + 1][rand].removeWall(Direction.North);
					}
					repaint(manager);
				}
				for (int k = i1 + 1; k < i2; k++) {
					result[k][rand].removeWall(Direction.South);
					result[k][rand].removeWall(Direction.North);
					repaint(manager);
				}
				divideChamber(result, i1, i2, j1, rand - 1, !horizontal, false, manager);
				divideChamber(result, i1, i2, rand + 1, j2, !horizontal, true, manager);
			}
		}
	}
}
