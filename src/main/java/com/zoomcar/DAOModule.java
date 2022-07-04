package com.zoomcar;




import com.zoomcar.jdbi.CarGroupDAO;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author AbhishekT [abhishek.tyagi@zoomcar.com] on 03/07/16.
 * <p>
 * Zoomcar.com
 */

public class DAOModule {

    private javasimpleprojectConfiguration configuration;
    private Environment environment;
    private static Jdbi JDBI;

    private static Map<String, Object> daoMap;

    private static final DAOModule INSTANCE = new DAOModule();

    private DAOModule() {
        System.out.println("I am getting called in Dao module");
    }


    public static DAOModule getInstance() {
        return INSTANCE;
    }


    public void init(javasimpleprojectConfiguration configuration, Environment environment) {


        if (INSTANCE.configuration == null && INSTANCE.environment == null) {
            INSTANCE.configuration = configuration;
            INSTANCE.environment = environment;
            JdbiFactory factory = new JdbiFactory();

            JDBI = factory.build(environment, configuration.getDataSourceFactory(), "mysql");

            daoMap = new ConcurrentHashMap<>();
            populateDAOMap();
        }
    }


    private void populateDAOMap() {
        if (daoMap != null) {
            daoMap.put(CarGroupDAO.class.getName(), JDBI.onDemand(CarGroupDAO.class));
        }

    }

    public CarGroupDAO getCarGroupDAO() {
        CarGroupDAO result = null;
        if (daoMap.get(CarGroupDAO.class.getName()) != null && daoMap.get(CarGroupDAO.class.getName()) instanceof CarGroupDAO) {
            result = (CarGroupDAO) daoMap.get(CarGroupDAO.class.getName());
        }
        return result;
    }

}

