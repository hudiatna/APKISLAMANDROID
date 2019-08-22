package com.developer.andi.tugasuas;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientAsyncTask3 extends AsyncTask<Void, Void, String> {
    private final int CONNECTON_TIMEOUT_MILLISECONDS = 60000;
    private OnPostExecuteListener mPostExecuteListener = null;
    private Context mContext;
    private ProgressBar mProgress;
    // sesuaikan url server api nya.
    private String base_api_url = "http://api.banghasan.com/quran/format/json/surat";

    public static interface OnPostExecuteListener {
        void onPostExecute(String result);
    }
    public void setmProgress(ProgressBar mProgress) {
        this.mProgress = mProgress;
    }

    public ClientAsyncTask3(Context mContext, OnPostExecuteListener mPostExecuteListener) {
        this.mPostExecuteListener = mPostExecuteListener;
        this.mContext = mContext;
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (mPostExecuteListener != null) {
            mPostExecuteListener.onPostExecute(result);
            if (mProgress != null) {
                mProgress.setVisibility(View.GONE);
            }
        }
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgress != null) {
            mProgress.setVisibility(View.VISIBLE);
        }
    }
    @Override
    protected String doInBackground(Void... voids) {
        String api_url = base_api_url;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(api_url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(CONNECTON_TIMEOUT_MILLISECONDS);
            urlConnection.setReadTimeout(CONNECTON_TIMEOUT_MILLISECONDS);
            String inString =
                    streamToString(urlConnection.getInputStream());
            return inString;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }
    private String streamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new
                InputStreamReader(inputStream));
        String line, result = "";
        try {
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    result += line;
                }
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
