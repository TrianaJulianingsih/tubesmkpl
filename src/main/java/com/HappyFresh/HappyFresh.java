package com.HappyFresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HappyFresh {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        HashMap<String, String> credentials = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        RegisterMenu registerMenu = new RegisterMenu(users, credentials);

        boolean isRunning = true;

        while (isRunning) {
            try {
                System.out.println("=== Selamat Datang di HappyFresh ===");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Keluar");
                System.out.print("Pilih opsi: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> registerMenu.tampilkanMenuRegister(scanner);
                    case 2 -> {
                        LoginMenu loginMenu = new LoginMenu(credentials, users, scanner);
                        User currentUser = loginMenu.login();

                        if (currentUser != null) {
                            if (currentUser instanceof Seller seller) {
                                System.out.println("Anda login sebagai Seller.");
                                SellerMenu sellerMenu = new SellerMenu(seller, scanner);
                                sellerMenu.tampilkanMenuSeller();
                            } else if (currentUser instanceof Buyer buyer) {
                                System.out.println("Anda login sebagai Buyer.");
                                BuyerMenu buyerMenu = new BuyerMenu(buyer, users);
                                buyerMenu.tampilkanMenu();
                            } else if (currentUser instanceof Driver driver) {
                                System.out.println("Anda login sebagai Driver.");
                                DriverMenu driverMenu = new DriverMenu(driver, scanner);
                                driverMenu.tampilkanMenuDriver();
                            }
                            System.out.println("Anda telah logout.");
                        }
                    }
                    case 3 -> {
                        System.out.println("Terima kasih telah menggunakan HappyFresh!");
                        isRunning = false;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan input. Pastikan Anda memasukkan angka yang valid.");
                scanner.nextLine(); 
            }
        }

        scanner.close();
    }
}
