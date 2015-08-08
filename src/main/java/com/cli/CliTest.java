package com.cli;

import java.util.Scanner;

public class CliTest {
	public static void main(String[] args) {
		/*Options options = new Options();
		options.addOption("t",false,"display current time");
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);
		
		if(cmd.hasOption("t")){
			System.out.println(new Date().toGMTString());
		}else{
			System.out.println("command is error");
		}*/
		
		Scanner s = new Scanner(System.in);
		while(true){
			System.out.println("请输入：");
			String str = s.next();
			System.out.println(str);
		}
		
	}
}
