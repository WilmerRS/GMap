package mapa_app.model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;

public class Model {

    public Grafo grafo;

    public Model(){
        grafo = new Grafo();
//        leerVertices();
//        leerAristas();
        //imprimir();
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
            archivo = new File("PAMPLONA-VERTICES-3.tsp");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea = "";
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("v")) {
                    String[] ver = linea.split(":");
                    grafo.agregarVertice(ver[0],Integer.parseInt(ver[1]),Integer.parseInt(ver[2]),Integer.parseInt(ver[3]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
            archivo = new File("ARISTAS1.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea = "";
            while ((linea = br.readLine()) != null) {
                if(linea.startsWith("a")) {
                    String[] ver = linea.split(":");
                    grafo.agregarArista(ver[1], ver[2], grafo.getVertices().get(ver[1]).posicion, grafo.getVertices().get(ver[2]).posicion,
                            grafo.getVertices().get(ver[1]).barrio, grafo.getVertices().get(ver[2]).barrio, ver[4]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void imprimir(){
        for(String a:grafo.getClaveVertices()){
            System.out.println(grafo.getVertices().get(a).nombre);
        }
    }
    
    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }
}
