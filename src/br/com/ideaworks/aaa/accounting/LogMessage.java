package br.com.ideaworks.aaa.accounting;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

@SuppressWarnings("deprecation")
public class LogMessage {

	private static final Logger traceLogger = Logger.getLogger("foneRNP-Tracing");
	private static final Logger auditLogger = Logger.getLogger("foneRNP-Auditoria");

	private final String message;

	LogMessage(final String message) {
		this.message = message;
	}

	private void log(final Logger logger, final Priority p) {
		if (logger.isEnabledFor(p)) {
			logger.log(p, message);
		}
	}

	public void audit() {
		log(auditLogger, Priority.INFO);
	}

	public void trace() {
		log(traceLogger, Priority.DEBUG);
	}

}
