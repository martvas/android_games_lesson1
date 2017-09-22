package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Ship {
    TextureRegion textureRegion;
    Vector2 position;
    private final float shipSize = 32;
    private final float shipHalfSize = shipSize / 2;
    int screenHeight = Gdx.graphics.getHeight();
    int screenWidth = Gdx.graphics.getWidth();
    Vector2 nextPosition = new Vector2(0, 0);

    public Ship(TextureRegion textureRegion, float x, float y) {
        this.textureRegion = textureRegion;
        this.position = new Vector2(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureRegion, position.x - shipHalfSize, position.y - shipHalfSize,
                shipSize, shipSize);
    }

    public void update(float dt) {
        if (position.y < shipHalfSize) position.y = shipHalfSize;
        if (position.y > screenHeight - shipHalfSize) position.y = screenHeight - shipHalfSize;
        if (position.x < shipHalfSize) position.x = shipHalfSize;
        if (position.x > screenWidth - shipHalfSize) position.x = screenWidth - shipHalfSize;
    }

    public void toRight(float dt){
        nextPosition.set(100, 0);
        position.mulAdd(nextPosition, dt);
    }

    public void toLeft(float dt){
        nextPosition.set(-100, 0);
        position.mulAdd(nextPosition, dt);
    }

    public void toUp(float dt){
        nextPosition.set(0, 100);
        position.mulAdd(nextPosition, dt);
    }

    public void toDown(float dt){
        nextPosition.set(0, -100);
        position.mulAdd(nextPosition, dt);
    }

}
