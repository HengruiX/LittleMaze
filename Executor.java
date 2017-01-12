/**
 * The Executor executes instructions from a solver and pass the instructions to the robot
 * <p>
 * Created by henryxing on 2/6/2015.
 */
public class Executor {

    private Robot robot;
    private Maze maze;
    private Displayer displayer;
    private static int WAIT = 0;

    /**
     * set the waiting time for each step of the robot
     *
     * @param WAIT
     */
    public static void setWAIT(int WAIT) {
        Executor.WAIT = WAIT;
    }

    /**
     * initialize the maze configuration using the specific type of generator
     *
     * @param generator the generator used to generate a maze configuration
     */
    public void init(MazeGenerator generator) {
        maze = new Maze();
        maze.generateMaze(generator);
        robot = new Robot(maze);
        displayer = new Displayer(robot, maze);
        displayer.init();
        maze.generateMaze(generator, new RepaintManager(displayer));
    }

    /**
     * Move the robot forward in the maze
     */
    public void advance() {
        try {
            Thread.sleep(WAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.advance(maze);
        displayer.repaint();
    }

    /**
     * test whether the robot has successfully got out of the maze
     *
     * @return whether the robot has successfully got out of the maze
     */
    public boolean hasWin() {
        return robot.checkVictory(maze);
    }

    /**
     * turn the robot to it's left
     */
    public void turnLeft() {
        try {
            Thread.sleep(WAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.turnLeft();
        displayer.repaint();
    }

    /**
     * test whether the direction where the robot is facing is clear
     *
     * @return whether the direction where the robot is facing is clear
     */
    public boolean isClear() {
        return robot.isClear(maze);
    }

    /**
     * make the robot put a beeper at its current location
     */
    public void putBeeper() {
        robot.putBeeper(maze);
        displayer.repaint();
    }

    /**
     * make the robot collect a beeper at its current location
     */
    public void collectBeeper() {
        robot.collectBeeper(maze);
        displayer.repaint();
    }

    /**
     * Test whether there are beepers at the robot's current location
     *
     * @return whether there are beepers at the robot's current location
     */
    public boolean detectBeeper() {
        return robot.detectBeeper(maze);
    }

    /**
     * shut down the displayer
     */
    public void close() {
        displayer.shut();
    }
}
