import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Snake {
    List<Point> body;
    int direction; // 0:上, 1:下, 2:左, 3:右
    Image headImage,bodyImage;
    int speed;
    boolean isMoving;
    int length;

    public Snake(Image head, Image body, int x, int y) {
        this.body = new ArrayList<>();
        this.body.add(new Point(x, y));
        this.body.add(new Point(x - 1, y));
        this.body.add(new Point(x - 2, y));
        length = 3;
        headImage = head;
        bodyImage = body;
        direction = 3; // 初始向右
        speed = 100;
        isMoving = false;
    }

    public Point getHead() {
        return body.get(0);
    }

    public void move() {
        if (!isMoving) {
            return;
        }
        Point head = getHead();
        switch (direction) {
            case 0:
                head = new Point(head.x, head.y - 1);
                break;
            case 1:
                head = new Point(head.x, head.y + 1);
                break;
            case 2:
                head = new Point(head.x - 1, head.y);
                break;
            case 3:
                head = new Point(head.x + 1, head.y);
                break;
        }
        body.add(0, head);
        if (body.size() > length) {
            body.remove(body.size() - 1);
        }
    }

    public int getLength() {
        return length;
    }

    public boolean checkCollision() {
        if (!isMoving) {
            return false;
        }
        Point head = getHead();
        if (head.x < 0 || head.x >= SnakeGame.GRID_SIZE || head.y < 0 || head.y >= SnakeGame.GRID_SIZE) {
            return true;
        }
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).x == head.x && body.get(i).y == head.y) {
                return true;
            }
        }
        return false;
    }
}
