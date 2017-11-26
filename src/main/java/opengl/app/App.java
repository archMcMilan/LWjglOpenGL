package opengl.app;

import opengl.controller.MouseController;

import static opengl.renderer.FrameRenderer.createWindow;
import static opengl.renderer.FrameRenderer.initGL;

public class App {
    private static MouseController controller = new MouseController();
    public static void main(String[] args) {
        createWindow();
        initGL();
        controller.run();
    }

}
