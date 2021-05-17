

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TresEnRaya {

	private static int victorias = 0, derrotas = 0;
	private static final char FICHAJ1 = 'X', FICHAJ2 = 'O';
	private static Jugador jugador1, jugador2;
	private static Archivo archivo;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		archivo = new Archivo();
		do {
			System.out.println("MENU");
			System.out.println("1. Jugar");
			System.out.println("2. Ver estadísticas");
			System.out.println("0. Salir");
			try {
				System.out.print("Seleccionar opcion (1-2) o 0 para salir: ");
				opcion = sc.nextInt();
			} catch (InputMismatchException e) {
				opcion = 100;
			}
			sc.nextLine();
			switch (opcion) {
			case 1:
				System.out.print("Introduce nombre jugador 1: ");
				jugador1 = new Jugador(sc.nextLine());
				System.out.print("Introduce nombre jugador 2: ");
				jugador2 = new Jugador(sc.nextLine());
				System.out.println("\n" + jugador1.getNombre() + " juega con: " + FICHAJ1);
				System.out.println(jugador2.getNombre() + " juega con: " + FICHAJ2 + "\n");
				jugar(sc);
				break;
			case 2:
				Archivo.leerArchivo();
				System.out.println("Victorias: " + getVictorias());
				System.out.println("Derrotas: " + getDerrotas() + "\n");
				break;
			case 0:
				System.out.println("Fin de programa \n");
				break;
			default:
				System.out.println("Opción no válida \n");
				break;
			}
		} while (opcion != 0);
		sc.close();
	}

	private static void jugar(Scanner input) {
		Tablero.numerarTablero();
		Tablero.mostrarTablero();
		Tablero.vaciarTablero();
		switch (partida(input)) {
		case FICHAJ2:
			System.out.println("VICTORIA!! \n");
			victorias++;
			break;
		case FICHAJ1:
			System.out.println("DERROTA!! \n");
			derrotas++;
			break;
		default:
			System.out.println("EMPATE!! \n");
			break;
		}
	}

	/**
	 * Bucle de partida. Devuelve char: Victoria = charUsuario Derrota = charMaquina
	 * Empate = ''
	 */
	private static char partida(Scanner input) {

		char ganador = ' ';
		int fichas = 0, casilla = 0;
		boolean turno = false;
		System.out.println("Pulse enter para comenzar la partida...");
		input.nextLine();
		int resultadoJ1 = (int) (Math.random()*6 + 1);
		int resultadoJ2 = (int) (Math.random()*6 + 1);
		System.out.print(jugador1.getNombre() + " pulsa enter para tirar el dado...");
		input.nextLine();
		System.out.println("Resultado: " + resultadoJ1);
		System.out.print(jugador2.getNombre() + " pulsa enter para tirar el dado...");
		input.nextLine();
		System.out.println("Resultado: " + resultadoJ2);
		try {
			Archivo.generarArchivo(jugador1.getNombre(), jugador2.getNombre());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// turno = TRUE comienza jugador 1 turno = FALSE comienza jugador 2
		if (resultadoJ1 > resultadoJ2) {
			turno = true;
		}
		// Bucle de jugadas
		do {
			Tablero.mostrarTablero();
			if (fichas < 9) {
				if (turno) {
					do {
						try {
							System.out.println("Turno de " + jugador1.getNombre() + ". Elige casilla (1-9)? ");
							casilla = input.nextInt();
							if (Tablero.tablero[casilla - 1] != ' ') {
								System.out.println("La casilla " + casilla + " está ocupada");
								casilla = 0;
							}
						} catch (ArrayIndexOutOfBoundsException | InputMismatchException ex) {
							System.out.println("ERROR. Casilla o valor incorrecto");
						}
					} while (casilla < 1 || casilla > 9);
					input.nextLine();
					Tablero.tablero[casilla - 1] = FICHAJ1; // se coloca su ficha
				} else {
					if (fichas < 9) {
						do {
							try {
							System.out.println("Turno de " + jugador2.getNombre() + ". Elige casilla (1-9)? ");
							casilla = input.nextInt();
							if (Tablero.tablero[casilla - 1] != ' ') {
								System.out.println("La casilla " + casilla + " está ocupada");
								casilla = 0;
							}
							}catch(ArrayIndexOutOfBoundsException ex) {
								System.out.println("ERROR. Casilla no válida");
							}
						} while (casilla < 1 || casilla > 9);
						input.nextLine();
						Tablero.tablero[casilla - 1] = FICHAJ2;
						System.out.println("Hecho!");
					}
				}
			}
			turno = !turno;

			ganador = victoria();
			fichas++;
		} while (fichas < 10 && ganador == ' ');

		Tablero.mostrarTablero();
		return ganador;
	}

	private static char victoria() {
		int i = 0;
		char r = ' ';
		do {
			if (FICHAJ1 == Tablero.tablero[3 * i] && FICHAJ1 == Tablero.tablero[(3 * i) + 1]
					&& FICHAJ1 == Tablero.tablero[(3 * i) + 2]) {
				r = FICHAJ1;
			}
			if (FICHAJ1 == Tablero.tablero[i] && FICHAJ1 == Tablero.tablero[3 + i]
					&& FICHAJ1 == Tablero.tablero[6 + i]) {
				r = FICHAJ1;
			}
			if (FICHAJ1 == Tablero.tablero[0] && FICHAJ1 == Tablero.tablero[4] && FICHAJ1 == Tablero.tablero[8]) {
				r = FICHAJ1;
			}
			if (FICHAJ1 == Tablero.tablero[2] && FICHAJ1 == Tablero.tablero[4] && FICHAJ1 == Tablero.tablero[6]) {
				r = FICHAJ1;
			}
			if (FICHAJ2 == Tablero.tablero[3 * i] && FICHAJ2 == Tablero.tablero[(3 * i) + 1]
					&& FICHAJ2 == Tablero.tablero[(3 * i) + 2]) {
				r = FICHAJ2;
			}
			if (FICHAJ2 == Tablero.tablero[i] && FICHAJ2 == Tablero.tablero[3 + i]
					&& FICHAJ2 == Tablero.tablero[6 + i]) {
				r = FICHAJ2;
			}
			if (FICHAJ2 == Tablero.tablero[0] && FICHAJ2 == Tablero.tablero[4] && FICHAJ2 == Tablero.tablero[8]) {
				r = FICHAJ2;
			}
			if (FICHAJ2 == Tablero.tablero[2] && FICHAJ2 == Tablero.tablero[4] && FICHAJ2 == Tablero.tablero[6]) {
				r = FICHAJ2;
			}
			i++;
		} while (r == ' ' && i < 3);
		return r;
	}

	/**
	 * @return the victorias
	 */
	public static int getVictorias() {
		return victorias;
	}

	/**
	 * @return the derrotas
	 */
	public static int getDerrotas() {
		return derrotas;
	}

}