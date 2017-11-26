package opengl.renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class FrameRenderer {
    public static void createWindow() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.setVSyncEnabled(true);
            Display.setTitle("Open GL labs");
            Display.create();
        } catch (LWJGLException e) {
            System.err.print(e.getMessage());
            System.exit(0);
        }
    }

    public static void initGL() {
        int width = Display.getDisplayMode().getWidth();
        int height = Display.getDisplayMode().getHeight();

        GL11.glViewport(0, 0, width, height);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(75.0f, ((float) width / (float) height), 0.1f, 100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }

    public static void drawAxis() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glColor4d(1, 0, 0, 1);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(0, 0, 0);
        GL11.glVertex3d(0, 0, 5);
        GL11.glEnd();
        GL11.glColor4d(0, 0, 1, 1);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(0, 0, 0);
        GL11.glVertex3d(5, 0, 0);
        GL11.glEnd();
        GL11.glColor4d(0, 1, 0, 1);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(0, 0, 0);
        GL11.glVertex3d(0, 5, 0);
        GL11.glEnd();
    }
}
