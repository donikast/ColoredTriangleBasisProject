package org.graphics;

import org.graphics.renders.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(800, 600, "OpenGL with Shaders");
        window.run();
    }
}