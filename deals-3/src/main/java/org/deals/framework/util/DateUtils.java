package org.deals.framework.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateUtils {

	private static Locale defaultLocale = Locale.ITALIAN;
	public static String customDatePattern = "dd/MM/yyyy";


	public static String toDateStringCustom(Date date) {
		Format formatter = new SimpleDateFormat(customDatePattern);
		return formatter.format(date);
	}   

	public static String toDateStringLong(Date date) {	   
		return DateFormat.getDateInstance(DateFormat.LONG, defaultLocale).format(date) ;
	}

	public static String toDateStringMedium(Date date) {
		return DateFormat.getDateInstance(DateFormat.MEDIUM, defaultLocale).format(date) ;
	}

	public static String toDateStringShort(Date date) {
		return DateFormat.getDateInstance(DateFormat.SHORT, defaultLocale).format(date) ;
	}

	public static String toTimeStringLong(Date date) {	   
		return DateFormat.getTimeInstance(DateFormat.LONG, defaultLocale).format(date) ;
	}

	public static String toTimeStringMedium(Date date) {
		return DateFormat.getTimeInstance(DateFormat.MEDIUM, defaultLocale).format(date) ;
	}

	public static String toTimeStringShort(Date date) {
		return DateFormat.getTimeInstance(DateFormat.SHORT, defaultLocale).format(date) ;
	}


	public static String toDateTimeStringShort(Date date) {
		return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, defaultLocale).format(date) ;	   
	}

	public static String toDateTimeStringMedium(Date date) {
		return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, defaultLocale).format(date) ;	   
	}

	public static String toDateTimeStringLong(Date date) {
		return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, defaultLocale).format(date) ;	   
	}

	/**
	 * Restituisce data e ora del momento in cui viene chiamato
	 * @return data e ora del momento in cui viene chiamato
	 */
	public static Date now() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Crea una data settando l'anno e il mese. Mette il giorno a 1.
	 * @param year anno da settare
	 * @param month mese da settare
	 * @return data settando l'anno e il mese (Mette il giorno a 1)
	 */
	public static Date toDate(int year, int month) {
		return toDate(year, month, 1, 0, 0, 0);
	}   

	/**
	 * Crea una data con anno, mese e giorno settati
	 * @param year anno da settare
	 * @param month mese da settare
	 * @param day giorno da settare
	 * @return data con anno, mese e giorno settati
	 */
	public static Date toDate(int year, int month, int day) {
		return toDate(year, month, day, 0, 0, 0);
	}

	/**
	 * Crea una data con anno, mese, giorno e ore settati
	 * @param year anno da settare
	 * @param month mese da settare
	 * @param day giorno da settare
	 * @param hours ore da settare
	 * @return data con anno, mese, giorno e ore settati
	 */
	public static Date toDate(int year, int month, int day, int hours) {
		return toDate(year, month, day, hours, 0, 0);
	}


	/**
	 * Crea una data con anno, mese, giorno, ore e minuti settati
	 * @param year anno da settare
	 * @param month mese da settare
	 * @param day giorno da settare
	 * @param hours ore da settare
	 * @param minutes minuti da settare
	 * @return data con anno, mese, giorno, ore e minuti settati
	 */
	public static Date toDate(int year, int month, int day, int hours, int minutes) {
		return toDate(year, month, day, hours, minutes, 0);
	}

	/**
	 * Crea una data con anno, mese, giorno, ore, minuti e secondi settati
	 * @param year anno da settare
	 * @param month mese da settare
	 * @param day giorno da settare
	 * @param hours ore da settare
	 * @param minutes minuti da settare
	 * @param seconds secondi da settare
	 * @return data con anno, mese, giorno, ore, minuti e secondi settati
	 */
	public static Date toDate(int year, int month, int day, int hours, int minutes, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day, hours, minutes, seconds);
		return cal.getTime();
	}


	/**
	 * Restitusice il mese come intero passando il nome del mese per esteso.
	 * Esempio: "Gennaio" -> "1"
	 * @param mese
	 * @return
	 */
	public static Integer toIntegerMonth(String mese){
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM", defaultLocale);  // Mese per esteso, per esempio "Gennaio"
		SimpleDateFormat formatter2 = new SimpleDateFormat("M", defaultLocale);  // Mese come intero, per esempio "1"
		try {
			return Integer.parseInt(formatter2.format(formatter.parse(mese)));
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}


}
