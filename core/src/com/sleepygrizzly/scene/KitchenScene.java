package com.sleepygrizzly.scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sleepygrizzly.core.GameButton;
import com.sleepygrizzly.core.Microwave;
import com.sleepygrizzly.core.PufferFish;
import com.uwsoft.editor.renderer.SceneLoader;

public class KitchenScene extends Scene {
	int x,y;
	private int count = 6;
	private int clicked = 1;
	private int counttime = 100;
	private long loadscene;
	private long endscene;
	private long startscene;
	SpriteBatch batch;
	BitmapFont readyfont;
	BitmapFont digit;
	Microwave microwave;
	private float elapsedTime = 0;
	private String kitchenstatus = "warm";
	GameButton microbutton1, microbutton2, microbutton3, microbutton4, microbutton5;
	private SpriteBatch sp;
	
	public String getKitchenstatus() {
		return kitchenstatus;
	}

	public void setKitchenstatus(String kitchenstatus) {
		this.kitchenstatus = kitchenstatus;
	}
	
	public KitchenScene(SceneManage sm, SceneLoader scene) {
		super(sm, scene);
	}

	@Override
	public void create() {
		Viewport vp = new FitViewport(1024, 668); 
		scene1.loadScene("KitchenScene", vp);
		sp = new SpriteBatch();
		
		/**** microwave button ****/
		microbutton1 = new GameButton("08");
		microbutton2 = new GameButton("09");
		microbutton3 = new GameButton("10");
		microbutton4 = new GameButton("11");
		microbutton5 = new GameButton("12");
		
		microbutton1.createMicroButton();
		microbutton2.createMicroButton();
		microbutton3.createMicroButton();
		microbutton4.createMicroButton();
		microbutton5.createMicroButton();
		
		batch = new SpriteBatch();
		readyfont = new BitmapFont(Gdx.files.internal("data/font/readyfont/readyfont.fnt"),Gdx.files.internal("data/font/readyfont/readyfont.png"), false);
		digit = new BitmapFont(Gdx.files.internal("data/font/digitfont/digitfont.fnt"),Gdx.files.internal("data/font/digitfont/digitfont.png"), false);
		microwave = new Microwave(scene1);
		microwave.setMicrowaveLabel();
		for(Microwave each: microwave.groupmicro){
			System.out.println(each.getTime());
			System.out.println(each.showTime());
		}
		loadscene = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis() - startscene);
	}
	
	@Override
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		x = Gdx.input.getX();
		y = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		sp.begin();
		sp.draw(microbutton1.button, 489, 453);
		sp.draw(microbutton2.button, 767, 452);
		sp.draw(microbutton3.button, 90, 224);
		sp.draw(microbutton4.button, 389, 225);
		sp.draw(microbutton4.button, 698, 227);
		
		sp.end();
		
		if(kitchenstatus.equals("warm")){
			
			batch.begin();
			if((count >=0) && (System.currentTimeMillis() - loadscene) >= counttime){
				System.out.println("if " + (System.currentTimeMillis() - loadscene));
				readyfont.draw(batch, count+"", 450, 450);
				count--;
				counttime += 500;
			}
			else if(count == -1 && (System.currentTimeMillis() - loadscene) >= counttime){
				System.out.println("else if");
				readyfont.draw(batch, "START", 170, 450);
				System.out.println("===" + (System.currentTimeMillis() - loadscene));
				count--;
				counttime += 500;
			}
			else if(count == -2 && (System.currentTimeMillis() - loadscene) >= counttime){
				this.setKitchenstatus("cook");
			}
			else if(count >= 0){
				readyfont.draw(batch, count+"", 450, 450);
			}
			else{
				readyfont.draw(batch, "START", 170, 450);
			}
			batch.end();
			startscene = System.currentTimeMillis();
		}
		else if(kitchenstatus.equals("cook")){
			
			if(!microwave.isTimeisFinish()){
				for(int i=0; i<5; i++){
					if(microwave.groupmicro.get(i).getStatus().equals("hot")){
						microwave.groupmicro.get(i).setTime(microwave.groupmicro.get(i).getTime() - (System.currentTimeMillis() - startscene));
					}
				}
			}
			else{
				endscene = System.currentTimeMillis();
				counttime = 1000;
				setKitchenstatus("gameover");
			}
			if(microwave.canSaveAllPuffer()){
				endscene = System.currentTimeMillis();
				counttime = 1000;
				setKitchenstatus("cool");
			}
			batch.begin();
			
			chooseDigit(0).draw(batch, microwave.groupmicro.get(0).showTime(), microwave.groupmicro.get(0).getDash_x(), microwave.groupmicro.get(0).getDash_y());
			chooseDigit(1).draw(batch, microwave.groupmicro.get(1).showTime(), microwave.groupmicro.get(1).getDash_x(), microwave.groupmicro.get(1).getDash_y());
			chooseDigit(2).draw(batch, microwave.groupmicro.get(2).showTime(), microwave.groupmicro.get(2).getDash_x(), microwave.groupmicro.get(2).getDash_y());
			chooseDigit(3).draw(batch, microwave.groupmicro.get(3).showTime(), microwave.groupmicro.get(3).getDash_x(), microwave.groupmicro.get(3).getDash_y());
			chooseDigit(4).draw(batch, microwave.groupmicro.get(4).showTime(), microwave.groupmicro.get(4).getDash_x(), microwave.groupmicro.get(4).getDash_y());
			
			
			batch.end();
			sp.begin();
			
			if (microbutton1.buttonPoly.contains(x, y)) {
				sp.draw(microbutton1.buttonClick, 489, 453);
				if(Gdx.input.justTouched()){
					if(microwave.groupmicro.get(0).getStatus().equals("hot")){
						microwave.groupmicro.get(0).setStatus("save");
						deleteTimeOther(0);
						microwave.groupmicro.get(0).setOrder(clicked++);
					}
				}
			} else if(microbutton2.buttonPoly.contains(x, y)){
				sp.draw(microbutton2.buttonClick, 767, 452);
				if(Gdx.input.justTouched()){
					if(microwave.groupmicro.get(1).getStatus().equals("hot")){
						microwave.groupmicro.get(1).setStatus("save");
						deleteTimeOther(1);
						microwave.groupmicro.get(1).setOrder(clicked++);
					}
				}

			} else if(microbutton3.buttonPoly.contains(x, y)){
				sp.draw(microbutton3.buttonClick, 90, 224);
				if(Gdx.input.justTouched()){
					if(microwave.groupmicro.get(2).getStatus().equals("hot")){
						microwave.groupmicro.get(2).setStatus("save");
						deleteTimeOther(2);
						microwave.groupmicro.get(2).setOrder(clicked++);
					}
				}
			} else if(microbutton4.buttonPoly.contains(x, y)){
				sp.draw(microbutton4.buttonClick, 389, 225);
				if(Gdx.input.justTouched()){
					if(microwave.groupmicro.get(3).getStatus().equals("hot")){
						microwave.groupmicro.get(3).setStatus("save");
						deleteTimeOther(3);
						microwave.groupmicro.get(3).setOrder(clicked++);
					}
				}
			} else if(microbutton5.buttonPoly.contains(x, y)){
				sp.draw(microbutton5.buttonClick, 698, 227);
				if(Gdx.input.justTouched()){
					if(microwave.groupmicro.get(4).getStatus().equals("hot")){
						microwave.groupmicro.get(4).setStatus("save");
						deleteTimeOther(4);
						microwave.groupmicro.get(4).setOrder(clicked++);
					}
				}
			}
			
			sp.end();
		}
		else if(kitchenstatus.equals("cool")){
			System.out.println("COOOOOOOOOOLLLLLL");
			
			for(int i=0; i<5; i++){
				System.out.println(microwave.groupmicro.get(i).getOrder() + " ] " +microwave.groupmicro.get(i).getTime()+ microwave.groupmicro.get(i).getTime());
			}
			if((System.currentTimeMillis() - endscene) <= counttime){
				batch.begin();
				readyfont.draw(batch, "     GOT \nCHANCE", 100, 600);
				batch.end();
			}
			else{
				this.sm.setScene("main");
				System.out.println("setscene");
			}
		}
		else if(kitchenstatus.equals("gameover")){
			System.out.println("GAME OVERRRRRRRRRR");
			for(int i=0; i<5; i++){
				System.out.println(microwave.groupmicro.get(i).getOrder() + " ] " +microwave.groupmicro.get(i).showTime()+ microwave.groupmicro.get(i).getStatus());
			}
			if(((System.currentTimeMillis() - endscene)  <= counttime)){
				batch.begin();
				readyfont.draw(batch, "GAME\n OVER", 170, 600);
				batch.end();
				System.out.println(System.currentTimeMillis() - endscene);
			}
			else{
				this.sm.setScene("yourscore");
				System.out.println("setscene");
			}
		}

	}

	@Override
	public void dispose() {

	}
	
	public BitmapFont chooseDigit(int index){
		if(microwave.groupmicro.get(index).getStatus().equals("save")){
			digit.setColor(0, 0, 255, 1);
		}
		else if(microwave.groupmicro.get(index).isVeryHot()){
			digit.setColor(255, 0, 0, 1);
		}
		else{
			digit.setColor(0, 255, 0, 1);
		}
		return digit;
	}
	public void deleteTimeOther(int idmicro){
		for(int i=0; i<5; i++){
			if(i != idmicro && microwave.groupmicro.get(i).getStatus().equals("hot")){
				microwave.groupmicro.get(i).setTime(microwave.groupmicro.get(i).getTime() - microwave.groupmicro.get(idmicro).getTime());
			}
		}
		
	}

}
