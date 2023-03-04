package com.example.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherService {
    public static final String CITY_NAME = "name";

    public static final String WIND = "wind";
    public static final String AMBIENT = "main";

    public static final String TEMPERATURE = "temp";
    public static final String HUMIDITY = "humidity";
    public static final String ATMOSPHERIC_PRESSURE = "pressure";
    public static final String WIND_DEGREE = "deg";
    public static final String WIND_SPEED = "speed";

    public static final int CODE_NOT_FOUND = 404;

    private Scanner in;
    private JSONObject weather;

    public WeatherService() {
        in = new Scanner(System.in);
        weather = null;
    }

    public static void main(String[]args) throws JSONException {
        WeatherService cw = new WeatherService();
        cw.run();
    }

    public void run() throws JSONException {
        String city;

        System.out.print("Type a city: ");
        city = in.nextLine();
        this.weather = this.getJsonWeather(city);
        //Get the reference to the object of type JSONObject if the city was found.

        if(this.weather!=null) {
            //If the weather of the city is correctly retrieved
            city = this.weather.getString(CITY_NAME);
            //Override the city insert with the one given by the Json response
            //(To have correct lower/upper case)

            JSONObject ambient = this.getJsonObject(AMBIENT);
            //Get the JSONObject of the part of data regarding the ambient (Temperature, Humidity, Pressure...)

            JSONObject wind = this.getJsonObject(WIND);
            //Get the JSONObject of the part of data regarding the wind (Speed, Degree)

            System.out.println("Temperature in "+ city + " is: " + this.getTemperature(ambient) + "°C");
            System.out.println("Humidity in "+ city + " is: " + this.getHumidity(ambient) + "%");
            System.out.println("Pressure in "+ city + " is: " + this.getPressure(ambient) + " hPa");

            System.out.println("Wind is blowing at "+ this.getSpeed(wind) + " m/s with degree " + this.getDegree(wind) + "°");
        }else {
            System.out.println("ERROR: Couldn't retrieve weather.");
        }
    }

    public JSONObject getJsonWeather(String city) {
        int responseCode=0;
        try {
            String inputLine;
            String url = "http://api.openweathermap.org/data/2.5/weather?q="+ city +"&appid=265af3bee62f35ed897a436cf2e0183d"+"&units=metric";
            URL urlObj = new URL(url);
            //Url to the API response

            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            //HttpConnection to the url
            responseCode = connection.getResponseCode();
            //Response from the server

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println(connection.getHeaderFields());
            String response = "";
            while((inputLine = in.readLine()) != null) {
                response = response + inputLine;
            }
            //Create a buffer of the http connection and iterate through it to get the data
            in.close();

            JSONObject myResponse = new JSONObject(response);
            //Create a JSONObject made with the Json response
            return myResponse;
        }
        catch (Exception e) {
            System.out.println("Response code: "+responseCode);
            switch (responseCode) {
                case CODE_NOT_FOUND:
                    System.out.println("The city "+city+" seems not to exist!");
                    break;
                default:
                    System.out.println("Something went wrong while retrieving data from OpenWeatherMap");
                    break;
            }
            return null;
        }
    }

    public JSONObject getJsonObject(String label) {
        try {
            JSONObject mainObject = new JSONObject(this.weather.getJSONObject(label).toString());
            return mainObject;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public double getTemperature(JSONObject obj) throws JSONException {
        return obj.getDouble(TEMPERATURE);
    }

    public int getHumidity(JSONObject obj) throws JSONException {
        return obj.getInt(HUMIDITY);
    }

    public double getPressure(JSONObject obj) throws JSONException {
        return obj.getDouble(ATMOSPHERIC_PRESSURE);
    }

    public double getDegree(JSONObject obj) throws JSONException {
        return obj.getDouble(WIND_DEGREE);
    }

    public double getSpeed(JSONObject obj) throws JSONException {
        return obj.getDouble(WIND_SPEED);
    }
}
