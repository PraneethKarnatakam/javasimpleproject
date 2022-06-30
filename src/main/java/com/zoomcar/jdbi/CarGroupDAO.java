package com.zoomcar.jdbi;


import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * since 1.0.0
 *
 * @author AbhishekT on 16/05/16.
 */
@RegisterRowMapper(CarGroupMapper.class)
public interface CarGroupDAO {

    /**
     * @return List of CarGroups
     */
    @SqlQuery("SELECT * FROM CARGROUPS WHERE STATUS = 1")
    List<CarGroup> findAll();

    @SqlQuery("SELECT ID FROM CARGROUPS WHERE STATUS =1")
    List<Integer> findAllGroupIds();

    @SqlQuery("SELECT ID FROM CARGROUPS WHERE ID IN (SELECT DISTINCT CARGROUP_ID FROM CAR_MOVEMENTS WHERE HUB_ID IN (SELECT ID FROM HUBS WHERE CITY_ID=:cityId AND STATUS =1) AND STATUS =1)AND STATUS =1")
    List<Integer> findIdByCity(@Bind("cityId") int cityId);

    /**
     * Retrieves an entity by its id.
     *
     * @param id id must not be null.
     * @return the entity with the given id or null if none found
     */
    @SqlQuery("SELECT * FROM CARGROUPS WHERE ID =:id LIMIT 1")
    CarGroup findOne(@Bind("id") int id);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    @SqlQuery("SELECT COUNT(*) FROM CARGROUPS;")
    long count();

    @SqlQuery("SELECT max(id) FROM CARGROUPS;")
    int maxId();

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be null.
     */
    @SqlUpdate("UPDATE CARGROUPS SET STATUS=0 WHERE ID= :id")
    void delete(@Bind("id") int id);

    @SqlQuery("SELECT EXISTS(SELECT ID FROM CARGROUPS WHERE ID = :id)")
    boolean carGroupExists(@Bind("id") int id);


    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the entity instance completely.
     *
     * @param carGroup entity
     */

    @SqlUpdate("INSERT INTO CARGROUPS " +
            "(ID, NAME, DISPLAY_NAME, STATUS, WAIT_PERIOD, CAR_TYPE, DEAL_CAR_TYPE, CREATED_BY, MODIFIED_BY) " +
            "VALUES (:id, :name, :displayName, :status, :waitPeriod, :carType, :dealCarType, :createdBy, :modifiedBy)")
    void save(@BindBean CarGroup carGroup) throws UnableToExecuteStatementException;

    @SqlQuery("SELECT LAST_INSERT_ID()")
    int lastInsertedId();

    @SqlUpdate("UPDATE CARGROUPS SET NAME =COALESCE(:name,NAME), DISPLAY_NAME =COALESCE(:displayName,DISPLAY_NAME), " +
            "STATUS =COALESCE(:status,STATUS), WAIT_PERIOD = COALESCE(NULLIF(:waitPeriod, 0), WAIT_PERIOD), " +
            "CAR_TYPE = COALESCE(NULLIF(:carType, 0), CAR_TYPE), DEAL_CAR_TYPE = COALESCE(NULLIF(:dealCarType, 0), DEAL_CAR_TYPE), " +
            "CREATED_AT =COALESCE(:createdAt,CREATED_AT), CREATED_BY =COALESCE(:createdBy,CREATED_BY), " +
            "MODIFIED_BY =COALESCE(:modifiedBy,CREATED_BY) where ID = :id")
    void update(@BindBean CarGroup carGroup, @Bind("id") int id) throws UnableToExecuteStatementException;

    /**
     * @param carGroup
     * @return
     */
    @SqlQuery("SELECT * FROM CARGROUPS WHERE CAR_TYPE = :carType AND STATUS = 1")
    List<CarGroup> getSimilarCarTypes(@BindBean CarGroup carGroup);

    /**
     * @param carType
     * @param upgradeLevel
     * @return
     */
    @SqlQuery("SELECT * FROM CARGROUPS WHERE CAR_TYPE >= (:carType + 1) AND CAR_TYPE <= (:carType + :upgradeLevel) AND STATUS = 1 ORDER BY CAR_TYPE")
    List<CarGroup> getUpgrades(@Bind("carType") int carType, @Bind("upgradeLevel") int upgradeLevel);

    /**
     * @param carType
     * @param downgradeLevel
     * @return
     */
    @SqlQuery("SELECT * FROM CARGROUPS WHERE CAR_TYPE >= (:carType - :downgradeLevel) AND CAR_TYPE <= (:carType - 1) AND STATUS = 1 ORDER BY CAR_TYPE")
    List<CarGroup> getDowngrades(@Bind("carType") int carType, @Bind("downgradeLevel") int downgradeLevel);


    /**
     * @param carType
     * @param upgradeLevel
     * @param downgradeLevel
     * @return
     */
    @SqlQuery("SELECT * FROM CARGROUPS WHERE (CAR_TYPE = :carType OR (CAR_TYPE >= (:carType + 1) AND CAR_TYPE <= (:carType + :upgradeLevel)) " +
            "OR (CAR_TYPE >= (:carType - :downgradeLevel) AND CAR_TYPE <= (:carType - 1))) AND STATUS = 1 ORDER BY CAR_TYPE")
    List<CarGroup> getAlternatives(@Bind("carType") int carType, @Bind("upgradeLevel") int upgradeLevel, @Bind("downgradeLevel") int downgradeLevel);

    /*@SqlQuery("SELECT NAME FROM  CARGROUPS")
    List<String> getAll();


    *//* Inserts *//*
    @SqlUpdate("INSERT INTO CARGROUPS ( NAME, DISPLAY_NAME, STATUS ,WAIT_PERIOD ,DISCLAIMER ,DESCRIPTION, CAR_TYPE , RANKING , DRIVE , FUEL , KMPL , KLE , DEAL_CARTYPE , FIRST_SERVICE_KM , SECOND_SERVICE_KM , RECURRING_SERVICE_KM , LAST_SERVICE_KM  , ENDED , SEATING, CREATED_AT, CREATED_BY, MODIFIED_BY )")
    void addCarGroup(@BindBean("carGroup")  CarGroup carGroup);*/

    /*
    ID INT PRIMARY KEY NOT NULL,
    NAME VARCHAR (255),
    DISPLAY_NAME VARCHAR (255),
    STATUS TINYINT,
    RANKING TINYINT,
    CREATED_AT DATETIME,
    MODIFIED_AT TIMESTAMP,
    CREATED_BY VARCHAR(32),
    MODIFIED_BY VARCHAR(32)
    * */
}
