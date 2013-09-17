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

	int x = 100, y = 100, size = 25;

	public void drawStuff() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		Keyboard.enableRepeatEvents(true);

		while(Keyboard.next()) {

			switch(Keyboard.getEventKey()) {
				case Keyboard.KEY_W: y+=10; break;
				case Keyboard.KEY_A: x-=10; break;
				case Keyboard.KEY_S: y-=10; break;
				case Keyboard.KEY_D: x+=10; break;
				case Keyboard.KEY_Q: size++; break;
				case Keyboard.KEY_E: size--; break;
			}
		}
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x - size, y - size);
		GL11.glVertex2f(x - size, y + size);
		GL11.glVertex2f(x + size, y + size);
		GL11.glVertex2f(x + size, y - size);
		GL11.glEnd();
	}
}