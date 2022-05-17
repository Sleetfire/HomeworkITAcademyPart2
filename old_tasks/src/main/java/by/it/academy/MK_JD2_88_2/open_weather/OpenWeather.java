package by.it.academy.MK_JD2_88_2.open_weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class OpenWeather {

    private String location;
    private String apiKey;

    public OpenWeather(String location, String apiKey) {
        this.location = location;
        this.apiKey = apiKey;
    }

    private Map<String, Object> jsonToMap(String str) {
        return new Gson().fromJson(str, new
                TypeToken<HashMap<String, Object>>() {
                }.getType());
    }

    private String getFullUrl() {
        return "http://api.openweathermap.org/data/2.5/weather?q=" + this.location + "&appid=" + this.apiKey + "&units=metric";
    }

    private String getRequest() {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(getFullUrl());
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public String getParameter(String parameter1, String parameter2) {
        Map<String, Object> respMap = jsonToMap(getRequest());
        Map<String, Object> mainMap = jsonToMap(respMap.get(parameter1).toString());
        if (parameter1 != null) {
            return mainMap.get(parameter2).toString();
        }
        return mainMap.toString();
    }

}

