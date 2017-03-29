package com.wetwired.voiceui.common.speech;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author Nik Cross
 */
public class DateTimePhraseGenerator {
	public static String durationToSpeech(Duration duration) {
		long minutes = duration.toMinutes() - (duration.toHours() * 60);
		String spokenDuration = "";
		if (duration.toHours() > 0) {
			spokenDuration += duration.toHours() + " hour";
			if (duration.toHours() != 1) {
				spokenDuration += "s";
			}
		}
		if (minutes > 0) {
			if (spokenDuration.length() > 0) {
				spokenDuration += " ";
			}
			spokenDuration += minutes + " minute";
			if (minutes != 1) {
				spokenDuration += "s";
			}
		}
		return spokenDuration;
	}

	public static String getLocalDateToDay(LocalDate date) {
		return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	}

	public static String getLocalDateToDate(LocalDate date) {
		String spokenText = getLocalDateToDay(date) + " " +
				date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
				date.getDayOfMonth() + getOrdinalSuffix(date.getDayOfMonth());
		return spokenText;
	}

	public static String getLocalDateToDateWithYear(LocalDate date) {
		String spokenText = getLocalDateToDay(date) + " " +
				date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
				date.getDayOfMonth() + getOrdinalSuffix(date.getDayOfMonth()) +  " " +
				date.getYear();
		return spokenText;
	}

	public static String getOrdinalSuffix(int number) {
		int lastDigit = number%10;
		String suffix;
		switch(lastDigit) {
			case 1:
				suffix = "st";
				break;
			case 2:
				suffix ="nd";
				break;
			case 3:
				suffix = "rd";
				break;
			default:
				suffix = "th";
		}

		return suffix;
	}
}
