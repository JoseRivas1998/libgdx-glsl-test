package com.tcg.glsltest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1280;

    private Viewport viewport;

    private SpriteBatch batch;
    private Texture img;

    private Vector2 mouse;
    private float radius = 300;

    private static final float RADIUS_SPEED = 300;

    private ShaderProgram shaderProgram;

    @Override
    public void create() {

        viewport = new StretchViewport(WIDTH, HEIGHT);

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");


        FileHandle vertexShader = Gdx.files.internal("shaders/vertex.glsl");
        FileHandle fragmentShader = Gdx.files.internal("shaders/frag.glsl");

        shaderProgram = new ShaderProgram(vertexShader, fragmentShader);
        if (!shaderProgram.isCompiled()) {
            Gdx.app.error(getClass().getName(), shaderProgram.getLog());
            System.exit(0);
        }


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float dt = Gdx.graphics.getDeltaTime();

        viewport.apply(true);
        mouse = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            radius += RADIUS_SPEED * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            radius -= RADIUS_SPEED * dt;
        }

        draw();
    }

    private void draw() {
        drawImage();
    }

    private void drawImage() {
        shaderProgram.bind();
        shaderProgram.setUniformf("u_mousePos", mouse);
        shaderProgram.setUniformf("u_radius", radius);

        batch.begin();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.setShader(shaderProgram);
        batch.draw(img, 0, 0, WIDTH, HEIGHT);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
