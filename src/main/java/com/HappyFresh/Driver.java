package com.HappyFresh;

public class Driver extends User {

    public Driver(String id, String name, String noHp, int saldo) {
        super(id, name, noHp, saldo);
    }

    public void menerimaOrderan(int ongkir) throws NegativeValueException {
         if (ongkir <= 0) {
            throw new NegativeValueException("Ongkos kirim harus lebih besar dari 0.");
        }
        super.setSaldo(super.getSaldo() + ongkir);
    }

    @Override
    public void melihatSaldo() {
        System.out.println("Saldo Anda saat ini adalah: Rp " + super.getSaldo());
    }
}
