package lv.accenture.bootcamp.io.conveyor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lv.accenture.bootcamp.db.DBUtil;
import lv.accenture.bootcamp.network.SunAPIService;

@Component
public class DateAdjuster {

	public void adjustLectionTime() {

		Connection connection = null;
		SunAPIService sunAPIService = null;

		try {
			Path path = Paths.get("./documents/course-id.txt");

			List<String> linesInList = Files.readAllLines(path);
			String s = linesInList.get(0);
			Long courseID = Long.parseLong(s);

			connection = DBUtil.acquireConnection();
			System.out.println(courseID);

			String sql = "select ID, LECTION_DTM from LECTION where COURSE_ID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, courseID);

			ResultSet rs = preparedStatement.executeQuery();
			long lectionDateRaw = 0;
			while (rs.next()) { // while, jo vairakas rindas

				Long lectionID = rs.getLong("ID");
				java.sql.Date lectionDate = rs.getDate("LECTION_DTM");
				lectionDateRaw = lectionDate.getTime();
				// System.out.println("row = " + lectionID + " date msec: " +
				// lectionDateRaw);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String lectionDateFormatted = simpleDateFormat.format(new java.util.Date(lectionDateRaw));
				java.util.Date sunriseDate = sunAPIService.getSunrise(lectionDateFormatted);
				System.out.println("Date: " + lectionDateFormatted + " | sunrise : " + sunriseDate);
				long sunriseTimeRaw = sunriseDate.getTime();

				Timestamp timestamp = new Timestamp();

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (connection != null) {
				try {

					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}

}
