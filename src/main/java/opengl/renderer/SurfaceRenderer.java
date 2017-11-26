package opengl.renderer;

import lombok.Setter;
import opengl.surface.Surface;
import org.lwjgl.opengl.GL11;

@Setter
public class SurfaceRenderer {
    public static final double ITERATION_COEFFICIENT = 0.05;
    public static final int T_MEASURE_VALUE = 2;
    private double angle = 0;
    private double scale = 0.5;
    private double xTranslate = 0;
    private double yTranslate = 0;
    private double zTranslate = -7;
    private double xRotate = 2;
    private double yRotate = 2;
    private double zRotate = 2;

    public void renderGl() {
        GL11.glLoadIdentity();
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        GL11.glTranslated(xTranslate, yTranslate, zTranslate);
        GL11.glRotated(angle, xRotate, yRotate, zRotate);
        GL11.glScaled(scale, scale, scale);

        drawSurface();
    }

    public void increaseScale() {
        scale *= 1.1;
    }

    public void decreaseScale() {
        scale /= 1.1;
    }

    public void rotateAngle(double value) {
        angle += value;
    }

    public void rotateFigure(double x, double y) {
        xRotate += x;
        yRotate += y;
    }

    public void translateFigure(double x, double y) {
        xTranslate += x;
        yTranslate += y;
    }


    private void drawSurface() {
        double alfa = 0;
        double t = -T_MEASURE_VALUE;
        GL11.glColor4d(1, 1, 1, 1);
        GL11.glBegin(GL11.GL_TRIANGLES);
        while (alfa <= 4 * Math.PI) {
            while (t <= T_MEASURE_VALUE) {
                createSurfaceWithParameters(alfa, t);
                createSurfaceWithParameters(alfa + ITERATION_COEFFICIENT, t);
                createSurfaceWithParameters(alfa, t + ITERATION_COEFFICIENT);
                t += ITERATION_COEFFICIENT;
            }
            t = -2;
            alfa += ITERATION_COEFFICIENT;
        }
        GL11.glEnd();
    }

    private void createSurfaceWithParameters(double alfa, double t) {
        Surface surface = new Surface(alfa, t);
        GL11.glVertex3d(surface.getX(), surface.getY(), surface.getZ());
    }

}
