package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private TextureRegion spaceship;
	private TextureRegion asteroid;
	private TextureRegion bullet;
	int i;

	float xForShip;
	float yForShip;
	float xForBullet;
	float yForBullet;
	float xForAsteroid;
	float yForAsteroid;

	float vxForShip;
	float vyForShip;
	float vxForBullet;
	float vyForBullet;
	float vxForAsteroid;
	float vyForAsteroid;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("space_texture.png");
		spaceship = new TextureRegion(img, 0, 0, 64,64);
		asteroid = new TextureRegion(img, 64, 0, 256,256);
		bullet = new TextureRegion(img, 320, 0, 16, 16);

		int screenHeight = Gdx.graphics.getHeight();
		int screenWidth = Gdx.graphics.getWidth();

		//Генерирую разные положения на экране для каждого объекта
		xForShip = (float) (Math.random() * screenWidth);
		yForShip = (float) (Math.random() * screenHeight);
		xForBullet = (float) (Math.random() * screenWidth);
		yForBullet = (float) (Math.random() * screenHeight);
		xForAsteroid = (float) (Math.random() * screenWidth);
		yForAsteroid = (float) (Math.random() * screenHeight);

		//Генерирую разную скорость для каждого обьекта на Х и Y
		// Как позитивную, так и негативную, чтобы объекты разлетались в разную сторону
		vxForShip = (float) ((Math.random() * 600) - 300);
		vyForShip = (float) ((Math.random() * 600) - 300);
		vxForBullet = (float) ((Math.random() * 600) - 300);
		vyForBullet = (float) ((Math.random() * 600) - 300);
		vxForAsteroid = (float) ((Math.random() * 600) - 300);
		vyForAsteroid = (float) ((Math.random() * 600) - 300);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(spaceship, xForShip, yForShip);
		batch.draw(bullet, xForBullet, yForBullet);
		batch.draw(asteroid, xForAsteroid, yForAsteroid, 128,128);
		batch.end();
	}

	public void update(float dt){
		xForShip += vxForShip * dt;
		yForShip += vyForShip * dt;
		xForBullet += vxForBullet * dt;
		yForBullet += vyForBullet * dt;
		xForAsteroid += vxForAsteroid * dt;
		yForAsteroid += vyForAsteroid * dt;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
