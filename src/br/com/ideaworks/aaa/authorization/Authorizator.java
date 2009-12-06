package br.com.ideaworks.aaa.authorization;

public interface Authorizator {

	void check() throws AuthorizationException;

}
