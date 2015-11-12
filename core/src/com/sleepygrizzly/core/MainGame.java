package com.sleepygrizzly.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class MainGame extends ApplicationAdapter {

	private SceneLoader sl;
	private Viewport vp;

	private SceneLoader sl2;
	private Viewport vp2;
	private Stage stage;

	SpriteAnimationStateComponent ss;
	TransformComponent tf;

	SpriteAnimationStateComponent rr;
	TransformComponent tr;

	OrthographicCamera cam;

	@Override
	public void create() {
		sl = new SceneLoader();
		vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sl.loadScene("MainScene", vp);

		sl2 = new SceneLoader();
		vp2 = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sl2.loadScene("playscene", vp2);

		ss = ComponentRetriever.get(sl.entityFactory.getEntityByUniqueId(7), SpriteAnimationStateComponent.class);
		tf = ComponentRetriever.get(sl.entityFactory.getEntityByUniqueId(7), TransformComponent.class);

		rr = ComponentRetriever.get(sl.entityFactory.getEntityByUniqueId(6), SpriteAnimationStateComponent.class);
		tr = ComponentRetriever.get(sl.entityFactory.getEntityByUniqueId(6), TransformComponent.class);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sl.getEngine().update(Gdx.graphics.getDeltaTime());

		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			animation();
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			tf.y -= 1f;

		}
		if (Gdx.input.isTouched()) {
			Gdx.input.setInputProcessor(null);
			sl2.getEngine().update(Gdx.graphics.getDeltaTime());
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			tf.y += 1f;
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			tf.x = 1f;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			/*
			 * boolean flip = (getDirection() == Direction.LEFT);
			 * SpriteBatch.draw(tr, flip ? tf.x+500f : tf.x, tf.y, flip ? -500f
			 * : 500f, 6); tf.x = 500f; tf.
			 */
			tr.x = 400f;

		}

		// camera();
	}

	public void animation() {
		ss.paused = ss.paused ? false : true;
	}

	/*
	 * public void camera() { cam = (OrthographicCamera)vp.getCamera();
	 * if(Gdx.input.isKeyPressed(Keys.RIGHT)) cam.position.x += 1; }
	 */
}
