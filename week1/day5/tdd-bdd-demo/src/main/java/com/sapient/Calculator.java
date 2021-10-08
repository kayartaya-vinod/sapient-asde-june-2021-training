package com.sapient;

public class Calculator {

    public int sum(String string) {
        if (string.equals(""))
            return 0;

        String[] arr = string.split(",");
        if (arr.length == 1)
            return Integer.valueOf(arr[0]);
        else if (arr.length == 2)
            return Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]);
        else
            return 0;
    }

}
