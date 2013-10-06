import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Quad {

	private float x;
	private float y;
	private float size;
	private float rotation;
	private double red = 100;
	private double blue = 0;
	private double green = 0;
	private float moveSpeed = 2.0f;
	private float growSpeed = 1.0f;
	private float rotationSpeed = 1.0f;
	private float sizeChangeSpeed = 1.0f;
	private float slowDownRate = 4;

	public Quad(int xCoord, int yCoord, int s, int r) {
		x = xCoord;
		y = yCoord;
		size = s;
		rotation = r;
	}

	public void moveUp(float num) {
		y += num;
	}

	public void moveDown(float num) {
		y -= num;
	}

	public void moveLeft(float num) {
		x -= num;
	}

	public void moveRight(float num) {
		x += num;
	}

	public void grow(float num) {
		size += num;
	}

	public void shrink(float num) {
		size -= num;
	}

	public void rotate(float num) {
		rotation += num;
	}

	public void incrementColor(double num) {
		checkColorValues();

		if (red < 100 && green == 0 && blue == 0)
			red += num;
		else if (red == 100 && green < 100 && blue == 0)
			green += num;
		else if (red > 0 && green == 100 && blue == 0)
			red -= num;
		else if (red == 0 && green == 100 && blue < 100)
			blue += num;
		else if (red ==0 && green > 0 && blue == 100)
			green -= num;
		else if (red < 100 && green == 0 && blue == 100)
			red += num;
		else if (red == 100 && green == 0 && blue > 0)
			blue -= num;
	}

	public void decrementColor(double num) {
		checkColorValues();

		if (red == 0 && green < 100 && blue == 100)
			green += num;
		else if (red == 0 && green == 100 && blue > 0)
			blue -= num;
		else if (red < 100 && green == 100 && blue == 0)
			red += num;
		else if (red == 100 && green > 0 && blue == 0)
			green -= num;
		else if (red == 100 && green == 0 && blue < 100)
			blue += num;
		else if (red > 0 && green == 0 && blue == 100)
			red -= num;
	}

	private void checkColorValues() {
		if (red > 100)
			red = 100;
		else if (green > 100)
			green = 100;
		else if (blue > 100)
			blue = 100;
		else if (red < 0)
			red = 0;
		else if (green < 0)
			green = 0;
		else if (blue < 0)
			blue = 0;
	}

	public void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT ) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT )) {
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) moveUp(moveSpeed / slowDownRate);
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) moveLeft(moveSpeed / slowDownRate);
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) moveDown(moveSpeed / slowDownRate);
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) moveRight(moveSpeed / slowDownRate);
			if (Keyboard.isKeyDown(Keyboard.KEY_Q)) grow(growSpeed / slowDownRate);
			if (Keyboard.isKeyDown(Keyboard.KEY_E)) shrink(growSpeed / slowDownRate);
			if (Keyboard.isKeyDown(Keyboard.KEY_Z)) rotate(rotationSpeed / slowDownRate);
			if (Keyboard.isKeyDown(Keyboard.KEY_C)) rotate(-rotationSpeed / slowDownRate);
			if (Keyboard.isKeyDown(Keyboard.KEY_1)) incrementColor(sizeChangeSpeed / slowDownRate);
			if (Keyboard.isKeyDown(Keyboard.KEY_2)) decrementColor(sizeChangeSpeed / slowDownRate);
		}
		else {
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) moveUp(moveSpeed);
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) moveLeft(moveSpeed);
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) moveDown(moveSpeed);
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) moveRight(moveSpeed);
			if (Keyboard.isKeyDown(Keyboard.KEY_Q)) grow(growSpeed);
			if (Keyboard.isKeyDown(Keyboard.KEY_E)) shrink(growSpeed);
			if (Keyboard.isKeyDown(Keyboard.KEY_Z)) rotate(rotationSpeed);
			if (Keyboard.isKeyDown(Keyboard.KEY_C)) rotate(-rotationSpeed);
			if (Keyboard.isKeyDown(Keyboard.KEY_1)) incrementColor(sizeChangeSpeed);
			if (Keyboard.isKeyDown(Keyboard.KEY_2)) decrementColor(sizeChangeSpeed);
		}
	}

	public void checkBoundaries() {
		if (y < size)
			y = size;
		if (x < size)
			x = size;
		if (y > DisplayWindow.WINDOW_HEIGHT - size)
			y = DisplayWindow.WINDOW_HEIGHT - size;
		if (x > DisplayWindow.WINDOW_WIDTH - size)
			x = DisplayWindow.WINDOW_WIDTH - size;
	}

	public void display() {
		checkBoundaries();

		GL11.glColor3d(red / 100, green / 100, blue / 100);

		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rotation, 0f, 0f, 1f);
		GL11.glTranslatef(-x, -y, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x - size, y - size);
		GL11.glVertex2f(x + size, y - size);
		GL11.glVertex2f(x + size, y + size);
		GL11.glVertex2f(x - size, y + size);
		GL11.glEnd();
		GL11.glPopMatrix();
	}
}