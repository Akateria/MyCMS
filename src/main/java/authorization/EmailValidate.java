package authorization;

import java.util.regex.Pattern;

public class EmailValidate {
	public static Boolean validateEmail(String email) {
		String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
		return Pattern.matches(emailRegex, email);
	}
}
