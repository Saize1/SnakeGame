
import java.util.*;

import static java.lang.System.exit;

public class Game {

    public static final int DIRECTION_NONE = 0, DIRECTION_RIGHT = 1,
            DIRECTION_LEFT = -1, DIRECTION_UP = 2, DIRECTION_DOWN = -2;
    private Snake snake;
    private Board board;
    private int direction;
    private boolean gameOver;

    private int score = 0;

    public Game(Snake snake, Board board)
    {
        this.snake = snake;
        this.board = board;
    }

    public Snake getSnake()
    {
        return snake;
    }

    public void setSnake(Snake snake)
    {
        this.snake = snake;
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    // We need to update the game at regular intervals,
    // and accept user input from the Keyboard.
    public void update()
    {
        System.out.println("Going to update the game");
        if (!gameOver) {
            if (direction != DIRECTION_NONE) {
                Cell nextCell = getNextCell(snake.getHead());

                if (snake.checkCrash(nextCell)) {
                    setDirection(DIRECTION_NONE);
                    gameOver = true;
                }
                else {
                    snake.move(nextCell);
                    if (nextCell.getCellType() == "FOOD") {
                        snake.grow();
                        score ++;
                        System.out.println("Score = " + score);
                        board.generateFood();
                    }
                }
            }
        }
    }

    private Cell getNextCell(Cell currentPosition)
    {
        System.out.println("Going to find next cell");
        int row = currentPosition.getRow();
        int col = currentPosition.getCol();

        if (direction == DIRECTION_RIGHT) {
            col++;
        }
        else if (direction == DIRECTION_LEFT) {
            col--;
        }
        else if (direction == DIRECTION_UP) {
            row--;
        }
        else if (direction == DIRECTION_DOWN) {
            row++;
        }

        if((row < 0 || row > 10)  || (col < 0 || col > 10)){
            gameOver = false;
            System.out.println("Outside the board --------- Crashed");
            exit(0);
        }

        Cell nextCell = board.getCells()[row][col];

        return nextCell;
    }

    public static void main(String[] args)
    {

        System.out.println("Going to start game");

        Cell initPos = new Cell(0, 0);
        Snake initSnake = new Snake(initPos);
        Board board = new Board(10, 10);
        Game newGame = new Game(initSnake, board);
        newGame.gameOver = false;
        newGame.direction = DIRECTION_RIGHT;

        // We need to update the game at regular intervals,
        // and accept user input from the Keyboard.

        // here I have just called the different methods
        // to show the functionality

        Scanner sc  = new Scanner(System.in);
        newGame.board.generateFood();

        while(true){
            int dir = sc.nextInt();
            if(dir == -1){
                newGame.direction = DIRECTION_LEFT;
            }
            else if(dir == 1){
                newGame.direction = DIRECTION_RIGHT;
            }

            else if(dir == -2){
                newGame.direction = DIRECTION_DOWN;
            }
            else if(dir == 2){
                newGame.direction = DIRECTION_UP;
            }

            else{
                System.out.println("Invalid input ----------- Please Enter Correct Input");
                dir = sc.nextInt();
            }
            newGame.update();
            if (newGame.gameOver == true)
                break;
        }
    }
}