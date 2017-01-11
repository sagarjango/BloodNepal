package bloodnepal.example.com.bloodnepal;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText inputName;
	EditText inputaddress;
	EditText inputblood;
	EditText inputcontact;
	EditText inputage;
	EditText inputgender;
	EditText inputpassword;
	// url to create new product
	private static String url_create_product = "http://192.168.1.107/android_connect/new.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register1);

		// Edit Text
		inputName = (EditText) findViewById(R.id.vtname);
		inputaddress = (EditText) findViewById(R.id.vtaddress);
		inputblood = (EditText) findViewById(R.id.vtblood);
		inputcontact = (EditText) findViewById(R.id.vtphone);
		inputgender = (EditText) findViewById(R.id.vtgender);
		inputage = (EditText) findViewById(R.id.vtage);
		inputpassword = (EditText) findViewById(R.id.vtage);
		// Create button
		Button btnCreateProduct = (Button) findViewById(R.id.Register);

		// button click event
		btnCreateProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				  if (TextUtils.isEmpty(inputName.getText().toString())) {
	                    Toast.makeText(getApplicationContext(),
	                            "Please enter the username", Toast.LENGTH_LONG)
	                            .show();
	                    return;
	                }
	                else if(TextUtils.isEmpty(inputpassword.getText().toString())) {
	                    Toast.makeText(getApplicationContext(),
	                            "Please enter the Password", Toast.LENGTH_LONG)
	                            .show();
	                    return;
	                }
	                if (TextUtils.isEmpty(inputaddress.getText().toString())) {
	                    Toast.makeText(getApplicationContext(),
	                            "Please enter the address", Toast.LENGTH_LONG)
	                            .show();
	                    return;
	                }
	                else if(TextUtils.isEmpty(inputcontact.getText().toString())) {
	                    Toast.makeText(getApplicationContext(),
	                            "Please enter the Phone number", Toast.LENGTH_LONG)
	                            .show();
	                    return;
	                }
	                else if(!TextUtils.isEmpty(inputpassword.getText().toString())&&
	                        !TextUtils.isEmpty(inputaddress.getText().toString()) &&
	                        !TextUtils.isEmpty(inputName.getText().toString())&&
	                        !TextUtils.isEmpty(inputcontact.getText().toString())) {
	                   
  
				new CreateNewProduct().execute();
			}}
		});
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		String name,address,blood,contact,gender,age;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(register.this);
			pDialog.setMessage("Creating Profile..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
			name = inputName.getText().toString();
			 address = inputaddress.getText().toString();
			 blood = inputblood.getText().toString();
			 contact = inputcontact.getText().toString();
			 age = inputage.getText().toString();
			 gender = inputgender.getText().toString();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {

			

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("address", address));
			params.add(new BasicNameValuePair("blood", blood));
			params.add(new BasicNameValuePair("contact", contact));
			params.add(new BasicNameValuePair("gender", gender));
			params.add(new BasicNameValuePair("age", age));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Intent i = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(i);
					
					// closing this screen
					finish();
				} else {
					// failed to create product
					String msg = json.getString(TAG_MESSAGE);
					Toast.makeText(getApplicationContext(), ""+ msg, Toast.LENGTH_LONG).show();
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
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
}
