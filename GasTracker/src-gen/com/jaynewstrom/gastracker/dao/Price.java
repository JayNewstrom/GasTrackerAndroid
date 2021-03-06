package com.jaynewstrom.gastracker.dao;

import java.util.List;

import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS
// KEEP INCLUDES - put your custom includes here
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
// KEEP INCLUDES END
/**
 * Entity mapped to table PRICE.
 */
public class Price {

    private Long id;
    /** Not-null value. */
    private String value;
    /** Not-null value. */
    private java.util.Date createdAtDate;
    private double longitude;
    private double latitude;
    private boolean uploadStarted;
    private boolean uploadCompleted;
    private long fuelGradeId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient PriceDao myDao;

    private Price price;
    private Long price__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    private static final String JSON_KEY_GRADE_ID = "grade_id";
    private static final String JSON_KEY_COST = "cost";
    private static final String JSON_KEY_LATITUDE = "latitude";
    private static final String JSON_KEY_LONGITUDE = "longitude";
    // KEEP FIELDS END


    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPriceDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getValue() {
        return value;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setValue(String value) {
        this.value = value;
    }

    /** Not-null value. */
    public java.util.Date getCreatedAtDate() {
        return createdAtDate;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreatedAtDate(java.util.Date createdAtDate) {
        this.createdAtDate = createdAtDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean getUploadStarted() {
        return uploadStarted;
    }

    public void setUploadStarted(boolean uploadStarted) {
        this.uploadStarted = uploadStarted;
    }

    public boolean getUploadCompleted() {
        return uploadCompleted;
    }

    public void setUploadCompleted(boolean uploadCompleted) {
        this.uploadCompleted = uploadCompleted;
    }

    public long getFuelGradeId() {
        return fuelGradeId;
    }

    public void setFuelGradeId(long fuelGradeId) {
        this.fuelGradeId = fuelGradeId;
    }

    /** To-one relationship, resolved on first access. */
    public Price getPrice() {
        long __key = this.fuelGradeId;
        if (price__resolvedKey == null || !price__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PriceDao targetDao = daoSession.getPriceDao();
            Price priceNew = targetDao.load(__key);
            synchronized (this) {
                price = priceNew;
            	price__resolvedKey = __key;
            }
        }
        return price;
    }

    public void setPrice(Price price) {
        if (price == null) {
            throw new DaoException("To-one property 'fuelGradeId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.price = price;
            fuelGradeId = price.getId();
            price__resolvedKey = fuelGradeId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    Price() {
        this.uploadStarted = false;
        this.uploadCompleted = false;
        this.createdAtDate = new Date();
    }

    public Price(String value, long fuelGradeId, double longitude, double latitude) {
        this();
        this.value = value;
        this.fuelGradeId = fuelGradeId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(JSON_KEY_GRADE_ID, this.getFuelGradeId());
        json.put(JSON_KEY_COST, this.getValue());
        json.put(JSON_KEY_LATITUDE, this.getLatitude());
        json.put(JSON_KEY_LONGITUDE, this.getLongitude());

        return json;
    }

    public static List<Price> readyForUpload(PriceDao priceDao) {
        QueryBuilder<Price> queryBuilder = priceDao.queryBuilder();

        queryBuilder.where(PriceDao.Properties.UploadCompleted.eq(false));
        queryBuilder.where(PriceDao.Properties.UploadStarted.eq(false));

        return queryBuilder.list();
    }

    public static void cleanupIncompleteUploads(PriceDao priceDao) {
        QueryBuilder<Price> queryBuilder = priceDao.queryBuilder();

        queryBuilder.where(PriceDao.Properties.UploadCompleted.eq(false));
        queryBuilder.where(PriceDao.Properties.UploadStarted.eq(true));

        List<Price> incompletePrices = queryBuilder.list();
        for (Price price : incompletePrices) {
            price.setUploadStarted(false);
        }

        priceDao.updateInTx(incompletePrices);
    }
    // KEEP METHODS END

}
