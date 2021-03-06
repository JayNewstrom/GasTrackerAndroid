package com.jaynewstrom.gastracker.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.jaynewstrom.gastracker.dao.FuelGrade;
import com.jaynewstrom.gastracker.dao.Price;

import com.jaynewstrom.gastracker.dao.FuelGradeDao;
import com.jaynewstrom.gastracker.dao.PriceDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig fuelGradeDaoConfig;
    private final DaoConfig priceDaoConfig;

    private final FuelGradeDao fuelGradeDao;
    private final PriceDao priceDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        fuelGradeDaoConfig = daoConfigMap.get(FuelGradeDao.class).clone();
        fuelGradeDaoConfig.initIdentityScope(type);

        priceDaoConfig = daoConfigMap.get(PriceDao.class).clone();
        priceDaoConfig.initIdentityScope(type);

        fuelGradeDao = new FuelGradeDao(fuelGradeDaoConfig, this);
        priceDao = new PriceDao(priceDaoConfig, this);

        registerDao(FuelGrade.class, fuelGradeDao);
        registerDao(Price.class, priceDao);
    }
    
    public void clear() {
        fuelGradeDaoConfig.getIdentityScope().clear();
        priceDaoConfig.getIdentityScope().clear();
    }

    public FuelGradeDao getFuelGradeDao() {
        return fuelGradeDao;
    }

    public PriceDao getPriceDao() {
        return priceDao;
    }

}
