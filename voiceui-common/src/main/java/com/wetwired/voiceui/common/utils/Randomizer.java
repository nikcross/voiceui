package com.wetwired.voiceui.common.utils;

/**
 * @author Nik Cross
 */
public class Randomizer {
	public static boolean testMode = false;

	public static int getRandom(int min,int max) {
		int random = (int)(Math.random()*max-min);
		if(testMode) random=0;
		return random+min;
	}
}
