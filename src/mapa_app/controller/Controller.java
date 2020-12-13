/**
 * APP EDUCATIVA
 * Prototipo de una aplicacion para la enseñanza de programacion de a jovenes
 * y adultos, de manera didactica y sencilla.
 *
 * WILMER RODRIGUEZ SANCHEZ
 * LUIS ALFREDO ACOSTA
 * 2020 - 2
 */
package mapa_app.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import mapa_app.view.View;
import mapa_app.model.Model;
import mapa_app.model.Vertice;

/**
 * Clase que permite controlar la interfaz, mediante el llamado de sus eventos
 *
 * @author WILMER
 */
public class Controller {

    private Model model;
    private View view;

    /**
     * Permite construir el controlador, que une el la interfaz con el modelo
     *
     * @param model Modelo, parte lógica del programa
     * @param view Interfaz, parte visual del programa
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        iniciarGrafo();
        agregarEventos();
    }

    public void iniciarGrafo() {
        model.iniciarGrafo();
        view.iniciarGrafo(model.getGrafo());
        
        ArrayList<Vertice> ver =  model.getGrafo().caminoMasCorto("v251", "v187");
        view.getPnAristas().setVer(ver);
        view.getPnAristas().setPintarCamino(true);
        view.getPnAristas().repaint();
    }

    public void agregarEventos() {
//        view.getBtnmas().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                model.setZoom(model.getZoom() + 0.3);
//                iniciarGrafo();
//                System.out.println("+");
//            }
//        });
//        view.getBtnmenos().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                if (model.getZoom() != 1) {
//                    model.setZoom(model.getZoom() - 0.3);
//                    iniciarGrafo();
//                }
//            }
//        });
    }

}
