public class Tablero {
	static char tablero[] = new char[9];
	
	public Tablero() {
	}
	/**
	 * Resetea el tablero con los numeros de casilla
	 */
	static void numerarTablero() {
		for (int i = 0; i < 9; i++) {
			tablero[i] = Integer.toString(i + 1).charAt(0);
		}
	}

	/**
	 * Vacía el tablero
	 */
	static void vaciarTablero() {
		for (int i = 0; i < 9; i++) {
			tablero[i] = ' ';
		}
	}

	/**
	 * Muestra el estado del tablero
	 */
	public static void mostrarTablero() {

		for (int i = 0; i < 7; i++) {
			switch (i) {
			case 0:
				System.out.println("- - - - - - -");
				break;
			case 2:
				System.out.println("- - - - - - -");
				break;
			case 4:
				System.out.println("- - - - - - -");
				break;
			case 6:
				System.out.println("- - - - - - -");
				break;
			case 1:
				System.out.println(" | " + tablero[0] + " | " + tablero[1] + " | " + tablero[2] + " |");
				break;
			case 3:
				System.out.println(" | " + tablero[3] + " | " + tablero[4] + " | " + tablero[5] + " |");
				break;
			case 5:
				System.out.println(" | " + tablero[6] + " | " + tablero[7] + " | " + tablero[8] + " |");
				break;

			}
		}
	}

}