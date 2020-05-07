package com.cf.gepos.pmnt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassManageImpl implements PassManage {

	private Context context;

	private Map<String, List<String>> scache = new HashMap<>();

	private Conductor cndt;

	private ContentRepository contentRepository;

	public void setContext(Context context) {
		this.context = context;
	}

	public void setContentRepository(ContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	}

	public void setCndt(Conductor cndt) {
		this.cndt = cndt;
	}

	private void init() {
		try {
			String fcontent = contentRepository.readContent();
			String pcontent = cndt.process((e) -> e.decrypt(fcontent));
			String[] kps = pcontent.split("\n");
			scache.putAll(Arrays.stream(kps).filter(kp -> !kp.isEmpty()).map(kp -> kp.split(" "))
					.filter(stk -> stk.length > 1).collect(Collectors.groupingBy(stk -> stk[0],
							Collectors.mapping(stk -> stk[1], Collectors.toList()))));

		} catch (NoSuchFileException e) {
			System.out.println("Message  -  No File");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Optional<Collection<String>> read(String string) {
		if (scache.isEmpty()) {
			init();
		}
		return Optional.ofNullable(scache.get(string));
	}

	public Optional<Collection<String>> readAll() {
		if (scache.isEmpty()) {
			init();
		}
		return Optional.ofNullable(scache.keySet());
	}

	@Override
	public void update(String key, String pass, final boolean isUpdate) {
		if (key.isBlank())
			return;

		if (scache.isEmpty()) {
			init();
		}

		scache.compute(key, (k, v) -> {
			if (v == null) {
				return Stream.of(pass).collect(Collectors.toList());
			} else {
				if (isUpdate)
					v.clear();
				v.add(pass);
				return v;
			}
		});

		ExecutorService ex = Executors.newSingleThreadExecutor();
		ex.execute(this::writeFile);
		ex.shutdown();
	}

	private void writeFile() {
		try {
			String scontent = scache.entrySet().stream()
					.flatMap(v -> v.getValue().stream().map(kv -> v.getKey() + " " + kv))
					.collect(Collectors.joining("\n"));
			String pcontent = cndt.process((e) -> e.encrypt(scontent));
			contentRepository.writeContent(pcontent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String string) {
		if (scache.isEmpty()) {
			init();
		}
		if (scache.isEmpty())
			return;
		scache.remove(string);

		ExecutorService ex = Executors.newSingleThreadExecutor();
		ex.execute(this::writeFile);
		ex.shutdown();
	}

	@Override
	public void deleteAll() {
		try {
			Path p1 = context.getPfile();
			Files.copy(p1, p1.getParent().resolve(p1.getFileName() + ".backup"), StandardCopyOption.REPLACE_EXISTING);
			Files.delete(p1);
			scache.clear();
		} catch (NoSuchFileException e) {
			System.out.println("Message  -  No File");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
