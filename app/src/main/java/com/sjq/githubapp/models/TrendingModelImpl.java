package com.sjq.githubapp.models;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.db.greendao.TrendingKeyEntityDao;
import com.sjq.githubapp.javabean.TrendingKeyEntity;

import java.util.ArrayList;

public class TrendingModelImpl implements TrendingModel {

    @Override
    public void saveAllLanguage(ArrayList<TrendingKeyEntity> list) {
     MyApplication.getmDaoSession().getTrendingKeyEntityDao().deleteAll();
        for (TrendingKeyEntity languageEntity : list) {
            MyApplication.getmDaoSession().getTrendingKeyEntityDao().insertOrReplace(languageEntity);
        }
    // MyApplication.getmDaoSession().getLanguageEntityDao().saveInTx(list);
    }

    /**
     * 从数据库中查询以选中的的语言
     *
     * @return
     */
    @Override
    public ArrayList<TrendingKeyEntity> getLanguage() {
        ArrayList<TrendingKeyEntity> mLanguages = new ArrayList<>();
        mLanguages = (ArrayList<TrendingKeyEntity>) MyApplication.getmDaoSession().getTrendingKeyEntityDao(). queryBuilder().where(TrendingKeyEntityDao.Properties.Checked.eq(true)).list();
        if (mLanguages == null) {
            mLanguages = new ArrayList<>();
        }
        return mLanguages;
    }

    /**
     * 从数据库中查询以选中的的语言
     *
     * @return
     */
    @Override
    public ArrayList<TrendingKeyEntity> getAllLanguage() {
        ArrayList<TrendingKeyEntity> mLanguages = new ArrayList<>();
        mLanguages = (ArrayList<TrendingKeyEntity>) MyApplication.getmDaoSession().getTrendingKeyEntityDao().loadAll();
        if (mLanguages == null) {
            mLanguages = new ArrayList<>();
        }
        return mLanguages;
    }



}
