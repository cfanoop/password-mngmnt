package com.cf.gepos.pmnt;

import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class CmdLineUser {

	private Scanner inputScanner = new Scanner(System.in);

	private int userInput = 0;

	public Map<String, String> read() {
		Map<String, String> userInps = new HashMap<>(3);

		do {
			try {
				display();
				userInput = inputScanner.nextInt();
				inputScanner.skip("\n");
				switch (userInput) {
				case 1:
					System.out.println("Enter key ");
					userInps.put("op", "read");
					userInps.put("key", inputScanner.nextLine());
					break;
				case 2:
					userInps.put("op", "add");
					System.out.println("Enter Key ");
					userInps.put("key", inputScanner.nextLine());
					System.out.println("Enter password ");
					userInps.put("pass", inputScanner.nextLine());
					break;
				case 3:
					userInps.put("op", "update");
					System.out.println("Enter Key ");
					userInps.put("key", inputScanner.nextLine());
					System.out.println("Enter password ");
					userInps.put("pass", inputScanner.nextLine());
					break;
				case 4:
					userInps.put("op", "delete");
					System.out.println("Enter Key ");
					userInps.put("key", inputScanner.nextLine());
					break;
				case 5:
					userInps.put("op", "deleteAll");
					break;
				case 6:
					userInps.put("op", "listAll");
					break;
				case 9:
					userInps.put("op", "stop");
					break;
				default:
					System.out.println("Invalid Entry");
					userInput = 0;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Entry");
				userInput = 0;
				inputScanner.next();
			}
		} while (userInput < 1);

		return userInps;
	}

	private void display() {
		StringBuilder strb = new StringBuilder();
		strb.append("\nMenu\n");
		strb.append("1) Read Password\n");
		strb.append("2) Add Password\n");
		strb.append("3) Update Password\n");
		strb.append("4) Delete Password\n");
		strb.append("5) Delete All\n");
		strb.append("6) List All Keys\n");
		strb.append("9) Exit\n");
		strb.append("\nSelect any option: ");
		System.out.println(strb.toString());
	}

	public void write(Optional<Collection<String>> keyPass) {
		System.out.println("\n*****************\nResult : ");
		keyPass.ifPresent(list -> System.out.print(String.join(", ", list)));
		System.out.println("\n*****************\n");
	}
}
