package com.machine.main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Machine {
	
	private static ArrayList<NameVector> vectors = new ArrayList<NameVector>();
	private static final int KTHRESSHOLD = 15;

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//if a file is passed read that first and process that first;
		if(args.length > 0)
		{
			try {
				FileInputStream file = new FileInputStream(args[0]);
				DataInputStream in = new DataInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				String[] input = br.readLine().trim().split("\\s+");
				while(input != null)
				{
					if(input.length == 3)
					{
						System.out.println("Learning....");
						learn(input);
					}
					else if(input.length < 3)
					{
						classify(input);
					}
					else
					{
						System.out.println("ERROR: Too many args.");
						System.exit(0);
					}
					
					String s = br.readLine();
					if(s != null)
						input = s.trim().split("\\s+");
					else
						input = null;
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		String[] input = StdIn.readStrings();
		while(input != null)
		{
			if(input.length == 3)
			{
				System.out.println("Learning....");
				learn(input);
			}
			else if(input.length < 3)
			{
				classify(input);
			}
			else
			{
				System.out.println("ERROR: Too many args.");
				System.exit(0);
			}
			System.out.println("\n");
			input = StdIn.readStrings();
		}

	}
	
	public static void learn(String[] input)
	{
		System.out.println(input[0] + " " + input[1]);
		NameVector v1 = new NameVector();
		v1.createVector(input[0], input[1]);
		v1.setLabel(input[2]);
		vectors.add(v1);
	}
	
	public static void classify(String[] input)
	{
		String label;
		double[] distances = new double[vectors.size()];
		Set<NameVector> vectorSet = new HashSet<NameVector>();
		NameVector v1 = new NameVector();
		v1.createVector(input[0], input[1]);
		int index = find(v1);
		
		if(index != -1)
		{
			System.out.println("Ethnicity is: " + vectors.get(index).getLabel());
		}
		else
		{

			for(int i = 0; i < vectors.size(); i++)
			{
				distances[i] = NameVector.computeDistance(v1, vectors.get(i));
			}

			for(int i = 0; i < KTHRESSHOLD; i++)
			{
				int index1 = getMinValue(distances);
				
				System.out.println(vectors.get(index1).getS());
				
				vectorSet.add(vectors.get(index1));
				distances[index1] = -1;
			}

			label = findMajorityLabel(vectorSet);

			if(label != null)
			{
				v1.setLabel(label);
				System.out.println("The ethnicity is: " + label);
			}

			vectors.add(v1);
		}
	}
	
	private static int find(NameVector v1)
	{
		for(int i = 0; i < vectors.size(); i++)
		{
			if(vectors.get(i).getS().equals(v1.getS()))
				return i;
		}
		
		return -1;
	}

	private static String findMajorityLabel(Set<NameVector> vectorSet) {
		Map<String, Integer> labels = new HashMap<String, Integer>();
		Object[] vectorArray = vectorSet.toArray();
		
		// Count the number of occurances of a given label
		for(int i = 0; i < vectorArray.length; i++)
		{
			if(labels.containsKey(((NameVector) vectorArray[i]).getLabel()))
			{
				Integer value = labels.get(((NameVector) vectorArray[i]).getLabel()) + 1;
				labels.put(((NameVector) vectorArray[i]).getLabel(), value);
			}
			else
			{
				labels.put(((NameVector) vectorArray[i]).getLabel(), 1);
			}
		}
		
		int maxValue = 0;
		String majLabel = null;
		Object[] keys = labels.keySet().toArray();
		// Find the max of the labels.
		for(int i = 0; i < labels.size(); i++)
		{
			if(labels.get(keys[i]) > maxValue)
			{
				maxValue = labels.get(keys[i]);
				majLabel = (String) keys[i];
			}
		}
		
		return majLabel;
	}

	public static int getMinValue(double[] numbers){  
		double minValue = numbers[0];
		int returnValue = 0;
		
		for(int i=1;i < numbers.length;i++){  
			if(numbers[i] < minValue && numbers[i] > 0){  
				minValue = numbers[i];
				returnValue = i;
			}  
		}
		System.out.println("minValue: " + minValue);
		
		return returnValue;  
	} 

}
