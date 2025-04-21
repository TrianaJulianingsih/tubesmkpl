package com.HappyFresh;
// digunakan untuk class Buyer
public interface InterfaceHF {
    public void beliProduct(Product produk, int jumlah, int jarakM, Driver driver) throws NegativeValueException;
    public void Keranjang(Product produk);
    public void tampilKeranjang();
    public void favorit(Product produk);
    public void tampilF();
    public void tambahSaldo(int jumlah) throws NegativeValueException;
    public void getInfo();
}
