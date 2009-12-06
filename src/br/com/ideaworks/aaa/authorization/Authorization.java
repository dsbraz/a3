package br.com.ideaworks.aaa.authorization;

import br.com.ideaworks.aaa.AaaContext;

/**
 * @author daniel.braz
 */
public class Authorization {

	public Authorizator toAccessRoles(final String... roles) {
		return new DatabaseAuthorizator(AaaContext.currentUser(), roles);
	}

}
