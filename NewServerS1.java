import java.net.*;
import java.io.*;

//SERVIDOR 12341

public class NewServerS1 {
    static int NoClients = 0;// numero de clientes conectados al servidor

    public static void main(String[] argumentos) throws IOException {
        ServerSocket socketServidor = null;
        Socket socketCliente = null;

        try {// nos tratamos de conectar al puerto 12341
            socketServidor = new ServerSocket(12341);

        } catch (Exception e) {// si no podemos, imprimimos el error
            System.out.println("Error : " + e.toString());
            System.exit(0);// terminamos de ejecutar el programa

        }

        System.out.println("Server 12341 started... (Socket TCP)");
        int enproceso = 1;

        while (enproceso == 1) {
            try {
                socketCliente = socketServidor.accept();
                NMServerT_S1 controlThread = new NMServerT_S1(socketCliente);
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
       
        //          int enproceso2 = 1;

        // while (enproceso2 == 1) {
        //     try {
        //         socketCliente = socketServidor.accept();
        //         NMServerT_S2 controlThread = new NMServerT_S2(socketCliente);
        //         controlThread.start();

        //     } catch (Exception e) {
        //         System.out.println("Error : " + e.toString());
        //         socketServidor.close();
        //         System.exit(0);

        //     }
        // }
        // System.out.println("Servidor enviando archivos 2...");


         
    }
}
