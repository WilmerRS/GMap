package mapa_app.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Grafo {

    private Hashtable<String,Vertice> vertices;
    private ArrayList<String> claveVertices;

    public Grafo(){
        vertices = new Hashtable<String,Vertice>();
        claveVertices = new ArrayList<String>();
    }

    public void agregarVertice(String nombre,int x,int y, int barrio){
        vertices.put(nombre,new Vertice(nombre,x,y,barrio));
        claveVertices.add(nombre);
    }

    public void agregarArista(String name1, String name2, Point pos1, Point pos2, int barrio1, int barrio2,String direccion){
        if(direccion.equalsIgnoreCase("bidireccional")) {
            vertices.get(name1).agregarVerticesAdyacentes(name2, pos2.x, pos2.y, barrio2);
            vertices.get(name2).agregarVerticesAdyacentes(name1, pos1.x, pos1.y, barrio1);
        }else{
            vertices.get(name1).agregarVerticesAdyacentes(name2, pos2.x, pos2.y, barrio2);
        }
    }
    public void caminosFrontera(){
        
    }
    
    public ArrayList<Vertice> caminoMasCorto(String inicio, String fin){
        AEstrella aEstrella= new AEstrella();
        ArrayList<Vertice> ver = aEstrella.aEstrella(inicio, fin, vertices);
        return ver;
    }
    
    public ArrayList<String> getClaveVertices() {
        return claveVertices;
    }

    public Hashtable<String,Vertice> getVertices() {
        return vertices;
    }
    
}
