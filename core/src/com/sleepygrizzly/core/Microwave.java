package com.sleepygrizzly.core;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.sleepygrizzly.scene.KitchenScene;
import com.uwsoft.editor.renderer.SceneLoader;

public class Microwave {
	
	private double time;
	private float dash_x;
	private float dash_y;
	private String status;
	private int order = 0;

	PufferFish hotfish;
	SceneLoader scene;
	
	public ArrayList<Microwave> groupmicro = new ArrayList<Microwave>();
	float[] pufposi_x = {393f, 667f, -7f, 293f, 601f};
	float[] pufposi_y = {360f, 360f, 135f, 135f, 135f};
	
	float[] dashposi_x = {556, 836, 163, 463, 771};
	float[] dashposi_y = {652, 652, 424, 424, 424};
	int[] idhotfish = {43, 41, 40, 42, 44};
	
	public Microwave(SceneLoader scene){
		this.scene = scene;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public double getTime() {
		return time;
	}

	public void setTime(double f) {
		this.time = f;
	}

	public float getDash_x() {
		return dash_x;
	}

	public void setDash_x(float dash_x) {
		this.dash_x = dash_x;
	}

	public float getDash_y() {
		return dash_y;
	}

	public void setDash_y(float dash_y) {
		this.dash_y = dash_y;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void setMicrowaveLabel(){
		for(int i=0; i<5; i++){
			groupmicro.add(new Microwave(scene));
			groupmicro.get(i).dash_x = dashposi_x[i];
			groupmicro.get(i).dash_y = dashposi_y[i];
			groupmicro.get(i).setStatus("hot");;
			groupmicro.get(i).randomTime();
			
			
		}
	}

	public String showTime(){
		return (int)(time/1000)/60 + " : " + (int)((time/1000)%60);
	}
	
	public boolean isVeryHot(){
		if(this.getTime() < 1000000){
			return true;
		}
		return  false;
	}
	
	public void randomTime(){
		this.setTime((int) (1000000 + (Math.random() * 10000000)));
		for(int i =0; i < groupmicro.size()-1;i++){
			if(Math.abs(this.getTime() - groupmicro.get(i).getTime()) < 1000000){
				this.setTime((int) (100000 + (Math.random() * 10000000)));
			}
		}
		
	}
	public boolean isTimeisFinish(){
		for(int i=0; i<5; i++){
			if(groupmicro.get(i).getTime() <= 0 && groupmicro.get(i).getStatus().equals("hot")){
				groupmicro.get(i).setTime(0);
				hotfish = new PufferFish(scene, "hotfish", idhotfish[i]);
				hotfish.createPufferID();
				hotfish.puffer.x = pufposi_x[i];
				hotfish.puffer.y = pufposi_y[i];
				return true;
			}
		}
		return false;
	}
	
	public boolean canSaveAllPuffer(){
		for(int i=0; i<5; i++){
			if(groupmicro.get(i).getStatus().equals("hot")){
				return false;
			}
		}
		return true;
	}
}