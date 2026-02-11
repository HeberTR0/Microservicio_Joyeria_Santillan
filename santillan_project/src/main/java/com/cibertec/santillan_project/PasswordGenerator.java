package com.cibertec.santillan_project;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("admin123: " + encoder.encode("admin123"));
        System.out.println("empleado123: " + encoder.encode("empleado123"));
    }
}