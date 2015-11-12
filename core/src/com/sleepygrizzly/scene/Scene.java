package com.sleepygrizzly.scene;

import com.uwsoft.editor.renderer.SceneLoader;

public abstract class Scene {
	SceneLoader scene1;
	SceneManage sm;
	public Scene(SceneManage sm, SceneLoader scene) {
		scene1 = scene;
		this.sm = sm;
	}
	
	abstract public void create();
	
	abstract public void render();
	
	abstract public void dispose();
}
