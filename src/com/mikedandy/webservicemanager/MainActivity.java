package com.mikedandy.webservicemanager;

import com.mikedandy.webservicemanager.tool.ParameterizedArgs;
import com.mikedandy.webservicemanager.tool.WebServiceManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener
{
	private EditText mUrl;
	private EditText mKey;
	private EditText mValue;
	private TextView mResponse;
	
	private WebServiceManager mWsm;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.mUrl = (EditText) findViewById(R.id.url);
        this.mKey = (EditText) findViewById(R.id.key);
        this.mValue = (EditText) findViewById(R.id.value);
        this.mResponse = (TextView) findViewById(R.id.response);
        clearResponse();
        
        Button btnPost = (Button) findViewById(R.id.post);
        Button btnGet = (Button) findViewById(R.id.get);
        
        btnPost.setOnClickListener(this);
        btnGet.setOnClickListener(this);
        
        this.mWsm = WebServiceManager.getInstance();
    }

	public void onClick(View v) 
	{
		clearResponse();
		switch (v.getId())
		{
			case R.id.post: doPost(); break;
			case R.id.get: doGet(); break;
		}
	}
	
	private String getUrl()
	{
		return this.mUrl.getText().toString();
	}
	
	private String getKey()
	{
		return this.mKey.getText().toString();
	}
	
	private String getValue()
	{
		return this.mValue.getText().toString();
	}
	
	private void clearResponse()
	{
		this.mResponse.setText("");
	}
	
	private void echoResponse(String response)
	{
		this.mResponse.append(response + "\n");
	}
	
	private void doPost()
	{
		String url = getUrl();
		String key = getKey();
		String value = getValue();
		
		ParameterizedArgs args = new ParameterizedArgs();
		args.addArgument(key, value);
		String[] response = this.mWsm.doPost(url, args.getArguments());
		
		echoResponse(response[0]);
		echoResponse(response[1]);
	}
	
	private void doGet()
	{
		String uri = getUrl();
		String[] response = this.mWsm.doGet(uri);
		
		echoResponse(response[0]);
		echoResponse(response[1]);
	}
}
