package college.edu.tomer.oopdesignurls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by master on 22/06/16.
 */
public class WhetherStation {
    private static WhetherStation sharedInstance;


    private ArrayList<OnWeatherChangedListener> listenerList;
    private String oldJson = "";
    private Weather weather = null;

    //private constructor
    private WhetherStation() {
        this.listenerList = new ArrayList<>();
        startGettingTemp();
    }

    public static synchronized WhetherStation getSharedInstance(){
        if (WhetherStation.sharedInstance==null)
            WhetherStation.sharedInstance = new WhetherStation();

        return sharedInstance;
    }
    private void startGettingTemp() {
        // go to the web and fetch new whether data on a timer
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //if(whether cheanged)
                        getWheatherData();
                        Thread.sleep(10 * 1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private void getWheatherData() {
        try {
            String newJson = HttpHandler.getUrl("http://api.openweathermap.org/data/2.5/weather?q=beersheba,il&appid=fb69467683eea597ea58d93b606f2df6&units=metric");
            try {
                JSONObject json = new JSONObject(newJson);
                JSONArray weather = json.getJSONArray("weather");
                String description = weather.getJSONObject(0).getString("description");

                String temp = json.getJSONObject("main").getString("temp");
                long sunrise = json.getJSONObject("sys").getLong("sunrise");
                long sunset = json.getJSONObject("sys").getLong("sunset");
                String city = json.getString("name");

                Weather wheather = new Weather(city, temp, description, new Date(sunset), new Date(sunrise));
                this.weather = wheather;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (!newJson.equals(oldJson)) {
                notifyListeners(weather);
                this.oldJson = newJson;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addListener(OnWeatherChangedListener listener) {
        listenerList.add(listener);
    }

    public void removeListener(OnWeatherChangedListener listener) {
        listenerList.remove(listener);
    }

    private void notifyListeners(Weather weather) {
        for (OnWeatherChangedListener listener : listenerList) {
            listener.onWeatherChanged(weather);
        }
    }
}
