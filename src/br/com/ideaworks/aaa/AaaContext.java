package br.com.ideaworks.aaa;

/**
 * @author daniel.braz
 */
public class AaaContext {

	private static final ThreadLocal<String> currentUser = new ThreadLocal<String>();

	public static void currentUser(final String user) {
		currentUser.set(user);
	}

	public static String currentUser() {
		return currentUser.get();
	}

}
