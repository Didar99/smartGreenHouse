package ne.iot.smartgreenhouse.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import ne.iot.smartgreenhouse.R;
import ne.iot.smartgreenhouse.utils.PrefConfig;

public class CameraActivity extends AppCompatActivity {

    WebView webView;
    String ip_address, prefix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ip_address = PrefConfig.loadIpPf(getApplicationContext());
        prefix = PrefConfig.loadPrefix(getApplicationContext());

        webView = findViewById(R.id.web);
        webView.loadUrl("http://" + ip_address + prefix);
    }
}