import com.google.gson.Gson;//LIBRERIAS DEL GSON
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.google.gson.Gson; //LIBRERIAS
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadJSON {// clase que nos lee el GSON

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("data.json")) { // tratamos de leer el GSON que contiene nuestro
                                                                // DICCIONARIO DE DISTRIBUCION

            JsonObject jobj = new Gson().fromJson(reader, JsonObject.class); // Creamos un objeto JSON de la clase json
                                                                             // y pasamos a objeto Json el archivo
            JsonObject getObject = jobj.getAsJsonObject("dictdist"); // Obtenemos los datos del objeto de nuestro
                                                                     // diccionario de distribucion
            JsonArray getArray = getObject.getAsJsonArray("services"); // Obtenemos los datos del array services

            Servicios array_s = new Servicios("0", "0", 0, 0); // Creamos nuestro objeto de la clase Servicios y
                                                               // colocamos los valores iniciales en NULL

            for (int i = 0; i < getArray.size(); i++) {// Desde i=0 hasta el tamaño de nuestro array -1

                JsonObject objects = (JsonObject) getArray.get(i); // Asignamos del array services un objeto a la
                                                                   // variable objects
                Iterator key = objects.keySet().iterator(); // Obtenemos las keys o datos guardados por asi decirlo

                array_s.addService(objects.get("nameOfService").toString(), // nombre del servicio
                        objects.get("ip").toString(), // obtenemos la ip
                        Integer.parseInt(objects.get("port").toString()), // optenemos el puerto
                        Integer.parseInt(objects.get("factor").toString()));// obtenemos el factor

                System.out.println(); // Salto de linea pa que se vea mejor :v
            }
            array_s.showServices(); // mostramos los valores que tenemos en nuestro arrar
        } catch (FileNotFoundException e) { // si no se encuentra el archivo
            e.printStackTrace();

        } catch (IOException e) {// otro mas, porque si xd
            e.printStackTrace();
        }
    }
}