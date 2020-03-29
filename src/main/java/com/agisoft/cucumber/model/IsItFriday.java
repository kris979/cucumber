package com.agisoft.cucumber.model;

public class IsItFriday {

    public static String isItFriday(String today) {
        return "Friday".equals(today) ? "TGIF" : "Nope";
    }

}
