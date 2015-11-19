package com.sleepygrizzly.core;

import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class drawImage {

	int idimg;
	public TransformComponent img;
	SceneLoader scene;
	
	public drawImage(SceneLoader scene, int idimg){
		this.scene = scene;
		this.idimg = idimg;
	}
	
	public void createImage(){
		img = ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(idimg), TransformComponent.class);
	}
	
	public void moveupposi(){
		img.y = 182f;
	}
	
	public void movedownposi(){
		img.y = -521f;
	}
	
	public void moverightposi(){
		img.x += 20; 
	}
	
	public void moveleftposi(){
		img.x -= 20; 
	}
	
	
	
	
	
}
