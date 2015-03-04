package com.myexperiment.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class GameExperiments extends Game implements InputProcessor {
	
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
	
	ScreenshotFactory screenies;
	
	@Override
	public void create() {
			
			Pixmap pm = new Pixmap(Gdx.files.internal("mouselens.png"));
			
			
			Gdx.input.setCursorImage(pm, 16, 16);
			
			batch = new SpriteBatch();
			texture = new Texture(Gdx.files.internal("camerashutter.png"));
			shape = new ShapeRenderer();
			
		//	texture = new Texture(Gdx.files.internal("lightanddark.png"));
			
			playerRect = new Rectangle(0, 0, playerWidth, playerHeight	);
			

			Gdx.app.log(tagger, "This is working");
			
			
	};
	
	public void render(){

		
		Gdx.input.setInputProcessor(this);
		
		screenies = new ScreenshotFactory();
		
		Boolean isPictureTaken = false;
	
		
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT){
			screenies.takeScreenshot();
			Gdx.app.log(tagger, "Screenshot?");
			
			
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	


	
	
}
