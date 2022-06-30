package com.zoomcar.impl;


import com.zoomcar.DAOModule;
import com.zoomcar.jdbi.CarGroup;
import com.zoomcar.jdbi.CarGroupDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.List;


/**
 * since 1.0.0
 *
 * @author AbhishekT on 01/06/16.
 */
public class CarGroupManagerImpl {


    private static final CarGroupManagerImpl INSTANCE = new CarGroupManagerImpl();

    public static CarGroupManagerImpl getInstance() {
        return INSTANCE;
    }


    private CarGroupDAO carGroupDAO;// = daoModule.getCarGroupDAO();


    private CarGroupManagerImpl() {
        DAOModule daoModule = DAOModule.getInstance();
        carGroupDAO = daoModule.getCarGroupDAO();
    }

    public CarGroup getCarGroup(int id)  {
        return carGroupDAO.findOne(id);
    }


}
