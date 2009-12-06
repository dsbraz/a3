package br.com.ideaworks.aaa.accounting;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author daniel.braz
 */
public class TracingInterceptor {

	/**
	 * @param invocation
	 * @return Object
	 * @throws Exception
	 */
	@AroundInvoke
	public Object trace(final InvocationContext invocation) throws Exception {
		new Log().forMessage(invocation.getTarget().getClass() + "." + invocation.getMethod())
				.trace();
		return invocation.proceed();
	}

}
