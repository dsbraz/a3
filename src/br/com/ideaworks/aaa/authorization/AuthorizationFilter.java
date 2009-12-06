package br.com.ideaworks.aaa.authorization;

import java.io.IOException;
import java.security.cert.X509Certificate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.ideaworks.aaa.authentication.Authentication;
import br.com.ideaworks.aaa.authentication.AuthenticationException;


public class AuthorizationFilter implements Filter {

	private String errorPage;
	private String[] rolesAllowed;

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		//
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(final ServletRequest req, final ServletResponse resp,
			final FilterChain chain) throws IOException, ServletException {
		try {
			final X509Certificate[] cert = getCert(req);
			if (cert != null) {
				new Authentication().withCert(cert).login();
				new Authorization().toAccessRoles(rolesAllowed).check();
			}
			chain.doFilter(req, resp);
		} catch (final AuthenticationException e) {
			dispatch(req, resp, errorPage);
		} catch (final AuthorizationException e) {
			dispatch(req, resp, errorPage);
		}
	}

	public X509Certificate[] getCert(final ServletRequest req) {
		X509Certificate[] result = null;
		final String cipherSuite = (String) req.getAttribute("javax.servlet.request.cipher_suite");
		if (cipherSuite != null) {
			result = (X509Certificate[]) req.getAttribute("javax.servlet.request.X509Certificate");
		}
		return result;
	}

	private void dispatch(final ServletRequest req, final ServletResponse resp, final String page)
			throws ServletException, IOException {
		assert req != null && resp != null && page != null;
		final RequestDispatcher disp = req.getRequestDispatcher(page);
		if (disp != null) {
			disp.forward(req, resp);
		}
	}

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(final FilterConfig config) throws ServletException {
		errorPage = config.getInitParameter("error-page");
		rolesAllowed = config.getInitParameter("roles-allowed").split(",");
		for (int i = 0; i < rolesAllowed.length; i++) {
			rolesAllowed[i] = rolesAllowed[i].trim();
		}
	}

}
