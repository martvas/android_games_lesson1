package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
     TextureRegion textureRegion;
     Vector2 position;
     Vector2 velocity;

    private final float asteroidSize = 32;
    private final float asteroidHalfSize = asteroidSize / 2;

    int screenHeight = Gdx.graphics.getHeight();
    int screenWidth = Gdx.graphics.getWidth();

    public Asteroid(TextureRegion textureRegion, float x, float y, float vx, float vy) {
        this.textureRegion = textureRegion;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureRegion, position.x - asteroidHalfSize, position.y - asteroidHalfSize,
                asteroidSize, asteroidSize);
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);

        if (position.y < asteroidHalfSize) {
            if (velocity.y < 0) velocity.y *= -1;
            position.y = asteroidHalfSize;
        }
        if (position.y > screenHeight - asteroidHalfSize) {
            if (velocity.y > 0) velocity.y *= -1;
            position.y = screenHeight - asteroidHalfSize;
        }
        if (position.x < asteroidHalfSize) {
            if (velocity.x < 0) velocity.x *= -1;
            position.x = asteroidHalfSize;
        }
        if (position.x > screenWidth - asteroidHalfSize) {
            if (velocity.x > 0) velocity.x *= -1;
            position.x = screenWidth - 32;
        }
    }

}
