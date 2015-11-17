package com.sleepygrizzly.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sleepygrizzly.core.Bear;
import com.sleepygrizzly.core.checkAction;
import com.sleepygrizzly.core.PufferFish;
import com.sleepygrizzly.core.TimeLimitBar;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;

public class MainScene extends Scene {

	private float elapsedTime = 0;

	Bear sleepybear;
	Bear beartime;
	checkAction action;
	String status = "playing";
	PufferFish puffersample;
	PufferFish puffernow;
	PufferFish pufferold;
	Sound waterfallsound;
	Sound birdsound;
	Sound bubblesound;
	Sound bombsound;
	BitmapFont scorefont;
	long starttime;
	float owl = 50;
	SpriteBatch batch;
	TimeLimitBar bar;

	public MainScene(SceneManage sm, SceneLoader sl) {
		super(sm, sl);
		
	}

	public void create() {
		Viewport vp = new FitViewport(1024, 668); 
		scene1.loadScene("Playbearscene", vp);
		batch = new SpriteBatch();
		scorefont = new BitmapFont(Gdx.files.internal("data/font/yellowfont/yellowfont.fnt"),Gdx.files.internal("data/font/yellowfont/yellowfont.png"), false);
		sleepybear = new Bear(scene1, "standybear", 10);
		sleepybear.createBearID();
		beartime = new Bear(scene1, "bearpointer", 12);
		bar = new TimeLimitBar(beartime);
		bar.createBar();
		puffersample = new PufferFish(scene1, "puffer_NAM");
		puffersample.randomFirstTime();
		action = new checkAction();
		/*
		waterfallsound = Gdx.audio.newSound(Gdx.files.internal("data/sound/waterfallsound.mp3"));
		birdsound = Gdx.audio.newSound(Gdx.files.internal("data/sound/birdsound.mp3"));
		*/
		bubblesound = Gdx.audio.newSound(Gdx.files.internal("data/sound/bubblesound.mp3"));
		bombsound = Gdx.audio.newSound(Gdx.files.internal("data/sound/bombsound.mp3"));
		//waterfallsound.loop();
		//birdsound.loop();
		starttime = System.currentTimeMillis();
	}
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		puffernow = puffersample.aquarium.peek();
		sleepybear.move();
		bar.movePointer(owl);
		if(owl>0){
			owl -= (float)(((System.currentTimeMillis() - starttime)/1000)*0.01);
		}
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			bubblesound.play();
			puffersample.aquarium.element().puffer.y = -295f;
			//puffersample.aquarium.element().move();
			pufferold = puffersample.aquarium.poll();
			if (action.isTrueSide("L", puffernow.namefish)) {
				owl += 1;
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
				//waterfallsound.stop();
				owl = 0;

			}
			for (PufferFish each: puffersample.aquarium) {
				System.out.print(each.namefish + "  y = " + each.puffer.y);
			}
			System.out.println("-------------------------------------------------------");

		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			bubblesound.play();
			puffersample.aquarium.element().puffer.y = -295f;
			//puffersample.aquarium.element().move();
			pufferold = puffersample.aquarium.poll();
			if (action.isTrueSide("R", puffernow.namefish)) {
				for (PufferFish each: puffersample.aquarium) {
					owl += 1;
					each.move();
				}
				puffersample.randomPuffer();
			} else {
				System.out.println("*******************\n" + "******Game Over****\n" + "*******************");
				//action.setScore(0);
				puffernow.pufferbomb(puffernow.namefish);
				bombsound.play();
				//waterfallsound.stop();
				owl = 0;
			}
			for (PufferFish each: puffersample.aquarium) {
				System.out.print(each.namefish + "-y-" + each.puffer.y);
			}
			
			System.out.println("\n-------------------------------------------------------");
		}
		if(owl < 0){
			puffersample.aquarium.element().puffer.y = -295f;
			pufferold = puffersample.aquarium.poll();
			System.out.println("*******************\n" + "******Game Over****\n" + "*******************");
			//action.setScore(0);
			puffernow.pufferbomb(puffernow.namefish);
			bombsound.play();
			//waterfallsound.stop();
			owl = 0;
		}
		if(owl>100){
			owl = 100;
		}
		batch.begin();
		scorefont.draw(batch, "SCORE: " + action.getScore(), 50f, 650f);
		scorefont.draw(batch, "TIME: " + ((System.currentTimeMillis() - starttime)/ 1000), 750f, 650f);
		scorefont.draw(batch, "OWL: " + (int)owl +"%", 750f, 450f);
		batch.end();
	}

	@Override
	public void dispose() {
		// scene1.rootEntity.removeAll();
		
	}
}
