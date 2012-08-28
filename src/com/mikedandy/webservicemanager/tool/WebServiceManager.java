package com.mikedandy.webservicemanager.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * This class provides several Web Services functionalities. This
 * class requires <strong>android.permission.INTERNET</strong>.
 * @author Michael Dandy
 */
public class WebServiceManager 
{
	private final static String TAG = "WebServiceManager";
	private static WebServiceManager mInstance;

	/**
	 * Get an instance of WebServiceManager.
	 * @return an instance of WebServiceManager
	 */
	public static WebServiceManager getInstance()
	{
		if (mInstance == null)
			mInstance = new WebServiceManager();
		return mInstance;
	}
	
	private String normalizedUrl(String url)
	{
		if (!url.startsWith("http://"))
			return "http://" + url;
		return url;
	}

	/**
	 * Send a POST request to a server.
	 * @param url The URL of the server
	 * @param args The arguments passed to the server
	 * @return an array of response from the server
	 * 	<ul>
	 * 		<li>0: status code </li>
	 * 		<li>1: server response </li>
	 *	</ul>
	 */
	public String[] doPost(String url, List<NameValuePair> args)
	{
		String[] result = new String[2];
		BufferedReader in = null;
		try 
		{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(normalizedUrl(url));
			httpPost.setEntity(new UrlEncodedFormEntity(args));
			HttpResponse response = httpClient.execute(httpPost);

			InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());
			in = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) 
				sb.append(line + NL);
			in.close();
			
			result[0] = response.getStatusLine().getStatusCode() + ": "+ response.getStatusLine().getReasonPhrase();
			result[1] = sb.toString();
		}
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
			Log.e(TAG, "UnsupportedEncodingException: " + e.getMessage());
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
			Log.e(TAG, "ClientProtocolException: " + e.getMessage());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			Log.e(TAG, "IOException: " + e.getMessage());
		}
		finally
		{
			if (in != null) 
			{
				try 
				{
					in.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					Log.e(TAG, "IOException: " + e.getMessage());
				}
			}
		}

		return result;
	}
	
	/**
	 * Send a GET request to a server.
	 * @param uri The URI of the server
	 * @return an array of response from the server
	 * 	<ul>
	 * 		<li>0: status code </li>
	 * 		<li>1: server response </li>
	 *	</ul>
	 */
	public String[] doGet(String uri)
	{
		String[] result = new String[2];
		BufferedReader in = null;
		try 
		{
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(normalizedUrl(uri));
			HttpResponse response = httpClient.execute(httpGet);

			InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());
			in = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) 
				sb.append(line + NL);
			in.close();
			
			result[0] = response.getStatusLine().getStatusCode() + ": "+ response.getStatusLine().getReasonPhrase();
			result[1] = sb.toString();
		}
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
			Log.e(TAG, "UnsupportedEncodingException: " + e.getMessage());
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
			Log.e(TAG, "ClientProtocolException: " + e.getMessage());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			Log.e(TAG, "IOException: " + e.getMessage());
		}
		finally
		{
			if (in != null) 
			{
				try 
				{
					in.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					Log.e(TAG, "IOException: " + e.getMessage());
				}
			}
		}

		return result;
	}
}