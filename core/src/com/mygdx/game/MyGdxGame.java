package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    private TextureRegion spaceship;
    private TextureRegion asteroidTexture;
    private TextureRegion bullet;
    Asteroid[] asteroids;
    Ship ship;
    int screenHeight;
    int screenWidth;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("space_texture.png");
        spaceship = new TextureRegion(img, 0, 0, 64, 64);
        asteroidTexture = new TextureRegion(img, 64, 0, 256, 256);
        bullet = new TextureRegion(img, 320, 0, 16, 16);
        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();
        asteroids = new Asteroid[10];
        ship = new Ship(spaceship, screenWidth / 2, screenHeight / 2);

        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid(asteroidTexture, (float) Math.random() * screenHeight,
                    (float) Math.random() * screenWidth,
                    300 * ((float) Math.random() - 0.5f),
                    300 * ((float) Math.random() - 0.5f));
        }
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for (Asteroid o : asteroids) {
            o.render(batch);
        }
        ship.render(batch);
        batch.end();
    }

    public void update(float dt) {
        for (Asteroid o : asteroids) {
            o.update(dt);
        }
        for (int i = 0; i < asteroids.length - 1; i++) {
            for (int j = i + 1; j < asteroids.length; j++) {
                float len = asteroids[i].position.cpy().sub(asteroids[j].position).len();
                if (len < 24) {
                    float interLen = 24 - len;
                    Vector2 n = asteroids[i].position.cpy().sub(asteroids[j].position).nor();
                    asteroids[i].position.mulAdd(n, interLen / 2);
                    asteroids[j].position.mulAdd(n, -interLen / 2);
                    asteroids[i].velocity.mulAdd(n, interLen * 5);
                    asteroids[j].velocity.mulAdd(n, -interLen * 5);
                }
            }
        }

        ship.update(dt);
        if (InputHandler.isPressedA()) ship.toLeft(dt);
        if (InputHandler.isPressedD()) ship.toRight(dt);
        if (InputHandler.isPressedW()) ship.toUp(dt);
        if (InputHandler.isPressedS()) ship.toDown(dt);

        if (InputHandler.isTouched()) {
            if (InputHandler.getY() > ship.position.y) ship.toUp(dt);
            if (InputHandler.getY() < ship.position.y) ship.toDown(dt);
            if (InputHandler.getX() > ship.position.x) ship.toRight(dt);
            if (InputHandler.getX() < ship.position.x) ship.toLeft(dt);
        }

        for (int i = 0; i < asteroids.length; i++) {
            float len = ship.position.cpy().sub(asteroids[i].position).len();
            if (len < 24) {
                asteroids[i].position.set((float) Math.random() * screenHeight, (float) Math.random() * screenWidth);
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
