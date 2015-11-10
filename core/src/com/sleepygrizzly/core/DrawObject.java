package com.sleepygrizzly.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.javafx.scene.traversal.Direction;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class DrawObject extends ApplicationAdapter{
	
	private SpriteBatch batch;
	private Texture dinotext;
	private TextureRegion region;
	private Sprite sprite;
	//private TextureAtlas atlas;
	
	 public void create () {
	        batch = new SpriteBatch();
	        dinotext = new Texture(Gdx.files.external("dino.png"));
	        sprite = new Sprite(dinotext, 20, 20, 50, 50); 
	        region = new TextureRegion(dinotext,20,20,50,50);
	        sprite.setPosition(10, 10);
	        sprite.setRotation(45);
	    }

	    public void render () {
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
	        batch.begin();
	        batch.draw(region, 100, 100);
	        //batch.disableBlending();
	        //backgroundSprite.draw(batch);
	        batch.enableBlending();
	        sprite.draw(batch);
	        batch.end();
	    
	    }
}
