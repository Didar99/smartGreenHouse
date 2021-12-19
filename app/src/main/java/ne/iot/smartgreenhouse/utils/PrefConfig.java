package ne.iot.smartgreenhouse.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {
    private static final String MY_PREFERENCE_NAME = "com.iot.smart_home";
    private static final String IP_KEY = "ip_key";
    private static final String WATER_PUMP_KEY = "water_pump_key";
    private static final String DROP_PUMP_KEY = "drop_pump_key";
    private static final String WINDOW_KEY = "window_key";
    private static final String HEATER_KEY = "heater_key";
    private static final String HEATER2_KEY = "heater2_key";
    private static final String HEATER3_KEY = "heater3_key";
    private static final String LIGHT_KEY = "light_key";
    private static final String TEMP_KEY = "temp_key";
    private static final String HUMIDITY_KEY = "humidity_key";
    private static final String HUMIDITY_SAND_KEY = "humidity_sand_key";
    private static final String GAS_KEY = "gas_key";
    private static final String PREFIX_KEY = "prefix_key";
    private static final String LOOP_KEY = "loop_key";


    //  IP ADDRESS
    public static void saveIpPf(Context context, String ip) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(IP_KEY, ip);
        editor.apply();
    }
    public static String loadIpPf(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(IP_KEY, "192.168.1.110");
    }

    //  WATER PUMP ON/OFF
    public static void saveWaterPump(Context context, int waterPump) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(WATER_PUMP_KEY, waterPump);
        editor.apply();
    }
    public static int loadWaterPump(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(WATER_PUMP_KEY, 0);
    }

    //  DROP PUMP ON/OFF
    public static void saveDropPump(Context context, int dropPump) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(DROP_PUMP_KEY, dropPump);
        editor.apply();
    }
    public static int loadDropPump(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(DROP_PUMP_KEY, 0);
    }

    //  WINDOW OPEN/CLOSE
    public static void saveWindow(Context context, int window) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(WINDOW_KEY, window);
        editor.apply();
    }
    public static int loadWindow(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(WINDOW_KEY, 0);
    }

    //  HEATER ON/OFF
    public static void saveHeater(Context context, int heater) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(HEATER_KEY, heater);
        editor.apply();
    }
    public static int loadHeater(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(HEATER_KEY, 0);
    }

    //  HEATER 2 ON/OFF
    public static void saveHeater2(Context context, int heater2) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(HEATER2_KEY, heater2);
        editor.apply();
    }
    public static int loadHeater2(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(HEATER2_KEY, 0);
    }

    //  HEATER 3 ON/OFF
    public static void saveHeater3(Context context, int heater3) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(HEATER3_KEY, heater3);
        editor.apply();
    }
    public static int loadHeater3(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(HEATER3_KEY, 0);
    }

    //  LIGHT ON/OFF
    public static void saveLight(Context context, int light) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(LIGHT_KEY, light);
        editor.apply();
    }
    public static int loadLight(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(LIGHT_KEY, 0);
    }

    //  TEMPERATURE (DEGREE)
    public static void saveTemp(Context context, int temp) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(TEMP_KEY, temp);
        editor.apply();
    }
    public static int loadTemp(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(TEMP_KEY, 0);
    }

    //  HUMIDITY (PERCENT)
    public static void saveHumidity(Context context, int humidity) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(HUMIDITY_KEY, humidity);
        editor.apply();
    }
    public static int loadHumidity(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(HUMIDITY_KEY, 0);
    }

    //  HUMIDITY SAND (PERCENT)
    public static void saveSandHumidity(Context context, int sandHumidity) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(HUMIDITY_SAND_KEY, sandHumidity);
        editor.apply();
    }
    public static int loadSandHumidity(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(HUMIDITY_SAND_KEY, 0);
    }

    //  GAS (PERCENT)
    public static void saveGas(Context context, int gas) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(GAS_KEY, gas);
        editor.apply();
    }
    public static int loadGas(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(GAS_KEY, 0);
    }

    //  PREFIX
    public static void savePrefix(Context context, String prefix) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREFIX_KEY, prefix);
        editor.apply();
    }
    public static String loadPrefix(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREFIX_KEY, "");
    }

    //  LOOP TIME FOR GETTING DATA
    public static void saveLoop(Context context, int loop) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(LOOP_KEY, loop);
        editor.apply();
    }
    public static int loadLoop(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(LOOP_KEY, 5000);
    }
}