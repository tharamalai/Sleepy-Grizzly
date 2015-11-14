package com.sleepygrizzly.core;

public class CheckAction {
	private String bearside;
	private String pufferside;
	private Integer score = 0;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public boolean isTrueSide(String bearside, String pufferside) {
		if (bearside.charAt(bearside.length() - 1) != (pufferside.charAt(pufferside.length() - 1))) {
			System.out.println("TRUE -- LL RR" + "  bearside: " + bearside + " | pufferside: " + pufferside);
			score += 1;
			System.out.println("Score:  " + score);
			return true;
		} 
		else {
			System.out.println("FALSE -- " + "  bearside: " + bearside + " | pufferside: " + pufferside);
			System.out.println("Score:(same)  " + score);
			return false;
		}
	}

}
