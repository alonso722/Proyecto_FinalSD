import java.net.*;
import java.io.*;

//SERVIDOR 12343

public class NewServerS3 {
    static int NoClients = 0;// numero de clientes conectados al servidor

    public static void main(String[] argumentos) throws IOException {
        ServerSocket socketServidor = null;
        Socket socketCliente = null;

        try {// nos tratamos de conectar al puerto 12343
            socketServidor = new ServerSocket(12343);

        } catch (Exception e) {// si no podemos, imprimimos el error
            System.out.println("Error : " + e.toString());
            System.exit(0);// terminamos de ejecutar el programa

        }

        System.out.println("Server 12343 started... (Socket TCP)");
        int enproceso = 1;

        while (enproceso == 1) {
            try {
                socketCliente = socketServidor.accept();
                NMServerT_S3 controlThread = new NMServerT_S3(socketCliente);
                // NewMultiServerThreadS1 controlThread = new
                // NewMultiServerThreadS1(socketCliente);
                controlThread.start();

            } catch (Exception e) {
                System.out.println("Error : " + e.toString());
                socketServidor.close();
                System.exit(0);

            }
        }
        System.out.println("Finalizando Servidor 12341...");

    }
}
