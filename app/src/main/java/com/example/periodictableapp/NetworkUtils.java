package com.example.periodictableapp;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String ELEMENTS_URL_MYSQL = "https://localhost:44310/api/element";
    private static final String ELEMENTS_URL_WEB = "https://periodic-table-elements-info.herokuapp.com/elements";

    static String searchElementsMysql(String queryString) {
        String url;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String elementJSONString = null;

        url = ELEMENTS_URL_MYSQL;
        if(queryString == null)
            url = ELEMENTS_URL_MYSQL + queryString;


        try {
            Uri builtURI = Uri.parse(url).buildUpon()
                    .build();

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                builder.append(linha);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            elementJSONString = builder.toString();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, elementJSONString);
        return elementJSONString;
    }

    static String searchElementsWeb(String queryString) {
        String url;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String elementJSONString = null;

        url = ELEMENTS_URL_WEB;
        if(queryString == null)
            url = ELEMENTS_URL_WEB + queryString;


        try {
            Uri builtURI = Uri.parse(url).buildUpon()
                    .build();

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                builder.append(linha);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            elementJSONString = builder.toString();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, elementJSONString);
        return elementJSONString;
    }

    static String deleteElement(String queryString, Element element) {
        String url;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String elementJSONString = null;

        url = ELEMENTS_URL_MYSQL;
        if(queryString == null)
            url = ELEMENTS_URL_MYSQL + queryString;


        try {
            Uri builtURI = Uri.parse(url).buildUpon()
                    .build();

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("DELETE");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                builder.append(linha);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            elementJSONString = builder.toString();
            Gson gson = new Gson();
            String jsonElement = gson.toJson(element);
            // Adiciona Json no body
            Log.d(LOG_TAG, jsonElement);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return elementJSONString;
    }

    static String updateElement(String queryString, Element element) {
        String url;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String elementJSONString = null;

        url = ELEMENTS_URL_MYSQL;
        if(queryString == null)
            url = ELEMENTS_URL_MYSQL + queryString;


        try {
            Uri builtURI = Uri.parse(url).buildUpon()
                    .build();

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                builder.append(linha);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            elementJSONString = builder.toString();
            Gson gson = new Gson();
            String jsonElement = gson.toJson(element);
            // Adiciona Json no body
            Log.d(LOG_TAG, jsonElement);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return elementJSONString;
    }

    static void addElement(Element element) {
        String url;
        HttpURLConnection urlConnection = null;

        url = ELEMENTS_URL_MYSQL;
        try {
            Uri builtURI = Uri.parse(url).buildUpon()
                    .build();

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();


            Gson gson = new Gson();
            String jsonElement = gson.toJson(element);
            // Adiciona Json no body
            Log.d(LOG_TAG, jsonElement);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        }
    }

}