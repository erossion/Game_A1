# Snake Game Project Description

## 1. Project Overview
This project is a Snake game developed using the Java language and the Swing library. It supports both single-player and two-player game modes. Players can use the keyboard to control the movement direction of the snake. When the snake eats food, its body gets longer. The game ends when the snake hits the wall or its own body.

## 2. Project Structure
### Main Files and Their Functions
1. **`SnakeGame.java`**: This is the main class of the game. It inherits from the `GameEngine` class and is responsible for initializing the game, managing the game state, handling user input, and drawing the game interface.
2. **`Snake.java`**: This class defines the snake. It includes the snake's body structure, movement logic, and collision detection methods.
3. **`GameEngine.java`**: This is the game engine class. It provides general functions such as creating the game window, controlling the frame rate, handling keyboard and mouse events, and drawing graphics.

### Configuration Files
- **`.idea` directory**: It contains the configuration files for the IntelliJ IDEA development environment, such as the project SDK configuration and UI designer configuration.
- **`.gitignore`**: It specifies the files and directories that the Git version control system should ignore, preventing unnecessary files from being committed to the repository.

## 3. Running Environment
- **JDK**: `openjdk-23`
- **Development Tool**: It is recommended to use IntelliJ IDEA.

## 4. Running Steps
1. **Clone the Project**: Clone the project to your local development environment.
```bash
git clone <Project Repository Address>
```
2. **Import the Project**: Open IntelliJ IDEA, select `File` -> `Open`, and then choose the directory where the project is located.
3. **Configure the JDK**: Make sure the JDK version used by the project is `openjdk-23`. You can configure it in `File` -> `Project Structure` -> `Project Settings` -> `Project`.
4. **Run the Game**: Find the `SnakeGame.java` file, right-click on it, and select `Run 'SnakeGame.main()'`.

## 5. Gameplay
### Menu Interface
- After starting the game, the menu interface will be displayed. It includes the following options:
  - Press the `1` key: Start the single-player game.
  - Press the `2` key: Start the two-player game.
  - Press the `Q` key: Exit the game.

### Game Interface
- **Single-player Game**: The player uses the `W`, `S`, `A`, `D` keys to control the movement direction of the snake. Eat the apples on the screen to make the snake's body longer. The game ends when the snake hits the wall or its own body.
- **Two-player Game**: Player 1 uses the `W`, `S`, `A`, `D` keys to control the movement direction of the snake, and Player 2 uses the arrow keys (up, down, left, right). Similarly, the game ends when the snake hits the wall, its own body, or the other snake.

### Game Over Interface
- After the game is over, the game over interface will be displayed, showing the player's score. Press the `R` key to restart the game.

## 6. Code Explanation
### `SnakeGame.java`
- **Constructor**: Create a graphics transformation stack and the game window.
- **`init()` method**: Initialize the game resources, such as loading the snake's head, body, and apple images.
- **`update(double dt)` method**: Update the game logic according to the game state. This includes handling user input, moving the snake, checking for food collisions, and collision detection.
- **`paintComponent()` method**: Draw different interfaces according to the game state, such as the menu interface, game interface, and game over interface.
- **`keyPressed(KeyEvent e)` method**: Handle keyboard key press events. Update the game state and the snake's movement direction according to the key pressed.
- **`keyReleased(KeyEvent e)` method**: Handle keyboard key release events.

### `Snake.java`
- **Constructor**: Initialize the snake's body structure, direction, speed, and other attributes.
- **`move()` method**: Move the snake's body according to its current direction.
- **`checkCollision()` method**: Check if the snake hits the wall or its own body.

### `GameEngine.java`
- **Constructor**: Create a graphics transformation stack.
- **`createGame(GameEngine game, int framerate)` method**: Initialize the game and start the game loop.
- **`gameLoop(int framerate)` method**: Start the game timer to control the frame rate of the game.
- **`update(double dt)` method**: This is an abstract method. Subclasses need to implement the game update logic.
- **`paintComponent()` method**: This is an abstract method. Subclasses need to implement the game drawing logic.
- **`keyPressed(KeyEvent event)`, `keyReleased(KeyEvent event)`, `keyTyped(KeyEvent event)` methods**: Handle keyboard events.
- **`mouseClicked(MouseEvent event)` and other methods**: Handle mouse events.
- **Graphics Drawing Methods**: Provide methods for drawing lines, rectangles, circles, text, and other graphics.

## 7. Notes
- Make sure the `resources` directory of the project contains the `head.png`, `dot.png`, and `apple.png` image files. Otherwise, the snake and apples may not be displayed correctly in the game.
- If you encounter problems during the running process, you can check the JDK configuration and dependency libraries of the project.

## 8. Contribution
If you have any suggestions or improvements for this project, welcome to submit a Pull Request or an Issue.
