package com.sleepygrizzly.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class PufferFish {
	int choose;
	float x = 100f;
	public float y = -500f;
	public String namefish;
	SceneLoader scene;
	TransformComponent puffer;
	SpriteAnimationStateComponent puffer_ori;
	String[] listpuffer = { "Opurple_R", "Opurple_L", "Opurple_C", "Ored_R", "Ored_L", "Ored_C", "Oblue_R", "Oblue_L",
			"Oblue_C", "Ogreen_R", "Ogreen_L", "Ogreen_C", "Oorange_R", "Oorange_L", "Oorange_C" };
	int[] pufferoverlap = { 1, 2, 3 };
	float[] posi_y = { 1000f, 800f, 600f, 400f, 200f, 0f, -200f, -300f, -400f, -600f };
	Map<String, Integer> posi_x = new HashMap<String, Integer>();

	public ArrayList<PufferFish> aquarium = new ArrayList<PufferFish>();

	public PufferFish(SceneLoader scene, String namefish) {
		this.namefish = namefish;
		this.scene = scene;
	}

	public void createPufferID(int id) {
		System.out.println("createBearID  :  " + namefish + " id :" + id);
		puffer_ori = ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(id),
				SpriteAnimationStateComponent.class);
		puffer = ComponentRetriever.get(scene.entityFactory.getEntityByUniqueId(id), TransformComponent.class);
	}

	public void randomFirstTime() {
		for (int i = 0; i < 10; i++) {
			choose = (int) (Math.random() * 8);
			PufferFish fish = new PufferFish(scene, listpuffer[choose]);
			fish.y = posi_y[i];
			aquarium.add(fish);
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("Puffer: " + aquarium.get(i).namefish + "  Y:" + aquarium.get(i).y);
		}
		System.out.println();
	}

	public void randomPuffer() {
		choose = (int) (Math.random() * 15);
		aquarium.set(9, new PufferFish(scene, listpuffer[choose]));
		System.out.println(listpuffer[choose]);
	}
}
