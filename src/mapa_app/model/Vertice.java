package mapa_app.model;


import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class Vertice {
    public String nombre;
    public Point posicion;
    public String padre;
    public int barrio;
    public double f;
    public double g;
    public double h;
    public boolean frontera;
    public ArrayList<Vertice> verticesAdyacentes;
    public ArrayList<Arista> aristas;

    public Vertice(String nombre, int x, int y, int barrio) {
        this.nombre = nombre;
        this.posicion = new Point(x, y);
        this.barrio = barrio;
        this.frontera = false;
        verticesAdyacentes = new ArrayList<Vertice>();
        aristas = new ArrayList<Arista>();
    }

    public double distancia(int x, int y) {
        double dx = Math.pow(Math.abs(this.posicion.x - x), 2.0);
        double dy = Math.pow(Math.abs(this.posicion.y - y), 2.0);
        return Math.sqrt(dx + dy);
    }

    public void agregarVerticesAdyacentes(String nameB, int x2, int y2, int barrio) {
        Vertice arista = new Vertice(nameB, x2, y2, barrio);
        arista.g = distancia(x2, y2);
        arista.padre = this.nombre;
        if (!verticesAdyacentes.contains(arista)) {
            verticesAdyacentes.add(arista);
        }
        if(this.barrio != barrio){
            this.frontera = true;
        }
        this.frontera = this.barrio != barrio;
        Arista nueva = new Arista(this.nombre, nameB);
        nueva.peso = distancia(x2,y2);
        if (!aristas.contains(nueva)) {
            aristas.add(nueva);
        }
    }
    public ArrayList<String> obtenerVerticesAdyacentes(){
        ArrayList<String> a = new ArrayList<String>();
        for(Arista q: aristas){
            a.add(q.name2);
        }
        return a;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
        return nombre.equals(vertice.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    public ArrayList<Arista> getAristas() {
        return aristas;
    }
    
}
