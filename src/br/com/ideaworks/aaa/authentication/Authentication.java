package br.com.ideaworks.aaa.authentication;

import java.security.cert.X509Certificate;

import br.com.ideaworks.aaa.AaaContext;


/**
 * @author daniel.braz
 */
public class Authentication {

	public Authenticator withCert(final X509Certificate[] cert) {
		return new CertAuthenticator(cert);
	}

	public static boolean verifyLogin() {
		return AaaContext.currentUser() != null;
	}

}
