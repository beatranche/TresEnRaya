
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Archivo {
	public Archivo() {
		
	}
	public static void generarArchivo(String jugador1, String jugador2) throws IOException {
		String fecha = new SimpleDateFormat("ddMMyyy-HHmm").format(new Date());  
		String fileName = fecha.concat("archivo.txt");
		try {
		FileWriter fw = new FileWriter("archivo.txt", true);
		fw.write(fecha);
		fw.write(fileName);
		fw.write("Van a jugar" + jugador1 + "y" + jugador2 + "\n");
		fw.write(jugador1 + "ha tirado el dado y ha sacado ");
		
		
		fw.close();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void leerArchivo() {
		String contenido = null;
		int lectura;
		try {
			FileReader fr = new FileReader("archivo.txt");
			lectura =fr.read();
			while(lectura != -1) {
				contenido += (char) lectura;
				lectura = fr.read();
			}
		} catch (FileNotFoundException e) {
			Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, e);;
		}
		System.out.println(contenido);
	}
}