package com.HappyFresh;

public abstract class User {
    private String id;
    private String name;
    private String noHp;
    private int saldo;

    public User(String id, String name, String noHp, int saldo) {
        this.id = id;
        this.name = name;
        this.noHp = noHp;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNoHp() {
        return noHp;
    }

    public int getSaldo() {
        return saldo;
    }


    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    public abstract void melihatSaldo();
}
