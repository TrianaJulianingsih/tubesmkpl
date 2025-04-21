package com.HappyFresh;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SellerMenu {
    private Seller seller;
    private Scanner scanner;

    public SellerMenu(Seller seller, Scanner scanner) {
        this.seller = seller;
        this.scanner = scanner;
    }

    public void tampilkanMenuSeller() throws NegativeValueException {
        boolean isSellerActive = true;

        while (isSellerActive) {
            System.out.println("=== Menu Seller ===");
            System.out.println("1. Tambahkan Produk");
            System.out.println("2. Hapus Produk");
            System.out.println("3. Edit Produk");
            System.out.println("4. Tambahkan Stok Produk");
            System.out.println("5. Tampilkan Semua Produk");
            System.out.println("6. Lihat Saldo");
            System.out.println("7. Tarik Saldo");
            System.out.println("8. Logout");
            System.out.print("Pilih opsi: ");

            try {
                int sellerChoice = scanner.nextInt();
                scanner.nextLine(); 

                switch (sellerChoice) {
                    case 1 -> tambahkanProduk();
                    case 2 -> hapusProduk();
                    case 3 -> editProduk();
                    case 4 -> tambahkanStokProduk();
                    case 5 -> seller.getInfoProduk();
                    case 6 -> seller.melihatSaldo();
                    case 7 -> tarikSaldo();
                    case 8 -> {
                        System.out.println("Logout berhasil.");
                        isSellerActive = false;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Masukkan harus berupa angka.");
                scanner.nextLine(); 
            }
        }
    }

    private void tambahkanProduk() throws NegativeValueException {
        System.out.print("Masukkan nama produk: ");
        String namaProduk = scanner.nextLine();

        Product produkExist = seller.cariProduk(namaProduk);
        if (produkExist != null) {
            System.out.print("Produk sudah ada. Masukkan jumlah stok tambahan: ");
            int stokTambah = scanner.nextInt();
            scanner.nextLine(); 
            try {
                seller.tambahkanStok(namaProduk, stokTambah);
            } catch (NegativeValueException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.print("Masukkan jenis produk: ");
            String jenisProduk = scanner.nextLine();
            System.out.print("Masukkan harga produk: ");
            int hargaProduk = scanner.nextInt();
            System.out.print("Masukkan stok produk: ");
            int stokProduk = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Masukkan deskripsi produk: ");
            String deskripsiProduk = scanner.nextLine();

            Product newProduct = new Product(
                seller.getNamaToko(),
                namaProduk,
                jenisProduk,
                hargaProduk,
                stokProduk,
                deskripsiProduk
            );
            seller.tambahkanProduk(newProduct);
        }
    }

    private void hapusProduk() {
        System.out.print("Masukkan nama produk yang ingin dihapus: ");
        String namaProduk = scanner.nextLine();
        if (seller.hapusProduk(namaProduk)) {
            System.out.println("Produk berhasil dihapus.");
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    private void editProduk() throws NegativeValueException {
    System.out.print("Masukkan nama produk yang ingin diedit: ");
    String namaProduk = scanner.nextLine();
    Product produkEdit = seller.cariProduk(namaProduk);

        if (produkEdit != null) {
            boolean isEditing = true;
            while (isEditing) {
                System.out.println("=== Edit Produk ===");
                System.out.println("1. Ubah Nama Produk");
                System.out.println("2. Ubah Jenis Produk");
                System.out.println("3. Ubah Harga Produk");
                System.out.println("4. Ubah Stok Produk");
                System.out.println("5. Ubah Deskripsi Produk");
                System.out.println("6. Selesai");
                System.out.print("Pilih opsi: ");

                try {
                    int editChoice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (editChoice) {
                        case 1 -> {
                            System.out.print("Masukkan nama baru: ");
                            String namaBaru = scanner.nextLine();
                            if (!namaBaru.trim().isEmpty()) {
                                produkEdit.setNamaProduk(namaBaru);
                                System.out.println("Nama produk berhasil diubah.");
                            } else {
                                System.out.println("Nama tidak boleh kosong.");
                            }
                        }
                        case 2 -> {
                            System.out.print("Masukkan jenis baru: ");
                            String jenisBaru = scanner.nextLine();
                            if (!jenisBaru.trim().isEmpty()) {
                                produkEdit.setJenis(jenisBaru);
                                System.out.println("Jenis produk berhasil diubah.");
                            } else {
                                System.out.println("Jenis tidak boleh kosong.");
                            }
                        }
                        case 3 -> {
                            System.out.print("Masukkan harga baru: ");
                            int hargaBaru = scanner.nextInt();
                            if (hargaBaru >= 0) {
                                produkEdit.setHarga(hargaBaru);
                                System.out.println("Harga produk berhasil diubah.");
                            } else {
                                System.out.println("Harga tidak boleh negatif.");
                            }
                        }
                        case 4 -> {
                            System.out.print("Masukkan stok baru: ");
                            int stokBaru = scanner.nextInt();
                            if (stokBaru >= 0) {
                                produkEdit.setStok(stokBaru);
                                System.out.println("Stok produk berhasil diubah.");
                            } else {
                                System.out.println("Stok tidak boleh negatif.");
                            }
                        }
                        case 5 -> {
                            System.out.print("Masukkan deskripsi baru: ");
                            String deskripsiBaru = scanner.nextLine();
                            if (!deskripsiBaru.trim().isEmpty()) {
                                produkEdit.setDeskripsiProduk(deskripsiBaru);
                                System.out.println("Deskripsi produk berhasil diubah.");
                            } else {
                                System.out.println("Deskripsi tidak boleh kosong.");
                            }
                        }
                        case 6 -> {
                            System.out.println("Selesai mengedit produk.");
                            isEditing = false;
                        }
                        default -> System.out.println("Pilihan tidak valid.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Masukkan harus berupa angka.");
                    scanner.nextLine(); // Membersihkan buffer
                }
            }
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }


    private void tambahkanStokProduk() {
        System.out.print("Masukkan nama produk yang ingin ditambahkan stoknya: ");
        String namaProduk = scanner.nextLine();
        System.out.print("Masukkan jumlah stok yang ingin ditambahkan: ");
        int stokTambah = scanner.nextInt();
        scanner.nextLine(); 

        try {
            seller.tambahkanStok(namaProduk, stokTambah);
        } catch (NegativeValueException e) {
            System.out.println(e.getMessage());
        }
    }

    private void tarikSaldo() {
        System.out.print("Masukkan jumlah saldo yang ingin ditarik: ");
        try {
            int jumlahTarik = scanner.nextInt();
            scanner.nextLine(); 
            if (jumlahTarik <= 0) {
                throw new IllegalArgumentException("Jumlah yang ditarik harus lebih dari 0.");
            }
            if (jumlahTarik > seller.getSaldo()) {
                throw new IllegalArgumentException("Saldo tidak mencukupi untuk jumlah yang diminta.");
            }
            seller.setSaldo(seller.getSaldo() - jumlahTarik);
            System.out.println("Saldo berhasil ditarik sebesar: Rp " + jumlahTarik);
        } catch (InputMismatchException e) {
            System.out.println("Masukkan harus berupa angka.");
            scanner.nextLine(); 
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
