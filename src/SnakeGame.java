import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.util.Stack;

public class SnakeGame extends GameEngine {
    private GameState state = GameState.MENU;
    private Snake player1, player2;
    private Point food;
    private boolean[] keys = new boolean[256];
    private Random rand = new Random();
    private Image headRed, bodyGreen, appleImage;
    public static final int CELL_SIZE = 20;
    public static final int GRID_SIZE = 45;
    public SnakeGame(){
        // Create graphics transform stack
        mTransforms = new Stack<AffineTransform>();

        // Create window
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create the window
                setupWindow(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
            }
        });
    }
    @Override
    public void init() {
        headRed = loadImage("resources/head.png");
        bodyGreen = loadImage("resources/dot.png");
        appleImage = loadImage("resources/apple.png");
    }

    private void spawnFood() {
        do {
            food = new Point(rand.nextInt(GRID_SIZE), rand.nextInt(GRID_SIZE));
        } while (isPointOccupied(food));
    }

    private boolean isPointOccupied(Point p) {
        if (state == GameState.PLAYING_SINGLE) {
            return player1.body.contains(p);
        } else if (state == GameState.PLAYING_DOUBLE) {
            return player1.body.contains(p) || player2.body.contains(p);
        }
        return false;
    }

    @Override
    public void update(double dt) {
        if (state == GameState.PLAYING_SINGLE) {
            handlePlayerInput(player1);
            player1.move();
            checkFoodCollision(player1);
            if (player1.checkCollision()) {
                state = GameState.GAME_OVER_SINGLE;
            }
        } else if (state == GameState.PLAYING_DOUBLE) {
            handlePlayerInput(player1);
            handlePlayer2Input(player2);
            player1.move();
            player2.move();
            checkFoodCollision(player1);
            checkFoodCollision(player2);
            if (player1.checkCollision() || player2.checkCollision() || checkPlayerCollision()) {
                state = GameState.GAME_OVER_DOUBLE;
            }
        }
    }

    private boolean checkPlayerCollision() {
        return player1.body.subList(1, player1.body.size()).contains(player2.getHead()) || player2.body.subList(1, player2.body.size()).contains(player1.getHead());
    }

    private void checkFoodCollision(Snake player) {
        if (player.getHead().x == food.x && player.getHead().y == food.y) {
            player.length++;
            spawnFood();
        }
    }

    private void handlePlayerInput(Snake player) {
        if (keys['W'] && player.direction != 1) {
            player.direction = 0;
            player.isMoving = true;
        }
        if (keys['S'] && player.direction != 0) {
            player.direction = 1;
            player.isMoving = true;
        }
        if (keys['A'] && player.direction != 3) {
            player.direction = 2;
            player.isMoving = true;
        }
        if (keys['D'] && player.direction != 2) {
            player.direction = 3;
            player.isMoving = true;
        }
    }
    private void handlePlayer2Input(Snake player) {
        if (keys[KeyEvent.VK_UP] && player.direction != 1) {
            player.direction = 0;
            player.isMoving = true;
        }
        if (keys[KeyEvent.VK_DOWN] && player.direction != 0) {
            player.direction = 1;
            player.isMoving = true;
        }
        if (keys[KeyEvent.VK_LEFT] && player.direction != 3) {
            player.direction = 2;
            player.isMoving = true;
        }
        if (keys[KeyEvent.VK_RIGHT] && player.direction != 2) {
            player.direction = 3;
            player.isMoving = true;
        }
    }

    private boolean keyPressed(int keyCode) {
        return keys[keyCode];
    }

    @Override
    public void paintComponent() {
        changeBackgroundColor(Color.BLACK);
        clearBackground(width(), height());

        switch (state) {
            case MENU:
                drawMenu();
                break;
            case PLAYING_SINGLE:
                drawSingleGame();
                break;
            case PLAYING_DOUBLE:
                drawDoubleGame();
                break;
            case GAME_OVER_SINGLE, GAME_OVER_DOUBLE:
                drawGameOver();
                break;
        }
    }

    private void drawSingleGame() {
        drawSnake(player1);
        drawImage(appleImage, food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
    private void drawDoubleGame() {
        drawSnake(player1);
        drawSnake(player2);
        drawImage(appleImage, food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }


    private void drawSnake(Snake snake) {
        for (int i = 0; i < snake.body.size(); i++) {
            Point p = snake.body.get(i);
            Image img = (i == 0) ? snake.headImage : snake.bodyImage;
            drawImage(img, p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    private void drawMenu() {
        drawBoldText(width() / 2 - 100, height() / 2 - 50, "Snake game", "Arial", 30);
        drawText(width() / 2 - 180, height() / 2, "Press '1' to start the single-player game", "Arial", 20);
        drawText(width() / 2 - 180, height() / 2 + 30, "Press '2' to start the two-player game", "Arial", 20);
        drawText(width() / 2 - 150, height() / 2 + 80, "Press 'Q' to exit the game", "Arial", 20);
    }

    private void drawGameOver() {
        drawBoldText(width() / 2 - 100, height() / 2 - 50, "Game Over!", "Arial", 40);
        if (state == GameState.GAME_OVER_SINGLE) {
            drawText(width() / 2 - 60, height() / 2, "Player score: " + (player1.getLength()-3), "Arial", 20);
        } else if (state == GameState.GAME_OVER_DOUBLE) {
            drawText(width() / 2 - 60, height() / 2, "Player 1 score: " + (player1.getLength()-3), "Arial", 20);
            drawText(width() / 2 - 60, height() / 2 + 30, "Player 2 score: " + (player2.getLength()-3), "Arial", 20);
        }
        drawText(width() / 2 - 80, height() / 2 + 80, "Press 'R' to start over", "Arial", 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keys[key] = true;
        if (state == GameState.MENU) {
            if (key == KeyEvent.VK_1) {
                state = GameState.PLAYING_SINGLE;
                player1 = new Snake(headRed, bodyGreen, 15, 15);
                spawnFood();
            } else if (key == KeyEvent.VK_2) {
                state = GameState.PLAYING_DOUBLE;
                player1 = new Snake(headRed, bodyGreen, 15, 15);
                player2 = new Snake(bodyGreen, headRed, 15, 17);
                spawnFood();
            } else if (key == KeyEvent.VK_Q) {
                System.exit(0);
            }
        } else if ((state == GameState.GAME_OVER_SINGLE || state == GameState.GAME_OVER_DOUBLE) &&  key == KeyEvent.VK_R) {
            resetGame();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keys[key] = false;
    }

    private void resetGame() {
        if (state == GameState.PLAYING_SINGLE) {
            player1 = new Snake(headRed, bodyGreen, 10, 10);
            spawnFood();
        } else if (state == GameState.PLAYING_DOUBLE) {
            player1 = new Snake(headRed, bodyGreen, 10, 10);
            player2 = new Snake(bodyGreen, headRed, 10, 12);
            spawnFood();
        }
        state = GameState.MENU;
    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        GameEngine.createGame(game, 10);
    }
}