package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        String sql = "CREATE TABLE if not exists " + tableName
                + " (id SERIAL PRIMARY KEY)";
        executeSql(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = "DROP TABLE " + tableName;
        executeSql(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = "ALTER TABLE " + tableName + " ADD " + columnName + " " + type;
        executeSql(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = "ALTER TABLE " + tableName + " DROP " + columnName;
        executeSql(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = "ALTER TABLE " + tableName
                + " RENAME COLUMN " + columnName + " TO " + newColumnName;
        executeSql(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public void executeSql(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("test_tableEditor.properties")) {
            config.load(in);

        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("demo");
            System.out.println(getTableScheme(connection, "demo"));
            tableEditor.addColumn("demo", "name", "text");
            System.out.println(getTableScheme(connection, "demo"));
            tableEditor.renameColumn("demo", "name", "Address");
            System.out.println(getTableScheme(connection, "demo"));
            tableEditor.dropColumn("demo", "Address");
            System.out.println(getTableScheme(connection, "demo"));
            tableEditor.dropTable("demo");
        }
    }
}