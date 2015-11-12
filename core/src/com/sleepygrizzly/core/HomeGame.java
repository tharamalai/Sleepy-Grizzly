package com.sleepygrizzly.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sleepygrizzly.scene.SceneManage;
import com.uwsoft.editor.renderer.SceneLoader;

public class HomeGame extends Game {
	private SceneLoader scene1;
	private Viewport vp;

	SpriteBatch batch;

	private SceneManage sm;
	/*
	 * Sprite sp_bear; Texture img_bear; TextureAtlas at_bear; Animation
	 * ani_bear;
	 */

	@Override
	public void create() {
		scene1 = new SceneLoader();
		vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		scene1.loadScene("PlayScene");
		sm = new SceneManage(scene1);
		/*
		 * bearstate_l = ComponentRetriever.get(
		 * scene1.entityFactory.getEntityByUniqueId(9),
		 * SpriteAnimationStateComponent.class); bear_l =
		 * ComponentRetriever.get(scene1.entityFactory.getEntityByUniqueId(9),
		 * TransformComponent.class);
		 * 
		 * bearstate_r = ComponentRetriever.get(
		 * scene1.entityFactory.getEntityByUniqueId(2),
		 * SpriteAnimationStateComponent.class); bear_r =
		 * ComponentRetriever.get(scene1.entityFactory.getEntityByUniqueId(2),
		 * TransformComponent.class);
		 */

		batch = new SpriteBatch();
		/*
		 * batch = new SpriteBatch(); img_bear = new
		 * Texture("data/standybear/standybear.png"); sp_bear = new
		 * Sprite(img_bear); sp_bear.setFlip(true, false); at_bear = new
		 * TextureAtlas(Gdx.files.internal("data/standybear/standybear.atlas"));
		 * ani_bear = new Animation(1/15f, at_bear.getRegions());
		 */

	}

	int i;
	int j;

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		scene1.getEngine().update(Gdx.graphics.getDeltaTime());
		Gdx.input.setInputProcessor(null);
		// sp_bear.setSize(169, 240);

		sm.render();
	}

}
