package com.cf.gepos.pmnt.runner;

import java.util.Scanner;

import com.cf.gepos.pmnt.Context;

public class LoginModule {

	private Context cntxt;

	public void setCntxt(Context cntxt) {
		this.cntxt = cntxt;
	}

	public void login() {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter login password : ");
		String loginpass = scn.nextLine();
		cntxt.setLoginPass(loginpass);
	}

}