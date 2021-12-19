package ne.iot.smartgreenhouse.utils;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import ne.iot.smartgreenhouse.model.Device;
import ne.iot.smartgreenhouse.model.Sensors;

public class Commands {
    static String ip_address, prefix;

    //  LIGHT ON/OFF
    public static void lightOnCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "parnik_room_light");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("LIGHT => ", "ON");
                    conn.disconnect();
                } else {
                    Log.e("LIGHT => ", "ON ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public static void lightOffCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "parnik_room_light");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("LIGHT => ", "OFF");
                    conn.disconnect();
                } else {
                    Log.e("LIGHT => ", "OFF ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //  WINDOW OPEN/CLOSE
    public static void windowOpenCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "window");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("WINDOW => ", "OPEN");
                    conn.disconnect();
                } else {
                    Log.e("WINDOW => ", "OPEN ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public static void windowCloseCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "window");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("WINDOW => ", "CLOSE");
                    conn.disconnect();
                } else {
                    Log.e("WINDOW => ", "CLOSE ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //  HEATER ON/OFF
    public static void heaterOnCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "mode_low");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("HEATER => ", "ON");
                    conn.disconnect();
                } else {
                    Log.e("HEATER => ", "ON ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public static void heaterOffCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "mode_low");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("HEATER => ", "OFF");
                    conn.disconnect();
                } else {
                    Log.e("HEATER => ", "OFF ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //  HEATER 2 ON/OFF
    public static void heater2OnCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "mode_med");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("HEATER 2 => ", "ON");
                    conn.disconnect();
                } else {
                    Log.e("HEATER 2 => ", "ON ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public static void heater2OffCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "mode_med");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("HEATER 2 => ", "OFF");
                    conn.disconnect();
                } else {
                    Log.e("HEATER 2 => ", "OFF ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //  HEATER 3 ON/OFF
    public static void heater3OnCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "mode_high");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("HEATER 3 => ", "ON");
                    conn.disconnect();
                } else {
                    Log.e("HEATER 3 => ", "ON ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public static void heater3OffCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "mode_high");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("HEATER 3 => ", "OFF");
                    conn.disconnect();
                } else {
                    Log.e("HEATER 3 => ", "OFF ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //  HEATER AUTO ON/OFF
    public static void heaterAutoCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "auto_manual_switch");
                jsonParam.put("action", "auto");
                pin_array.put(jsonParam);
                json.put("command", "command");
                json.put("pins", pin_array);
                Log.i("JSON", json.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(json.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("HEATER AUTO => ", "ON");
                    conn.disconnect();
                } else {
                    Log.e("HEATER AUTO => ", "ON ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public static void heaterManualCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "auto_manual_switch");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("HEATER AUTO => ", "OFF");
                    conn.disconnect();
                } else {
                    Log.e("HEATER AUTO => ", "OFF ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //  WATER PUMP OPEN/CLOSE
    public static void waterOpenCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "parnik_water_pump");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("WATER PUMP => ", "OPEN");
                    conn.disconnect();
                } else {
                    Log.e("WATER PUMP => ", "OPEN ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public static void waterCloseCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "parnik_water_pump");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("WATER PUMP => ", "CLOSE");
                    conn.disconnect();
                } else {
                    Log.e("WATER PUMP => ", "CLOSE ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //  DROP PUMP OPEN/CLOSE
    public static void dropOpenCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "drop");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("DROP PUMP => ", "OPEN");
                    conn.disconnect();
                } else {
                    Log.e("DROP PUMP => ", "OPEN ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public static void dropCloseCom(Context context) {
        ip_address = PrefConfig.loadIpPf(context);
        prefix = PrefConfig.loadPrefix(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("http://" + ip_address + prefix);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "drop");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.e("DROP PUMP => ", "CLOSE");
                    conn.disconnect();
                } else {
                    Log.e("DROP PUMP => ", "CLOSE ERROR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

}