package com.HappyFresh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuyerTest {

    @Test
    void testTambahSaldoBerhasil() throws NegativeValueException {
        Buyer buyer = new Buyer(
                "B01",
                "Intan",
                "0812",
                100000,
                "Bogor"
        );

        buyer.tambahSaldo(50000);

        assertEquals(150000, buyer.getSaldo());
    }

    @Test
    void testTambahSaldoNegatif() {
        Buyer buyer = new Buyer(
                "B01",
                "Intan",
                "0812",
                100000,
                "Bogor"
        );

        assertThrows(
                NegativeValueException.class,
                () -> buyer.tambahSaldo(-10000)
        );
    }

    @Test
    void testBeliProdukBerhasil() throws NegativeValueException {
        Buyer buyer = new Buyer(
                "B01",
                "Intan",
                "0812",
                100000,
                "Bogor"
        );

        Driver driver = new Driver(
                "D01",
                "Budi",
                "0813",
                0
        );

        Product produk = new Product(
                "Toko Buah",
                "Apel",
                "Buah",
                10000,
                10,
                "Apel segar"
        );

        buyer.beliProduct(produk, 2, 5000, driver);

        assertEquals(8, produk.getStok());
        assertEquals(70000, buyer.getSaldo());
    }

    @Test
    void testBeliProdukStokTidakCukup() throws NegativeValueException {
        Buyer buyer = new Buyer(
                "B01",
                "Intan",
                "0812",
                100000,
                "Bogor"
        );

        Driver driver = new Driver(
                "D01",
                "Budi",
                "0813",
                0
        );

        Product produk = new Product(
                "Toko Buah",
                "Apel",
                "Buah",
                10000,
                5,
                "Apel segar"
        );

        buyer.beliProduct(produk, 10, 5000, driver);

        assertEquals(5, produk.getStok());
        assertEquals(100000, buyer.getSaldo());
    }

    @Test
    void testBeliProdukJumlahNegatif() throws NegativeValueException {
        Buyer buyer = new Buyer(
                "B01",
                "Intan",
                "0812",
                100000,
                "Bogor"
        );

        Driver driver = new Driver(
                "D01",
                "Budi",
                "0813",
                0
        );

        Product produk = new Product(
                "Toko Buah",
                "Apel",
                "Buah",
                10000,
                10,
                "Apel segar"
        );

        assertThrows(
                NegativeValueException.class,
                () -> buyer.beliProduct(produk, -1, 5000, driver)
        );
    }
}


