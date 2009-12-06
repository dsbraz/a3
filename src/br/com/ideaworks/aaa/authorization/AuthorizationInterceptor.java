package br.com.ideaworks.aaa.authorization;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author daniel
 */
public class AuthorizationInterceptor {

	/**
	 * @param invocation
	 * @return Object
	 * @throws Exception
	 */
	@AroundInvoke
	public Object check(final InvocationContext invocation) throws Exception {
		final Method target = invocation.getMethod();
		if (target.isAnnotationPresent(Secure.class)) {
			final String[] roles = target.getAnnotation(Secure.class).rolesAllowed();
			new Authorization().toAccessRoles(roles).check();
		}
		return invocation.proceed();
	}

}
