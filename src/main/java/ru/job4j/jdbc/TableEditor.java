package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        this.connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s (%s, %s);",
                tableName,
                "id serial primary key",
                "name varchar(255)"
        );
        executeSql(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("drop table if exists %s;",
                tableName
        );
        executeSql(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("alter table if exists %s add column %s %s constraint;",
                tableName,
                columnName,
                type
        );
        executeSql(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("alter table if exists %s drop column %s;",
                tableName,
                columnName
        );
        executeSql(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
            String sql = String.format("alter table if exists %s rename column %s to %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
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
            System.out.println(getTableScheme(connection, "NewTable"));
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "./test_tableEditor.properties";
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            TableEditor tableEditor = new TableEditor(properties);
            tableEditor.createTable("NewTable");
            tableEditor.addColumn("NewTable", "column", "text");
            tableEditor.renameColumn("NewTable", "column", "newColumn");
            tableEditor.dropColumn("NewTable", "newColumn");
            tableEditor.dropTable("NewTable");
        }
    }
}