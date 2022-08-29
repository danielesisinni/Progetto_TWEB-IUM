package com.ium.example.booking;

public class MyURL {
    public static String URLPOST = "http://10.0.2.2:8080/demo_war_exploded/ServletController";
    public static String URLGETP = String.format("http://10.0.2.2:8080/demo_war_exploded/ServletController?action=%1$s&action2=%2$s", "androidP", "android");
    public static String URLGETR = String.format("http://10.0.2.2:8080/demo_war_exploded/ServletController?action=%1$s&action2=%2$s", "androidR", "android");
}
