import java.net.*;//LIBRERIAS
import java.io.*;

public class ServerMultiClient {// este server es nuestro server de siempre y trabaja en el puerto 12345
	static int NoClients = 0;// numero de clientes conectados al servidor

	public static void main(String[] argumentos) throws IOException {
		ServerSocket socketServidor = null;// Socket del servidor
		Socket socketCliente = null; // Socket del cliente

		try {
			socketServidor = new ServerSocket(12345);// tratamos de conectar nuestro servidor al puerto 12345
		} catch (Exception e) {// si no podemos conectarnos, imprimimos cual es el error
			System.out.println("Error : " + e.toString());
			System.exit(0);// terminamos de ejecutar el programa
		}

		System.out.println("Server started in port 12345... (Socket TCP)");
		int enproceso = 1;

		while (enproceso == 1) {
			try {// tratamos de conectar nuestro servidor con nuestro cliente
				socketCliente = socketServidor.accept();
				MultiServerThread controlThread = new MultiServerThread(socketCliente);// le otorgamos el control de
																						// nuestro thread a nuestro
																						// MultiServerThread
				controlThread.start();// empezamos el proceso de nuestro MultiServerThread

			} catch (Exception e) {// en caso de no poder conectar nuestros sewrvidores
				System.out.println("Error : " + e.toString());// imprimimos nuestro error
				socketServidor.close();// cerramos nuestro socket
				System.exit(0);// terminamos el programa
			}
		}
		System.out.println("Finalizando Servidor...");

	}
}
