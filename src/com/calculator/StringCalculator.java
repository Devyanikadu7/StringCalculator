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
		StringBuilder negativeNumbers = new StringBuilder();
		boolean foundNegative = false;
		if(numbers.isEmpty())
			return 0;
		else
		{
			if(numbers.contains("//"))
			{
				
				int index=numbers.indexOf("//");
				int newLineIndex=numbers.indexOf("\n");
				
				String delimiter=numbers.substring(index+2,newLineIndex);
				numbers = numbers.substring(newLineIndex + 1).trim().replace("\n", delimiter);
				
				String regex = "(\\d+" + java.util.regex.Pattern.quote(delimiter) + ")*\\d+";
			        if (!numbers.matches(regex)) {
			            throw new IllegalArgumentException("Input does not strictly adhere to the specified delimiter: " + delimiter);
			        }
				
				List<String> number=Arrays.asList(numbers.split(java.util.regex.Pattern.quote(delimiter)));
				
				for(String i:number)
				{
					int num=Integer.parseInt(i);
					if (num < 0) {
		                if (foundNegative) {
		                    negativeNumbers.append(", ").append(num);
		                } else {
		                    negativeNumbers.append(num);
		                    foundNegative = true;
		                }
					}
					else
					{
						if(num>1000)
							continue;
						else
							result+=num;		
					}
				}	
			}
			else
			{
		
			List<String> number=Arrays.asList(numbers.replace("\n", ",").split(","));
			for(String i:number)
			{
				int num=Integer.parseInt(i);
				if (num < 0) {
	                if (foundNegative) {
	                    negativeNumbers.append(", ").append(num);
	                } else {
	                    negativeNumbers.append(num);
	                    foundNegative = true;
	                }
				}
				else
				{
					if(num>1000)
						continue;
					else
						result+=num;	
				}
			}
			}
		}
		if (foundNegative) {
	        throw new RuntimeException("Negative numbers not allowed: " + negativeNumbers);
	    }
		return result;
	}

}
