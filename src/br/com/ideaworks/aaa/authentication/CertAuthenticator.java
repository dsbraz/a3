package br.com.ideaworks.aaa.authentication;

import java.security.cert.X509Certificate;

/**
 * @author daniel.braz
 */
public class CertAuthenticator implements Authenticator {

	private final String user;
	private final String pass;

	CertAuthenticator(final X509Certificate[] cert) {
		user = null;
		pass = null;
	}

	public void login() throws AuthenticationException {
		if (!user.equals("_fonernp_") && !pass.equals("pass")) { throw new AuthenticationException(
				"Usuário e/ou senha inválido(s)!"); }
	}

}
