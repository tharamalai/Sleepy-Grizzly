package com.sleepygrizzly.scene;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.SceneLoader;

public class MenuScene extends Scene {
	private float elapsedTime = 0;
	public MenuScene(SceneManage sm, SceneLoader scene) {
		super(sm, scene);
	}

	@Override
	public void create() {
		scene1.loadScene("MenuScene");
		System.out.print("----------Menuscene--------------");
	}

	@Override
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
