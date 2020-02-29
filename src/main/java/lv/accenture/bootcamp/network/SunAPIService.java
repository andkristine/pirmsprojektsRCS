package lv.accenture.bootcamp.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class SunAPIService {

	@Value("${api.request}")
	private String requestUrl;
	
	public Date getSunrise(String requestedDate) {
		
		try {
			URL url = new URL(requestUrl + "&date=" + requestedDate);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setReadTimeout(3000);
			urlConnection.connect();
			
			InputStream inputStream = urlConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			String jsonResponse = sb.toString();
			System.out.println(jsonResponse);
			
			bufferedReader.close();
			
			Gson gson = new Gson(); //klase no  arejas bibleotekas
			SunAPIResponse sunAPIResponse = gson.fromJson(jsonResponse, SunAPIResponse.class);    
			
			String sunrise = sunAPIResponse.getResults().getSunrise();
			System.out.println(sunrise);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss aa"); //HH lieto gadījumā, ja būtu jānolasa,
			// piem, 05, nevis 5
			Date sunriseDate = simpleDateFormat.parse(sunrise);
			sunriseDate = new Date(sunriseDate.getTime() + (2 * 60 * 60 * 1000));
			System.out.println(sunriseDate);
			
			return sunriseDate;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
