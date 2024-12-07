package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(String path) {
        this.path = path;
    }
    public void load() throws IllegalArgumentException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().forEach(line -> {
                if (line.endsWith("=") || line.startsWith("=")) {
                    throw new IllegalArgumentException();
                }
                String[] splitString = line.split("=");
                if (splitString.length > 1) {
                    values.put(splitString[0], splitString[1]);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
