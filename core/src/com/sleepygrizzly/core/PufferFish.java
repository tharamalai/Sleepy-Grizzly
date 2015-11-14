package com.sleepygrizzly.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class PufferFish {
	int screentus;
	int idfish;
	int choose;
	int idbomb;
	public PufferFish fish;
	public String namefish;
	SceneLoader scene;
	public TransformComponent puffer;
	public SpriteAnimationStateComponent puffer_ori;
	String[] listpuffer = {"Opurple_C", "Ored_C", "Oblue_C","Ogreen_C", "Oorange_C",
							"Opurple_L","Ored_L","Oblue_L","Ogreen_L","Oorange_L",
							"Opurple_R","Ored_R","Oblue_R","Ogreen_R","Oorange_R"};
	int[] listid = {16,37,14,17,15,
					23,22,21,20,19,
					13,24,4,3,11};
	int[] listbomb = { 30, 27, 39, 33, 32,
						30, 27, 18, 26, 32,
						34, 38, 39, 33,36};
	float[] posi_y = { 173f, 107f, 41f, -25f, -91f, -157f, -223f, -289f };
	Map<String, Integer> posi_x = new HashMap<String, Integer>();

	public Queue<PufferFish> aquarium = new LinkedList<PufferFish>();
	
	public PufferFish(SceneLoader scene, String namefish) {
		this.namefish = namefish;
		this.scene = scene; 
	}
	
	public PufferFish(SceneLoader scene, String namefish, int idfish) {
		this.namefish = namefish;
		this.scene = scene;
		this.idfish = idfish; 
	}

	public void createPufferID() {
		System.out.println("create Puffer ID  :  " + namefish + " id :" + idfish);
		puffer_ori = ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(idfish),
				SpriteAnimationStateComponent.class);
		puffer = ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(idfish), TransformComponent.class);
	}

	public void randomFirstTime() {
		screentus = 0;
		while(screentus < 8){
			if(screentus<=3){
				choose = (int) (Math.random() * 4);
			}else{
				choose = (int) (Math.random() * 14);
			}
			if(!checksame(listpuffer[choose])){
				fish = new PufferFish(scene, listpuffer[choose], listid[choose]);
				fish.createPufferID();
				fish.puffer.y = posi_y[screentus];
				aquarium.add(fish);
				screentus += 1;
			}
		}
		for (PufferFish each: aquarium) {
			System.out.println("Puffer: " + each.namefish + "  Y:" + each.puffer.y);
		}
		System.out.println();
	}

	public void randomPuffer() {
		do{
			choose = (int) (Math.random() * 14);
		}while(checksame(listpuffer[choose]));
		fish = new PufferFish(scene, listpuffer[choose],listid[choose]);
		fish.createPufferID();
		fish.puffer.y = -289f;
		aquarium.add(fish);
		System.out.println(listpuffer[choose]);
	}
	
	public void pufferbomb(String bombname){
		for(int i = 0; i< listpuffer.length;i++){
			if(listpuffer[i] == bombname){
				idbomb = listbomb[i];
				break;
			}
		}
		fish = new PufferFish(scene, bombname, idbomb);
		fish.createPufferID();
		fish.puffer.y = 173f;
	}
	
	public void move(){
		puffer.y += 66f; 
	}
	
	public boolean checksame(String now){
		for(PufferFish each: aquarium){
			if(each.namefish.equals(now)){
				return true;
			}
		}
		return false;
	}
}
