package com.sleepygrizzly.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sleepygrizzly.core.Bear;
import com.sleepygrizzly.core.GameButton;
import com.sleepygrizzly.core.Person;
import com.sleepygrizzly.core.checkAction;
import com.sleepygrizzly.core.PufferFish;
import com.sleepygrizzly.core.TimeLimitBar;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;

public class MainScene extends Scene {

	private float elapsedTime = 0;
	private String playstatus = "playing";
	private int count = 6;
	private int counttime = 100;
	private long loadscene;
	private long endscene;
	private long startscene;
	private long starttime;
	float owl = 50;
	int x,y;
	
	checkAction action;
	Bear sleepybear;
	Bear beartime;
	String status = "playing";
	PufferFish puffersample;
	PufferFish puffernow;
	PufferFish pufferold;
	Sound waterfallsound;
	Sound birdsound;
	Sound bubblesound;
	Sound bombsound;
	BitmapFont scorefont;
	BitmapFont readyfont;
	SpriteBatch batch;
	TimeLimitBar bar;
	GameButton homemini; 
	private SpriteBatch sp;
	ShapeRenderer sr;
	private boolean isClickHome = false;
	
	
	public MainScene(SceneManage sm, SceneLoader sl) {
		super(sm, sl);
		Viewport vp = new FitViewport(1024, 668); 
		scene1.loadScene("Playbearscene", vp);
		Person.getPerson();
	}

	public void create() {
		sp = new SpriteBatch();
		sr = new ShapeRenderer();
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
		
		readyfont = new BitmapFont(Gdx.files.internal("data/font/readyfont/readyfont.fnt"),Gdx.files.internal("data/font/readyfont/readyfont.png"), false);
		
		waterfallsound = Gdx.audio.newSound(Gdx.files.internal("data/sound/waterfallsoundcut.mp3"));
		birdsound = Gdx.audio.newSound(Gdx.files.internal("data/sound/birdsoundcut.mp3"));
		
		bubblesound = Gdx.audio.newSound(Gdx.files.internal("data/sound/bubblesound.mp3"));
		bombsound = Gdx.audio.newSound(Gdx.files.internal("data/sound/bombsound.mp3"));
		waterfallsound.loop();
		birdsound.loop();
		homemini = new GameButton("13");
		homemini.createButton();
		starttime = System.currentTimeMillis();
		action.setScore(Person.getPerson().getScore());
	}
	
	public String getPlaystatus() {
		return playstatus;
	}

	public void setPlaystatus(String playstatus) {
		this.playstatus = playstatus;
	}
	
	public void render() {
		x = Gdx.input.getX();
		y = Gdx.graphics.getHeight() - Gdx.input.getY();
		elapsedTime += Gdx.graphics.getDeltaTime();
		
		if(getPlaystatus().equals("playing")){
			
			
			/*-------------- Home mini Button --------------*/
			sp.begin();
			sp.draw(homemini.button, 920, 576);
			
			if (homemini.buttonPoly.contains(x, y)) {
				sp.draw(homemini.buttonClick, 920, 576);		
			} 
			sp.end();
			
			if (Gdx.input.isTouched()) {
				if (homemini.buttonPoly.contains(x, y)) {
					this.sm.setScene("menu");
				} 
			}
			/*--------------------------------------------*/
			
			puffernow = puffersample.aquarium.peek();
			sleepybear.move();
			bar.movePointer(owl);
			if(owl>0){
				owl -= (float)(((System.currentTimeMillis() - starttime)/1000)*0.05);
			}
			if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
				bubblesound.play();
				puffersample.aquarium.element().puffer.y = -295f;
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
					puffernow.pufferbomb(puffernow.namefish);
					bombsound.play();
					waterfallsound.stop();
					owl = 0;
					endscene = System.currentTimeMillis();
					counttime = 1000;
					Person.person.setScore(action.getScore());
					setPlaystatus("minigame");

				}
				for (PufferFish each: puffersample.aquarium) {
					System.out.print(each.namefish + "  y = " + each.puffer.y);
				}
				System.out.println("-------------------------------------------------------");

			}
			if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
				bubblesound.play();
				puffersample.aquarium.element().puffer.y = -295f;
				pufferold = puffersample.aquarium.poll();
				if (action.isTrueSide("R", puffernow.namefish)) {
					for (PufferFish each: puffersample.aquarium) {
						owl += 1;
						each.move();
					}
					puffersample.randomPuffer();
				} else {
					System.out.println("*******************\n" + "******Game Over****\n" + "*******************");
					puffernow.pufferbomb(puffernow.namefish);
					bombsound.play();
					waterfallsound.stop();
					owl = 0;
					endscene = System.currentTimeMillis();
					counttime = 1000;
					Person.person.setScore(action.getScore());
					setPlaystatus("minigame");
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
				puffernow.pufferbomb(puffernow.namefish);
				bombsound.play();
				waterfallsound.stop();
				owl = 0;
				endscene = System.currentTimeMillis();
				counttime = 1000;
				Person.person.setScore(action.getScore());
				setPlaystatus("minigame");
			}
			if(owl>100){
				owl = 100;
			}
			batch.begin();
			scorefont.draw(batch, "SCORE: " + action.getScore(), 50f, 650f);
			batch.end();
		}
		else if(getPlaystatus().equals("minigame")){
			if(((System.currentTimeMillis() - endscene)  <= counttime)){
				batch.begin();
				readyfont.draw(batch, "GAME\n OVER", 170, 600);
				batch.end();
				System.out.println(System.currentTimeMillis() - endscene);
			}
			else{
				this.sm.setScene("microwave");
				System.out.println("setscene");
			}
		}
		
		
		
	}
	@Override
	public void dispose() {
		
	}
}
