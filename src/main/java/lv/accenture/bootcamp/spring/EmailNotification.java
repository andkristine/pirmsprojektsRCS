package lv.accenture.bootcamp.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component ("emailService")
public class EmailNotification implements NotificationChannel {

	@Value("${notification.email.from}")
	private String operator;
	
	@Override
	public void notifyUser(User user) {
		System.out.println(user.getFullName() + " is notified on e-mail " + user.getEmail() + " from " + operator);
		
	}

}
