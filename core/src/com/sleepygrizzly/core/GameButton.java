package com.sleepygrizzly.core;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class GameButton {
	
	private String id;
	public Texture button;
	public Texture buttonClick;
	public Polygon buttonPoly;
	int[] posiplay = {400, 497, 636,475, 631, 420, 397, 445};
	int[] posihowto = {398, 405, 623, 410, 624, 366, 400, 363};
	int[] posiscore = {419, 352, 612, 335, 611, 294, 420, 310};
	int[] posiquit = {409, 280, 619, 285, 624, 244, 408, 240};
	int[] posihome = {382, 91, 625, 89, 627, 26, 384, 28};
	int[] posileft = {423, 164, 492, 164, 491, 141, 422, 143};
	int[] posiright = {529, 164, 593, 163, 589, 139, 529, 141};
	int[] posimicro1 = {499, 609, 709, 609, 709, 471, 499, 471};
	int[] posimicro2 = {776, 608, 987, 608, 987, 469, 776, 469};
	int[] posimicro3 = {99, 380, 310, 380, 310, 241, 99, 241};
	int[] posimicro4 = {399, 382, 609, 382, 609, 242, 399, 242};
	int[] posimicro5 = {707, 384, 919, 384, 919, 246, 707, 246};
	int[] posihomemini = {925, 648, 999, 648, 1000, 582, 925, 582};
	int[] posinext = {382, 111, 625, 109, 627, 46, 384, 48};
	int[] posiokay = {382, 141, 625, 139, 627, 76, 384, 78};
	int[] posihome2 = {595, 94, 838, 94, 841, 31, 599, 31};

	HashMap<String, int[]> posi  = new HashMap<String, int[]>();

	public GameButton(String id){
		this.id = id;
		posi.put("01", posiplay);
		posi.put("02", posihowto);
		posi.put("03", posiscore);
		posi.put("04", posiquit);
		posi.put("05", posihome);
		posi.put("06", posileft);
		posi.put("07", posiright);
		posi.put("08", posimicro1);
		posi.put("09", posimicro2);
		posi.put("10", posimicro3);
		posi.put("11", posimicro4);
		posi.put("12", posimicro5);
		posi.put("13", posihomemini);
		posi.put("14", posinext);
		posi.put("15", posiokay);
		posi.put("16", posihome2);
		
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
	
	public void createMicroButton(){	
		button = new Texture("imgs/microwave/m"+id+".png");
		buttonClick = new Texture("imgs/microwave/mn"+id+".png");
			
		buttonPoly = new Polygon(new float[]{
					posi.get(id)[0], posi.get(id)[1],
					posi.get(id)[2], posi.get(id)[3],
					posi.get(id)[4], posi.get(id)[5],
					posi.get(id)[6], posi.get(id)[7]
		});
		
	}
	
}
