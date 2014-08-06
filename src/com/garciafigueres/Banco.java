/**
 * @author Luis Garcia Figueres
 * 
 * Esta clase contiene la funcionalidad basica de la aplicacion.
 */

package com.garciafigueres;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Banco {
	// El AL debe ser static para que pueda ser accedido desde cualquier punto
	public static ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
	public Iterator<Cuenta> iteradorCuentas = listaCuentas.iterator();
	public Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Banco objMain = new Banco();
		objMain.mostrarMenu();
	}

	public void mostrarMenu(){
		Banco objMenu = new Banco();
		String opcion;

		System.out.println("MENU PRINCIPAL");
		System.out.println("--------------");
		System.out.println("1. Crear cuenta");
		System.out.println("2. Ingresar en cuenta");
		System.out.println("3. Reintegrar de cuenta");
		System.out.println("4. Consultar saldo de una cuenta");
		System.out.println("10. Total de cuentas");
		System.out.println("X. Salir");

		opcion = sc.next();

		objMenu.ejecutarAccion(opcion);
	}

	public void ejecutarAccion(String accion){
		Banco objEjec = new Banco();
		accion = accion.toUpperCase();

		switch (accion) {
		case "1":
			objEjec.crearCuenta();
			objEjec.mostrarMenu();
			break;
		case "2":
			objEjec.ingresar(objEjec.introId(), objEjec.introImporte());
			objEjec.mostrarMenu();
			break;
		case "3":
			objEjec.reintegrar(objEjec.introId(), objEjec.introImporte());
			objEjec.mostrarMenu();
			break;
		case "4":
			objEjec.verSaldo(objEjec.introId());
			objEjec.mostrarMenu();
			break;
		case "10":
			System.out.println(objEjec.numTotalCuentas());
			objEjec.mostrarMenu();
			break;
		case "X":
			System.out.println("Adios.");
			sc.close();
			System.exit(0);
			break;
		default:
			System.out.println("Error: opcion no reconocida.\n");
			objEjec.mostrarMenu();
		}
	}

	//Este m�todo crea una nueva cuenta y la a�ade al AL
	public void crearCuenta(){
		Banco objCrearCuenta = new Banco();
		Cuenta cnt = new Cuenta(objCrearCuenta.introTitular(),objCrearCuenta.introId(),objCrearCuenta.introImporte());
		listaCuentas.add(cnt);
		System.out.println("Cuenta " + cnt.id + " creada correctamente.\n");
	}

	// Este m�todo devuelve el numero de cuentas que existen actualmente en el banco
	public String numTotalCuentas() {
		String totalCuentas = "Actualmente el banco administra " + listaCuentas.size() + " cuentas.\n";
		return totalCuentas;
	}

	// Este m�todo permite ingresar un importe dado en una cuenta determinada
	public void ingresar(int idCuenta, float importe){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while(iteradorCuentas.hasNext()){
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Cuando coincida la id, realizamos la operaci�n solicitada.
			if(cnt.getId()==idCuenta){
				coincidencias++;
				float saldoActual = cnt.getSaldo();
				cnt.setSaldo(saldoActual+importe);
				System.out.println("Se han ingresado " + importe + " euros en la cuenta " + idCuenta +".\n");
			}
		}

		if (coincidencias==0) {
			System.out.println("No existe ninguna cuenta con la clave " + idCuenta +".\n");
		}
	}

	// Este m�todo permite reintegrar un importe dado de una cuenta determinada
	public void reintegrar(int idCuenta, float importe){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while(iteradorCuentas.hasNext()){
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Cuando coincida la id, realizamos la operaci�n solicitada.
			if(cnt.getId()==idCuenta){
				coincidencias++;
				float saldoActual = cnt.getSaldo();
				cnt.setSaldo(saldoActual-importe);
				System.out.println("Se han retirado " + importe + " euros de la cuenta " + idCuenta +".\n");
			}
		}

		if (coincidencias==0) {
			System.out.println("No existe ninguna cuenta con la clave " + idCuenta +".\n");
		}
	}

	// Este m�todo permite obtener el saldo de una cuenta determinada
	public void verSaldo(int idCuenta){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while(iteradorCuentas.hasNext()){
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Cuando coincida la id, realizamos la operaci�n solicitada.
			if(cnt.getId()==idCuenta){
				coincidencias++;
				float saldoActual = cnt.getSaldo();
				System.out.println("Actualmente, la cuenta " + idCuenta +" tiene un saldo de " + saldoActual + " euros.\n");
			}
		}

		if (coincidencias==0) {
			System.out.println("No existe ninguna cuenta con la clave " + idCuenta +".\n");
		}
	}

	// M�todo para recibir por teclado una id de cuenta
	public int introId(){
		Banco objIntroId = new Banco();
		String id=null;

		while (!objIntroId.esEntero(id)){
			System.out.print("Introduzca id de cuenta: ");
			id = sc.next();
			if (!objIntroId.esEntero(id)){
				System.out.println(id + " no es un valor valido. Pruebe de nuevo.\n");
			}
		}
		return Integer.parseInt(id);
	}

	// M�todo para recibir por teclado un titular de cuenta
	public String introTitular(){
		String titular="";

		while (titular==""){
			System.out.print("Introduzca nombre del titular: ");
			titular = sc.next();
		}
		return titular;
	}

	// M�todo para recibir por teclado un importe
	public float introImporte(){
		Banco objIntroImporte = new Banco();
		String importe=null;

		while (!objIntroImporte.esFlotante(importe)){
			System.out.print("Introduzca importe: ");
			importe = sc.next();
			if (!objIntroImporte.esFlotante(importe)){
				System.out.println(importe + " no es un valor valido. Pruebe de nuevo.\n");
			}
		}
		return Float.parseFloat(importe);
	}

	// M�todo para comprobar que una determinada cadena es un entero
	public boolean esEntero(String cadena){
		try{
			Integer.parseInt(cadena);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	// M�todo para comprobar que una determinada cadena es un flotante
	public boolean esFlotante(String cadena){
		try{
			Float.parseFloat(cadena);
			return true;
		}catch (Exception e){
			return false;
		}
	}
}
