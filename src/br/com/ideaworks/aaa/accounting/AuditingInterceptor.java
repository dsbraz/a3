package br.com.ideaworks.aaa.accounting;

import java.lang.reflect.Method;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.ideaworks.aaa.AaaContext;


/**
 * @author daniel
 */
public class AuditingInterceptor {

	/**
	 * @param invocation
	 * @return Object
	 * @throws Exception
	 */
	@AroundInvoke
	public Object audit(final InvocationContext invocation) throws Exception {
		final Method target = invocation.getMethod();
		if (target.isAnnotationPresent(Audit.class)) {
			final Object result = invocation.proceed();
			new Log().forMessage(getMessage(invocation)).audit();
			return result;
		}
		return invocation.proceed();
	}

	public String getMessage(final InvocationContext invocation) {
		final StringBuilder event = new StringBuilder();
		event.append("<event>");
		event.append("<date>").append(new Date()).append("</date>");
		event.append("<user>").append(AaaContext.currentUser()).append("</user>");
		event.append("<class>").append(invocation.getTarget().getClass().getSimpleName()).append(
				"</class>");
		event.append("<method>").append(invocation.getMethod().getName()).append("</method>");
		if (invocation.getParameters() != null) {
			for (final Object param : invocation.getParameters()) {
				event.append("<method-param>").append(param).append("</method-param>");
			}
		} else {
			event.append("<method-param></method-param>");
		}
		event.append("<description>").append(
				invocation.getMethod().getAnnotation(Audit.class).description()).append(
				"</description>");
		event.append("</event>");
		return event.toString();
	}
}
