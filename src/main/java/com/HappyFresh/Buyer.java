package com.HappyFresh;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Buyer extends User implements InterfaceHF {
    private String alamat;
    private ArrayList<Product> keranjang;
    private ArrayList<Product> favorit;

    public Buyer(String id, String name, String noHp, int saldo, String alamat) {
        super(id, name, noHp, saldo);
        this.alamat = alamat;
        this.keranjang = new ArrayList<>();
        this.favorit = new ArrayList<>();  
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public void melihatSaldo() {
        System.out.println("Saldo Anda saat ini adalah: Rp " + super.getSaldo());
    }

    @Override
    public void beliProduct(Product produk, int jumlah, int jarakm, Driver driver) throws NegativeValueException {
        if (jumlah <= 0) {
            throw new NegativeValueException("Jumlah pembelian harus lebih dari 0.");
        }

        if (produk.getStok() < jumlah) {
            System.out.println("Stok produk '" + produk.getNamaProduk() + "' tidak mencukupi!");
            return;
        }

        int totalHarga = produk.getHarga() * jumlah;
        int ongkir;

        if (jarakm <= 0) {
            System.out.printf("Maaf, jarak pengiriman %d m tidak valid. Harap masukkan jarak yang lebih besar dari 0 m.%n", jarakm);
            return;
        } else if (jarakm <= 5000) {
            ongkir = 10000;
        } else if (jarakm <= 10000) {
            ongkir = 20000;
        } else {
            System.out.printf("Maaf, jarak pengiriman %d m melebihi batas maksimal 10000 m.%n", jarakm);
            return;
        }

        int totalBiaya = totalHarga + ongkir;

        if (super.getSaldo() < totalBiaya) {
            System.out.printf("Saldo tidak mencukupi untuk membeli produk ini. Diperlukan: Rp %d, Saldo saat ini: Rp %d%n", totalBiaya, super.getSaldo());
            return;
        }

        produk.setStok(produk.getStok() - jumlah); 
        super.setSaldo(super.getSaldo() - totalBiaya); 
        driver.menerimaOrderan(ongkir);

        System.out.printf("Berhasil membeli %d %s.%n", jumlah, produk.getNamaProduk());
        System.out.printf("Ongkir sebesar Rp %d telah dibayarkan untuk jarak %d m.%n", ongkir, jarakm);
    }

    @Override
    public void Keranjang(Product produk) {
        keranjang.add(produk);
        System.out.println("Produk '" + produk.getNamaProduk() + "' berhasil ditambahkan ke keranjang.");
    }

    @Override
    public void tampilKeranjang() {
        System.out.println("Keranjang Anda:");
        if (keranjang.isEmpty()) {
            System.out.println("- Tidak ada produk dalam keranjang.");
        } else {
            for (Product produk : keranjang) {
                produk.infoProduct();
            }
        }
    }

    @Override
    public void favorit(Product produk) {
        favorit.add(produk);
        System.out.println("Produk '" + produk.getNamaProduk() + "' berhasil ditambahkan ke favorit.");
    }

    @Override
    public void tampilF() {
        System.out.println("Daftar Produk Favorit Anda:");
        if (favorit.isEmpty()) {
            System.out.println("- Tidak ada produk dalam daftar favorit.");
        } else {
            for (Product produk : favorit) {
                produk.infoProduct();
            }
        }
    }

    @Override
    public void tambahSaldo(int jumlah) throws NegativeValueException {
        if (jumlah <= 0) {
            throw new NegativeValueException("Jumlah saldo yang ditambahkan harus lebih dari 0.");
        }
        super.setSaldo(super.getSaldo() + jumlah); 
        System.out.println("Saldo berhasil ditambahkan sebesar Rp " + jumlah + ". Saldo Anda sekarang adalah Rp " + super.getSaldo() + ".");
    }

    @Override
    public void getInfo() {
        Locale myIndonesianLocale = new Locale("in", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(myIndonesianLocale);
        formatter.setMaximumFractionDigits(0);
        String formattedPrice = formatter.format(super.getSaldo());
        System.out.println("============================================================");
        System.out.println("Nama pembeli: " + super.getName());
        System.out.println("Id: " + super.getId());
        System.out.println("Saldo: " + formattedPrice);
        System.out.println("============================================================");
    }
}
