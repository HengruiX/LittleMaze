import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A generator using Randomized Kruskal's algorithm derived method
 * 
 * @author Hengrui Xing
 */
public class RKGenerator extends MazeGenerator {

    @Override
    public Cell[][] generate() {
        Cell[][] result = super.generate();
        int[][] ids = new int[width][length];
        List<Wall> walls = new LinkedList<>();
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                ids[i][j] = k;
                if (j != length - 1) {
                    walls.add(new Wall(i, j, Direction.East));
                }
                if (i != width - 1) {
                    walls.add(new Wall(i, j, Direction.South));
                }
                k++;
            }
        }
        Collections.shuffle(walls);
        for (Wall wall : walls) {
            int current = ids[wall.i][wall.j];
            int neighbor_i = wall.i;
            int neighbor_j = wall.j;
            switch (wall.direction) {
                case East:
                    neighbor_j++;
                    break;
                case South:
                    neighbor_i++;
            }
            int neighbor = ids[neighbor_i][neighbor_j];
            if (current != neighbor) {
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < length; j++) {
                        if (ids[i][j] == neighbor) {
                            ids[i][j] = current;
                        }
                    }
                }
                result[wall.i][wall.j].removeWall(wall.direction);
                result[neighbor_i][neighbor_j].removeWall(getCounter(wall.direction));
            }
        }
        return result;

    }

    @Override
    public void generate(RepaintManager manager, Cell[][] result) {
        super.generate(manager, result);
        int[][] ids = new int[width][length];
        List<Wall> walls = new LinkedList<>();
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                ids[i][j] = k;
                if (j != length - 1) {
                    walls.add(new Wall(i, j, Direction.East));
                }
                if (i != width - 1) {
                    walls.add(new Wall(i, j, Direction.South));
                }
                k++;
            }
        }
        Collections.shuffle(walls);
        for (Wall wall : walls) {
            int current = ids[wall.i][wall.j];
            int neighbor_i = wall.i;
            int neighbor_j = wall.j;
            switch (wall.direction) {
                case East:
                    neighbor_j++;
                    break;
                case South:
                    neighbor_i++;
            }
            int neighbor = ids[neighbor_i][neighbor_j];
            if (current != neighbor) {
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < length; j++) {
                        if (ids[i][j] == neighbor) {
                            ids[i][j] = current;
                        }
                    }
                }
                result[wall.i][wall.j].removeWall(wall.direction);
                result[neighbor_i][neighbor_j].removeWall(getCounter(wall.direction));
            }

            repaint(manager);
        }

    }

    private Direction getCounter(Direction direction) {
        Direction result = null;
        switch (direction) {
            case North:
                result = Direction.South;
                break;
            case South:
                result = Direction.North;
                break;
            case West:
                result = Direction.East;
                break;
            case East:
                result = Direction.West;
        }
        return result;
    }

    private class Wall {
        private int i;
        private int j;
        private Direction direction;

        public Wall(int i, int j, Direction direction) {
            this.i = i;
            this.j = j;
            this.direction = direction;
        }
    }
}
