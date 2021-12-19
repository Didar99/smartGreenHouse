package ne.iot.smartgreenhouse.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.hadiidbouk.charts.BarData;
import com.hadiidbouk.charts.ChartProgressBar;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ne.iot.smartgreenhouse.R;
import ne.iot.smartgreenhouse.adapter.SensorsAdapter;
import ne.iot.smartgreenhouse.model.Device;
import ne.iot.smartgreenhouse.model.Sensors;
import ne.iot.smartgreenhouse.utils.NetworkChangeReceiver;
import ne.iot.smartgreenhouse.utils.OnNetworkListener;
import ne.iot.smartgreenhouse.utils.PrefConfig;

import static ne.iot.smartgreenhouse.utils.Commands.dropCloseCom;
import static ne.iot.smartgreenhouse.utils.Commands.dropOpenCom;
import static ne.iot.smartgreenhouse.utils.Commands.heater2OffCom;
import static ne.iot.smartgreenhouse.utils.Commands.heater2OnCom;
import static ne.iot.smartgreenhouse.utils.Commands.heater3OffCom;
import static ne.iot.smartgreenhouse.utils.Commands.heater3OnCom;
import static ne.iot.smartgreenhouse.utils.Commands.heaterAutoCom;
import static ne.iot.smartgreenhouse.utils.Commands.heaterManualCom;
import static ne.iot.smartgreenhouse.utils.Commands.heaterOffCom;
import static ne.iot.smartgreenhouse.utils.Commands.heaterOnCom;
import static ne.iot.smartgreenhouse.utils.Commands.lightOffCom;
import static ne.iot.smartgreenhouse.utils.Commands.lightOnCom;
import static ne.iot.smartgreenhouse.utils.Commands.waterCloseCom;
import static ne.iot.smartgreenhouse.utils.Commands.waterOpenCom;
import static ne.iot.smartgreenhouse.utils.Commands.windowCloseCom;
import static ne.iot.smartgreenhouse.utils.Commands.windowOpenCom;
import static ne.iot.smartgreenhouse.utils.InternetUtils.isNetworkConnected;

public class MainActivity extends Activity implements OnNetworkListener {


    MaterialButton mLight, mWindow, mHeater, mHeater2, mHeater3, mWater, mDrop;
    TextView tLight, tWindow, tHeater, tHeater2, tHeater3, tWater, tDrop;
    int iLight, iWindow, iHeater, iHeater2, iHeater3, iWater, iDrop;
    ImageView toCamera;

    String url, ip, prefix;
    RecyclerView sensor_recycler;
    LinearLayoutManager linearLayoutManager;
    List<Sensors> sensorList;
    RecyclerView.Adapter sensorsAdapter;
    boolean dataChecked = false;
    ImageView settings;
    int min = 20;
    int max = 30;
    int minim = 1;
    int maxim = 5;
    int s1, s2, s3, s4, s5, s6, s7;
    int random, random_s;
    ArrayList<BarData> dataList;

    private Snackbar snack;
    private NetworkChangeReceiver mNetworkReceiver;

    ChartProgressBar mChart;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = PrefConfig.loadIpPf(getApplicationContext());
        prefix = PrefConfig.loadPrefix(getApplicationContext());
        url = "http://" + ip + prefix;

        random = new Random().nextInt((max - min) + 1) + min;
        random_s = new Random().nextInt((maxim - minim) + 1) + min;
        s1 = random - random_s;
        s2 = random - random_s - 1;
        s3 = random - random_s - 2;
        s4 = random + random_s;
        s5 = random + random_s + 1;
        s6 = random + random_s + 2;
        s7 = random + random_s + 3;

        dataList = new ArrayList<>();

        mChart = findViewById(R.id.ChartProgressBar);
        sensor_recycler = findViewById(R.id.sensor_list);
        snack = Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE);
        settings = findViewById(R.id.settings);

        //  content_main
        mLight = findViewById(R.id.light_panel);
        mWindow = findViewById(R.id.window_panel);
        mHeater = findViewById(R.id.heater_panel);
        mHeater2 = findViewById(R.id.heater2_panel);
        mHeater3 = findViewById(R.id.heater3_panel);
        mWater = findViewById(R.id.water_panel);
        mDrop = findViewById(R.id.drop_panel);

        tLight = findViewById(R.id.lightName);
        tWindow = findViewById(R.id.windowName);
        tHeater = findViewById(R.id.heaterName);
        tHeater2 = findViewById(R.id.heater2Name);
        tHeater3 = findViewById(R.id.heater3Name);
        tWater = findViewById(R.id.waterName);
        tDrop = findViewById(R.id.dropName);
        toCamera = findViewById(R.id.camera);

        iLight = PrefConfig.loadLight(getApplicationContext());
        iWindow = PrefConfig.loadWindow(getApplicationContext());
        iHeater = PrefConfig.loadHeater(getApplicationContext());
        iHeater2 = PrefConfig.loadHeater2(getApplicationContext());
        iHeater3 = PrefConfig.loadHeater3(getApplicationContext());
        iWater = PrefConfig.loadWaterPump(getApplicationContext());
        iDrop = PrefConfig.loadDropPump(getApplicationContext());

        if (iLight == 1) {
            fLightOn();
        } else if (iLight == 0) {
            fLightOff();
        }
        if (iWindow == 1) {
            fWindowOpen();
        } else if (iWindow == 0) {
            fWindowClose();
        }
        if (iHeater == 1) {
            fHeaterOn();
        } else if (iHeater == 0) {
            fHeaterOff();
        }
        if (iHeater2 == 1) {
            fHeater2On();
        } else if (iHeater2 == 0) {
            fHeater2Off();
        }
        if (iHeater3 == 1) {
            fHeater3On();
        } else if (iHeater3 == 0) {
            fHeater3Off();
        }
        if (iWater == 1) {
            fWaterOpen();
        } else if (iWater == 0) {
            fWaterClose();
        }
        if (iDrop == 1) {
            fDropOpen();
        } else if (iDrop == 0) {
            fDropClose();
        }

        sensorList = new ArrayList<>();

        sensorsAdapter = new SensorsAdapter(getApplicationContext(), sensorList);

        setupRecyclerViews();
        getAllData();
        randomChart();

        mNetworkReceiver = new NetworkChangeReceiver();
        mNetworkReceiver.setOnNetworkListener(this);

        toCamera.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CameraActivity.class)));
        settings.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SettingsActivity.class)));

        // Only one onClick Listener for all Material buttons
        @SuppressLint("NonConstantResourceId") View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.light_panel:
                    if (iLight == 0) {
                        lightOnCom(getApplicationContext());
                        fLightOn();
                        iLight = 1;
                    } else if (iLight == 1){
                        lightOffCom(getApplicationContext());
                        fLightOff();
                        iLight = 0;
                    }
                    break;
                case R.id.window_panel:
                    if (iWindow == 0) {
                        windowOpenCom(getApplicationContext());
                        fWindowOpen();
                        iWindow = 1;
                    } else if (iWindow == 1) {
                        windowCloseCom(getApplicationContext());
                        fWindowClose();
                        iWindow = 0;
                    }
                    break;
                case R.id.heater_panel:
                    if (iHeater == 0) {
                        fHeaterOn();
                        fHeater2Off();
                        fHeater3Off();
                        iHeater = 1;
                        if (iHeater2 == 0 && iHeater3 == 0) {
                            heaterAutoCom(getApplicationContext());
                        }
                        heaterOnCom(getApplicationContext());

                    } else if (iHeater == 1) {
                        fHeaterOff();
                        iHeater = 0;
                        if (iHeater2 == 0 && iHeater3 == 0) {
                            heaterManualCom(getApplicationContext());
                        }
                        heaterOffCom(getApplicationContext());

                    }
                    break;
                case R.id.heater2_panel:
                    if (iHeater2 == 0) {
                        fHeater2On();
                        fHeaterOff();
                        fHeater3Off();
                        iHeater2 = 1;
                        if (iHeater == 0 && iHeater3 == 0) {
                            heaterAutoCom(getApplicationContext());
                        }
                        heater2OnCom(getApplicationContext());

                    } else if (iHeater2 == 1) {
                        fHeater2Off();
                        iHeater2 = 0;
                        if (iHeater == 0 && iHeater3 == 0) {
                            heaterAutoCom(getApplicationContext());
                        }
                        heater2OffCom(getApplicationContext());

                    }
                    break;
                case R.id.heater3_panel:
                    if (iHeater3 == 0) {
                        fHeater3On();
                        fHeaterOff();
                        fHeater2Off();
                        iHeater3 = 1;
                        if (iHeater == 0 && iHeater2 == 0) {
                            heaterAutoCom(getApplicationContext());
                        }
                        heater3OnCom(getApplicationContext());

                    } else if (iHeater3 == 1) {
                        fHeater3Off();
                        iHeater3 = 0;
                        if (iHeater == 0 && iHeater2 == 0) {
                            heaterAutoCom(getApplicationContext());
                        }
                        heater3OffCom(getApplicationContext());

                    }
                    break;
                case R.id.water_panel:
                    if (iWater == 0) {
                        waterOpenCom(getApplicationContext());
                        fWaterOpen();
                        iWater = 1;
                    } else if (iWater == 1) {
                        waterCloseCom(getApplicationContext());
                        fWaterClose();
                        iWater = 0;
                    }
                    break;
                case R.id.drop_panel:
                    if (iDrop == 0) {
                        dropOpenCom(getApplicationContext());
                        fDropOpen();
                        iDrop = 1;
                    } else if (iDrop == 1) {
                        dropCloseCom(getApplicationContext());
                        fDropClose();
                        iDrop = 0;
                    }
                    break;
            }
        };
        mLight.setOnClickListener(onClickListener);
        mWindow.setOnClickListener(onClickListener);
        mHeater.setOnClickListener(onClickListener);
        mHeater2.setOnClickListener(onClickListener);
        mHeater3.setOnClickListener(onClickListener);
        mWater.setOnClickListener(onClickListener);
        mDrop.setOnClickListener(onClickListener);
    }

    private void setupRecyclerViews() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sensor_recycler.setHasFixedSize(true);
        sensor_recycler.setLayoutManager(linearLayoutManager);
        sensor_recycler.setAdapter(sensorsAdapter);
    }

    public void getAllData() {
        if (!dataChecked) {
            if (isNetworkConnected(this)) {
                getData();
                dataChecked = true;
            }
        }
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.data_loading));
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);

                    Device device = new Device();
                    Sensors sensors = new Sensors();

                    device.setCommand(jsonObject.getString("command"));

                    sensors.setId(jsonObject.getInt("id"));
                    sensors.setCommand(jsonObject.getString("command"));
                    sensors.setState(jsonObject.getInt("state"));

                    sensorList.add(sensors);
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
            sensorsAdapter.notifyDataSetChanged();
            progressDialog.dismiss();

        }, error -> {
            Log.e("Volley", error.toString());
            progressDialog.dismiss();
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onNetworkConnected() {
        hideSnackBar();
        getAllData();
    }

    @Override
    public void onNetworkDisconnected() {
        showSnackBar();
    }

    public void showSnackBar() {
        snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        snack.show();
    }

    public void hideSnackBar() {
        snack.dismiss();
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    public void randomChart() {

        BarData data = new BarData("Temp.", 24f, String.valueOf(24));
        dataList.add(data);

        data = new BarData("Temp.2", 28f, String.valueOf(28));
        dataList.add(data);

        data = new BarData("Çyg.", 21, String.valueOf(21));
        dataList.add(data);

        data = new BarData("Çyg.2", 26, String.valueOf(26));
        dataList.add(data);

        data = new BarData("Topr.", 25, String.valueOf(25));
        dataList.add(data);

        data = new BarData("Topr.2", 27, String.valueOf(27));
        dataList.add(data);

        data = new BarData("Topr.3", 22, String.valueOf(22));
        dataList.add(data);

        mChart.setDataList(dataList);
        mChart.build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerNetworkBroadcastForNougat();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mNetworkReceiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fLightOn(){
        mLight.setBackgroundTintList(getColorStateList(R.color.blue_200));
        mLight.setIconTintResource(R.color.white);
        mLight.setRippleColorResource(R.color.blue_200);
        tLight.setTextColor(getResources().getColor(R.color.white));
        PrefConfig.saveLight(getApplicationContext(), 1);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fLightOff(){
        mLight.setBackgroundTintList(getColorStateList(R.color.gray_200));
        mLight.setIconTintResource(R.color.gray_800);
        mLight.setRippleColorResource(R.color.gray_400);
        tLight.setTextColor(getResources().getColor(R.color.gray_800));
        PrefConfig.saveLight(getApplicationContext(), 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fWindowOpen(){
        mWindow.setBackgroundTintList(getColorStateList(R.color.blue_200));
        mWindow.setIconTintResource(R.color.white);
        mWindow.setRippleColorResource(R.color.blue_200);
        tWindow.setTextColor(getResources().getColor(R.color.white));
        PrefConfig.saveWindow(getApplicationContext(), 1);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fWindowClose(){
        mWindow.setBackgroundTintList(getColorStateList(R.color.gray_200));
        mWindow.setIconTintResource(R.color.gray_800);
        mWindow.setRippleColorResource(R.color.gray_400);
        tWindow.setTextColor(getResources().getColor(R.color.gray_800));
        PrefConfig.saveWindow(getApplicationContext(), 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fHeaterOn(){
        mHeater.setBackgroundTintList(getColorStateList(R.color.blue_200));
        mHeater.setIconTintResource(R.color.white);
        mHeater.setRippleColorResource(R.color.blue_200);
        tHeater.setTextColor(getResources().getColor(R.color.white));
        PrefConfig.saveHeater(getApplicationContext(), 1);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fHeaterOff(){
        mHeater.setBackgroundTintList(getColorStateList(R.color.gray_200));
        mHeater.setIconTintResource(R.color.gray_800);
        mHeater.setRippleColorResource(R.color.gray_400);
        tHeater.setTextColor(getResources().getColor(R.color.gray_800));
        PrefConfig.saveHeater(getApplicationContext(), 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fHeater2On(){
        mHeater2.setBackgroundTintList(getColorStateList(R.color.blue_200));
        mHeater2.setIconTintResource(R.color.white);
        mHeater2.setRippleColorResource(R.color.blue_200);
        tHeater2.setTextColor(getResources().getColor(R.color.white));
        PrefConfig.saveHeater2(getApplicationContext(), 1);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fHeater2Off(){
        mHeater2.setBackgroundTintList(getColorStateList(R.color.gray_200));
        mHeater2.setIconTintResource(R.color.gray_800);
        mHeater2.setRippleColorResource(R.color.gray_400);
        tHeater2.setTextColor(getResources().getColor(R.color.gray_800));
        PrefConfig.saveHeater2(getApplicationContext(), 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fHeater3On(){
        mHeater3.setBackgroundTintList(getColorStateList(R.color.blue_200));
        mHeater3.setIconTintResource(R.color.white);
        mHeater3.setRippleColorResource(R.color.blue_200);
        tHeater3.setTextColor(getResources().getColor(R.color.white));
        PrefConfig.saveHeater3(getApplicationContext(), 1);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fHeater3Off(){
        mHeater3.setBackgroundTintList(getColorStateList(R.color.gray_200));
        mHeater3.setIconTintResource(R.color.gray_800);
        mHeater3.setRippleColorResource(R.color.gray_400);
        tHeater3.setTextColor(getResources().getColor(R.color.gray_800));
        PrefConfig.saveHeater3(getApplicationContext(), 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fWaterOpen(){
        mWater.setBackgroundTintList(getColorStateList(R.color.blue_200));
        mWater.setIconTintResource(R.color.white);
        mWater.setRippleColorResource(R.color.blue_200);
        tWater.setTextColor(getResources().getColor(R.color.white));
        PrefConfig.saveWaterPump(getApplicationContext(), 1);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fWaterClose(){
        mWater.setBackgroundTintList(getColorStateList(R.color.gray_200));
        mWater.setIconTintResource(R.color.gray_800);
        mWater.setRippleColorResource(R.color.gray_400);
        tWater.setTextColor(getResources().getColor(R.color.gray_800));
        PrefConfig.saveWaterPump(getApplicationContext(), 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fDropOpen(){
        mDrop.setBackgroundTintList(getColorStateList(R.color.blue_200));
        mDrop.setIconTintResource(R.color.white);
        mDrop.setRippleColorResource(R.color.blue_200);
        tDrop.setTextColor(getResources().getColor(R.color.white));
        PrefConfig.saveDropPump(getApplicationContext(), 1);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fDropClose(){
        mDrop.setBackgroundTintList(getColorStateList(R.color.gray_200));
        mDrop.setIconTintResource(R.color.gray_800);
        mDrop.setRippleColorResource(R.color.gray_400);
        tDrop.setTextColor(getResources().getColor(R.color.gray_800));
        PrefConfig.saveDropPump(getApplicationContext(), 0);
    }
}