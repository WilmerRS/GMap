/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa_app;

import mapa_app.controller.Controller;
import mapa_app.model.Model;
import mapa_app.view.View;

/**
 *
 * @author WILMER
 */
public class Mapa_app_main {
private static Model model;
    private static View view;
    private static Controller controller;

    /**
     * Permite inicial el modelo MVC
     */
    public Mapa_app_main() {
        model = new Model();
        view = new View();
        controller = new Controller(model, view);
    }
    
    /**
     * Inicia la interfaz de la aplicacion
     * @param args argumentos en la linea de comandos
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /*
        * Ejecuta la ventana principal de la aplicacion
        */
        java.awt.EventQueue.invokeLater(() -> {
            new Mapa_app_main();
            view.setVisible(true);
       });
    }
    
}
