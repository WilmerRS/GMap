/**
 * GRAFOS APP
 * Aplicacion de escritorio realizada para crear y hacer groficamente un grafo no
 * dirigido. Permite evaluar las propiedades de un grafo tanto simple como multiple.
 *
 * WILMER RODRIGUEZ SANCHEZ
 * 2020 - 2
 */
package mapa_app.view.panelesCentrales;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import mapa_app.model.Arista;
import mapa_app.model.Grafo;
import mapa_app.model.Vertice;
import mapa_app.view.Patron;
import static mapa_app.view.Patron.ALTO;
import static mapa_app.view.Patron.ANCHO;

/**
 *
 * @author WILMER
 */
public class PnLienzo extends JPanel implements Patron {
    
    private int radio_2;
    private int radio;
    private int altoMitad;
    private int anchoMitad;
    
    private final String TIPO_LIENZO;
    
    private Grafo grafo;
    
    public PnLienzo(String TIPO_LIENZO, Grafo grafo) {
        super(new BorderLayout());
        this.TIPO_LIENZO = TIPO_LIENZO;
        this.grafo = grafo;
        this.setOpaque(false);
//        this.setBackground(COLOR_TERCIARIO);
        this.setSize(new Dimension((int) (ANCHO * 2.8), (int) (ALTO * 2.8)));
    }
    
    public void paint (Graphics grphcs) {
        
        super.paint(grphcs);
        
        anchoMitad = getWidth() / 2;
        altoMitad = getHeight() / 2;
        Graphics2D graphics = (Graphics2D) grphcs;
        
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Radio
        radio = (int) (ANCHO * 0.004);
        radio_2 = (int) (ANCHO * 0.0028);
        // Pintar vertices
        graphics.setColor(FONT_PRINCIPAL);
//
        switch (TIPO_LIENZO) {
            case "VERTICES":
                pintarVertices(graphics);
                break;
            case "ARISTAS":
                pintarAristas(graphics);
                break;
            case "RUTA":
                pintarRuta(graphics);
                break;
            case "PUNTOS_INTERES":
                pintarPuntosInteres(graphics);
                break;
        }
        
    }
    
    private void pintarAristas(Graphics2D graphics) {
        // Pintar vertices
        System.out.println("************** pintando aristas **************");
        graphics.setColor(COLOR_ACENTUADOR);
        graphics.setStroke(new BasicStroke((int) (radio)));
        for (Vertice vertice : grafo.getVertices()) {
            for (Arista arista : vertice.getAristas()) {
                int x_1 = (int) arista.getX1();
                int y_1 = (int) arista.getY1();
                
                int x_2 = (int) arista.getX2();
                int y_2 = (int) arista.getY2();
                
                graphics.drawLine(x_1, y_1, x_2, y_2);
            }
        }
    }
    
    private void pintarPuntosInteres(Graphics2D graphics) {
        
    }
    
    private void pintarRuta(Graphics2D graphics) {
        
    }
    
    private void pintarVertices(Graphics2D graphics) {
        // Dibujar vertices
        System.out.println("-------------- pintando vertices --------------");
        for (Vertice vertice : grafo.getVertices()) {
            //Vertices
            graphics.setColor(vertice.getColor());
            double x = (int) vertice.getX();
            double y = (int) vertice.getY();
            graphics.fillOval((int) (x - (radio / 2)), (int) (y - (radio / 2)), radio, radio);
            
        }
    }
    
}
