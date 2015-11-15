package com.sleepygrizzly.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.sleepygrizzly.core.Bear;
import com.sleepygrizzly.core.CheckAction;
import com.sleepygrizzly.core.PufferFish;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;

public class MainScene extends Scene {

	private float elapsedTime = 0;

	Bear sleepybear;
	CheckAction action;
	String status = "playing";
	PufferFish puffersample;
	PufferFish puffernow;
	PufferFish pufferold;
	Sound waterfallsound;
	Sound birdsound;
	Sound bubblesound;
	Sound bombsound;
	BitmapFont scorefont;
	Timer gametime;
	long starttime;
	SpriteBatch batch;

	public MainScene(SceneManage sm, SceneLoader sl) {
		super(sm, sl);
	}

	public void create() {
		scene1.loadScene("Playbearscene");
		batch = new SpriteBatch();
		scorefont = new BitmapFont(Gdx.files.internal("data/font/yellowfont/yellowfont.fnt"),Gdx.files.internal("data/font/yellowfont/yellowfont.png"), false);
		sleepybear = new Bear(scene1, "standybear", 10);
		sleepybear.createBearID();
		puffersample = new PufferFish(scene1, "puffer_NAM");
		puffersample.randomFirstTime();
		action = new CheckAction();
		gametime = new Timer();
		waterfallsound = Gdx.audio.newSound(Gdx.files.internal("data/sound/waterfallsound.mp3"));
		birdsound = Gdx.audio.newSound(Gdx.files.internal("data/sound/birdsound.mp3"));
		bubblesound = Gdx.audio.newSound(Gdx.files.internal("data/sound/bubblesound.mp3"));
		bombsound = Gdx.audio.newSound(Gdx.files.internal("data/sound/bombsound.mp3"));
		waterfallsound.loop();
		birdsound.loop();
		gametime.start();
	}
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		puffernow = puffersample.aquarium.peek();
		sleepybear.move();
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			bubblesound.play();
			puffersample.aquarium.element().puffer.y = -173f;
			//puffersample.aquarium.element().move();
			pufferold = puffersample.aquarium.poll();
			if (action.isTrueSide("L", puffernow.namefish)) {
				for (PufferFish each: puffersample.aquarium) {
					each.move();
				}
				puffersample.randomPuffer();
			} 
			else {
				System.out.println("*******************\n" + "******Game Over****\n" + "*******************");
				//action.setScore(0);
				puffernow.pufferbomb(puffernow.namefish);
				bombsound.play();
				waterfallsound.stop();
				gametime.stop();
				System.out.println("Game Timer....." + gametime.toString());

			}
			for (PufferFish each: puffersample.aquarium) {
				System.out.print(each.namefish + "  y = " + each.puffer.y);
			}
			System.out.println("-------------------------------------------------------");

		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			bubblesound.play();
			puffersample.aquarium.element().puffer.y = -173f;
			//puffersample.aquarium.element().move();
			pufferold = puffersample.aquarium.poll();
			if (action.isTrueSide("R", puffernow.namefish)) {
				for (PufferFish each: puffersample.aquarium) {
					each.move();
				}
				puffersample.randomPuffer();
			} else {
				System.out.println("*******************\n" + "******Game Over****\n" + "*******************");
				//action.setScore(0);
				puffernow.pufferbomb(puffernow.namefish);
				bombsound.play();
				waterfallsound.stop();
				System.out.println("Game Timer....." + gametime.toString());
			}
			for (PufferFish each: puffersample.aquarium) {
				System.out.print(each.namefish + "-y-" + each.puffer.y);
			}
			
			System.out.println("\n-------------------------------------------------------");
		}
		batch.begin();
		scorefont.draw(batch, "SCORE: " + action.getScore(), 50f, 650f);
		batch.end();
	}

	@Override
	public void dispose() {
		// scene1.rootEntity.removeAll();
		
	}
}
