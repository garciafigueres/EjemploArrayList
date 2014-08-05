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
			objEjec.ingresar(objEjec.introId(), 100);
			objEjec.mostrarMenu();
		case "10":
			System.out.println(objEjec.numTotalCuentas());
			objEjec.mostrarMenu();
			break;
		case "X":
			System.out.println("Adios.");
			System.exit(0);
			break;
		default:
			System.out.println("Error: opcion no reconocida.\n");
			objEjec.mostrarMenu();
		}
	}

	//Este método crea una nueva cuenta y la añade al AL
	public void crearCuenta(){
		Cuenta cnt = new Cuenta("Oscar Garcia", 1, 100);
		listaCuentas.add(cnt);
		System.out.println("Cuenta " + cnt.id + " creada correctamente.\n");
	}

	// Este método devuelve el numero de cuentas que existen actualmente en el banco
	public String numTotalCuentas() {
		String totalCuentas = "Actualmente el banco administra " + listaCuentas.size() + " cuentas.\n";
		return totalCuentas;
	}

	// Este método permite ingresar un importe dado en una cuenta determinada
	public void ingresar(int idCuenta, float importe){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while(iteradorCuentas.hasNext()){
			// Cuando coincida la id, realizamos la operación solicitada.
			if(iteradorCuentas.next().getId()==idCuenta){
				coincidencias++;
				System.out.println("Se han ingresado " + importe + " euros en la cuenta " + idCuenta +".\n");
			}
		}
		
		if (coincidencias==0) {
			System.out.println("No existe ninguna cuenta con la clave " + idCuenta +".\n");
		}
	}
	
	// Método para recibir por teclado una id de cuenta
	public int introId(){
		Banco objIntroId = new Banco();
		String id=null;
		
		while (!objIntroId.esEntero(id)){
			System.out.print("Introduzca id de cuenta: ");
			id = sc.next();
			if (!objIntroId.esEntero(id)){
				System.out.println(id + " no es entero. Pruebe de nuevo.\n");
			}
		}
		return Integer.parseInt(id);
	}
	
	// Método para comprobar que una determinada cadena es un entero
	public boolean esEntero(String cadena){
		try{
			Integer.parseInt(cadena);
			return true;
		}catch (Exception e){
			//System.out.print(" " + cadena + " no es entero.");
			return false;
		}
	}
}
