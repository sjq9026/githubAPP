package com.sjq.githubapp.models;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.db.greendao.PopularKeyEntityDao;
import com.sjq.githubapp.javabean.PopularKeyEntity;

import java.util.ArrayList;

public class PopularModelImpl implements PopularModel {

    @Override
    public void saveAllLanguage(ArrayList<PopularKeyEntity> list) {
     MyApplication.getmDaoSession().getPopularKeyEntityDao().deleteAll();
        for (PopularKeyEntity popularKeyEntity : list) {
            MyApplication.getmDaoSession().getPopularKeyEntityDao().insertOrReplace(popularKeyEntity);
        }
    // MyApplication.getmDaoSession().getLanguageEntityDao().saveInTx(list);
    }

    @Override
    public void saveSortFinishLanguage(ArrayList<PopularKeyEntity> list) {
        for (PopularKeyEntity popularKeyEntity : getLanguage()) {
            MyApplication.getmDaoSession().getPopularKeyEntityDao().delete(popularKeyEntity);
        }


        for (PopularKeyEntity popularKeyEntity : list) {
            popularKeyEntity.setAutoId((Long.getLong(list.indexOf(popularKeyEntity)+"")));
            MyApplication.getmDaoSession().getPopularKeyEntityDao().insert(popularKeyEntity);

        }
    }

    /**
     * 从数据库中查询以选中的的语言
     * @return
     */
    @Override
    public ArrayList<PopularKeyEntity> getLanguage() {
        ArrayList<PopularKeyEntity> mLanguages = new ArrayList<>();
        mLanguages = (ArrayList<PopularKeyEntity>) MyApplication.getmDaoSession().getPopularKeyEntityDao(). queryBuilder().where(PopularKeyEntityDao.Properties.Checked.eq(true)).list();
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
    public ArrayList<PopularKeyEntity> getAllLanguage() {
        ArrayList<PopularKeyEntity> mLanguages = new ArrayList<>();
        mLanguages = (ArrayList<PopularKeyEntity>) MyApplication.getmDaoSession().getPopularKeyEntityDao().loadAll();
        if (mLanguages == null) {
            mLanguages = new ArrayList<>();
        }
        return mLanguages;
    }



}
