/**
 * APP EDUCATIVA
 * Prototipo de una aplicacion para la enseñanza de programacion de a jovenes
 * y adultos, de manera didactica y sencilla.
 *
 * WILMER RODRIGUEZ SANCHEZ
 * LUIS ALFREDO ACOSTA
 * 2020 - 2
 */
package mapa_app.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import mapa_app.model.Grafo;
import mapa_app.view.panelesCentrales.PnLienzo;

/**
 * Clase que permite crear la interfaz del programa. Solo crea la parte visual
 *
 * @author WILMER
 */
public class View extends JFrame implements Patron {

    JPanel pn;
    private JButton btnmas;
    private JButton btnmenos;

    private JLayeredPane layeredPane;
    JScrollPane sp;

    public View() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pn = new JPanel(new BorderLayout());
        pn.setBackground(COLOR_PRINCIPAL);
        pn.setPreferredSize(new Dimension((int) (ANCHO * 0.8), (int) (ALTO * 0.8)));

        JPanel pn2 = new JPanel(new GridLayout(1, 1));
        pn2.setBackground(COLOR_ACENTUADOR);

        btnmas = new JButton("Zoom+");
        btnmenos = new JButton("Zoom-");

//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pn, pn2);
        layeredPane = new JLayeredPane();
        layeredPane.setBorder(BorderFactory.createTitledBorder("Capas"));

        sp = new JScrollPane(layeredPane,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setViewportView(layeredPane);
        sp.setOpaque(false);
        sp.setBorder(null);

        pn.add(sp);
        pn.add(btnmas, BorderLayout.WEST);
        pn.add(btnmenos, BorderLayout.EAST);
//        pn.add(layeredPane);
        getContentPane().add(pn, java.awt.BorderLayout.CENTER);
        this.pack();
    }

    public void iniciarGrafo(Grafo g) {
        layeredPane.removeAll();
        layeredPane.updateUI();
        PnLienzo pnVertices;
        PnLienzo pnAristas;
        
        System.out.println("-------------a-----------------\n-------------a-----------------");
        pnAristas = new PnLienzo("ARISTAS", g);
        layeredPane.add(pnAristas, new Integer(0));
        layeredPane.updateUI();

        pnVertices = new PnLienzo("VERTICES", g);
        layeredPane.add(pnVertices, new Integer(1));
        layeredPane.updateUI();
    }

    public JButton getBtnmas() {
        return btnmas;
    }

    public void setBtnmas(JButton btnmas) {
        this.btnmas = btnmas;
    }

    public JButton getBtnmenos() {
        return btnmenos;
    }

    public void setBtnmenos(JButton btnmenos) {
        this.btnmenos = btnmenos;
    }

}
