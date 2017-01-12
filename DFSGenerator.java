import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A generator using depth-first-search derived method
 * 
 * @author Hengrui Xing
 */
public class DFSGenerator extends MazeGenerator {

    @Override
    public Cell[][] generate() {
        Cell[][] result = super.generate();
        Stack<Integer> iStack = new Stack<>();
        Stack<Integer> jStack = new Stack<>();
        boolean[][] visited = new boolean[width][length];
        return generateCell(visited, 0, 0, iStack, jStack, result);
    }

    @Override
    public void generate(RepaintManager manager, Cell[][] result) {
        super.generate(manager, result);
        Stack<Integer> iStack = new Stack<>();
        Stack<Integer> jStack = new Stack<>();
        boolean[][] visited = new boolean[width][length];
        generateCell(visited, 0, 0, iStack, jStack, result, manager);
    }

    private Cell[][] generateCell(boolean[][] visited, int i, int j, Stack<Integer> iStack, Stack<Integer> jStack, Cell[][] result) {

        boolean allVisited = true;
        for (boolean[] row : visited) {
            for (boolean element : row) {
                if (!element) allVisited = false;
            }
        }

        if (!allVisited) {

            List<Direction> unvisited = new ArrayList<>();
            if (i + 1 < visited.length && !visited[i + 1][j])
                unvisited.add(Direction.South);
            if (i - 1 >= 0 && !visited[i - 1][j])
                unvisited.add(Direction.North);
            if (j + 1 < visited[i].length && !visited[i][j + 1])
                unvisited.add(Direction.East);
            if (j - 1 >= 0 && !visited[i][j - 1])
                unvisited.add(Direction.West);

            if (!visited[i][j]) {
                visited[i][j] = true;
                iStack.push(i);
                jStack.push(j);
            }

            if (unvisited.size() > 0) {
                Direction direction = unvisited.get((int) (Math.random() * unvisited.size()));
                int next_i = i, next_j = j;
                Direction counter = null;
                switch (direction) {
                    case North:
                        next_i = i - 1;
                        counter = Direction.South;
                        break;
                    case South:
                        next_i = i + 1;
                        counter = Direction.North;
                        break;
                    case West:
                        next_j = j - 1;
                        counter = Direction.East;
                        break;
                    case East:
                        next_j = j + 1;
                        counter = Direction.West;
                }
                result[i][j].removeWall(direction);
                result[next_i][next_j].removeWall(counter);
                result = generateCell(visited, next_i, next_j, iStack, jStack, result);
            } else {
                result = generateCell(visited, iStack.pop(), jStack.pop(), iStack, jStack, result);
            }
        }
        return result;

    }

    private void generateCell(boolean[][] visited, int i, int j, Stack<Integer> iStack, Stack<Integer> jStack, Cell[][] result, RepaintManager manager) {

        boolean allVisited = true;
        for (boolean[] row : visited) {
            for (boolean element : row) {
                if (!element) allVisited = false;
            }
        }

        if (!allVisited) {

            List<Direction> unvisited = new ArrayList<>();
            if (i + 1 < visited.length && !visited[i + 1][j])
                unvisited.add(Direction.South);
            if (i - 1 >= 0 && !visited[i - 1][j])
                unvisited.add(Direction.North);
            if (j + 1 < visited[i].length && !visited[i][j + 1])
                unvisited.add(Direction.East);
            if (j - 1 >= 0 && !visited[i][j - 1])
                unvisited.add(Direction.West);

            if (!visited[i][j]) {
                visited[i][j] = true;
                iStack.push(i);
                jStack.push(j);
            }

            if (unvisited.size() > 0) {
                Direction direction = unvisited.get((int) (Math.random() * unvisited.size()));
                int next_i = i, next_j = j;
                Direction counter = null;
                switch (direction) {
                    case North:
                        next_i = i - 1;
                        counter = Direction.South;
                        break;
                    case South:
                        next_i = i + 1;
                        counter = Direction.North;
                        break;
                    case West:
                        next_j = j - 1;
                        counter = Direction.East;
                        break;
                    case East:
                        next_j = j + 1;
                        counter = Direction.West;
                }
                result[i][j].removeWall(direction);
                result[next_i][next_j].removeWall(counter);
                repaint(manager);
                generateCell(visited, next_i, next_j, iStack, jStack, result, manager);
            } else {
                repaint(manager);
                generateCell(visited, iStack.pop(), jStack.pop(), iStack, jStack, result, manager);
            }
        }

    }
}

