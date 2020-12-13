package mapa_app.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class AEstrella {


//    public static void main(String[] args) {
//        model grafo = new model();
//        //comprobar el metodo AEstrella
//        ArrayList<Vertice> camino = AEstrella("v238", "v250", grafo.grafo.vertices);
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

    public  ArrayList<Vertice> aEstrella(String inicio, String fin, Hashtable<String, Vertice> grafo) {
        Vertice actual = grafo.get(inicio);
        ArrayList<Vertice> cAbiertos = actual.verticesAdyacentes;
        ArrayList<Vertice> cCerrados = new ArrayList<>();
        ArrayList<Vertice> camino = new ArrayList<>();
        cCerrados.add(actual);
        camino.add(grafo.get(fin));
        Vertice llegada = grafo.get(fin);
        boolean terminado = false;
        while (!terminado && cAbiertos.size() > 0) {
            int mejorVecino = 0;
            for (int i = 0; i < cAbiertos.size(); i++) {
                //calculamos la heuristica
                if(!cCerrados.contains(cAbiertos.get(i))) {
                    cAbiertos.get(i).h = heuristica(llegada.posicion, cAbiertos.get(i).posicion);
                    cAbiertos.get(i).f =actual.f+ cAbiertos.get(i).g + cAbiertos.get(i).h;
                    if (cAbiertos.get(i).f < cAbiertos.get(mejorVecino).f) {
                        mejorVecino = i;
                    }
                }

            }
            actual = cAbiertos.get(mejorVecino);
            if (fin.equalsIgnoreCase(actual.nombre)) {
                String name = actual.padre;
                for (int i = 0; i < cCerrados.size() && !name.equals(inicio); i++) {
                    if (cCerrados.get(i).nombre.equals(name)) {
                        name = cCerrados.get(i).padre;
                        camino.add(cCerrados.get(i));
                        cCerrados.remove(cCerrados.get(i));
                        i = 0;
                    }
                }
                terminado = true;
            } else {
                cCerrados.add(actual);
                cAbiertos.remove(actual);
                for (int i = 0; i < grafo.get(actual.nombre).verticesAdyacentes.size(); i++) {
                    if(!cCerrados.contains(grafo.get(actual.nombre).verticesAdyacentes.get(i)) &&
                            !cAbiertos.contains(grafo.get(actual.nombre).verticesAdyacentes.get(i))) {
                        cAbiertos.add(grafo.get(actual.nombre).verticesAdyacentes.get(i));
                    }
                }
            }
        }
        camino.add(grafo.get(inicio));
        Collections.reverse(camino);
        System.out.println(camino.size());
        return camino;
    }
}
