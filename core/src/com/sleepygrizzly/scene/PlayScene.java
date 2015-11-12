package com.sleepygrizzly.scene;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.SceneLoader;

public class PlayScene extends Scene {

	public PlayScene(SceneManage sm, SceneLoader scene) {
		super(sm ,scene);
	}

	@Override
	public void create() {
		scene1.loadScene("Playbearscene");
	}

	@Override
	public void render() {
		if (Gdx.input.justTouched()) {
			sm.setScene("main");
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
