package ne.iot.smartgreenhouse.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import ne.iot.smartgreenhouse.R;
import ne.iot.smartgreenhouse.utils.PrefConfig;

public class SettingsActivity extends AppCompatActivity {

    EditText editIP, editPrefix, editLoop;
    String strIp, strPrefix;
    int strLoop;
    Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // declare variables
        editIP = findViewById(R.id.editIP);
        editPrefix = findViewById(R.id.editPrefix);
        editLoop = findViewById(R.id.editLoop);

        // load data from PrefConfig
        strIp = PrefConfig.loadIpPf(getApplicationContext());
        strPrefix = PrefConfig.loadPrefix(getApplicationContext());
        strLoop = PrefConfig.loadLoop(getApplicationContext());

        editIP.setText(strIp);
        editPrefix.setText(strPrefix);
        editLoop.setText(String.valueOf(strLoop));
    }

    private void saveData() {
        // get user data changes and save into mobile phone
        strIp = editIP.getText().toString();
        strPrefix = editPrefix.getText().toString();
        strLoop = Integer.parseInt(editLoop.getText().toString());

        PrefConfig.saveIpPf(getApplicationContext(), strIp);
        PrefConfig.savePrefix(getApplicationContext(), strPrefix);
        PrefConfig.saveLoop(getApplicationContext(), strLoop);
        // show text for successfully
        Toast.makeText(SettingsActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
        finishAffinity();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.action_save) {// if place doesn't exist any characters
            if (editIP.getText().toString().length() <= 0) {
                editIP.setError(getResources().getString(R.string.fit));
            } else if (editPrefix.getText().toString().length() <= 0) {
                editPrefix.setError(getResources().getString(R.string.fit));
            } else if (editLoop.getText().toString().length() <= 0) {
                editLoop.setError(getResources().getString(R.string.fit));
            } else {
                saveData();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}