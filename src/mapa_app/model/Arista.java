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

/**
 *
 * @author WILMER
 */
public class Arista {

    private int cantidad;
    private String nombreArista;
    private String a, b;
    private double x1;
    private double x2;
    private double y1;
    private double y2;

    public Arista(double x1, double x2, double y1, double y2, int cantidad, String a, String b) {
        this.cantidad = cantidad;
        this.nombreArista = "{" + a + ", " + b + "}";
        this.a = a;
        this.b = b;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreArista() {
        return nombreArista;
    }

    public void setNombreArista(String nombreArista) {
        this.nombreArista = nombreArista;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

}
