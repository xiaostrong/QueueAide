package dao;
import com.google.gson.Gson;
import model.User;
import org.apache.commons.dbutils.QueryRunner;
import utils.JDBCUtils;
import utils.MyHandler;

import java.sql.SQLException;
import java.util.List;

public class QueryUser {

    private static QueryUser instance = new QueryUser();

    private QueryUser(){}

    public static QueryUser getInstance(){
        return instance;
    }

    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSources());
    String sql = "select * from user";

    public String queryUsers() {
        String jsonString = null;
        try {
            List<User> list = queryRunner.query(sql, new MyHandler());
            Gson gson = new Gson();
            jsonString = gson.toJson(list);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return jsonString;
    }


}
