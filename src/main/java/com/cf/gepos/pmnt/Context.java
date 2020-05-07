package com.cf.gepos.pmnt;

import java.nio.file.Path;

public final class Context {

	private Path pfile, keyfile;

	private String loginPass;

	private boolean isLoginCompleted;

	public Path getPfile() {
		return pfile;
	}

	public void setPfile(Path pfile) {
		this.pfile = pfile;
	}

	public Path getKeyfile() {
		return keyfile;
	}

	public void setKeyfile(Path keyfile) {
		this.keyfile = keyfile;
	}

	public void setLoginPass(String loginpass) {
		this.loginPass = loginpass;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public boolean isLoginCompleted() {
		return isLoginCompleted;
	}

	protected void setLoginCompleted(boolean isLoginCompleted) {
		this.isLoginCompleted = isLoginCompleted;
	}

}
