package com.sleepygrizzly.core;

public class TimeLimitBar {
	Bear bearpointer;
	float percent;
	public TimeLimitBar(Bear bearpointer){
		this.bearpointer = bearpointer;
	}
	public void createBar(){
		bearpointer.createBearID();
	}
	
	public void movePointer(float percent){
		bearpointer.bear.x = 221 + ((280*percent)/100);
		bearpointer.bear.y = 370f;
	}
}
