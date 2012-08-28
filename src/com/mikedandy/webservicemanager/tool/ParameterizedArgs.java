package com.mikedandy.webservicemanager.tool;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ParameterizedArgs 
{
	private List<NameValuePair> args;

	/**
	 * Constructor
	 */
	public ParameterizedArgs()
	{
		this.args = new ArrayList<NameValuePair>();
	}

	/**
	 * Add an argument.
	 * @param key The key of the argument
	 * @param value The value of the argument
	 * @return always true
	 */
	public boolean addArgument(String key, String value)
	{
		return this.args.add(new BasicNameValuePair(key, value));
	}

	/**
	 * Get the list of parameterized arguments.
	 * @return the list of parameterized arguments
	 */
	public List<NameValuePair> getArguments()
	{
		return this.args;
	}
}