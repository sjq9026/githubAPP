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
import com.sjq.githubapp.fragments.TrendingContentFragment;
import com.sjq.githubapp.javabean.TrendingKeyEntity;
import com.sjq.githubapp.javabean.UserContactTrendingKeyEntity;
import com.sjq.githubapp.models.TrendingModel;
import com.sjq.githubapp.models.TrendingModelImpl;
import com.sjq.githubapp.util.UtilsSharePre;
import com.sjq.githubapp.views.TrendingView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class TrendingPresenter implements BasePresenter {


    TrendingModel model;
    private TrendingView mView;


    public TrendingPresenter(TrendingView view) {
        mView = view;
        model = new TrendingModelImpl();
    }

    public void initLanguage() {
        String userName = UtilsSharePre.getInstance().getPreferenceString(mView.getContext(), UtilsSharePre.USER_NAME, "");
        ArrayList<TrendingKeyEntity> mLanguages = model.getLanguage(userName);

        //如果数据库中没有数据，那么从本地文件中解析
        if (mLanguages == null || mLanguages.size() == 0) {
            InputStreamReader inputStreamReader;
            mLanguages = new ArrayList<>();
            try {
                inputStreamReader = new InputStreamReader(mView.getContext().getAssets().open("keys.json"), "UTF-8");
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
                    TrendingKeyEntity language = gson.fromJson(obj, TrendingKeyEntity.class);
                    mLanguages.add(language);
                    UserContactTrendingKeyEntity contactPopularKeyEntity = new UserContactTrendingKeyEntity();
                    contactPopularKeyEntity.setTrending_key_id(language.getId());
                    contactPopularKeyEntity.setUser_name(userName);
                    MyApplication.getmDaoSession().getTrendingKeyEntityDao().insertOrReplace(language);
                    if (language.getChecked()) {
                        MyApplication.getmDaoSession().getUserContactTrendingKeyEntityDao().insertOrReplace(contactPopularKeyEntity);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //更新UI
        mView.refreshLanguage(mLanguages);
    }


    public MyFragmentPagerAdapter getFragmentPagerAdapter(ArrayList<TrendingKeyEntity> languageEntities, FragmentManager manager) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < languageEntities.size(); i++) {
            fragments.add(TrendingContentFragment.newInstance(languageEntities.get(i).getName(), TrendingContentFragment.NET_WORK));
        }
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(manager, fragments);
        return myFragmentPagerAdapter;

    }

    public CommonNavigator initCommonNavigator(final ArrayList<TrendingKeyEntity> mTitleDataList) {
        CommonNavigator commonNavigator = new CommonNavigator(mView.getContext());
        commonNavigator.setEnablePivotScroll(true);

        CommonNavigatorAdapter adapter = new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView colorTransitionPagerTitleView = new SimplePagerTitleView(context);

                colorTransitionPagerTitleView.setNormalColor(R.color.white);
                colorTransitionPagerTitleView.setTextColor(mView.getContext().getResources().getColor(R.color.white));
                colorTransitionPagerTitleView.setSelectedColor(R.color.white);
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index).getName());
//                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
//                clipPagerTitleView.setText(mTitleDataList.get(index).getName());
//                clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
//                clipPagerTitleView.setClipColor(Color.WHITE);


                colorTransitionPagerTitleView.setText(mTitleDataList.get(index).getName());
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View m_view) {
                        mView.onTabClick(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.WHITE);


                return indicator;
            }
        };
        commonNavigator.setAdapter(adapter);
        return commonNavigator;
    }


    @Override
    public void onDestroy() {

    }
}
