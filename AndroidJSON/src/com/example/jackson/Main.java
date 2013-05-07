package com.example.jackson;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Main  extends ListActivity {
  private static final String tag = Main.class.getName();
  //url to make request
  private static String url = "http://api.androidhive.info/contacts/";
  @Override
  public void onCreate(Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      TextView textview = (TextView) this.findViewById(R.id.textview);
      // Hashmap for ListView
   	  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
      
      // Creating JSON Parser instance
   	  JSONParser jParser = new JSONParser();

   	  // getting JSON string from URL
   	  String jsonString = jParser.getStringFromUrl(url);

      ContactsController contactsController = new ContactsController(jsonString);
      contactsController.init();
      for (Contact contact : contactsController.findAll())
        {
    	  StringBuffer strBuffer = new StringBuffer();
    	  strBuffer.append("ID: " + contact.id + "\n");
          strBuffer.append("Name: " + contact.name + "\n");
          
          strBuffer.append("email: " + contact.email + "\n");
          strBuffer.append("address: " + contact.address+ "\n");
          strBuffer.append("gender: " + contact.gender + "\n");
          strBuffer.append("phone.mobile: " + contact.phone.mobile + "\n");
          strBuffer.append("phone.home: " + contact.phone.home + "\n");
          strBuffer.append("phone.office: " + contact.phone.office + "\n");
          
          // creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			
			// adding each child node to HashMap key => value
			map.put("ID: ", contact.id);
			map.put("Name: ", contact.name);
			map.put("email: ", contact.email);
			map.put("address: ", contact.address);
			map.put("gender: ", contact.gender);
			map.put("phone.mobile: ", contact.phone.mobile);
			map.put("phone.home: ", contact.phone.home);
			map.put("phone.office: ", contact.phone.office);

			// adding HashList to ArrayList
			contactList.add(map);
        }

          
          /**
  		 * Updating parsed JSON data into ListView
  		 * */
  		ListAdapter adapter = new SimpleAdapter(this, contactList,
  				R.layout.list_item,
  				new String[] { "ID: ", "Name: ", "email: ", "address: ","gender: ","phone.mobile: ","phone.home: ","phone.office: " }, new int[] {
  					R.id.mid, R.id.name, R.id.email, R.id.address, R.id.gender, R.id.phone_mobile,  R.id.phone_home, R.id.phone_office });

  		setListAdapter(adapter);

  		// selecting single ListView item
  		ListView lv = getListView();

  		// Launching new screen on Selecting Single ListItem
  		lv.setOnItemClickListener(new OnItemClickListener() {

  			@Override
  			public void onItemClick(AdapterView<?> parent, View view,
  					int position, long id) {
  				// getting values from selected ListItem
  				String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
  				String cost = ((TextView) view.findViewById(R.id.email)).getText().toString();
  				String description = ((TextView) view.findViewById(R.id.phone_mobile)).getText().toString();
  				
  				// Starting new intent
  				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
//  				in.putExtra(TAG_NAME, name);
//  				in.putExtra(TAG_EMAIL, cost);
//  				in.putExtra(TAG_PHONE_MOBILE, description);
  				startActivity(in);

  			}
  		});
     }
  /**
   * {
   *  "id": "c200",
   *  "name": "Ravi Tamada",
   *	"email": "ravi@gmail.com",
   *	"address": "xx-xx-xxxx,x - street, x - country",
   *	"gender" : "male",
   *	"phone": {
   *     "mobile": "+91 0000000000",
   *     "home": "00 000000",
   *     "office": "00 000000"
   *   }
   * }
   */
}
