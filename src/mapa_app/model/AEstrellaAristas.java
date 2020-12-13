package mapa_app.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class AEstrellaAristas {


//    public static void main(String[] args) {
//        model grafo = new model();
//        //comprobar el metodo AEstrella
//        ArrayList<Vertice> camino = AEstrella("v238", "v23", grafo.grafo.vertices);
//        imprimirCamino(camino);
//    }

    public static double heuristica(Point punto, Point punto2) {
        double x = Math.pow(Math.abs(punto.getX() - punto2.getX()), 2.0);
        double y = Math.pow(Math.abs(punto.getY() - punto2.getY()), 2.0);
        return Math.sqrt(x + y);
    }

    public static void imprimirCamino(ArrayList<Vertice> camino) {
        System.out.println("\n------Impresion del camino mas corto--------");
        double longitudCamino = 0;
        System.out.println("camino: " + camino.get(0).nombre + " :---");
        for (int i = 1; i < camino.size(); i++) {
            System.out.println("camino: " + camino.get(i).nombre + " :---");
            longitudCamino += calcularDistancia(camino.get(i - 1).posicion, camino.get(i).posicion);
        }
        System.out.println("\nLongitud del camino: " + longitudCamino);
    }

    public static double calcularDistancia(Point p1, Point p2) {
        return Math.sqrt(Math.pow(Math.abs(p1.x - p2.x), 2.0) + Math.pow(Math.abs(p1.y - p2.y), 2.0));
    }

    public static ArrayList<Vertice> AEstrella(String inicio, String fin, Hashtable<String, Vertice> grafo) {
        ArrayList<Arista> cAbiertos = grafo.get(inicio).getAristas();
        ArrayList<Arista> cCerrados = new ArrayList<>();
        ArrayList<Vertice> camino = new ArrayList<>();
        camino.add(grafo.get(fin));
        Vertice llegada = grafo.get(fin);
        boolean terminado = false;
        Arista actual = cAbiertos.get(0);
        while (!terminado && cAbiertos.size() > 0) {
            int mejorVecino = 0;
            for (int i = 0; i < cAbiertos.size(); i++) {
                //calculamos la heuristica
                if (!cCerrados.contains(cAbiertos.get(i))) {
                    cAbiertos.get(i).heur = heuristica(llegada.posicion, grafo.get(cAbiertos.get(i).name2).posicion);
                    cAbiertos.get(i).prob = cAbiertos.get(i).peso + cAbiertos.get(i).heur;
                    if (actual.prob+cAbiertos.get(i).prob < cAbiertos.get(mejorVecino).prob) {
                        mejorVecino = i;
                    }
                }
            }
            actual = cAbiertos.get(mejorVecino);
            if (fin.equalsIgnoreCase(actual.name2)) {
                String name = actual.name1;
                for (int i = 0; i < cCerrados.size() && !name.equals(inicio); i++) {
                    if (cCerrados.get(i).name2.equals(name)) {
                        name = cCerrados.get(i).name1;
                        camino.add(grafo.get(cCerrados.get(i).name2));
                        cCerrados.remove(cCerrados.get(i));
                        i = 0;
                    }
                }
                terminado = true;
            } else {
                cCerrados.add(cAbiertos.get(mejorVecino));
                //eliminamos a actual de los abiertos y agregamos los adyacentes
                cAbiertos.remove(cAbiertos.get(mejorVecino));
                for (int i = 0; i < grafo.get(actual.name2).getAristas().size(); i++) {
                    if (!cCerrados.contains(grafo.get(actual.name2).aristas.get(i)) &&
                            !cAbiertos.contains(grafo.get(actual.name2).aristas.get(i))) {
                        cAbiertos.add(grafo.get(actual.name2).getAristas().get(i));
                    }
                }
            }
        }
        camino.add(grafo.get(inicio));
        Collections.reverse(camino);
        return camino;
    }
}

