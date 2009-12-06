package br.com.ideaworks.aaa.authorization;

public class DatabaseAuthorizator implements Authorizator {

	private final String user;
	private final String[] roles;

	DatabaseAuthorizator(final String user, final String... roles) {
		this.user = user;
		this.roles = roles;
	}

	public void check() throws AuthorizationException {
		boolean isInRole = false;
		for (final String role : roles) {
			isInRole = role != null && user != null && user.equals("_fonernp_");
			if (isInRole) {
				break;
			}
		}
		// if (!isInRole) { throw new AuthorizationException("Acesso negado."); }
	}

}
