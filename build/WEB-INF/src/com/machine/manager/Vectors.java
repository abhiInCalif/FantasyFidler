package com.machine.manager;

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

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.machine.model.Name;
import com.machine.main.NameVector;
import com.machine.manager.Manager;

public class Vectors extends Manager
{
	private ArrayList<NameVector> vectors = new ArrayList<NameVector>();
	private static final int KTHRESSHOLD = 15;
	
	public Vectors()
	{
		this.loadVectors();
	}

	
	public void learn(String filename)
	{
		try {
			FileInputStream file = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String[] input = br.readLine().trim().split("\\s+");
			while(input != null)
			{
				System.out.println("Learning....");
				learn(input);
				
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
	
	private void learn(String[] input)
	{
		Session sess = Manager.getCurrentSession();
		
		Name v1 = new Name(); // create the new data object
		v1.setFirstName(input[0]);
		v1.setLastName(input[1]);
		v1.setEthnicity(input[2]);
		
		Manager.save(v1, sess); // save the object to the database
	}
	
	public String classify(String first, String last)
	{
		//Start the session object
		Session sess = Manager.getCurrentSession();
		
		String label;
		double[] distances = new double[vectors.size()];
		Set<NameVector> vectorSet = new HashSet<NameVector>();
		NameVector v1 = new NameVector();
		v1.createVector(first, last);
		
		int index = find(v1);
		
		if(index != -1)
		{
			return vectors.get(index).getLabel();
		}
		
		// not in list clause
		for(int i = 0; i < vectors.size(); i++)
		{
			distances[i] = NameVector.computeDistance(v1, vectors.get(i));
		}

		for(int i = 0; i < KTHRESSHOLD; i++)
		{
			int index1 = getMinValue(distances);			
			vectorSet.add(vectors.get(index1));
			distances[index1] = -1;
		}

		label = findMajorityLabel(vectorSet);

		if(label != null)
		{
			v1.setLabel(label);
			vectors.add(v1);
		}
		
		// add the object to the database;
		Name name = new Name();
		name.setEthnicity(label);
		name.setFirstName(first);
		name.setLastName(last);
		Manager.save(name, sess);
		
		return label;
	}

	private void loadVectors()
	{
		Session sess = Manager.getCurrentSession();
		Transaction tr = sess.beginTransaction();
		int i = 2;
		Name name = (Name) sess.get(Name.class, 1);
		
		while(name != null)
		{
			NameVector v1 = new NameVector();
			v1.createVector(name.getFirstName(), name.getLastName());
			v1.setLabel(name.getEthnicity());
			this.vectors.add(v1);
			name = (Name) sess.get(Name.class, i);
			i++;
		}
	}
	
	private int find(NameVector v1)
	{
		for(int i = 0; i < vectors.size(); i++)
		{
			if(vectors.get(i).getS().equals(v1.getS()))
				return i;
		}
		
		return -1;
	}

	private String findMajorityLabel(Set<NameVector> vectorSet) {
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

	private int getMinValue(double[] numbers){  
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
