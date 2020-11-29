/**
 * GRAFOS APP
 * Aplicacion de escritorio realizada para crear y hacer groficamente un grafo no
 * dirigido. Permite evaluar las propiedades de un grafo tanto simple como multiple.
 *
 * WILMER RODRIGUEZ SANCHEZ
 * 2020 - 2
 */
package mapa_app.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author WILMER
 */
public class Model {

    private Grafo grafo;
    private double zoom = 1;
    
    
    public Model() {
        grafo = new Grafo();
//        iniciarGrafo();
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public Grafo iniciarGrafo() {
        leerVertices();
        leerAristas();
        return null;
    }

    private void leerVertices() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("PAMPLONA-VERTICES-3.tsp");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea = "";
            String tipo = "";
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("TYPE:")) {
                    tipo = linea;
                }
                if (linea.startsWith("v") && tipo.endsWith("VERTICES")) {
//                    System.out.println(linea);
                    String[] ver = linea.split("\t");
                    System.out.println(ver[0] + "\t" + ver[1] + "\t" + ver[2]);
                    grafo.agregarVertice(ver[0], Double.parseDouble(ver[1])*zoom, Double.parseDouble(ver[2])*zoom);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void leerAristas() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("PAMPLONA-ARISTAS.tsp");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea = "";
            String tipo = "";
            System.out.println("********************");
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("TYPE:")) {
                    tipo = linea;
                }
                if (linea.startsWith("a") && tipo.endsWith("ARISTAS")) {
//                    System.out.println(linea);
                    String[] ver = linea.split("\t");
                    System.out.println(ver[0] + "\t" + ver[1] + "\t" + ver[2] + "\t" + ver[3] + "\t" + ver[4] + "\t" + ver[5] + "\t" + ver[6]);
//                    grafo.agregarVertice(ver[0], Double.parseDouble(ver[1]), Double.parseDouble(ver[2]));
                    grafo.agregarArista(ver[1], ver[2], Double.parseDouble(ver[3]), Double.parseDouble(ver[5]), Double.parseDouble(ver[4]), Double.parseDouble(ver[6]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public ArrayList<Character> crearGrafo(int n) {
        ArrayList<Character> temp = new ArrayList<>();
        char a = 32;
        temp.add(a);

        int limite = (n * n) + (2 * n) + 1;
        char f = 65;
        char c = 65;
        char cero = 48;
        int i = 1;
        while (i < limite) {
            if (i <= n) {
                temp.add(f);
                f++;
            } else {
                if (i % (n + 1) == 0) {
                    temp.add(c);
                    c++;
                } else {
                    temp.add(cero);
                }
            }
            i++;
        }
        return temp;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

}
