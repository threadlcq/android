package lacom.lzu.edu.webservice;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lacom.lzu.edu.ado.Contact;
import lacom.lzu.edu.service.ContactService;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.webkit.WebView;

public class WebService extends Activity
{
	private ContactService service;
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_service);
		service = new ContactService();
		webView = (WebView) this.findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		//在html中的javascript中 把java的对象contact传递给他 这个对象就是new ContactPlugin()
		webView.addJavascriptInterface(new ContactPlugin(), "contacts");
		webView.loadUrl("file:///android_asset/index.html");// 添加html文件
	}

	private final class ContactPlugin
	{
		public void getContacts()
		{
			try
			{
				List<Contact> contacts = service.getContacts();
				// OO的方法将对象的数据转成json字符串；
				JSONArray jsonArray = new JSONArray();// 创建json数组
				for (Contact contact : contacts)
				{
					JSONObject item = new JSONObject();
					item.put("id", contact.getId());
					item.put("name", contact.getName());
					item.put("mobile", contact.getMobile());
					jsonArray.put(item);
				}
				String json = jsonArray.toString();
				webView.loadUrl("javascript:show('"+json+"')");//调用js的show方法；
			} catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void call(String mobile){  
	        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));  
	        startActivity(intent);  
	    } 
	}
	
	

}
