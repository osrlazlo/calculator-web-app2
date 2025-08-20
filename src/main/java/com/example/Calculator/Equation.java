package com.example.Calculator;

import java.util.ArrayList;


public class Equation {
    private String string;

    public Equation() {
        string = "";
    }
    
    public Equation(String string) {
       this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String str) {
        string = str;
    }

    public ArrayList<String> splitWithDelimiters(String regex, ArrayList<String> list) {
        System.out.println("split: "+regex);

        if (string == null || string.length() < 2) {
            list.add(string);
            return list;
        }

        string = this.splitWith(regex, list);
        if (list.size() < 1) return list;
        
        this.splitWithDelimiters(regex, list);

        return list;
    }

    private String splitWith(String regex, ArrayList<String> list) {
        String str = this.string;
        
        for (int i = 0; i < str.length(); i++) {
            String c = ""+str.charAt(i);
                if (c.equals("")) continue;
                if (c.matches(regex)) {
                    System.out.println("match: "+str.charAt(i) + i);
                    if (c.equals("-")) {
                    	if ((""+str.charAt(i+1)).matches("[0-9]")) continue; 
                    }
                    	
                    list.add(str.substring(0,i).trim());
                    list.add(""+str.charAt(i));
                    
                    str = str.substring(i+1).trim();
                    System.out.println("sub: "+str);
                    break;
                }
            }
            return str;
    }

    public Equation trim() {
        string.trim();
        return this;
    }

    @Override
    public String toString() {
        return string;
    }
}
