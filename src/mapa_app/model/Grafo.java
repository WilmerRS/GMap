/**
 * GRAFOS APP
 * Aplicacion de escritorio realizada para crear y hacer groficamente un grafo no
 * dirigido. Permite evaluar las propiedades de un grafo tanto simple como multiple.
 *
 * WILMER RODRIGUEZ SANCHEZ
 * 2020 - 2
 */
package mapa_app.model;

import java.awt.Point;
import java.util.ArrayList;
import static mapa_app.view.Patron.ANCHO;

/**
 *
 * @author WILMER
 */
public class Grafo {


    private ArrayList<Vertice> vertices;
    private int numVertices = 0;
    private ArrayList<Point> posiciones;
    private int radio = (int) (ANCHO * 0.09);
    private int radio_2 = (int) (ANCHO * 0.015);

    public Grafo() {
        vertices = new ArrayList<>();
        posiciones = new ArrayList<>();
    }

    public void limpiarGrafo() {
        vertices.removeAll(vertices);
        posiciones.removeAll(posiciones);
        numVertices = 0;
    }

    private boolean existeVertice(String nombre) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public boolean agregarVertice(String nombre, double x, double y) {

        if (!existeVertice(nombre)) {
            Vertice vertice = new Vertice(nombre);
            vertices.add(vertice);
            numVertices++;
            vertice.setX(x);
            vertice.setY(y);
            
            return true;
        }
        return false;
    }

    public boolean agregarArista(String nomVertice_1, String nomVertice_2, double x1, double x2, double y1, double y2) {
        for (Vertice vertice : vertices) {
            if (vertice.getNombre().equals(nomVertice_1)) {
                vertice.agregarArista(x1, x2, y1, y2, 1, nomVertice_1, nomVertice_2);
            }
        }
        return true;
    }
    
    public Vertice colorDefecto() {
        for (Vertice vertice : vertices) {
            vertice.colorDefecto();
        }
        return null;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public ArrayList<Point> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(ArrayList<Point> posiciones) {
        this.posiciones = posiciones;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public int getRadio_2() {
        return radio_2;
    }

    public void setRadio_2(int radio_2) {
        this.radio_2 = radio_2;
    }

}
