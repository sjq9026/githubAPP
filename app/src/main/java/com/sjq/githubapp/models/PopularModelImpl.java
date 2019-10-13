package com.sjq.githubapp.models;

import com.sjq.githubapp.MyApplication;
import com.sjq.githubapp.base.BaseModel;
import com.sjq.githubapp.db.greendao.LanguageEntityDao;
import com.sjq.githubapp.javabean.LanguageEntity;

import java.util.ArrayList;

public class PopularModelImpl implements PopularModel {

    /**
     * 从数据库中查询所有的语言
     *
     * @return
     */
    @Override
    public ArrayList<LanguageEntity> getLanguage() {
        ArrayList<LanguageEntity> mLanguages = new ArrayList<>();
        mLanguages = (ArrayList<LanguageEntity>) MyApplication.getmDaoSession().getLanguageEntityDao(). queryBuilder().where(LanguageEntityDao.Properties.Checked.eq(true)).list();
        if (mLanguages == null) {
            mLanguages = new ArrayList<>();
        }
        return mLanguages;
    }
}
