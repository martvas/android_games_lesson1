package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.W;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.D;



public class InputHandler {
    public static boolean isPressedW() {
        return Gdx.input.isKeyPressed(W);
    }
    public static boolean isPressedA() {
        return Gdx.input.isKeyPressed(A);
    }
    public static boolean isPressedS() {
        return Gdx.input.isKeyPressed(S);
    }
    public static boolean isPressedD() {
        return Gdx.input.isKeyPressed(D);
    }

    public static boolean isTouched() {
        return Gdx.input.isTouched();
    }

    public static int getX() {
        return Gdx.input.getX();
    }

    public static int getY() {
        return Gdx.graphics.getHeight() - Gdx.input.getY();
    }

}
