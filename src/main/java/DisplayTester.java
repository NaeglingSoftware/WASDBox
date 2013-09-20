import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

public class DisplayTester {
	public void start() {

		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		while (!Display.isCloseRequested()) {
			drawStuff();
			Display.update();
		}
		Display.destroy();
	}

	int x = 100, y = 100, size = 25, rotation = 0;

	public void drawStuff() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		Keyboard.enableRepeatEvents(true);

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				switch (Keyboard.getEventKey()) {
					case Keyboard.KEY_W: y+=10; break;
					case Keyboard.KEY_A: x-=10; break;
					case Keyboard.KEY_S: y-=10; break;
					case Keyboard.KEY_D: x+=10; break;
					case Keyboard.KEY_Q: size++; break;
					case Keyboard.KEY_E: size--; break;
					case Keyboard.KEY_Z: rotation += 5; break;
					case Keyboard.KEY_C: rotation -= 5; break;
				}
			}
		}

		if (y < size)
			y = size;
		else if (x < size)
			x = size;
		else if (y > 600 - size)
			y = 600 - size;
		else if (x > 800 - size)
			x = 800 - size;

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