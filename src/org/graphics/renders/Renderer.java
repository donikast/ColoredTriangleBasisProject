package org.graphics.renders;

import static org.lwjgl.opengl.GL46.*;

import org.graphics.utils.ShaderProgram;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public class Renderer {
    private int vaoID, vboID;
    private ShaderProgram shaderProgram;

    public void init() {
        shaderProgram = new ShaderProgram("res/shaders/vertexShader.vert", "res/shaders/fragmentShader.frag");

        float[] vertices = {
                // X, Y, Z,  R, G, B
                -0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f,  // Червен връх
                0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f,  // Зелен връх
                0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f   // Син връх
        };

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertexBuffer.put(vertices).flip();
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Атрибут 0 - позиция
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        // Атрибут 1 - цвят
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);


        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void render() {
        shaderProgram.use();
        glBindVertexArray(vaoID);

        glDrawArrays(GL_TRIANGLES, 0, 3);
        glBindVertexArray(0);
    }
}