package com.sleepygrizzly.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sleepygrizzly.core.GameButton;
import com.sleepygrizzly.core.Person;
import com.sleepygrizzly.core.checkAction;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent.ButtonListener;

public class MenuScene extends Scene {
	private float elapsedTime = 0;
	private boolean isClickPlay = false;
	private boolean isClickHowto = false;
	private boolean isClickContact = false;
	private boolean isClickQuit = false;
	private SpriteBatch sp;
	
	GameButton playbutton, howtobutton, contactbutton, quitbutton; 
	
	int x,y;
	public MenuScene(SceneManage sm, SceneLoader scene) {
		super(sm, scene);
		Viewport vp = new FitViewport(1024, 668);
		scene1.loadScene("MenuScene", vp);
		Person.getPerson();
	}

	@Override
	public void create() {
		sp = new SpriteBatch();
		
		playbutton = new GameButton("01");
		howtobutton = new GameButton("02");
		contactbutton = new GameButton("03");
		quitbutton = new GameButton("04");

		playbutton.createButton();
		howtobutton.createButton();
		contactbutton.createButton();
		quitbutton.createButton();
	}

	@Override
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		
		x = Gdx.input.getX();
		y = Gdx.graphics.getHeight() - Gdx.input.getY();
		
			
		sp.begin();
		sp.draw(playbutton.button, 394, 418);
		sp.draw(howtobutton.button, 394, 352);
		sp.draw(contactbutton.button, 394, 292);
		sp.draw(quitbutton.button, 394, 232);
		
		if (playbutton.buttonPoly.contains(x, y)) {
			sp.draw(playbutton.buttonClick, 394, 418);		
		} else if(howtobutton.buttonPoly.contains(x, y)){
			sp.draw(howtobutton.buttonClick, 394, 352);
		} else if(contactbutton.buttonPoly.contains(x, y)){
			sp.draw(contactbutton.buttonClick, 394, 292);
		} else if(quitbutton.buttonPoly.contains(x, y)){
			sp.draw(quitbutton.buttonClick, 394, 232);
		}
		
		sp.end();
		
		/**** Check is Touched ****/
		if (Gdx.input.isTouched()) {
			if (playbutton.buttonPoly.contains(x, y)) {
				isClickPlay = true;
				Person.person.setScore(0);
			} else if (howtobutton.buttonPoly.contains(x, y)) {
				isClickHowto = true;
			} else if (contactbutton.buttonPoly.contains(x, y)) {
				isClickContact = true;
			} else if (quitbutton.buttonPoly.contains(x, y)) {
				isClickQuit = true;
			}
		}
		
		/**** Change Scene ****/
		if (isClickPlay) {
			this.sm.setScene("main");
		} else if(isClickHowto) {
			this.sm.setScene("howtoplay");
		} else if(isClickContact) {
			this.sm.setScene("contact");
		} else if(isClickQuit) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
	}

}
