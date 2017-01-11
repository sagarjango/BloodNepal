package bloodnepal.example.com.bloodnepal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
	private Button btnlogin, btnreg;
	private EditText username, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Log.d("loging layout bhanda mathi", "ashdashdh");
		setContentView(R.layout.activity_login);

		btnlogin = (Button) findViewById(R.id.Login);
		btnreg = (Button) findViewById(R.id.Register);

		username = (EditText) findViewById(R.id.editusername);
		password = (EditText) findViewById(R.id.editpassword);

		/*
		 * if(TextUtils.isEmpty(username.getText().toString())) {
		 * Toast.makeText(this, "plz enter your name ",
		 * Toast.LENGTH_SHORT).show(); return; }
		 */

		btnlogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String user = username.getText().toString().trim();
				String pass = password.getText().toString().trim();

				if (TextUtils.isEmpty(username.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Please enter the username", Toast.LENGTH_LONG)
							.show();
					return;
				} else if (TextUtils.isEmpty(password.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Please enter the Password", Toast.LENGTH_LONG)
							.show();
					return;
				} else if (!TextUtils.isEmpty(password.getText().toString())
						&& !TextUtils.isEmpty(password.getText().toString())) {
					Intent i = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(i);

				}
			}
		});

		// Link to Register Screen
		btnreg.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), register.class);
				startActivity(i);
			}
		});
	}

}
