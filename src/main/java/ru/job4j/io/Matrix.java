package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {
    public static void main(String[] args) {
        Matrix.multiple(9);
    }
    public static void multiple(int size) {
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    output.write(Integer.toString((i + 1) * (j + 1)).getBytes());
                    output.write(" ".getBytes());
                    if (j == size - 1) {
                        output.write(System.lineSeparator().getBytes());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}