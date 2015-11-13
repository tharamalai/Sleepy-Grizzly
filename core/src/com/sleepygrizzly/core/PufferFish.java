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
	int idfish;
	int choose;
	float x = 100f;
	public float y = -500f;
	public String namefish;
	SceneLoader scene;
	TransformComponent puffer;
	SpriteAnimationStateComponent puffer_ori;
	String[] listpuffer = { "Opurple_R", "Opurple_L", "Opurple_C",
							"Ored_R", "Ored_L", "Ored_C", 
							"Oblue_R", "Oblue_L","Oblue_C",
							"Ogreen_R", "Ogreen_L", "Ogreen_C", 
							"Oorange_R", "Oorange_L", "Oorange_C" };
	int[] listid = {13, 23, 16,
					24,22,18,
					4,21,14,
					3,20,17,
					11,19,15};
	int[] pufferoverlap = { 1, 2, 3 };
	float[] posi_y = { 353f, 239f, 125f, 11f, -103f, -217f, -331f, -445f, -559f, -673f };
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
		for (int i = 0; i < 10; i++) {
			choose = (int) (Math.random() * 8);
			PufferFish fish = new PufferFish(scene, listpuffer[choose], listid[choose]);
			fish.createPufferID();
			fish.y = posi_y[i];
			aquarium.add(fish);
		}
		for (PufferFish each: aquarium) {
			System.out.println("Puffer: " + each.namefish + "  Y:" + each.y);
		}
		System.out.println();
	}

	public void randomPuffer() {
		choose = (int) (Math.random() * 15);
		PufferFish fish2 = new PufferFish(scene, listpuffer[choose],listid[choose]);
		fish2.createPufferID();
		fish2.y = -673;
		aquarium.add(fish2);
		System.out.println(listpuffer[choose]);
	}
	
	public void move(){
		puffer.y = y;
		//puffer.scaleX
	}
}
