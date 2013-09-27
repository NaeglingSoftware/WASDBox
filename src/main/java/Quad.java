import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Quad {

	private int x;
	private int y;
	private int size;
	private int rotation;
	private double red = 100;
	private double blue = 0;
	private double green = 0;

	public Quad(int xCoord, int yCoord, int s, int r) {
		x = xCoord;
		y = yCoord;
		size = s;
		rotation = r;
	}

	public void moveUp(int num) {
		y += num;
	}

	public void moveDown(int num) {
		y -= num;
	}

	public void moveLeft(int num) {
		x -= num;
	}

	public void moveRight(int num) {
		x += num;
	}

	public void grow(int num) {
		size += num;
	}

	public void shrink(int num) {
		size -= num;
	}

	public void rotate(int num) {
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

	public void decrementColor(int num) {

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
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
					switch (Keyboard.getEventKey()) {
						case Keyboard.KEY_W: moveUp(1); break;
						case Keyboard.KEY_A: moveLeft(1); break;
						case Keyboard.KEY_S: moveDown(1); break;
						case Keyboard.KEY_D: moveRight(1); break;
						case Keyboard.KEY_Q: grow(1); break;
						case Keyboard.KEY_E: shrink(1); break;
						case Keyboard.KEY_Z: rotate(1); break;
						case Keyboard.KEY_C: rotate(-1); break;
						case Keyboard.KEY_2: incrementColor(1); break;
						case Keyboard.KEY_1: decrementColor(1); break;
					}
				else {
					switch (Keyboard.getEventKey()) {
						case Keyboard.KEY_W: moveUp(10); break;
						case Keyboard.KEY_A: moveLeft(10); break;
						case Keyboard.KEY_S: moveDown(10); break;
						case Keyboard.KEY_D: moveRight(10); break;
						case Keyboard.KEY_Q: grow(1); break;
						case Keyboard.KEY_E: shrink(1); break;
						case Keyboard.KEY_Z: rotate(5); break;
						case Keyboard.KEY_C: rotate(-5); break;
						case Keyboard.KEY_2: incrementColor(5); break;
						case Keyboard.KEY_1: decrementColor(5); break;
					}
				}
			}
		}
	}

	public void checkBoundaries() {
		if (y < size)
			y = size;
		else if (x < size)
			x = size;
		else if (y > 600 - size)
			y = 600 - size;
		else if (x > 800 - size)
			x = 800 - size;
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