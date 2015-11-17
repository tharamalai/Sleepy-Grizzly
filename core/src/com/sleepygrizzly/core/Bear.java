package com.sleepygrizzly.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class Bear {
	int id;
	SceneLoader scene;
	String name;
	TransformComponent bear;
	SpriteAnimationStateComponent bear_ori;
	Sprite sp_bear;
	Texture img_bear;
	TextureAtlas at_bear;
	Animation ani_bear;

	public Bear(SceneLoader scene, String name, int id) {
		this.scene = scene;
		this.name = name;
		this.id = id;
	}

	public void createBearID() {
		System.out.println("createBearID  :  " + name + " id :" + id);
		bear_ori = ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(id),
				SpriteAnimationStateComponent.class);
		bear = ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(id), TransformComponent.class);
		bear.y = 330f;
	}

	public void move() {
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			bear.x = 410;
			bear.scaleX = -Math.abs(bear.scaleX);
		}
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			bear.x = 90;
			bear.scaleX = Math.abs(bear.scaleX);
		}
	}
}
