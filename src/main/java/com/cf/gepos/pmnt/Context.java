package com.cf.gepos.pmnt;

import java.nio.file.Path;

public class Context {

	private Path pfile, keyfile;

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

}
