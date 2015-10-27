package com.sleepygrizzly.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class MainGame extends ApplicationAdapter {
	
	private SceneLoader sl;
	private Viewport vp;
	
	SpriteAnimationStateComponent ss;
	TransformComponent tf;
	
	OrthographicCamera cam;
	
	@Override
	public void create () {
		sl = new SceneLoader();
		vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sl.loadScene("MainScene", vp);
		
		ss =  ComponentRetriever.get(
				sl.entityFactory.getEntityByUniqueId(40), SpriteAnimationStateComponent.class);
		tf = ComponentRetriever.get(sl.entityFactory.getEntityByUniqueId(40), TransformComponent.class);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sl.getEngine().update(Gdx.graphics.getDeltaTime());
		
		if(Gdx.input.isKeyJustPressed(Keys.S)) {
			animation();
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			tf.y -= 1f;
		}
		
		camera();
	}
	
	public void animation() {
		ss.paused = ss.paused ? false : true;
	}
	
	public void camera() {
		cam = (OrthographicCamera)vp.getCamera();
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
			cam.position.x += 1;
	}
}
