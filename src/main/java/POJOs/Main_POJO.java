package POJOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main_POJO {

    public static void main(String[] args) throws SQLException {


        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/",
                "root",
                "2419839"
        );
        PreparedStatement statement1 = connection.prepareStatement(
                "use hibernate_with_mapping;");
        statement1.execute();

        PreparedStatement statement = connection.prepareStatement(
                "select *from hibernate_with_mapping.user_table;");
        ResultSet resultSet = statement.executeQuery();
        List<String> strings = new ArrayList<String>();
        List<UserTable> userTables = new ArrayList<UserTable>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1); // might be "id" instead of 1
            String name = resultSet.getString(2); // might be "name"
            int passport_id = resultSet.getInt(3);
            String obj = id + ", " + name+", "+passport_id;
            strings.add(obj);
            System.out.println(obj);
            UserTable userTable = new UserTable();
            userTable.setId(id);
            userTable.setName(name);
            userTable.setPassportId(passport_id);
            userTables.add(userTable);
           // System.out.println(userTables);
        }
        for (UserTable userTable : userTables) {
            System.out.println(userTable);
        }

        PreparedStatement statement2 = connection.prepareStatement(
                "select name, number, series from hibernate_with_mapping.user_table " +
                        "inner join hibernate_with_mapping.passport_table on user_table.passport_id=passport_table.id;");

        ResultSet resultSet1 = statement2.executeQuery();
        while (resultSet1.next()){
            String name = resultSet1.getString(1);
            String number = resultSet1.getString(2);
            String series = resultSet1.getString(3);
            System.out.println(name+", "+number+", "+series);
        }
        PreparedStatement statement3 = connection.prepareStatement(
                "select name, value from hibernate_with_mapping.user_table " +
                        "left join hibernate_with_mapping.user_table_aka_table " +
                        "on user_table.id=user_table_aka_table.users_id " +
                        " left join aka_table on user_table_aka_table.akas_id=" +
                        "aka_table.id");

        ResultSet resultSet2 = statement3.executeQuery();

        while (resultSet2.next()){
            String name = resultSet2.getString(1);
            String value = resultSet2.getString(2);
            System.out.println(name+", "+value);
            AkaTable akaTable = new AkaTable();
            akaTable.setValue(value);
            System.out.println(akaTable);
        }
    }


}
