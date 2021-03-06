package com.sleepygrizzly.scene;

import java.util.HashMap;

import com.uwsoft.editor.renderer.SceneLoader;

public class SceneManage {

	SceneLoader sl;
	MainScene ms;

	HashMap<String, Scene> level = new HashMap<String, Scene>();

	Scene currentLevel;
	
	String s_level;
	
	public SceneManage(SceneLoader sl) {
		this.sl = sl;
		s_level = "main";
		currentLevel = new MenuScene(this, sl);
		currentLevel.create();
	}

	public void render() {
		currentLevel.render();
	}
	
	public void setScene(String s_level) {
		currentLevel.dispose();
		if (s_level.equals("main")) {
			currentLevel = new MainScene(this, sl);
		} else if (s_level.equals("play")) {
			currentLevel = new PlayScene(this, sl);
		} else if (s_level.equals("menu")) {
			currentLevel = new MenuScene(this, sl);
		} else if (s_level.equals("howtoplay")) {
			currentLevel = new HowtoScene(this, sl);
		} else if (s_level.equals("yourscore")) {
			currentLevel = new YourScoreScene(this, sl);
		} else if (s_level.equals("microwave")) {
			currentLevel = new KitchenScene(this, sl);
		} else if (s_level.equals("contact")) {
			currentLevel = new ContactScene(this, sl);
		} 
			
		currentLevel.create();
	}
}
