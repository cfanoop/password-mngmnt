package com.cf.gepos.pmnt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class ContentRepositoryImpl implements ContentRepository {

	private Context context;

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String readContent() throws IOException {
		return Files.readString(context.getPfile());
	}

	@Override
	public void writeContent(String content) throws IOException {
		Files.writeString(context.getPfile(), content, new StandardOpenOption[] { StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE });

	}

}
