package com.banco.model;

public class Cuenta {
    private String numeroCuenta;
    private double saldoDisponible;
    private String titularCuenta;

    public Cuenta(String numeroCuenta, double saldoDisponible, String titularCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldoDisponible = saldoDisponible;
        this.titularCuenta = titularCuenta;
    }


    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public String getTitularCuenta() {
        return titularCuenta;
    }

    public void setTitularCuenta(String titularCuenta) {
        this.titularCuenta = titularCuenta;
    }
}
