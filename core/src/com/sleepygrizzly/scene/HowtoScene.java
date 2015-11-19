package com.sleepygrizzly.scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sleepygrizzly.core.GameButton;
import com.sleepygrizzly.core.drawImage;
import com.uwsoft.editor.renderer.SceneLoader;

public class HowtoScene extends Scene{
	int x,y;
	int page = 1;
	int pagepointer = 0;
	int pointslide = 0;
	private float elapsedTime = 0;
	private SpriteBatch sp;
	private boolean isClickHome = false;
	private boolean isClickLeft = false;
	private boolean isClickRight = false;
	
	GameButton homebutton, leftbutton, rightbutton;
	drawImage img01, img02, img03, img04, img05;
	
	ArrayList<drawImage> imglist = new ArrayList<drawImage>();

	
	public HowtoScene(SceneManage sm, SceneLoader scene) {
		super(sm, scene);
		Viewport vp = new FitViewport(1024, 668);
		scene1.loadScene("HowtoScene", vp);
	}

	@Override
	public void create() {
		sp = new SpriteBatch();
		
		homebutton = new GameButton("05");
		leftbutton = new GameButton("06");
		rightbutton = new GameButton("07");
		homebutton.createButton();
		leftbutton.createButton();
		rightbutton.createButton();
		
		img01 = new drawImage(scene1, 9);
		img02 = new drawImage(scene1, 11);
		img03 = new drawImage(scene1, 71);
		img04 = new drawImage(scene1, 8);
		img05 = new drawImage(scene1, 72);
		
		imglist.add(img01);
		imglist.add(img02);
		imglist.add(img03);
		imglist.add(img04);
		imglist.add(img05);
	}

	@Override
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		x = Gdx.input.getX();
		y = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		
		sp.begin();
		sp.draw(homebutton.button, 379, 23);
		if(pagepointer > 0){
			sp.draw(leftbutton.button, 417, 126);
		}
		if(pagepointer < 4){
			sp.draw(rightbutton.button, 520, 125);
		}
		
		img01.createImage();
		img02.createImage();
		img03.createImage();
		img04.createImage();
		img05.createImage();
		
		if (homebutton.buttonPoly.contains(x, y)) {
			sp.draw(homebutton.buttonClick, 379, 23);		
		} else if (leftbutton.buttonPoly.contains(x, y)  && pagepointer > 0) {
			sp.draw(leftbutton.buttonClick, 417, 126);		
		} else if (rightbutton.buttonPoly.contains(x, y) && pagepointer < 4) {
			sp.draw(rightbutton.buttonClick, 520, 125);		
		}
		
		if(pagepointer == 0){
			imglist.get(0).moveupposi();
		}
		
		
		sp.end();
		if (Gdx.input.justTouched()) {
			if (homebutton.buttonPoly.contains(x, y)) {
				if(Gdx.input.justTouched()){
					pagepointer = 0;
					this.sm.setScene("menu");
					
				}
			} else if (leftbutton.buttonPoly.contains(x, y)) {
				if(Gdx.input.justTouched()){
					if(pagepointer > 0){
						pagepointer--;
						pointslide = -1;
						System.out.println("LL" + pagepointer);
						System.out.println("LL" +pointslide);
					}
				}
			} else if (rightbutton.buttonPoly.contains(x, y)) {
				if(Gdx.input.justTouched()){
					if(pagepointer < 4){
						pagepointer++;
						pointslide = 1;
						System.out.println("RR" +pagepointer);
						System.out.println("RR" +pointslide);
					}
				}
			}
			
			imglist.get(pagepointer).moveupposi();
			if(pointslide == -1){
				imglist.get(pagepointer+1).movedownposi();
			} else if(pointslide == 1 && pagepointer != 0){
				imglist.get(pagepointer-1).movedownposi();
			}
			System.out.println(pagepointer);
			System.out.println(pointslide);	
			
		}
	}

	@Override
	public void dispose() {
		
		
	}
	
	
}
