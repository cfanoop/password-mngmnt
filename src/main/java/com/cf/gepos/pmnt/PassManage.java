package com.cf.gepos.pmnt;

import java.util.Collection;
import java.util.Optional;

public interface PassManage {

	Optional<Collection<String>> read(String key);

	void update(String key, String pass, boolean isUpdate);

	public void delete(String string);

	public void deleteAll();
	
	public Optional<Collection<String>> readAll();

}
