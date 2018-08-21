package com.krm.test;

import com.krm.report.common.config.UserConfig;

public class UserConfigTest {
	public static void main(String[] args) {
		System.out.println(UserConfig.getUserName());
		System.out.println(UserConfig.getPassword());
		System.out.println(UserConfig.getUrl());
	}
}
