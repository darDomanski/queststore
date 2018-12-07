package com.codecool.MKM.queststore.DAO;

import com.codecool.MKM.queststore.Controller.MediaDao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MediaDaoPostgress extends DAO implements MediaDao {

    public Map<String,String> getMedia(String mediaTableName) {

        Connection connection =  openDataBase();
        String query = "SELECT * FROM " + mediaTableName + ";";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Data base error - check Your internet connection or try later!");
        }

        ResultSet result = askDataBaseForData(query, statement);

        return getMapFromResultSet(result);


    }

    private Map<String,String> getMapFromResultSet(ResultSet result) {
        Map<String,String> resultMap = new HashMap<>();
        try {
            while (result.next()) {
                resultMap.put(result.getString(1), result.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data base error - check Your internet connection or try later!");
        }
        return resultMap;
    }
}
