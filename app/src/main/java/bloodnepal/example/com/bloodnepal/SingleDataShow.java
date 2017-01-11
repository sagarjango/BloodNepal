package bloodnepal.example.com.bloodnepal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SingleDataShow extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_details);
		TextView name1 = (TextView) findViewById(R.id.tvname);
		TextView adress = (TextView) findViewById(R.id.tvaddress);
		TextView bloodgrp = (TextView) findViewById(R.id.tvbloodgroup);
		TextView tvcontact = (TextView) findViewById(R.id.tvphoneno);
		TextView tvgender = (TextView) findViewById(R.id.tvgender);
		TextView tvage = (TextView) findViewById(R.id.tvage);

		String name = getIntent().getExtras().getString("s");
		String address = getIntent().getExtras().getString("t");
		String blood = getIntent().getExtras().getString("u");
		String contact = getIntent().getExtras().getString("v");
		String gender = getIntent().getExtras().getString("w");
		String age = getIntent().getExtras().getString("x");

		name1.setText(name);
		adress.setText(address);
		bloodgrp.setText(blood);
		tvcontact.setText(contact);
		tvgender.setText(gender);
		tvage.setText(age);
	}

}
