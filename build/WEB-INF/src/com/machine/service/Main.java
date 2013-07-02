package com.machine.service;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.machine.manager.*;

@Controller
@RequestMapping("/main")
public class Main 
{
	private Vectors vectorManager;
	
	public Main()
	{
		vectorManager = new Vectors();
		this.vectorManager.learn("/var/lib/tomcat6/webapps/listOfNames.txt");
	}
	
	/**
	 * Classifies the name to an ethnicity and spits back the answer.
	 * @param first the first name
	 * @param last the last name
	 * @param callback the function to call after execution is complete
	 * @param model default model object
	 * @return the JSONView
	 */
	@RequestMapping(value = "/classify", method = RequestMethod.GET)
	public String classifyName(@RequestParam("first") String first, @RequestParam("last") String last, @RequestParam("callback") String callback, ModelMap model)
	{
		// open service wide session
		Session sess = Manager.openSession();
		
		// implement the main vector classification model here.
		String ethnicity = vectorManager.classify(first, last);
		
		//json the match object
		Gson gson = new Gson();
		String json = gson.toJson(ethnicity);
		
		//add the json to the view and push the model
		model.addAttribute("json", json);
		model.addAttribute("callback", callback);

		sess.close();
		
		return "JSONView";
	}
	

}
