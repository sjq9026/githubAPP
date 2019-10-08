package com.sjq.githubapp.presenters;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.javabean.LanguageEntity;
import com.sjq.githubapp.util.UtilLog;
import com.sjq.githubapp.util.UtilsFile;
import com.sjq.githubapp.views.LoginView;
import com.sjq.githubapp.views.MainView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class MainPresenter extends BasePresenter<MainView> {
    public void initLangrage() {
        ArrayList<LanguageEntity> mLanguages = new ArrayList<>();
        mLanguages = (ArrayList<LanguageEntity>) MyApplication.getmDaoSession().getLanguageEntityDao().loadAll();
        if (mLanguages == null || mLanguages.size() == 0) {
            InputStreamReader inputStreamReader;
            mLanguages = new ArrayList<>();
            try {
                inputStreamReader = new InputStreamReader(view.getContext().getAssets().open("LanguageJsonStr.json"), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStreamReader.close();
                bufferedReader.close();
                String resultString = stringBuilder.toString();
                JsonParser parser = new JsonParser();
                JsonArray jsonArray = parser.parse(resultString).getAsJsonArray();
                Gson gson = new Gson();
                for (JsonElement obj : jsonArray) {
                    LanguageEntity language = gson.fromJson(obj, LanguageEntity.class);
                    mLanguages.add(language);
                    MyApplication.getmDaoSession().getLanguageEntityDao().insertOrReplace(language);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        view.refreshLanguage(mLanguages);
    }


}
