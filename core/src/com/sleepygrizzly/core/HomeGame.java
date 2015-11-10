package com.sleepygrizzly.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.sprite.SpriteAnimationStateComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class HomeGame extends Game{
	private SceneLoader scene1;
	private Viewport vp;
	
	TransformComponent bear_l;
	SpriteAnimationStateComponent bearstate_l;
	TransformComponent bear_r;
	SpriteAnimationStateComponent bearstate_r;
	SpriteBatch batch;
	/*Sprite sp_bear;
	Texture img_bear;
	TextureAtlas at_bear;
	Animation ani_bear;*/
	private float elapsedTime = 0;
	
	Bear standybear_L;
	Bear standybear_R;
	CheckAction action;
	String status = "playing";
	PufferFish puffersample;
	PufferFish puffernow;
	
	@Override
	public void create() {
		scene1 = new SceneLoader();
		vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		scene1.loadScene("Playbearscene");
		/*
		bearstate_l =  ComponentRetriever.get(
				scene1.entityFactory.getEntityByUniqueId(9), SpriteAnimationStateComponent.class);
		bear_l = ComponentRetriever.get(scene1.entityFactory.getEntityByUniqueId(9), TransformComponent.class);
		
		bearstate_r =  ComponentRetriever.get(
				scene1.entityFactory.getEntityByUniqueId(2), SpriteAnimationStateComponent.class);
		bear_r = ComponentRetriever.get(scene1.entityFactory.getEntityByUniqueId(2), TransformComponent.class);
		*/
		Bear standybear_L = new Bear(scene1,"standybear_L",9);
		standybear_L.createBearID();
		Bear standybear_R = new Bear(scene1,"standybear_R",2);
		standybear_R.createBearID();
		standybear_R.createBearSrite("standybear");
		puffersample = new PufferFish(scene1,"puffer_NAM");
		puffersample.randomFirstTime();
		action = new CheckAction();
		batch = new SpriteBatch();
		/*
		batch = new SpriteBatch();
		img_bear = new Texture("data/standybear/standybear.png");
		sp_bear = new Sprite(img_bear);
		sp_bear.setFlip(true, false);
		at_bear = new TextureAtlas(Gdx.files.internal("data/standybear/standybear.atlas"));
		ani_bear = new Animation(1/15f, at_bear.getRegions());*/
		
		
	}
	int i;
	int j;
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		scene1.getEngine().update(Gdx.graphics.getDeltaTime());
		Gdx.input.setInputProcessor(null);
		//sp_bear.setSize(169, 240);
		elapsedTime += Gdx.graphics.getDeltaTime();
		puffernow = puffersample.aquarium.get(0);
		//batch.begin();
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			//standybear_L.bear.x = -25f;
			//batch.draw(standybear_R.ani_bear.getKeyFrame(elapsedTime, true), 100, 300);
			System.out.println("left  "+i);
			i += 1;
			if(action.isTrueSide("nam -- L", puffernow.namefish)){
				for(int i=0;i<8;i++){
					puffersample.aquarium.set(i, puffersample.aquarium.get(i+1));
					puffersample.aquarium.get(i).y += 200f;
				}
				puffersample.randomPuffer();
			}
			else{
				dispose();
			}
			for(int i=0;i<10;i++){
				System.out.print(puffersample.aquarium.get(i).namefish + "-y-"+puffersample.aquarium.get(i).y);
			}
			System.out.println("-------------------------------------------------------");
			
			
			//bear_l.x = -25f;
			//bear.scaleX = 1f;
			//batch.draw(ani_bear.getKeyFrame(elapsedTime, true), 100, 300);
			//bear.rotation = 90f;
			/*
			sp_bear.flip(true, false);
			sp_bear.setCenterX(1000);
			sp_bear.setScale(-1000f, 0);
			sp_bear.isFlipX();
			*/
			//bear.enableTransform();
			//sprite.
		
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			//standybear_R.sp_bear.setX(100f);
			//batch.draw(standybear_R.ani_bear.getKeyFrame(elapsedTime, true), 100, 100);
			//standybear_L.bear.x = 250f;
			//standybear_R.bear.x = 250f;
			if(action.isTrueSide("nam -- R", puffernow.namefish)){
				System.out.println("right  "+j);
				j+=1;
				for(int i=0;i<8;i++){
					puffersample.aquarium.set(i, puffersample.aquarium.get(i+1));
					puffersample.aquarium.get(i).y += 200f;
				}
				puffersample.randomPuffer();
			}
			else{
				dispose();
			}
			for(int i=0;i<10;i++){
				System.out.print(puffersample.aquarium.get(i).namefish + "-y-"+puffersample.aquarium.get(i).y);
			}
			System.out.println("-------------------------------------------------------");
			//batch.draw(ani_bear.getKeyFrame(elapsedTime, true), 500, 300);
			
			/*bear_r.x = 250f;
			sp_bear.flip(true, false);
			sp_bear.setPosition(800, 300);
			sp_bear.setCenterX(3000);
			*/
			
		}
		//batch.draw(ani_bear.getKeyFrame(elapsedTime, true), 100, 300);
		//batch.begin();
		//batch.draw(standybear_R.ani_bear.getKeyFrame(elapsedTime, true), 100, 300);
		//batch.end();
		
		//--now--sprite.setFlip(false, true);
		//batch.draw(ani_bear.getKeyFrame(elapsedTime, true), 100, 300);
        //batch.draw(sp_bear, sp_bear.getX(), sp_bear.getY());
        //sprite.setFlip(false, true);
		//batch.draw(sp_bear, 500, 350, 169, 240);
		//batch.draw(sp_bear, 169, 240, transform);
		
        //batch.end();
	}

}
