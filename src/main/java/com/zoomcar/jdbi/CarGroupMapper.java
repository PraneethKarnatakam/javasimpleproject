package com.zoomcar.jdbi;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * since 1.0.0
 *
 * @author AbhishekT on 30/05/16.
 */
public class CarGroupMapper implements RowMapper<CarGroup> {
    @Override
    public CarGroup map(ResultSet resultSet, StatementContext statementContext) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String displayName =  resultSet.getString("display_name");

        Integer status = 1;

        int waitPeriod = resultSet.getInt("wait_period");
        int carType = resultSet.getInt("car_type");
        int dealCarType = resultSet.getInt("deal_car_type");
        String createdBy = resultSet.getString("created_by");
        String modifiedBy = resultSet.getString("modified_by");

        Date createdAt = resultSet.getTimestamp("created_at");
        Date modifiedAt = resultSet.getTimestamp("modified_at");

//        System.out.println("Created_at"+resultSet.getDate("created_at").toString()+"\tModified at: "+resultSet.getDate("modified_at").toString());
        return new CarGroup(id,createdBy, modifiedBy,createdAt,modifiedAt,name,displayName,status,waitPeriod,carType,dealCarType);
    }
}
