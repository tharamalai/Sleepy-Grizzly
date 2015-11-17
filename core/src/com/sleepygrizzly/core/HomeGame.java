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

	@Override
	public void create() {
		scene1 = new SceneLoader();
		sm = new SceneManage(scene1);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		scene1.getEngine().update(Gdx.graphics.getDeltaTime());
		Gdx.input.setInputProcessor(null);

		sm.render();
	}

}
