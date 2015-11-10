package com.sleepygrizzly.core;

import java.util.ArrayList;

import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class PufferFish {
	int choose;
	float x = 100f;
	float y;
	String namefish;
	SceneLoader scene;
	TransformComponent puffer;
	SpriteAnimationStateComponent puffer_ori;
	String[] listpuffer = {"pink_R", "pink_L","pink_C",
							"red_R", "red_L","red_C",
							"blue_R","blue_L","blue_C"};
	float[] list_y = {1000f,800f,600f,400f,200f,0f,-200f,-300f,-400f,-600f};
	ArrayList<PufferFish> aquarium = new ArrayList<PufferFish>();
	
	public PufferFish(SceneLoader scene,String namefish){
		this.namefish = namefish;
		this.scene = scene;
	}
	
	public void createPufferID(int id){
		System.out.println("createBearID  :  " + namefish + " id :" + id );
		puffer_ori =  ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(id), SpriteAnimationStateComponent.class);
		puffer = ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(id), TransformComponent.class);
	}
	
	public void randomFirstTime(){
		for(int i=0; i<10;i++){
			choose = (int) (Math.random()*8);
			PufferFish fish = new PufferFish(scene,listpuffer[choose]);
			fish.y = list_y[i];
			aquarium.add(fish);
		}
		for(int i=0;i<10;i++){
			System.out.println("Puffer: "+aquarium.get(i).namefish +"  Y:"+ aquarium.get(i).y);
		}
		System.out.println();
	}
	
	public void randomPuffer(){
		choose = (int) (Math.random()*8);
		aquarium.set(9, new PufferFish(scene, listpuffer[choose]));
		System.out.println(listpuffer[choose]);
	}
}
