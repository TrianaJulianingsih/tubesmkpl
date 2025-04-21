package com.HappyFresh;

public class Product {
    private String namaPenjual;
    private String namaProduk;
    private String jenis;
    private int harga;
    private int stok;
    private String deskripsiProduk;

    public Product(String namaPenjual, String namaProduk, String jenis, int harga, int stok, String deskripsiProduk) throws NegativeValueException {
        if (harga < 0) {
            throw new NegativeValueException("Harga tidak boleh negatif!");
        }
        if (stok < 0) {
            throw new NegativeValueException("Stok tidak boleh negatif!");
        }
        this.namaPenjual = namaPenjual;
        this.namaProduk = namaProduk;
        this.jenis = jenis;
        this.harga = harga;
        this.stok = stok;
        this.deskripsiProduk = deskripsiProduk;
    }

    public String getNamaPenjual() {
        return namaPenjual;
    }

    public void setNamaPenjual(String namaPenjual) {
        this.namaPenjual = namaPenjual;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) throws NegativeValueException {
        if (harga < 0) {
            throw new NegativeValueException("Harga tidak boleh negatif!");
        }
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) throws NegativeValueException {
        if (stok < 0) {
            throw new NegativeValueException("Stok tidak boleh negatif!");
        }
        this.stok = stok;
    }

    public String getDeskripsiProduk() {
        return deskripsiProduk;
    }

    public void setDeskripsiProduk(String deskripsiProduk) {
        this.deskripsiProduk = deskripsiProduk;
    }

    public void infoProduct() {
        System.out.println("- Nama Produk: " + namaProduk);
        System.out.println("  Jenis: " + jenis);
        System.out.println("  Harga: Rp" + harga);
        System.out.println("  Stok: " + stok);
        System.out.println("  Penjual: " + namaPenjual);
        System.out.println("  Deskripsi: " + deskripsiProduk);
        System.out.println();
    }
}
