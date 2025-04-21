package com.HappyFresh;

import java.util.ArrayList;
import java.util.Locale;
import java.text.NumberFormat;

public class Seller extends User {
    private String alamat;
    private String namaToko;
    private String deskripsiToko;
    private ArrayList<Product> daftarProduk;

    public Seller(String id, String name, String noHp, int saldo, String alamat, String namaToko, String deskripsiToko) {
        super(id, name, noHp, saldo); 
        this.alamat = alamat;
        this.namaToko = namaToko;
        this.deskripsiToko = deskripsiToko;
        this.daftarProduk = new ArrayList<>(); 
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public String getDeskripsiToko() {
        return deskripsiToko;
    }

    public ArrayList<Product> getDaftarProduk() {
        return daftarProduk;
    }

    public Product cariProduk(String namaProduk) {
        for (Product produk : daftarProduk) {
            if (produk.getNamaProduk().equalsIgnoreCase(namaProduk)) {
                return produk;
            }
        }
        return null;
    }

    public void tambahkanProduk(Product produk) {
        daftarProduk.add(produk);
        System.out.println("Produk '" + produk.getNamaProduk() + "' berhasil ditambahkan!");
    }

    public boolean hapusProduk(String namaProduk) {
        Product produkUntukDihapus = cariProduk(namaProduk);
        if (produkUntukDihapus != null) {
            daftarProduk.remove(produkUntukDihapus);
            System.out.println("Produk '" + namaProduk + "' berhasil dihapus!");
            return true;
        } else {
            System.out.println("Produk '" + namaProduk + "' tidak ditemukan!");
            return false;
        }
    }

    public void editProduk(String namaProduk, String namaBaru, String jenisBaru, int hargaBaru, int stokBaru, String deskripsiBaru) throws NegativeValueException {
        Product produkUntukDiedit = cariProduk(namaProduk);
        if (produkUntukDiedit != null) {
            if (namaBaru != null && !namaBaru.trim().isEmpty()) {
                produkUntukDiedit.setNamaProduk(namaBaru);
            }
            if (jenisBaru != null && !jenisBaru.trim().isEmpty()) {
                produkUntukDiedit.setJenis(jenisBaru);
            }
            if (hargaBaru >= 0) {
                produkUntukDiedit.setHarga(hargaBaru);
            } else {
                throw new NegativeValueException("Harga tidak boleh negatif!");
            }
            if (stokBaru >= 0) {
                produkUntukDiedit.setStok(stokBaru);
            } else {
                throw new NegativeValueException("Stok tidak boleh negatif!");
            }
            if (deskripsiBaru != null && !deskripsiBaru.trim().isEmpty()) {
                produkUntukDiedit.setDeskripsiProduk(deskripsiBaru);
            }
            System.out.println("Produk '" + namaProduk + "' berhasil diedit!");
        } else {
            System.out.println("Produk '" + namaProduk + "' tidak ditemukan!");
        }
    }

    public void getInfoProduk() {
        System.out.println("Daftar Produk dari " + namaToko + ":");
        if (daftarProduk.isEmpty()) {
            System.out.println("- Tidak ada produk yang terdaftar.");
        } else {
            for (Product produk : daftarProduk) {
                produk.infoProduct();
            }
        }
    }

    public void tambahkanStok(String namaProduk, int jumlahStok) throws NegativeValueException {
        if (jumlahStok <= 0) {
            throw new NegativeValueException("Jumlah stok yang ditambahkan harus lebih dari 0.");
        }

        Product produkUntukDitambahStok = cariProduk(namaProduk);
        if (produkUntukDitambahStok != null) {
            produkUntukDitambahStok.setStok(produkUntukDitambahStok.getStok() + jumlahStok);
            System.out.println("Stok produk '" + namaProduk + "' berhasil ditambahkan sebanyak " + jumlahStok + ".");
        } else {
            System.out.println("Produk '" + namaProduk + "' tidak ditemukan!");
        }
    }

    public void getInfo() {
        int consoleWidth = 60;
        int padding = (consoleWidth - namaToko.length()) / 2;
        Locale myIndonesianLocale = new Locale("in", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(myIndonesianLocale);
        formatter.setMaximumFractionDigits(0);
        String formattedSaldo = formatter.format(super.getSaldo());

        System.out.println("=".repeat(consoleWidth));
        System.out.printf("%" + (padding + namaToko.length()) + "s%n", namaToko);
        System.out.println("=".repeat(consoleWidth));
        System.out.println("Nama Penjual: " + super.getName());
        System.out.println("ID: " + super.getId());
        System.out.println("Saldo: " + formattedSaldo);
        System.out.println("Deskripsi Toko: " + deskripsiToko);
        System.out.println("=".repeat(consoleWidth));
    }

    @Override
    public void melihatSaldo() {
        System.out.println("Saldo saat ini: Rp" + super.getSaldo());
    }
}
