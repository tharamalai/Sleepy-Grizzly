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
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent.ButtonListener;

public class MenuScene extends Scene {
	private float elapsedTime = 0;
	private boolean isClickPlay = false;
	private boolean isClickHowto = false;
	private boolean isClickScore = false;
	private boolean isClickQuit = false;
	
	private SpriteBatch sp;
	
	ShapeRenderer sr;
	
	GameButton playbutton, howtobutton, scorebutton, quitbutton; 
	
	int x,y;
	public MenuScene(SceneManage sm, SceneLoader scene) {
		super(sm, scene);
		Viewport vp = new FitViewport(1024, 668);
		scene1.loadScene("MenuScene", vp);
		
	}

	@Override
	public void create() {
		sp = new SpriteBatch();
		sr = new ShapeRenderer();
		
		playbutton = new GameButton("06");
		howtobutton = new GameButton("09");
		scorebutton = new GameButton("11");
		quitbutton = new GameButton("13");

		playbutton.createButton();
		howtobutton.createButton();
		scorebutton.createButton();
		quitbutton.createButton();
		
	}

	@Override
	public void render() {
		x = Gdx.input.getX();
		y = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		sr.begin(ShapeType.Line);
		sr.setColor(1, 0, 0, 1);

		sr.polygon(playbutton.buttonPoly.getVertices());
		sr.polygon(howtobutton.buttonPoly.getVertices());
		sr.polygon(scorebutton.buttonPoly.getVertices());
		sr.polygon(quitbutton.buttonPoly.getVertices());
		sr.end();
			
		sp.begin();
		sp.draw(playbutton.button, 394, 418);
		sp.draw(howtobutton.button, 394, 352);
		sp.draw(scorebutton.button, 394, 292);
		sp.draw(quitbutton.button, 394, 232);
		
		if (playbutton.buttonPoly.contains(x, y)) {
			sp.draw(playbutton.buttonClick, 394, 418);		
		} else if(howtobutton.buttonPoly.contains(x, y)){
			sp.draw(howtobutton.buttonClick, 394, 352);
		} else if(scorebutton.buttonPoly.contains(x, y)){
			sp.draw(scorebutton.buttonClick, 394, 292);
		} else if(quitbutton.buttonPoly.contains(x, y)){
			sp.draw(quitbutton.buttonClick, 394, 232);
		}
		
		sp.end();
		
		if (Gdx.input.isTouched()) {
			if (playbutton.buttonPoly.contains(x, y)) {
				isClickPlay = true;
			} else if (howtobutton.buttonPoly.contains(x, y)) {
				isClickHowto = true;
			} else if (scorebutton.buttonPoly.contains(x, y)) {
				isClickScore = true;
			} else if (quitbutton.buttonPoly.contains(x, y)) {
				isClickQuit = true;
			}
		}
		
		elapsedTime += Gdx.graphics.getDeltaTime();
		if (isClickPlay) {
			this.sm.setScene("main");
		} else if(isClickHowto) {
			this.sm.setScene("howto");
		} else if(isClickScore) {
			this.sm.setScene("score");
		} else if(isClickQuit) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
	}

}
