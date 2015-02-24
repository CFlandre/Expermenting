package com.myexperiment.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class GameExperiments extends Game {
	
	Rectangle playerRect;
	ShapeRenderer shape;
	SpriteBatch batch;
	Texture texture;
	static String tagger;
	
	float positionX;
	float positionY;
	static float mousePositionX;
	static float mousePositionY;
	
	float playerWidth = 100;
	float playerHeight = 100;
	
	float topBorder;
	float leftBorder;
	float bottomBorder;
	float rightBorder;
	
	float dt = 1/60;
	
	
	
	@Override
	public void create() {
		
			Pixmap pm = new Pixmap(Gdx.files.internal("mouseCameraLens.png"));
			
			Gdx.input.setCursorImage(pm, 128, 64);
			
			batch = new SpriteBatch();
			texture = new Texture(Gdx.files.internal("camerashutter.png"));
			shape = new ShapeRenderer();
			
		//	texture = new Texture(Gdx.files.internal("lightanddark.png"));
			
			playerRect = new Rectangle(0, 0, playerWidth, playerHeight	);
			

			Gdx.app.log(tagger, "This is working");
			
			
	};
	
	public void render(){
		
		ScreenshotFactory screenies = new ScreenshotFactory();
		
		Boolean isPictureTaken = false;
		final float timestep = 1/60f;
		float pictureTimer = 0f;
		
		mousePositionX = Gdx.input.getX();
		mousePositionY = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		positionX = (mousePositionX - (texture.getWidth()/2));
		positionY = (mousePositionY - (texture.getHeight()/2)); 
		
		topBorder = playerRect.y + playerRect.getHeight();
	
		if (playerRect.x < 0){
			playerRect.x = 0;
		}
		
		if(Gdx.input.isKeyPressed(Keys.W)){
			playerRect.y += 10;
		};
		if(Gdx.input.isKeyPressed(Keys.S)){
			playerRect.y -= 10;
		};
		if(Gdx.input.isKeyPressed(Keys.A)){
			playerRect.x -= 10;
		};
		if(Gdx.input.isKeyPressed(Keys.D)){
			playerRect.x += 10;
		};
		
		if(Gdx.input.isKeyPressed(Keys.W) && topBorder >= Gdx.graphics.getHeight() ){
			playerRect.y = Gdx.graphics.getHeight() - playerRect.getHeight();
			
		}
		
		if (Gdx.input.isButtonPressed(Input.Keys.LEFT)){
			Gdx.app.log(tagger, "Screenshot?");
			pictureTimer =+ timestep;
			
			if (pictureTimer >= 0f && pictureTimer <= 1/30f){
				isPictureTaken = true;
				screenies.takeScreenshot();;
			}
			
			if (pictureTimer >= 1/30f){
				isPictureTaken = false;
			}
		}
	

		
		Gdx.gl.glClearColor(0, .7f, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(1, 0, 0, 1);
		shape.rect(playerRect.x, playerRect.y, playerRect.width, playerRect.height);
		shape.end();
	
		batch.begin();
		batch.draw(texture, positionX, positionY);
		batch.end();
		
	}
	
	public void dispose(){
		texture.dispose();
	}

	
	
}
