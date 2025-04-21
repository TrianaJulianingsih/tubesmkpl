package com.HappyFresh;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyerMenu {
    private Buyer buyer;
    private List<User> users;

    public BuyerMenu(Buyer buyer, List<User> users) {
        this.buyer = buyer;
        this.users = users;
    }

    public void tampilkanMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isBuyerActive = true;

        while (isBuyerActive) {
            System.out.println("=== Menu Buyer ===");
            System.out.println("=== Produk Tersedia ===");
            for (User user : users) {
                if (user instanceof Seller seller) {
                    seller.getInfoProduk(); 
                }
            }
            System.out.println("=======================");
            System.out.println("1. Beli Produk");
            System.out.println("2. Tambahkan ke Keranjang");
            System.out.println("3. Tampilkan Keranjang");
            System.out.println("4. Tambahkan ke Favorit");
            System.out.println("5. Tampilkan Favorit");
            System.out.println("6. Tambah Saldo");
            System.out.println("7. Logout");
            System.out.print("Pilih opsi: ");

            int buyerChoice = scanner.nextInt();
            scanner.nextLine(); 
            switch (buyerChoice) {
                case 1 -> beliProduk(scanner);
                case 2 -> tambahKeKeranjang(scanner);
                case 3 -> buyer.tampilKeranjang();
                case 4 -> tambahKeFavorit(scanner);
                case 5 -> buyer.tampilF();
                case 6 -> tambahSaldo(scanner);
                case 7 -> {
                    System.out.println("Logout berhasil.");
                    isBuyerActive = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void beliProduk(Scanner scanner) {
        System.out.println("=== Beli Produk ===");
        System.out.print("Masukkan nama produk yang ingin dibeli: ");
        String namaProdukBeli = scanner.nextLine();
        System.out.print("Masukkan jumlah yang ingin dibeli: ");
        int jumlahBeli = scanner.nextInt();
        System.out.print("Masukkan jarak pengiriman (m): ");
        int jarak = scanner.nextInt();
        scanner.nextLine(); 

        Product produkBeli = null;
        Seller sellerBeli = null;

        for (User user : users) {
            if (user instanceof Seller seller) {
                produkBeli = seller.cariProduk(namaProdukBeli);
                if (produkBeli != null) {
                    sellerBeli = seller; 
                    break;
                }
            }
        }

        if (produkBeli != null) {
            try {
                buyer.beliProduct(produkBeli, jumlahBeli, jarak, pilihDriver(scanner));
                if (sellerBeli != null) {
                    sellerBeli.setSaldo(sellerBeli.getSaldo() + produkBeli.getHarga() * jumlahBeli);
                    System.out.println("Saldo penjual " + sellerBeli.getNamaToko() + " berhasil diperbarui.");
                }
            } catch (NegativeValueException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    private void tambahKeKeranjang(Scanner scanner) {
        System.out.print("Masukkan nama produk yang ingin ditambahkan ke keranjang: ");
        String namaProdukKeranjang = scanner.nextLine();
        Product produkKeranjang = null;

        for (User user : users) {
            if (user instanceof Seller seller) {
                produkKeranjang = seller.cariProduk(namaProdukKeranjang);
                if (produkKeranjang != null) break;
            }
        }

        if (produkKeranjang != null) {
            buyer.Keranjang(produkKeranjang);
            System.out.println("Produk berhasil ditambahkan ke keranjang.");
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    private void tambahKeFavorit(Scanner scanner) {
        System.out.print("Masukkan nama produk yang ingin ditambahkan ke favorit: ");
        String namaProdukFavorit = scanner.nextLine();
        Product produkFavorit = null;

        for (User user : users) {
            if (user instanceof Seller seller) {
                produkFavorit = seller.cariProduk(namaProdukFavorit);
                if (produkFavorit != null) break;
            }
        }

        if (produkFavorit != null) {
            buyer.favorit(produkFavorit);
            System.out.println("Produk berhasil ditambahkan ke favorit.");
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    private void tambahSaldo(Scanner scanner) {
        System.out.print("Masukkan jumlah saldo yang ingin ditambahkan: ");
        int saldoTambah = scanner.nextInt();

        try {
            buyer.tambahSaldo(saldoTambah);
        } catch (NegativeValueException e) {
            System.out.println(e.getMessage());
        }
    }

    private Driver pilihDriver(Scanner scanner) {
        System.out.println("=== Pilih Driver ===");
        List<Driver> availableDrivers = new ArrayList<>();

        for (User user : users) {
            if (user instanceof Driver driver) {
                availableDrivers.add(driver);
            }
        }

        if (availableDrivers.isEmpty()) {
            System.out.println("Tidak ada driver tersedia saat ini.");
            return null;
        } else {
            for (int i = 0; i < availableDrivers.size(); i++) {
                Driver driver = availableDrivers.get(i);
                System.out.println((i + 1) + ". " + driver.getName()+ " - Saldo: Rp" + driver.getSaldo());
            }

            System.out.print("Pilih Driver (1-" + availableDrivers.size() + "): ");
            int driverIndex = scanner.nextInt() - 1;
            scanner.nextLine(); 

            if (driverIndex >= 0 && driverIndex < availableDrivers.size()) {
                return availableDrivers.get(driverIndex);
            } else {
                System.out.println("Pilihan driver tidak valid.");
                return null;
            }
        }
    }
}
