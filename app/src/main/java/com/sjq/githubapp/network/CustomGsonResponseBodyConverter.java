package com.sjq.githubapp.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.sjq.githubapp.javabean.WanAndroidEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String response = value.string();
            Log.i("CustomGsonResponse", "convert: " + response);
            JSONObject mJSONObject = null;
            try {
                mJSONObject = new JSONObject(response);
            } catch (JSONException mE) {
                mE.printStackTrace();
            }
            int code = mJSONObject.optInt("errorCode", -1);
            if (code == 0) {
                MediaType contentType = value.contentType();
                Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
                InputStream inputStream = new ByteArrayInputStream(response.getBytes());
                Reader reader = new InputStreamReader(inputStream, charset);
                JsonReader jsonReader = gson.newJsonReader(reader);
                return adapter.read(jsonReader);
            } else {
                String message = mJSONObject.optString("message");
                value.close();
                    throw new IOException("SSS");
            }

        } finally {
            value.close();
        }
    }



}
