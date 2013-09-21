import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

public class DisplayTester {

	Quad WASDBox = new Quad(400, 300, 25, 0);

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

		Keyboard.enableRepeatEvents(true);

		while (!Display.isCloseRequested()) {
			drawStuff();
			Display.update();
		}
		Display.destroy();
	}

	public void drawStuff() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		WASDBox.update();
		WASDBox.display();
	}
}