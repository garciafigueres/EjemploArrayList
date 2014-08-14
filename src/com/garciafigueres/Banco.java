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
		System.out.println("5. Ver detalles completos de una cuenta");
		System.out.println("6. Listar todas las cuentas");
		System.out.println("7. Numero total de cuentas");
		System.out.println("8. Calcular saldo total de todas las cuentas");
		System.out.println("9. Eliminar una cuenta");
		System.out.println("----------------------------------------------");
		System.out.println("X. Salir");
		System.out.print("\n -> ");

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
			objEjec.consultarSaldo(objEjec.introId());
			objEjec.mostrarMenu();
			break;
		case "5":
			objEjec.detallarCuenta(objEjec.introId());
			objEjec.mostrarMenu();
			break;
		case "6":
			objEjec.listarCuentas();
			objEjec.mostrarMenu();
			break;
		case "7":
			System.out.println(objEjec.numTotalCuentas());
			objEjec.mostrarMenu();
			break;
		case "8":
			System.out.println(objEjec.saldoTotal());;
			objEjec.mostrarMenu();
			break;
		case "9":
			objEjec.eliminarCuenta(objEjec.introId());
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

	//Este método crea una nueva cuenta y la añade al AL
	public void crearCuenta(){
		Banco objCrearCuenta = new Banco();
		Cuenta cnt = new Cuenta(objCrearCuenta.introTitular(),objCrearCuenta.introId(),objCrearCuenta.introImporte());
		listaCuentas.add(cnt);
		System.out.println("Cuenta " + cnt.id + " creada correctamente.\n");
	}

	// Este método permite ingresar un importe dado en una cuenta determinada
	public void ingresar(int idCuenta, float importe){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while(iteradorCuentas.hasNext()){
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Cuando coincida la id, realizamos la operación solicitada.
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

	// Este método permite reintegrar un importe dado de una cuenta determinada
	public void reintegrar(int idCuenta, float importe){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while(iteradorCuentas.hasNext()){
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Cuando coincida la id, realizamos la operación solicitada.
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

	// Este método permite obtener el saldo de una cuenta determinada
	public void consultarSaldo(int idCuenta){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while(iteradorCuentas.hasNext()){
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Cuando coincida la id, realizamos la operación solicitada.
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

	// Este método muestra todos los detalles de una cuenta determinada
	public void detallarCuenta(int idCuenta){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while (iteradorCuentas.hasNext()){
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Cuando coincida la id, realizamos la operación solicitada
			if(cnt.getId()==idCuenta){
				coincidencias++;
				System.out.println(" Numero de cuenta: " + cnt.getId());
				System.out.println(" Titular: " + cnt.getTitular());
				System.out.println(" Saldo actual: " + cnt.getSaldo() + "\n");
			}
		}

		if (coincidencias==0) {
			System.out.println("No existe ninguna cuenta con la clave " + idCuenta +".\n");
		}
	}

	// Este método lista los detalles de todas las cuentas del Banco
	public void listarCuentas(){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while (iteradorCuentas.hasNext()){
			coincidencias++;
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Mostramos por pantalla los detalles de la cuenta actual
			System.out.println(" Numero de cuenta: " + cnt.getId());
			System.out.println(" Titular: " + cnt.getTitular());
			System.out.println(" Saldo actual: " + cnt.getSaldo() + "\n");
		}
		if (coincidencias==0) {
			System.out.println("No existen cuentas en este banco.\n");
		}
	}

	// Este método calcula el salto total de todas las cuentas que administra el banco
	public String saldoTotal(){
		int coincidencias = 0;
		float saldoTotal = 0;
		// Recorremos la lista con el iterador.
		while (iteradorCuentas.hasNext()){
			coincidencias++;
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Cuando coincida la id, realizamos la operación solicitada.
			float saldoCuenta = cnt.getSaldo();
			saldoTotal += saldoCuenta;
		}
		
		if (coincidencias==0) {
			return "No existen cuentas en este banco.\n";
		}else{
			return "Este banco administra un total de " + coincidencias + " cuentas con un saldo total de " + saldoTotal + " euros.\n";
		}
	}

	//Este método elimina una cuenta determinada
	public void eliminarCuenta(int idCuenta){
		int coincidencias = 0;
		// Recorremos la lista con el iterador.
		while(iteradorCuentas.hasNext()){
			// Creamos un objeto Cuenta que almacena el elemento actual del iterador
			Cuenta cnt = iteradorCuentas.next();
			// Cuando coincida la id, realizamos la operación solicitada.
			if(cnt.getId()==idCuenta){
				coincidencias++;
				listaCuentas.remove(cnt);
				System.out.println("La cuenta " + idCuenta+ " se ha eliminado correctamente.\n");
				break;
			}
		}

		if (coincidencias==0) {
			System.out.println("No existe ninguna cuenta con la clave " + idCuenta +".\n");
		}
	}

	// Este método devuelve el numero de cuentas que existen actualmente en el banco
	public String numTotalCuentas() {
		String totalCuentas = "Actualmente el banco administra " + listaCuentas.size() + " cuentas.\n";
		return totalCuentas;
	}

	// Método para recibir por teclado una id de cuenta
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

	// Método para recibir por teclado un titular de cuenta
	public String introTitular(){
		String titular="";

		while (titular==""){
			System.out.print("Introduzca nombre del titular: ");
			titular = sc.next();
		}
		return titular;
	}

	// Método para recibir por teclado un importe
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

	// Método para comprobar que una determinada cadena es un entero
	public boolean esEntero(String cadena){
		try{
			Integer.parseInt(cadena);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	// Método para comprobar que una determinada cadena es un flotante
	public boolean esFlotante(String cadena){
		try{
			Float.parseFloat(cadena);
			return true;
		}catch (Exception e){
			return false;
		}
	}
}
