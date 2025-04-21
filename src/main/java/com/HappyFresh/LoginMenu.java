package com.HappyFresh;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class LoginMenu {
    private final Map<String, String> credentials;
    private final ArrayList<User> users;
    private final Scanner scanner;

    public LoginMenu(Map<String, String> credentials, ArrayList<User> users, Scanner scanner) {
        if (credentials == null || users == null || scanner == null) {
            throw new IllegalArgumentException("isinya ga boleh kosong.");
        }
        this.credentials = credentials;
        this.users = users;
        this.scanner = scanner;
    }

    public User login() {
        System.out.println("=== Login ===");

        System.out.print("Masukkan ID: ");
        String loginId = scanner.nextLine().trim();
        if (loginId.isEmpty()) {
            System.out.println("ID tidak boleh kosong.");
            return null;
        }

        System.out.print("Masukkan Password: ");
        String loginPassword = scanner.nextLine().trim();
        if (loginPassword.isEmpty()) {
            System.out.println("Password tidak boleh kosong.");
            return null;
        }
        
        if (!credentials.containsKey(loginId)) {
            System.out.println("ID salah. Silakan coba lagi.");
            return null;
        }
        
        if (!credentials.get(loginId).equals(loginPassword)) {
            System.out.println("ID atau Password salah. Silakan coba lagi.");
            return null;
        }

        for (User user : users) {
            if (user.getId().equals(loginId)) {
                System.out.println("Login berhasil! Selamat datang, " + user.getName() + ".");
                return user;
            }
        }

        System.out.println("User dengan ID ini tidak ditemukan dalam sistem.");
        return null;
    }
}
