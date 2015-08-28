package com.example.atli.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private TextView display_m;
    private Vibrator vibrator_m;
    private Boolean use_vibrator_m = false;
    SharedPreferences sp_m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display_m = (TextView) findViewById(R.id.display);

        vibrator_m = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        sp_m = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        use_vibrator_m = sp_m.getBoolean("vibrate", false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        use_vibrator_m = sp_m.getBoolean("vibrate", false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, CalcPreferenceActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonPressed(View view) {
        Button buttonView = (Button) view;
        String text = null;

        // Log.i("Calculator", "Clicked");

        switch( view.getId() ) {
            case R.id.buttonPlus:
                text = display_m.getText().toString();
                if(!(text.endsWith("+") || text.endsWith("-"))) {
                    display_m.append( " " + buttonView.getText() + " " );
                }
                break;
            case R.id.buttonMinus:
                text = display_m.getText().toString();
                if(!text.endsWith(" ")) {
                    display_m.append( " " + buttonView.getText() + " " );
                }
                break;
            case R.id.buttonBack:
                text = display_m.getText().toString();
                if(text.length() > 0) {
                    display_m.setText(text.substring(0, text.length()-1));
                }
                break;
            case R.id.buttonEq:
                text = display_m.getText().toString();
                StringTokenizer st = new StringTokenizer(text , "[+\\-]", true);
                int result = 0;
                String token = "";
                if(st.hasMoreElements()) result = Integer.parseInt(st.nextToken());
                while(st.hasMoreElements()) {
                    String elem = st.nextToken();
                    if(elem == "+") {
                        result += Integer.parseInt(st.nextToken());
                    }

                }
            case R.id.buttonC:
                display_m.setText("");
                break;
            default:
                display_m.append( buttonView.getText() );
                break;
        }

        if( use_vibrator_m ) {
            vibrator_m.vibrate( 500 );
            Toast.makeText( getApplicationContext(), "Vibrate ...", Toast.LENGTH_LONG);
        }
    }
}
