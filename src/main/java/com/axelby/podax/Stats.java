package com.axelby.podax;

import android.content.Context;
import android.content.SharedPreferences;

public class Stats {
	private Stats() { }

	public static void addListenTime(Context context, float seconds) {
		SharedPreferences statsPrefs = context.getSharedPreferences("stats", Context.MODE_PRIVATE);
		float listenTime = statsPrefs.getFloat("listenTime", 0f) + seconds;
		statsPrefs.edit().putFloat("listenTime", listenTime).apply();
	}

	private static float getListenTime(Context context) {
		SharedPreferences statsPrefs = context.getSharedPreferences("stats", Context.MODE_PRIVATE);
		return statsPrefs.getFloat("listenTime", 0f);
	}

	public static String getListenTimeString(Context context) {
        return Helper.getVerboseTimeString(context, Stats.getListenTime(context), true);
	}

    public static void addCompletion(Context context) {
		SharedPreferences statsPrefs = context.getSharedPreferences("stats", Context.MODE_PRIVATE);
		int completions = statsPrefs.getInt("completions", 0) + 1;
		statsPrefs.edit().putInt("completions", completions).apply();
	}

	public static int getCompletions(Context context) {
		SharedPreferences statsPrefs = context.getSharedPreferences("stats", Context.MODE_PRIVATE);
		return statsPrefs.getInt("completions", 0);
	}
}
