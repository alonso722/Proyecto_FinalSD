import java.net.*;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MultiServerThread extends Thread {
  Scanner datos = new Scanner(System.in);// para leer los datos
  private Socket socket = null;

  String maquinaX = "localhost";// DIRECCION IP
  int puertoX = 13777; // Puerto 13777 en caso de que nos pidan conectarnos a otro servidor
  int puertoS = 0;
  String DatosEnviadosX;

  PrintWriter escritorX = null;
  BufferedReader entradaX = null;
  Socket clienteX = null;

  Servicios s = new Servicios("0", "0", 0, 0);// Variable que nos va a regresar los valores del dicdist

  public MultiServerThread(Socket socket) {// constructor del Tred
    super("MultiServerThread");
    this.socket = socket;
    ServerMultiClient.NoClients++;// Para ver cuantos clientes se han conectado
    System.out.println("After Init");
  }

  public void run() {

    try {
      PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);// manda la informacion al cliente
      BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String lineIn, lineOut, SucursalIn;

      while ((lineIn = entrada.readLine()) != null) {
        SucursalIn = lineIn;

        System.out.println("Received: " + lineIn);// imprimimos lo que recibamos
        escritor.flush();// limpiamos el buffer

        if (lineIn.equals("FIN")) {// si la palabra es fin salimos del servidor
          ServerMultiClient.NoClients--;// Disminuye cuando un cliente se desconecta
          break;

        } else if (lineIn.equals("CUANTOS")) {// imprime el numeron de clientes que se conecto al servidor
          escritor.println("NC: " + ServerMultiClient.NoClients);
          escritor.flush();

        } else if (lineIn.equals("DATOS")) {// imprime el numeron de clientes que se conecto al servidor
          escritor.println("HOLA");
          escritor.flush();

        } else if (lineIn.equals("SUCURSAL")) {// imprime LAS SUCURSALES
          escritor.println("SUCURSAL001-SUCURSAL002-SUCURSAL003-SUCURSAL004 ");
          escritor.flush();
          DatosEnviadosX = "Sucursales";

        } else if (lineIn.equals("SUCURSAL001") || lineIn.equals("SUCURSAL001-CUENTA")
            || lineIn.equals("SUCURSAL001-CURP") || lineIn.equals("SUCURSAL001-NOMBRE")
            || lineIn.equals("SUCURSAL001-APELLIDO") || lineIn.equals("SUCURSAL001-SALDO")
            || SucursalIn.equals("SUCURSAL001-DATOS")
            || lineIn.equals("SUCURSAL002") || lineIn.equals("SUCURSAL002-CUENTA")
            || lineIn.equals("SUCURSAL002-CURP") || lineIn.equals("SUCURSAL002-NOMBRE")
            || lineIn.equals("SUCURSAL002-APELLIDO") || lineIn.equals("SUCURSAL002-SALDO")
            || SucursalIn.equals("SUCURSAL002-DATOS")
            || lineIn.equals("SUCURSAL003") || lineIn.equals("SUCURSAL003-CUENTA")
            || lineIn.equals("SUCURSAL003-CURP") || lineIn.equals("SUCURSAL003-NOMBRE")
            || lineIn.equals("SUCURSAL003-APELLIDO") || lineIn.equals("SUCURSAL003-SALDO")
            || SucursalIn.equals("SUCURSAL003-DATOS")
            || lineIn.equals("SUCURSAL004") || lineIn.equals("SUCURSAL004-CUENTA")
            || lineIn.equals("SUCURSAL004-CURP") || lineIn.equals("SUCURSAL004-NOMBRE")
            || lineIn.equals("SUCURSAL004-APELLIDO") || lineIn.equals("SUCURSAL004-SALDO")
            || SucursalIn.equals("SUCURSAL004-DATOS")) {// si pedimos conectarnos a otro servidor

          if (SucursalIn.equals("SUCURSAL001") || SucursalIn.equals("SUCURSAL001-CUENTA")
              || SucursalIn.equals("SUCURSAL001-CURP") || SucursalIn.equals("SUCURSAL001-NOMBRE")
              || SucursalIn.equals("SUCURSAL001-APELLIDO") || SucursalIn.equals("SUCURSAL001-SALDO")
              || SucursalIn.equals("SUCURSAL001-DATOS")) {

            puertoS = 12341;
            puertoX = puertoS; // nos conectamos al puerto 12341
            System.out.println("Connected to another server" + SucursalIn);
            DatosEnviadosX = SucursalIn;

          }

          if (SucursalIn.equals("SUCURSAL002") || SucursalIn.equals("SUCURSAL002-CUENTA")
              || SucursalIn.equals("SUCURSAL002-CURP") || SucursalIn.equals("SUCURSAL002-NOMBRE")
              || SucursalIn.equals("SUCURSAL002-APELLIDO") || SucursalIn.equals("SUCURSAL002-SALDO")
              || SucursalIn.equals("SUCURSAL002-DATOS")) {

            puertoS = 12342;
            puertoX = puertoS; // nos conectamos al puerto 12342
            System.out.println("Connected to another server" + SucursalIn);
            DatosEnviadosX = SucursalIn;

          }
          if (SucursalIn.equals("SUCURSAL003") || SucursalIn.equals("SUCURSAL003-CUENTA")
              || SucursalIn.equals("SUCURSAL003-CURP") || SucursalIn.equals("SUCURSAL003-NOMBRE")
              || SucursalIn.equals("SUCURSAL003-APELLIDO") || SucursalIn.equals("SUCURSAL003-SALDO")
              || SucursalIn.equals("SUCURSAL003-DATOS")) {

            puertoS = 12343;
            puertoX = puertoS; // nos conectamos al puerto 12343
            System.out.println("Connected to another server" + SucursalIn);
            DatosEnviadosX = SucursalIn;

          }

          if (SucursalIn.equals("SUCURSAL004") || SucursalIn.equals("SUCURSAL004-CUENTA")
              || SucursalIn.equals("SUCURSAL004-CURP") || SucursalIn.equals("SUCURSAL004-NOMBRE")
              || SucursalIn.equals("SUCURSAL004-APELLIDO") || SucursalIn.equals("SUCURSAL004-SALDO")
              || SucursalIn.equals("SUCURSAL004-DATOS")) {

            puertoS = 12344;
            puertoX = puertoS; // nos conectamos al puerto 12344
            System.out.println("Connected to another server" + SucursalIn);
            DatosEnviadosX = SucursalIn;

          }

          if (SucursalIn.equals("SUCURSAL002")) {
            puertoS = 12342;
            DatosEnviadosX = "SUCURSAL002";
          }
          if (SucursalIn.equals("SUCURSAL003")) {
            puertoS = 12343;
            DatosEnviadosX = "SUCURSAL003";
          }
          if (SucursalIn.equals("SUCURSAL004")) {
            puertoS = 12344;
            DatosEnviadosX = "SUCURSAL004";
          }

          System.out.println("Connecting to another server: " + puertoS);
          // puertoX = 13777;// nos conectamos al puerto 13777

          try {// tratamos de conectarnos con un nuevo server con los valores de maquinaX //
               // puertoX que se reciban
            clienteX = new Socket(maquinaX, puertoX);
            System.out.println("Connected to another server");

          } catch (Exception e) {// en caso de que no se pueda conectar con el otro server, imprimimos el error
            System.out.println("Fallo : " + e.toString());
            System.exit(0);

          }

          System.out.println("Trying to send data to another server: " + puertoX);

          try {
            escritorX = new PrintWriter(clienteX.getOutputStream(), true);
            entradaX = new BufferedReader(new InputStreamReader(clienteX.getInputStream()));

          } catch (Exception e) {
            System.out.println("Fallo : " + e.toString());
            clienteX.close();
            System.exit(0);

          }

          String lineX;
          System.out.println("Sending connecting to another server");

          escritorX.println(DatosEnviadosX);
          lineX = entradaX.readLine();
          System.out.println("Server: " + lineX);
          DatosEnviadosX = "FIN";
          escritorX.println(DatosEnviadosX);
          System.out.println("Closing another server");
          clienteX.close();
          escritorX.close();
          entradaX.close();
          escritor.println("Response from another Server... " + lineX);
          escritor.flush();

        } else {

          try (FileReader reader = new FileReader("data.json")) {

            JsonObject jobj = new Gson().fromJson(reader, JsonObject.class); // Pasa a objeto Json el archivo
            JsonObject getObject = jobj.getAsJsonObject("dictdist"); // Pasa tomar los datos del objeto dictdist
            JsonArray getArray = getObject.getAsJsonArray("services"); // Pasa tomar los datos del array services

            Servicios s = new Servicios("0", "0", 0, 0);

            for (int i = 0; i < getArray.size(); i++)// Obtenemos cantidad de datos del array services
            {
              JsonObject objects = (JsonObject) getArray.get(i); // Asignamos del array services un objeto a la variable
                                                                 // objects
              Iterator key = objects.keySet().iterator(); // Obtenemos las keys o datos guardados por asi decirlo

              s.addService(objects.get("nameOfService").toString(), objects.get("ip").toString(),
                  Integer.parseInt(objects.get("port").toString()), Integer.parseInt(objects.get("factor").toString()));

            } // fin del fo

            boolean a = s.checkServices(lineIn);
            // System.out.println(a); //imprimir bandera
            System.out.println(lineIn);

            if (a) {

              Integer puerto2 = s.ServiceRecovery(lineIn.toString());
              System.out.println(puerto2);

              try {
                clienteX = new Socket(maquinaX, puerto2);
                System.out.println("Connected to another server");

              } catch (Exception e) {
                System.out.println("Fallo : " + e.toString());
                System.exit(0);

              }

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
              DatosEnviadosX = lineIn.toString();
              escritorX.println(DatosEnviadosX);
              lineX = entradaX.readLine();
              System.out.println("Server2: " + lineX);
              DatosEnviadosX = "FIN";
              escritorX.println(DatosEnviadosX);
              System.out.println("Closing another server");
              clienteX.close();
              escritorX.close();
              entradaX.close();
              escritor.println("Response from Server2... " + lineX);
              escritor.flush();

            } else {
              escritor.println("Echo... " + lineIn);
              escritor.flush();
            }

          } catch (FileNotFoundException e) {
            e.printStackTrace();

          } catch (IOException e) {
            e.printStackTrace();

          }

        }
      } // fin del while
      try {
        entrada.close();
        escritor.close();
        socket.close();

      } catch (Exception e) {
        System.out.println("Error : " + e.toString());
        socket.close();
        System.exit(0);
      }
    } catch (IOException e) {
      System.out.println("Error---");
      e.printStackTrace();
    }
  }
}
