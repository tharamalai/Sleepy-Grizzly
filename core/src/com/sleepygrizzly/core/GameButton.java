package com.sleepygrizzly.core;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class GameButton {
	
	public Texture button;
	public Texture buttonClick;
	public Polygon buttonPoly;
	int[] posiplay = {400, 497, 636,475, 631, 420, 397, 445};
	int[] posihowto = {398, 405, 623, 410, 624, 366, 400, 363};
	int[] posiscore = {419, 352, 612, 335, 611, 294, 420, 310};
	int[] posiquit = {409, 280, 619, 285, 624, 244, 408, 240};
	HashMap<String, int[]> posi  = new HashMap<String, int[]>();
	private String id;
	
	
	public GameButton(String id){
		this.id = id;
		posi.put("06", posiplay);
		posi.put("09", posihowto);
		posi.put("11", posiscore);
		posi.put("13", posiquit);
		
	}
	
	public void createButton(){
		
		button = new Texture("imgs/button/menu_"+id+".png");
		buttonClick = new Texture("imgs/button/menuon_"+id+".png");
			
		buttonPoly = new Polygon(new float[]{
					posi.get(id)[0], posi.get(id)[1],
					posi.get(id)[2], posi.get(id)[3],
					posi.get(id)[4], posi.get(id)[5],
					posi.get(id)[6], posi.get(id)[7]
		});
		
	}
	
}
