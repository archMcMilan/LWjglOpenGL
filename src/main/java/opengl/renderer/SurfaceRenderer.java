package opengl.renderer;

import lombok.Setter;
import opengl.surface.Surface;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import javax.vecmath.Vector3d;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

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
    private int[] texture = new int[9];

    public void renderGl() {
        glLoadIdentity();
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        glTranslated(xTranslate, yTranslate, zTranslate);
        glRotated(angle, xRotate, yRotate, zRotate);
        glScaled(scale, scale, scale);

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
        glColor4d(1, 1, 1, 1);
        glBegin(GL_TRIANGLE_STRIP);
        while (alfa <= 4 * Math.PI) {
            while (t <= T_MEASURE_VALUE) {
                createNormal(alfa, t, 0, 0);
                createNormal(alfa + ITERATION_COEFFICIENT, t, 1, 0);
                createNormal(alfa, t + ITERATION_COEFFICIENT, 1, 1);
                createNormal(alfa + ITERATION_COEFFICIENT, t + ITERATION_COEFFICIENT, 0, 1);

                t += ITERATION_COEFFICIENT;
            }
            t = -T_MEASURE_VALUE;
            alfa += ITERATION_COEFFICIENT;
        }
        glEnd();
    }

    private void createNormal(double alfa, double t, int coord1, int coord2) {
        Surface surface = new Surface(alfa, t);
        Vector3d normalVector = surface.getNormal(alfa, t, ITERATION_COEFFICIENT);
        glTexCoord2d(coord1, coord2);
        glNormal3d(normalVector.x, normalVector.y, normalVector.z);
        glVertex3d(surface.getX(), surface.getY(), surface.getZ());
    }

    public void createTexture() {
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glBindTexture(GL_TEXTURE_2D, texture[0]);
        for (int i = 0; i < 9; i++) {
            texture[0] = loadTexture(i);
        }
        glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
        glEnable(GL_TEXTURE_2D);
    }

    private int loadTexture(int level) {
        BufferedImage image = null;
        try {
            int size = (int) Math.pow(2, level);
            image = ImageIO.read(new File("D:\\Education\\5 kurs\\OpenGL\\LWjglOpenGL\\src\\main\\resources\\textures\\bricks" + size + ".jpg"));
        } catch (IOException ex) {
        }

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        ByteBuffer byteBuffer = BufferUtils.createByteBuffer((width * height) * 3);

        for (int i = 0; i < pixels.length; i++) {
            byte red = (byte) ((pixels[i] >> 16) & 0xFF);
            byte green = (byte) ((pixels[i] >> 8) & 0xFF);
            byte blue = (byte) (pixels[i] & 0xFF);

            byteBuffer.put(red);
            byteBuffer.put(green);
            byteBuffer.put(blue);
        }
        byteBuffer.flip();

        glTexImage2D(GL_TEXTURE_2D, level, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, byteBuffer);
        int texture = glGenTextures();
        return texture;
    }
}