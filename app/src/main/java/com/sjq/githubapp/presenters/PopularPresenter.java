package com.sjq.githubapp.presenters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.R;
import com.sjq.githubapp.adapters.MyFragmentPagerAdapter;
import com.sjq.githubapp.base.BasePresenter;
import com.sjq.githubapp.fragments.LanguageContentFragment;
import com.sjq.githubapp.javabean.LanguageEntity;
import com.sjq.githubapp.models.PopularModel;
import com.sjq.githubapp.models.PopularModelImpl;
import com.sjq.githubapp.views.PopularView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class PopularPresenter extends BasePresenter<PopularView> {
    PopularModel model;

    public PopularPresenter() {
        model = new PopularModelImpl();
    }

    public void initLangrage() {
        ArrayList<LanguageEntity> mLanguages = model.getLanguage();
        //如果数据库中没有数据，那么从本地文件中解析
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
        //更新UI
        view.refreshLanguage(mLanguages);
    }


    public MyFragmentPagerAdapter getFragmentPagerAdapter(int size, FragmentManager manager) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            fragments.add(LanguageContentFragment.newInstance(i + ""));
        }
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(manager, fragments);
        return myFragmentPagerAdapter;

    }

    public CommonNavigator initCommonNavigator(final ArrayList<LanguageEntity> mTitleDataList) {
        CommonNavigator commonNavigator = new CommonNavigator(view.getContext());
        commonNavigator.setEnablePivotScroll(true);
        CommonNavigatorAdapter adapter = new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView colorTransitionPagerTitleView = new SimplePagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(R.color.colorPrimary);
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index).getName());
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View m_view) {
                        view.onTabClick(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        };
        commonNavigator.setAdapter(adapter);
        return commonNavigator;
    }


}
