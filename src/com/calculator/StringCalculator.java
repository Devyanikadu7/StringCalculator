package com.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			List<String> number=new ArrayList<>();
			
			//If the string of numbers contain delimiters like //**
			if(numbers.contains("//"))
			{
				int index=numbers.indexOf("//");
				int newLineIndex=numbers.indexOf("\n");
				String delimiter=numbers.substring(index+2,newLineIndex);
			
				//If the string of numbers contain multiple delimiters like //[*][?]
				if(delimiter.contains("[") && delimiter.contains("]"))
				{
					Pattern pattern = Pattern.compile("\\[(.*?)]");
			        Matcher matcher = pattern.matcher(delimiter);

			        List<String> delimiters = new ArrayList<>();
			        while (matcher.find()) {
			            delimiters.add(matcher.group(1)); 
			        }
			        delimiters.add("\n");
			        
			        String regex = delimiters.stream()
                            .map(Pattern::quote) 
                            .reduce((d1, d2) -> d1 + "|" + d2) 
                            .orElse("");
			      
			        numbers = numbers.substring(newLineIndex + 1).trim();
			        String validationRegex = "^(\\-?\\d+(" + regex + ")?)+$";
			        if (!numbers.matches(validationRegex)) {
			            throw new IllegalArgumentException("Input contains unsupported delimiters.");
			        }
			        number=Arrays.asList(numbers.split(regex));
			        
				}
				//If the string of numbers contain only one delimiter of any length like //***
				else
				{
					numbers = numbers.substring(newLineIndex + 1).trim().replace("\n", delimiter);
					
					String regex = "(\\-?\\d+" + java.util.regex.Pattern.quote(delimiter) + ")*\\-?\\d+";
				        if (!numbers.matches(regex)) {
				            throw new IllegalArgumentException("Input does not strictly adhere to the specified delimiter: " + delimiter);
				        }
					
				     number=Arrays.asList(numbers.split(java.util.regex.Pattern.quote(delimiter)));
				}	
			}
			//If the string of numbers doesn't contain any delimiters other than ,
			else
			number=Arrays.asList(numbers.replace("\n", ",").split(","));
			
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
		if (foundNegative) {
	        throw new RuntimeException("Negative numbers not allowed: " + negativeNumbers);
	    }
		return result;
	}

}
