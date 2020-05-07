package com.cf.gepos.pmnt;

import java.io.IOException;

public interface ContentRepository {

	public String readContent() throws IOException;

	public void writeContent(String content) throws IOException;
}
