package com.sapient;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        String filename = "info.txt";

        try (FileReader reader = new FileReader(filename); 
            BufferedReader in = new BufferedReader(reader);) 
        {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } // all resources are closed automatically(reader.close(), in.close())
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
