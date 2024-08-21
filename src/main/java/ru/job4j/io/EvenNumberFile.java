package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                if (read == 10 || read == 13) {
                    System.out.print(text.charAt(text.length() - 1) % 2 == 0);
                    System.out.print((char) read);
                } else {
                    text.append((char) read);
                }
            }
            System.out.println(text.charAt(text.length() - 1) % 2 == 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}