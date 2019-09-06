package com.ibm.notifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractor {
    public String extract(String input){
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(input);
        String output = "";
        if(m.find()){
            output = m.group();
        }
        return output;
    }
}
