package lv.accenture.bootcamp.network;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversions {

	public static void main(String[] args) throws Exception {

		Long currentTimeMsec = System.currentTimeMillis();
		System.out.println("currentTimeMsec = " + currentTimeMsec);

		Date currentDate = new Date(currentTimeMsec);

		System.out.println("currentDate = " + currentDate);
		
		
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy"); //lielai m menesiem, mazais m- minutem
		
		System.out.println(df1.format(currentDate));
		System.out.println(df2.format(currentDate));
		
		
		SimpleDateFormat tf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dateWithTime = tf1.format(currentDate);
		System.out.println(tf1.format(currentDate));
		
		Date parsedDate = df1.parse("29/02/2020");
		long parsedRawMsec = parsedDate.getTime();
		
		System.out.println("parsedRawMsec = " + parsedRawMsec);
		
		
		
		parsedDate = tf1.parse(dateWithTime);
		parsedRawMsec = parsedDate.getTime();
		System.out.println("parsedRawMsec = " + parsedRawMsec);
		
		
		
		
		
		
		
		
	}

}
