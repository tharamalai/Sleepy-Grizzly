package com.sleepygrizzly.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Timer;
import com.sleepygrizzly.core.Bear;
import com.sleepygrizzly.core.CheckAction;
import com.sleepygrizzly.core.PufferFish;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;

public class MainScene extends Scene {

	TransformComponent bear_l;
	SpriteAnimationStateComponent bearstate_l;
	TransformComponent bear_r;
	SpriteAnimationStateComponent bearstate_r;

	private float elapsedTime = 0;

	Bear sleepybear;
	CheckAction action;
	String status = "playing";
	PufferFish puffersample;
	PufferFish puffernow;

	public MainScene(SceneManage sm, SceneLoader sl) {
		super(sm, sl);
	}

	public void create() {
		scene1.loadScene("Playbearscene");
		sleepybear = new Bear(scene1, "standybear_R", 10);
		sleepybear.createBearID();
		puffersample = new PufferFish(scene1, "puffer_NAM");
		puffersample.randomFirstTime();
		action = new CheckAction();
	}

	int i;
	int j;

	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		Timer timer = new Timer();
		timer.start();
		puffernow = puffersample.aquarium.peek();
		sleepybear.move();
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			System.out.println(timer.toString());
			puffersample.aquarium.element().puffer.y = -673;
			puffersample.aquarium.poll();
			System.out.println("left  " + i);
			i += 1;
			if (action.isTrueSide("nam -- L", puffernow.namefish)) {
				for (PufferFish each: puffersample.aquarium) {
					each.move();
				}
				puffersample.randomPuffer();
			} 
			else {
				System.out.println("*******************\n" + "******Game Over****\n" + "*******************");
				action.setScore(0);
				puffernow.pufferbomb(puffernow.namefish);

			}
			for (PufferFish each: puffersample.aquarium) {
				System.out.print(each.namefish + "  y = " + each.puffer.y);
			}
			System.out.println("-------------------------------------------------------");

		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			puffersample.aquarium.element().puffer.y = -673;
			puffersample.aquarium.poll();
			if (action.isTrueSide("nam -- R", puffernow.namefish)) {
				System.out.println("right  " + j);
				j += 1;
				for (PufferFish each: puffersample.aquarium) {
					each.move();
				}
				puffersample.randomPuffer();
			} else {
				System.out.println("*******************\n" + "******Game Over****\n" + "*******************");
				action.setScore(0);
				puffernow.pufferbomb(puffernow.namefish);
			}
			for (PufferFish each: puffersample.aquarium) {
				System.out.print(each.namefish + "-y-" + each.puffer.y);
			}
			System.out.println("\n-------------------------------------------------------");
			
		}
	}

	@Override
	public void dispose() {
		// scene1.rootEntity.removeAll();
		
	}
}
