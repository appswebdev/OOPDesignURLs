package college.edu.tomer.oopdesignurls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by master on 22/06/16.
 */
public class WhetherStation {
    private ArrayList<OnWeatherChangedListener> listenerList;
    private String oldJson ="";
    public WhetherStation() {
        this.listenerList = new ArrayList<>();
        startGettingTemp();
    }

    private void startGettingTemp() {
        // go to the web and fetch new whether data on a timer
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
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
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=beersheba,il&appid=fb69467683eea597ea58d93b606f2df6&units=metric");
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();

            StringBuilder newJson = new StringBuilder();

            while (line!=null){
                newJson.append(line);
                line = reader.readLine();
            }

            if (!newJson.toString().equals(oldJson)) {
                notifyListeners(newJson.toString());
                this.oldJson = newJson.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addListener(OnWeatherChangedListener listener){
        listenerList.add(listener);
    }

    public void removeListener(OnWeatherChangedListener listener){
        listenerList.remove(listener);
    }

    private void notifyListeners(String temp){
        for (OnWeatherChangedListener listener : listenerList) {
            listener.onWeatherChanged(temp);
        }
    }
}
