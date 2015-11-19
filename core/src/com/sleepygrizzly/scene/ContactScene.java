package com.sleepygrizzly.scene;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sleepygrizzly.core.GameButton;
import com.sleepygrizzly.core.Person;
import com.uwsoft.editor.renderer.SceneLoader;

public class ContactScene extends Scene{
	private float elapsedTime = 0;
	private SpriteBatch sp;
	private boolean isClickHome = false;
	GameButton homebutton;
	int x,y;
	public ContactScene(SceneManage sm, SceneLoader scene){
		super(sm, scene);
		Viewport vp = new FitViewport(1024, 668);
		scene1.loadScene("ScoreboardScene", vp);
	}

	@Override
	public void create(){
		sp = new SpriteBatch();
		
		homebutton = new GameButton("05");
		homebutton.createButton();


	}

	@Override
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		
		x = Gdx.input.getX();
		y = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		
		sp.begin();
		sp.draw(homebutton.button, 379, 23);
		if (homebutton.buttonPoly.contains(x, y)) {
			sp.draw(homebutton.buttonClick, 379, 23);		
		}
		
		sp.end();
		
		/**** Check is Touched ****/
		if (Gdx.input.isTouched()) {
			if (homebutton.buttonPoly.contains(x, y)) {
				this.sm.setScene("menu");
			}
		}
	}
	
	@Override
	public void dispose() {
		
	}

}
