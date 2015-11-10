package com.sleepygrizzly.core;

import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class Bear {
	int id;
	SceneLoader scene;
	String name;
	TransformComponent bear;
	SpriteAnimationStateComponent bearori;
	
	public Bear(SceneLoader scene,String name){
		this.scene = scene;
		this.name = name;
	}
	
	public void createBearID(int id){
		System.out.println("createBearID  :  " + name + " id :" + id );
		bearori =  ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(this.id), SpriteAnimationStateComponent.class);
		bear = ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(this.id), TransformComponent.class);
		//System.out.println("createBearID  :  " + name + " id :" + id );
	}
}
