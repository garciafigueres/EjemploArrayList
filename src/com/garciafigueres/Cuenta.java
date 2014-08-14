/**
 * @author Luis Garcia Figueres
 * 
 * Esta clase contiene todos los atributos y metodos para los objetos tipo Cuenta
 * 
 * Una cuenta debe contener asociados un titular, una id y un saldo,
 * además de los getters y setters correspondientes. 
 */
package com.garciafigueres;

public class Cuenta {
	public String titular;
	public int id;
	public float saldo;
	
	public Cuenta(String titularCuenta, int idCuenta, float saldoCuenta) {
		this.titular = titularCuenta;
		this.id = idCuenta;
		this.saldo = saldoCuenta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
}
