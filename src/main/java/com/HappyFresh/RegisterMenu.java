package com.HappyFresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RegisterMenu {
    private final ArrayList<User> users;
    private final HashMap<String, String> credentials;

    public RegisterMenu(ArrayList<User> users, HashMap<String, String> credentials) {
        if (users == null || credentials == null) {
            throw new IllegalArgumentException("Users atau credentials tidak boleh NULL");
        }
        this.users = users;
        this.credentials = credentials;
    }

    public void tampilkanMenuRegister(Scanner scanner) {
        System.out.println("=== Register ===");

        System.out.print("Masukkan ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("ID tidak boleh kosong.");
            return;
        }
        if (credentials.containsKey(id)) {
            System.out.println("ID sudah digunakan. Silakan pilih ID lain.");
            return;
        }

        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine().trim();
        if (password.isEmpty()) {
            System.out.println("Password tidak boleh kosong.");
            return;
        }

        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine().trim();
        if (nama.isEmpty()) {
            System.out.println("Nama tidak boleh kosong.");
            return;
        }

        System.out.print("Masukkan No HP: ");
        String noHp = scanner.nextLine().trim();
        if (noHp.isEmpty()) {
            System.out.println("No HP tidak boleh kosong.");
            return;
        }

        System.out.println("Pilih tipe pengguna: ");
        System.out.println("1. Seller");
        System.out.println("2. Buyer");
        System.out.println("3. Driver");
        System.out.print("Pilih opsi: ");
        int tipePengguna = scanner.nextInt();
        scanner.nextLine(); 

        switch (tipePengguna) {
            case 1 -> registrasiSeller(scanner, id, password, nama, noHp);
            case 2 -> registrasiBuyer(scanner, id, password, nama, noHp);
            case 3 -> registrasiDriver(scanner, id, password, nama, noHp);
            default -> System.out.println("Pilihan tidak valid. Registrasi dibatalkan.");
        }
    }

    private void registrasiSeller(Scanner scanner, String id, String password, String nama, String noHp) {
        System.out.print("Masukkan alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan saldo awal: ");
        int saldo = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Masukkan nama toko: ");
        String namaToko = scanner.nextLine();
        System.out.print("Masukkan deskripsi toko: ");
        String deskripsiToko = scanner.nextLine();
        Seller seller = new Seller(id, nama, noHp, saldo, alamat, namaToko, deskripsiToko);
        users.add(seller);
        credentials.put(id, password); 
        System.out.println("Registrasi Seller berhasil!");
    }

    private void registrasiBuyer(Scanner scanner, String id, String password, String nama, String noHp) {
        System.out.print("Masukkan alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan saldo awal: ");
        int saldo = scanner.nextInt();
        scanner.nextLine(); 

        Buyer buyer = new Buyer(id, nama, noHp, saldo, alamat);
        users.add(buyer);
        credentials.put(id, password); 
        System.out.println("Registrasi Buyer berhasil!");
    }

    private void registrasiDriver(Scanner scanner, String id, String password, String nama, String noHp) {
        System.out.print("Masukkan saldo awal: ");
        int saldo = scanner.nextInt();
        scanner.nextLine();

        Driver driver = new Driver(id, nama, noHp, saldo);
        users.add(driver);
        credentials.put(id, password); 
        System.out.println("Registrasi Driver berhasil!");
    }
}
