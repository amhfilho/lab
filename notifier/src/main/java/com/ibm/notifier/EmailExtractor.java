package com.ibm.notifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractor {
    public String extract(String input){
        String s = "*** test@gmail.com&&^ test2@gmail.com((& ";
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(s);
        String output = "";
        while(m.find()){
            output = m.group();
        }
        return output;
    }
}
