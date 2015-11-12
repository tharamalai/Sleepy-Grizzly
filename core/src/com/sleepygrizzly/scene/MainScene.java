package com.sleepygrizzly.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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

	Bear standybear_L;
	Bear standybear_R;
	CheckAction action;
	String status = "playing";
	PufferFish puffersample;
	PufferFish puffernow;

	public MainScene(SceneManage sm, SceneLoader sl) {
		super(sm, sl);
	}

	public void create() {
		scene1.loadScene("Playbearscene");
		standybear_L = new Bear(scene1, "standybear_L", 9);
		standybear_L.createBearID();
		// standybear_L.createBearSprite("standybear");
		standybear_R = new Bear(scene1, "standybear_R", 2);
		standybear_R.createBearID();
		// standybear_R.createBearSprite("standybear");
		puffersample = new PufferFish(scene1, "puffer_NAM");
		puffersample.randomFirstTime();
		action = new CheckAction();
	}

	int i;
	int j;

	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		puffernow = puffersample.aquarium.get(0);
		// batch.begin();
		standybear_R.move();
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			// standybear_L.bear.x = -25f;
			// standybear_L.bear.x = -10f;
			// batch.draw(standybear_R.ani_bear.getKeyFrame(elapsedTime, true),
			// 100, 300);
			System.out.println("left  " + i);
			i += 1;
			if (action.isTrueSide("nam -- L", puffernow.namefish)) {
				for (int i = 0; i < 8; i++) {
					puffersample.aquarium.set(i, puffersample.aquarium.get(i + 1));
					puffersample.aquarium.get(i).y += 200f;
				}
				puffersample.randomPuffer();
			} else {
				System.out.println("*******************\n" + "******Game Over****\n" + "*******************");
				action.setScore(0);

			}
			for (int i = 0; i < 10; i++) {
				System.out.print(puffersample.aquarium.get(i).namefish + "-y-" + puffersample.aquarium.get(i).y);
			}
			System.out.println("-------------------------------------------------------");

			// bear_l.x = -25f;
			// bear.scaleX = 1f;
			// batch.draw(ani_bear.getKeyFrame(elapsedTime, true), 100, 300);
			// bear.rotation = 90f;
			/*
			 * sp_bear.flip(true, false); sp_bear.setCenterX(1000);
			 * sp_bear.setScale(-1000f, 0); sp_bear.isFlipX();
			 */
			// bear.enableTransform();
			// sprite.

		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			// standybear_R.sp_bear.setX(100f);
			// batch.draw(standybear_R.ani_bear.getKeyFrame(elapsedTime, true),
			// 100, 100);
			// standybear_L.bear.x = 250f;
			// standybear_R.bear.x = 250f;
			// standybear_R.bear.x = 100f;

			// standybear_R.bear.x = 100f;

			if (action.isTrueSide("nam -- R", puffernow.namefish)) {
				System.out.println("right  " + j);
				j += 1;
				for (int i = 0; i < 8; i++) {
					puffersample.aquarium.set(i, puffersample.aquarium.get(i + 1));
					puffersample.aquarium.get(i).y += 200f;
				}
				puffersample.randomPuffer();
			} else {
				System.out.println("*******************\n" + "******Game Over****\n" + "*******************");
				action.setScore(0);
			}
			for (int i = 0; i < 10; i++) {
				System.out.print(puffersample.aquarium.get(i).namefish + "-y-" + puffersample.aquarium.get(i).y);
			}
			System.out.println("\n-------------------------------------------------------");
			// batch.draw(ani_bear.getKeyFrame(elapsedTime, true), 500, 300);

			/*
			 * bear_r.x = 250f; sp_bear.flip(true, false);
			 * sp_bear.setPosition(800, 300); sp_bear.setCenterX(3000);
			 */

		}
		// batch.draw(ani_bear.getKeyFrame(elapsedTime, true), 100, 300);
		// batch.begin();
		// batch.draw(standybear_R.ani_bear.getKeyFrame(elapsedTime, true), 100,
		// 300);
		// batch.end();

		// --now--sprite.setFlip(false, true);
		// batch.draw(ani_bear.getKeyFrame(elapsedTime, true), 100, 300);
		// batch.draw(sp_bear, sp_bear.getX(), sp_bear.getY());
		// sprite.setFlip(false, true);
		// batch.draw(sp_bear, 500, 350, 169, 240);
		// batch.draw(sp_bear, 169, 240, transform);

		// batch.end();
		
		/*if (Gdx.input.justTouched()) {
			sm.setScene("play");
		}*/
	}

	@Override
	public void dispose() {
		// scene1.rootEntity.removeAll();
		
	}
}
