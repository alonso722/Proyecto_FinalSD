import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientFullDuplex {

	public static void main(String[] argumentos) throws IOException {
		Socket cliente = null;
		PrintWriter escritor = null;
		String DatosEnviados = null;
		BufferedReader entrada = null;
		Scanner datos = new Scanner(System.in);// para leer los datos
		String maquina;
		int puerto;
		BufferedReader DatosTeclado = new BufferedReader(new InputStreamReader(System.in));

		Socket cliente2 = null;
		PrintWriter escritor2 = null;
		String DatosEnviados2 = null;
		BufferedReader entrada2 = null;
		Scanner datos2 = new Scanner(System.in);// para leer los datos
		int puerto2=12342;
		BufferedReader DatosTeclado2 = new BufferedReader(new InputStreamReader(System.in));
		///////////////////////////////////////////
		Socket cliente3 = null;
		PrintWriter escritor3 = null;
		String DatosEnviados3 = null;
		BufferedReader entrada3 = null;
		Scanner datos3 = new Scanner(System.in);// para leer los datos
		int puerto3=12343;
		BufferedReader DatosTeclado3 = new BufferedReader(new InputStreamReader(System.in));

		if (argumentos.length != 2) {
			maquina = "localhost";
			puerto = 12345;
			System.out.println("Ingrese puerto: ");
			puerto = datos.nextInt();
			System.out.println("Establecidos valores por defecto:\nEQUIPO =" + maquina + " \nPORT =" + puerto);

		} else {
			maquina = argumentos[0];
			Integer pasarela = new Integer(argumentos[1]);
			puerto = pasarela.parseInt(pasarela.toString());
			System.out.println("Conectado a " + maquina + " en puerto: " + puerto);

		}
		try {
			cliente = new Socket(maquina, puerto);
			cliente2 = new Socket(maquina, puerto2);
			cliente3 = new Socket(maquina, puerto3);
		} catch (Exception e) {
			System.out.println("Fallo : " + e.toString());
			System.exit(0);

		}
		try {
			System.out.println("Escritor 1 , Escritor 2");
			escritor = new PrintWriter(cliente.getOutputStream(), true);
			escritor2 = new PrintWriter(cliente2.getOutputStream(), true);
			escritor3 = new PrintWriter(cliente3.getOutputStream(), true);
			System.out.println("Entrada 1 , Entrada 2");
			entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			entrada2 = new BufferedReader(new InputStreamReader(cliente2.getInputStream()));
			entrada3 = new BufferedReader(new InputStreamReader(cliente3.getInputStream()));
		} catch (Exception e) {
			System.out.println("Fallo : " + e.toString());
			cliente.close();
			cliente2.close();
			cliente3.close();
			System.exit(0);

		}
		String line,line2,line3;

		System.out.println("Conectado con el Servidor. Listo para enviar datos...");

		do {
			DatosEnviados = DatosTeclado.readLine();
			escritor.println(DatosEnviados);
			escritor2.println(DatosEnviados);
			escritor3.println(DatosEnviados);
			line = entrada.readLine();
			System.out.println(line);//Hilo de server thread
			//DatosEnviados2 = DatosTeclado2.readLine();
			line2 = entrada2.readLine();
			System.out.println(line2);//Server S2
			line3 = entrada3.readLine();
			System.out.println(line3);//Server S3
			
			Servicios djs=new Servicios(line,line2,line3);
			djs.menu();
		} while (!DatosEnviados.equals("FIN"));

		System.out.println("Finalizada conexion con el servidor");
		try {
			escritor.close();
			escritor2.close();
			escritor3.close();

		} catch (Exception e) {
		}
	}
}
