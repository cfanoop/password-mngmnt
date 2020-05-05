package com.cf.gepos.pmnt;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class PasswordManager {

	private CmdLineUser ri;

	private PassManage pssmange;

	public static void main(String[] args) {

		PasswordManager pmgr = new PasswordManager();
		pmgr.run();
	}

	private void run() {

		ri = new CmdLineUser();
		pssmange = new PassManageImpl();
		Context ctxt = new Context();
		ctxt.setKeyfile(Paths.get("/Users/anoopchiramel/Files/temp/pass.txt"));
		ctxt.setPfile(Paths.get("/Users/anoopchiramel/Files/temp/abc1.txt"));
		Conductor cdt = new Conductor(ctxt);
		pssmange.setCndt(cdt);
		pssmange.setContext(ctxt);

		String op = "";
		System.out.println("Starting Password Manager");
		while (!op.equals("stop")) {
			Map<String, String> userInps = ri.read();
			op = userInps.get("op");
			if (op.equals("read")) {
				Optional<Collection<String>> keyPass = pssmange.read(userInps.get("key"));
				ri.write(keyPass);
			} else if (op.equals("add") || op.equals("update")) {
				pssmange.update(userInps.get("key"), userInps.get("pass"), op.equals("update"));
			} else if (op.equals("delete")) {
				pssmange.delete(userInps.get("key"));
			} else if (op.equals("deleteAll")) {
				pssmange.deleteAll();
			} else if (op.equals("listAll")) {
				Optional<Collection<String>> keys = pssmange.readAll();
				ri.write(keys);
			}

		}

		System.out.println("Exiting");
	}

}
