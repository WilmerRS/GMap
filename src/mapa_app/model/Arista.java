package mapa_app.model;


import java.awt.*;
import java.util.Objects;

public class Arista {

    public String name1;
    public String name2;
    public String tipo;
    public double peso;
    public double prob;
    public double heur;
    public Arista(String name1,String name2) {
        this.name1 = name1;
        this.name2 = name2;
        this.tipo = "Mediana";
    }
}
