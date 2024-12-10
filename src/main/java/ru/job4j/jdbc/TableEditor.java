package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;


import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException, IOException {
        this.properties = properties;
        initConnection();
    }

    public static void main(String[] args) throws Exception {
        TableEditor editor = new TableEditor(new Properties());
        editor.createTable("demo_table");
        System.out.println(editor.getTableScheme("demo_table"));
        editor.addColumn("demo_table", "id", "serial primary key");
        System.out.println(editor.getTableScheme("demo_table"));
        editor.renameColumn("demo_table", "id", "ids");
        System.out.println(editor.getTableScheme("demo_table"));
        editor.dropColumn("demo_table", "ids");
        System.out.println(editor.getTableScheme("demo_table"));
        editor.dropTable("demo_table");
        System.out.println(editor.getTableScheme("demo_table"));
        editor.connection.close();
    }

    private void initConnection() throws SQLException, ClassNotFoundException, IOException {
        try (InputStream input = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(input);
        }
        Class.forName(properties.getProperty("jdbc.connection.driver_class"));
        connection = DriverManager.getConnection(
                properties.getProperty("jdbc.connection.url"),
                properties.getProperty("jdbc.connection.username"),
                properties.getProperty("jdbc.connection.password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s ();",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE %s;",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s;",
                    tableName,
                    columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        } catch (SQLException e) {
            buffer.add("Отношение не существует".concat(System.lineSeparator()));
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}