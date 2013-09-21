import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Quad {

	private int x;
	private int y;
	private int size;
	private int rotation;

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

	public void update() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				switch (Keyboard.getEventKey()) {
					case Keyboard.KEY_W: moveUp(10); break;
					case Keyboard.KEY_A: moveLeft(10); break;
					case Keyboard.KEY_S: moveDown(10); break;
					case Keyboard.KEY_D: moveRight(10); break;
					case Keyboard.KEY_Q: grow(1); break;
					case Keyboard.KEY_E: shrink(1); break;
					case Keyboard.KEY_Z: rotate(5); break;
					case Keyboard.KEY_C: rotate(-5); break;
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