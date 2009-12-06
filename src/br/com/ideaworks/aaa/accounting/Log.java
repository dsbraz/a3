package br.com.ideaworks.aaa.accounting;

/**
 * @author daniel.braz
 */
public class Log {

	public LogMessage forMessage(final String message) {
		return new LogMessage(message);
	}

}
