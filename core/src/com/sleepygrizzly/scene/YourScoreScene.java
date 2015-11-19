package com.sleepygrizzly.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sleepygrizzly.core.GameButton;
import com.sleepygrizzly.core.Person;
import com.uwsoft.editor.renderer.SceneLoader;

public class YourScoreScene extends Scene{
	private float elapsedTime = 0;
	private SpriteBatch sp;
	GameButton homebutton;
	int x,y;
	BitmapFont scorefont;
	
	
	public YourScoreScene(SceneManage sm, SceneLoader scene) {
		super(sm, scene);
		Viewport vp = new FitViewport(1024, 668);
		scene1.loadScene("YourScoreScene", vp);
	}

	@Override
	public void create() {
		sp = new SpriteBatch();
		
		homebutton = new GameButton("05");
		homebutton.createButton();
		scorefont = new BitmapFont(Gdx.files.internal("data/font/readyfont/readyfont.fnt"),Gdx.files.internal("data/font/readyfont/readyfont.png"), false);
	}

	@Override
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		
		x = Gdx.input.getX();
		y = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		sp.begin();
		sp.draw(homebutton.button, 379, 43);
		if (homebutton.buttonPoly.contains(x, y)) {
			sp.draw(homebutton.buttonClick, 379, 43);		
		}
		scorefont.draw(sp, ""+Person.person.getScore(), 390, 370);
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
