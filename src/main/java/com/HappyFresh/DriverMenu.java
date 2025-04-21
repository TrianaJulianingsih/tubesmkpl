package com.HappyFresh;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DriverMenu {
    private Driver driver;
    private Scanner scanner;

    public DriverMenu(Driver driver, Scanner scanner) {
        this.driver = driver;
        this.scanner = scanner;
    }

    public void tampilkanMenuDriver() {
        boolean isDriverActive = true;

        while (isDriverActive) {
            System.out.println("=== Menu Driver ===");
            System.out.println("1. Lihat Saldo");
            System.out.println("2. Tarik Saldo");
            System.out.println("3. Logout");
            System.out.print("Pilih opsi: ");

            try {
                int driverChoice = scanner.nextInt();
                scanner.nextLine(); 

                switch (driverChoice) {
                    case 1 -> driver.melihatSaldo(); 
                    case 2 -> tarikSaldo(); 
                    case 3 -> {
                        System.out.println("Logout berhasil.");
                        isDriverActive = false;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka.");
                scanner.nextLine(); 
            }
        }
    }

    private void tarikSaldo() {
        System.out.print("Masukkan jumlah saldo yang ingin ditarik: ");
        try {
            int jumlahTarik = scanner.nextInt();
            scanner.nextLine(); 

            if (jumlahTarik <= 0) {
                throw new NegativeValueException("Jumlah penarikan harus lebih dari 0.");
            }
            if (jumlahTarik > driver.getSaldo()) {
                throw new IllegalArgumentException("Saldo tidak mencukupi untuk penarikan.");
            }

            driver.setSaldo(driver.getSaldo() - jumlahTarik);
            System.out.println("Penarikan berhasil. Saldo Anda sekarang: Rp " + driver.getSaldo());
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka.");
            scanner.nextLine(); 
        } catch (NegativeValueException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
