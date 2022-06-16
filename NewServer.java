import java.net.*;
import java.io.*;

//SERVIDOR 13777

public class NewServer {
	static int NoClients = 0;// numero de clientes conectados al servidor

	public static void main(String[] argumentos) throws IOException {
		ServerSocket socketServidor = null;
		Socket socketCliente = null;

		try {// nos tratamos de conectar al puerto 137777
			socketServidor = new ServerSocket(13777);

		} catch (Exception e) {// si no podemos, imprimimos el error
			System.out.println("Error : " + e.toString());
			System.exit(0);// terminamos de ejecutar el programa

		}

		System.out.println("Server 13777 started... (Socket TCP)");
		int enproceso = 1;

		while (enproceso == 1) {
			try {
				socketCliente = socketServidor.accept();
				NewMultiServerThread controlThread = new NewMultiServerThread(socketCliente);
				controlThread.start();

			} catch (Exception e) {
				System.out.println("Error : " + e.toString());
				socketServidor.close();
				System.exit(0);

			}
		}
		System.out.println("Finalizando Servidor 13777...");

	}
}
