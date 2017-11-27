package opengl.controller;

import opengl.renderer.FrameRenderer;
import opengl.renderer.SurfaceRenderer;
import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;

public class MouseController {
    private SurfaceRenderer surfaceRenderer = new SurfaceRenderer();
    private FrameRenderer frameRenderer = new FrameRenderer();

    public void run() {
        while (notClose()) {
            mouseListener();
            frameRenderer.drawAxis();
            frameRenderer.light();
            surfaceRenderer.renderGl();
            Display.update();
        }
    }

    public boolean notClose() {
        if (Display.isCloseRequested()) {
            return false;
        }
        return true;
    }

    public void mouseListener() {
        while (Mouse.next()) {
            wheelEvent();
            mouseClickEvent();

        }
    }

    private void mouseClickEvent() {
        if (Mouse.isButtonDown(1)) {//right
            int x = Mouse.getDX() / 5;
            int y = Mouse.getDY() / 5;
            surfaceRenderer.translateFigure(x, y);
        }
        if (Mouse.getEventButtonState()) {
            if (Mouse.getEventButton() == 0) {//left
                surfaceRenderer.rotateAngle(-5);
            }
            if (Mouse.getEventButton() == 1) {//right
                surfaceRenderer.rotateAngle(5);
            }
        }
    }

    private void wheelEvent() {
        int dWheel = Mouse.getDWheel();
        if (Mouse.isButtonDown(2)) {
            int x = Mouse.getDX() / 5;
            int y = Mouse.getDY() / 5;
            surfaceRenderer.rotateFigure(x, y);
        }
        if (dWheel < 0) {
            surfaceRenderer.decreaseScale();
        }
        if (dWheel > 0) {
            surfaceRenderer.increaseScale();
        }
    }
}
