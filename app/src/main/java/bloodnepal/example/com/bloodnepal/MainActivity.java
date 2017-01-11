package bloodnepal.example.com.bloodnepal;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
		OnItemSelectedListener {
	ListView list;
	infoAdapter adapter;
	/*String[] iname={"neapl","neapl","neapl","neapl","neapl","neapl","neapl","neapl","neapl"};
	String[] iaddress ={"neapl","neapl","neapl","neapl","neapl","neapl","neapl","neapl","neapl"};
	String[] ibloodgrp={"neapl","neapl","neapl","neapl","neapl","neapl","neapl","neapl","neapl"};
	*/
	String[] iname;
	String[] iaddress ;
	String[] ibloodgrp;
	String[] icontact;
	String[] igender;
	String[] iage;
	List<row> rowitems;

	// Progress Dialog

		private ProgressDialog pDialog;

		// Creating JSON Parser object

		JSONParser jParser = new JSONParser();

		
		// url to get all products list
		private static String url_all_products = "http://192.168.1.107/android_connect/can.php";

		// JSON Node names
		private static final String TAG_SUCCESS = "success";
		private static final String TAG_NAME = "name";
		private static final String TAG_ADDRESS = "adress";
		private static final String TAG_BLOODGROUP = "bloodgroup";
		private static final String TAG_CONTACT = "contact";
		private static final String TAG_GENDER = "gender";
		private static final String TAG_PERSON = "stall";
		private static final String TAG_AGE = "age";
	
		// products JSONArray
		JSONArray products = null;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API. See
	 * https://g.co/AppIndexing/AndroidStudio for more information.
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);

		// Spinner element
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
		// listview for info
		list = (ListView) findViewById(R.id.listView);

		// Spinner click listenera
		spinner.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> address = new ArrayList<String>();
		address.add("--Select your city--");
		address.add("Pokhara");
		address.add("Kathmandu");
		address.add("Butwal");
		address.add("Chitwan");
		address.add("Biratnagar");
		address.add("Jhapa");

		List<String> bloodgrp = new ArrayList<String>();

		bloodgrp.add("A+");
		bloodgrp.add("A-");
		bloodgrp.add("B+");
		bloodgrp.add("B-");
		bloodgrp.add("AB+");
		bloodgrp.add("AB-");
		bloodgrp.add("O+");
		bloodgrp.add("O-");
		
		rowitems = new ArrayList<row>();
		new Loadfiles().execute();
		//Log.i("yaha pugyo", "app row item ko agadi");
		
	/*	for (int i = 0; i < iname.length; i++) {

			row r = new row(iname[i], iaddress[i], ibloodgrp[i]);
			rowitems.add(r);

		}*/

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, bloodgrp);
		ArrayAdapter<String> ppladress = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, address);

		// creating adapter for list view
		adapter = new infoAdapter(this, rowitems);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ppladress
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
		spinner2.setAdapter(ppladress);
		list.setAdapter(adapter);
		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			row r=(row) rowitems.get(position);
			String s = r.getname().toString();
			String t = r.getaddress().toString();
			String u = r.getblood().toString();
			String v = r.getcontact().toString();
			String w = r.getgender().toString();
			String x = r.getage().toString();
			
			
			
			Intent i = new Intent(MainActivity.this,SingleDataShow.class);
			i.putExtra("s",s);
			i.putExtra("t",t);
			i.putExtra("u",u);
			i.putExtra("v",v);
			i.putExtra("w",w);
			i.putExtra("x",x);
			
			startActivity(i);
			
			
		}
	});
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();

		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "Selected: " + item,
				Toast.LENGTH_LONG).show();

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	class Loadfiles extends AsyncTask<String, String, String> {

		
		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//Log.i("yaha pugyo", "app pre execute ko agadi");
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Loading products. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET",
					params);

			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_PERSON);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						
						List<String> man = new ArrayList<String>();
						
						Log.d("c", "before saving name");
						man.add(c.getString(TAG_NAME));
						Log.d("c", "before saving  address");
						 man.add(c.getString(TAG_ADDRESS));
						Log.d("c", "before saving bloodgrp");
						man.add(c.getString(TAG_BLOODGROUP));
						man.add(c.getString(TAG_CONTACT));
						man.add(c.getString(TAG_AGE));
						man.add(c.getString(TAG_GENDER));
						row r = new row(man);
						rowitems.add(r);					}
				} 
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			

		}

	}

}

class row {
	String name;
	String address;
	String bloodgrp;
	String contact;
	String age;
	String gender;

	row(String name, String address, String blood) {
		this.name = name;
		this.address = address;
		this.bloodgrp = blood;
	}

	public row(List<String> man) {
		// TODO Auto-generated constructor stub
		this.name= man.get(0);
		this.address= man.get(1);
		this.bloodgrp= man.get(2);
		this.contact=man.get(3);
		this.gender=man.get(4);
		this.age=man.get(5);
		
	
	
	}

	public String getname() {
		return name;
	}
	public String getcontact() {
		return contact;
	}
	public String getage() {
		return age;
	}
	public String getgender() {
		return gender;
	}

	public String getaddress() {
		return address;
	}

	public String getblood() {
		return bloodgrp;
	}

	public void setImageId(int imageId) {
		// this.imageId = imageId;
	}

	public void setStringTitle(String title) {
		// this.title = title;
	}

}