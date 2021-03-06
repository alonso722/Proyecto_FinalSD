import java.net.*;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//NewMultiServerThread de la sucursal1

public class NMServerT_S2 extends Thread {// UA server que se conecta al puerto 12342
    private Socket socket = null;// declaramos el socket como nulo

    String maquinaX = "localhost";// String de la maquina virtual
    int puertoX = 12345;// puerto 12345 (al que le vamos a responder), en caso de que nos pida
                        // conectarnos a otro server con la palabra "connecting"

    PrintWriter escritorX = null;
    String DatosEnviadosX = null;
    BufferedReader entradaX = null;
    Socket clienteX = null;

    public NMServerT_S2(Socket socket) {// constructor de nuestro NewMultiServerThread
        super("NMServerT_S2");
        this.socket = socket;
        ServerMultiClient.NoClients++;// numero de clientes aumenta al conectarnos
        System.out.println("After Init");
    }

    public void run() {

        try {
            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String lineOut,lineIn="SUCURSAL001-CUENTA";

            while ((lineIn = entrada.readLine()) != null) {// mientras enviemos algun mensaje
                System.out.println("Received: " + lineIn);// imprimimos lo que recibamos
                escritor.flush();// limpiamos la memoria

                if (lineIn.equals("FIN")) {// si recibimos la palabra fin, rompemos el bucle y disminumos el numero de
                                           // clientes
                    ServerMultiClient.NoClients--;
                    break;
                }
                if (lineIn.equals("\"FECHA\"")) {// si nos piden la fecha
                    String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());// obtenemos
                                                                                                                        // la
                                                                                                                        // fecha
                    escritor.println("Fecha: " + fecha);// la imprimimos en consola
                    escritor.flush();// limpiamos el buffer

                } else if (lineIn.equals("\"CUANTOS\"")) {// si nos piden el num. de clientes que estan conectados
                    escritor.println("NC: " + ServerMultiClient.NoClients);// imprime el numeron de clientes que se
                                                                           // conecto al
                                                                           // servidor
                    escritor.flush();// limpiamos el buffer
                } else if (lineIn.equals("\"CRYPT\"")) {// si pedimos que nos encripten los datos
                    escritor.println("ENCRIPTANDO DATOS DE MANERA EXITOSA");// imprime el numeron de clientes que se
                                                                            // conecto al
                                                                            // servidor
                    escritor.flush();// encriptamos los datos (segun xD)
                } else if (lineIn.equals("SUCURSAL001")) {// si pedimos que nos encripten los datos
                    escritor.println("Bienvendoa la sucursal 1 PARTE 2");
                    escritor.flush();

                } else if (lineIn.equals("SUCURSAL001-CUENTA")) {// si pedimos que nos encripten los datos
                    escritor.println(
                            "XTA00001-XTA00002-XTA00003-XTA00004-XTA00005-XTA00006-XTA00007-XTA00008-XTA00009-XTA00010-XTA00011-XTA00012-XTA00013-XTA00014");
                    escritor.flush();

                } else if (lineIn.equals("SUCURSAL001-CURP")) {// si pedimos que nos encripten los datos
                    escritor.println(
                            "CURP010101-CURP010102-CURP010103-CURP010104-CURP010105-CURP010106-CURP010107-CURP010108-CURP010109-CURP010110-CURP010111-CURP010112-CURP010113-CURP010114");
                    escritor.flush();

                } else if (lineIn.equals("SUCURSAL001-NOMBRE")) {// si pedimos que nos encripten los datos
                    escritor.println(
                            "ANDREA-DAVID-ROLAS-GERRY-MIKE-JON--ALBERTO-IVETTE-JOSE-LILIA-ANGEL-MANUEL-SILVIA-IVETTE");
                    escritor.flush();

                } else if (lineIn.equals("SUCURSAL001-APELLIDO")) {// si pedimos que nos encripten los datos
                    escritor.println(
                            "H-H-H-H-H-H-H-H-H-H-H-H-H-H");
                    escritor.flush();

                } else if (lineIn.equals("SUCURSAL001-SALDO")) {// si pedimos que nos encripten los datos
                    escritor.println(
                            "100-101-102-103-104-105-106-107-108-109-110-111-112-113");
                    escritor.flush();

                } else if (lineIn.equals("SUCURSAL001-DATOS")) {// si pedimos que nos encripten los datos
                    escritor.println(
                            "Usiel Alonso Olivares-Lopez Lopez Arturo-Gerardo Islas Santana-Maria Flores Juarez-Rembrant Jesus Perez-Thomas Rodriguez Venegas-Juan Perez Cruz-Luis Diaz Ordaz-Luis Nava -Yarib Espinoza-Maria Angeles Cruz-Paola Nava Pi??a-Cesar Cruz Torres-Julio Vargas-Jesus Trujillo-Pedro Hernan-Juan de la Cruz-Cristo Espinoza-Miguel Cruz-Javet Espinoza");
                    escritor.flush();

                } else if (lineIn.equals("connect")) {// pedimos que nos conecten a otro servidor
                    System.out.println("Connecting to another server");

                    try {
                        clienteX = new Socket(maquinaX, puertoX);// nos vamos a coectar al puerto 12345
                        System.out.println("Connected to another server");
                    } catch (Exception e) {
                        System.out.println("Fallo : " + e.toString());// en caso de que falle la coneccion
                        System.exit(0);
                    }

                    System.out.println("Trying to send data to another server");

                    try {
                        escritorX = new PrintWriter(clienteX.getOutputStream(), true);
                        entradaX = new BufferedReader(new InputStreamReader(clienteX.getInputStream()));

                    } catch (Exception e) {
                        System.out.println("Fallo : " + e.toString());
                        clienteX.close();
                        System.exit(0);
                    }

                    String lineX;
                    String DatosEnviadosX;

                    System.out.println("Sending connecting to another server");
                    DatosEnviadosX = "HOLA";

                    escritorX.println(DatosEnviadosX);
                    lineX = entradaX.readLine();

                    System.out.println("Server12342: " + lineX);
                    DatosEnviadosX = "FIN";
                    escritorX.println(DatosEnviadosX);
                    System.out.println("Closing another server");
                    clienteX.close();
                    escritorX.close();
                    entradaX.close();
                    escritor.println("Response from Server 12342... " + lineX);
                    escritor.flush();
                } else {
                    escritor.println("Echo... " + lineIn);
                    escritor.flush();
                }
            } // termina el while

            try {// tratamos de cerrar el socket
                entrada.close();
                escritor.close();
                socket.close();
            } catch (Exception e) {
                System.out.println("Error : " + e.toString());
                socket.close();
                System.exit(0);
            }
        } // fin del try principal
        catch (IOException e) {
            System.out.println("Error---");
            e.printStackTrace();
        }
    }
}
