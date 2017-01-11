package bloodnepal.example.com.bloodnepal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Jango on 07/03/2016.
 */
public class registration extends AppCompatActivity{
    private Button btnsave;
    private EditText username,password,address,phonenum;

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register1);

        /*Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Bloodgroup,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select your Blood group");

        spinner.setAdapter(
                new SpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));
*/
        btnsave=(Button)findViewById(R.id.Register);

      /*  username= (EditText) findViewById(R.id.username2);
        password = (EditText) findViewById(R.id.password2);
        address= (EditText) findViewById(R.id.address);
        phonenum = (EditText) findViewById(R.id.phone);
        spinner  = (Spinner) findViewById(R.id.spinner);
*/
        btnsave.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String add = address.getText().toString().trim();
                String phon = phonenum.getText().toString().trim();






                if (TextUtils.isEmpty(username.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the username", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                else if(TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the Password", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                if (TextUtils.isEmpty(address.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the address", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                else if(TextUtils.isEmpty(phonenum.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the Phone number", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                else if(!TextUtils.isEmpty(password.getText().toString())&&
                        !TextUtils.isEmpty(password.getText().toString()) &&
                        !TextUtils.isEmpty(address.getText().toString())&&
                        !TextUtils.isEmpty(phonenum.getText().toString())) {
                    Intent i = new Intent(getApplicationContext(),
                            display.class);
                    startActivity(i);

                }
            }
        });

    }





    }

