package com.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StringCalculator {

	public static void main(String[] args) {
		String numbers="";
		System.out.println("Enter String of numbers:");
		Scanner scanner=new Scanner(System.in);
		while(true)
		{
			String line=scanner.nextLine();
			if(line.trim().isEmpty())
				break;
			numbers+=line+"\n";
		}
		scanner.close();
		int sum=Add(numbers);
		System.out.println("Sum:"+sum);

	}

	private static int Add(String numbers) {
		int result=0;
		if(numbers.isEmpty())
			return 0;
		else
		{
			List<String> number=Arrays.asList(numbers.replace("\n", ",").split(","));
			for(String i:number)
				result=result+Integer.parseInt(i);	
		}
		return result;
	}

}
