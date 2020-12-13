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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
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
public class PnLienzo extends JPanel implements Patron, MouseWheelListener, MouseListener, MouseMotionListener {

    private int radio_2;
    private int radio;
    private int altoMitad;
    private int anchoMitad;

    private final String TIPO_LIENZO;

    private Grafo grafo;

    //atributos zoom
    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean zoomer;
    private boolean dragger;
    private boolean released;
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff;
    private int yDiff;
    private Point startPoint;

    private ArrayList<Vertice> ver;
    private boolean pintarCamino = false;

    public PnLienzo(String TIPO_LIENZO, Grafo grafo) {
        super(new BorderLayout());
        this.TIPO_LIENZO = TIPO_LIENZO;
        this.grafo = grafo;
        this.setOpaque(false);
//        this.setBackground(COLOR_TERCIARIO);
        this.setSize(new Dimension((int) (ANCHO * 0.8), (int) (ALTO * 0.8)));
        initComponent();
    }

    private void initComponent() {
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics grphcs) {

        super.paint(grphcs);

        anchoMitad = getWidth() / 2;
        altoMitad = getHeight() / 2;
        Graphics2D graphics = (Graphics2D) grphcs;

//        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if (zoomer) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            graphics.transform(at);
            zoomer = false;

        }

        if (dragger) {
            AffineTransform at = new AffineTransform();
            at.translate(xOffset + xDiff, yOffset + yDiff);
            at.scale(zoomFactor, zoomFactor);
            graphics.transform(at);

            if (released) {
                xOffset += xDiff;
                yOffset += yDiff;
                dragger = false;
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        }

        // Radio
        radio = (int) (ANCHO * 0.002);
        radio_2 = (int) (ANCHO * 0.0028);
        // Pintar vertices
        graphics.setColor(FONT_PRINCIPAL);
//
//        switch (TIPO_LIENZO) {
//            case "VERTICES":

//                break;
//            case "ARISTAS":
        pintarAristas(graphics, 1, COLOR_ACENTUADOR);
        pintarAristas(graphics, 0, COLOR_HOVER_GRIS);

        pintarVertices(graphics, 1, COLOR_ACENTUADOR);
//                pintarVertices(graphics,0, COLOR_HOVER_GRIS);
//                break;
//            case "RUTA":
//                pintarRuta(graphics);
//                break;
//            case "PUNTOS_INTERES":
//                pintarPuntosInteres(graphics);
//                break;
//        }
        if (pintarCamino) {
            pintarCaminos(graphics, 0, Color.ORANGE, ver);
        }
    }

    private void pintarFondo(Graphics2D graphics, Color color) {
        // Pintar vertices
//        System.out.println("************** pintando aristas **************");
        graphics.setColor(color);
//        graphics.setStroke(new BasicStroke((int) (radio) + ancho));
//        for (Vertice vertice : grafo.getVertices()) {
//            for (Arista arista : vertice.getAristas()) {
//                int x_1 = (int) arista.getX1();
//                int y_1 = (int) arista.getY1();
//
//                int x_2 = (int) arista.getX2();
//                int y_2 = (int) arista.getY2();
//
//                graphics.drawLine(x_1, y_1, x_2, y_2);
//            }
//        }
    }

    private void pintarAristas(Graphics2D graphics, int ancho, Color color) {
        // Pintar vertices
//        System.out.println("************** pintando aristas **************");
        graphics.setColor(color);
        graphics.setStroke(new BasicStroke((int) (radio) + ancho));
        for (String nom_ver : grafo.getClaveVertices()) {
            Vertice vertice = grafo.getVertices().get(nom_ver);
            for (Arista arista : vertice.getAristas()) {
                int h = grafo.getVertices().get(arista.name1).posicion.x;
                graphics.setStroke(new BasicStroke((int) (radio) + ancho));

                int x_1 = (int) grafo.getVertices().get(arista.name1).posicion.x;
                int y_1 = (int) grafo.getVertices().get(arista.name1).posicion.y;

                int x_2 = (int) grafo.getVertices().get(arista.name2).posicion.x;
                int y_2 = (int) grafo.getVertices().get(arista.name2).posicion.y;
//                if(grafo.getVertices().get(arista.name1).nombre.equals("v53")&& grafo.getVertices().get(arista.name2).nombre.equals("v56")){
//                    System.out.println("    fnnj  "+x_1+"  "+x_2);
//                }
                
                graphics.drawLine(x_1, y_1, x_2, y_2);
            }
        }
    }

    public void pintarCaminos(Graphics2D graphics, int ancho, Color color, ArrayList<Vertice> ver) {

        graphics.setColor(color);
        graphics.setStroke(new BasicStroke((int) (radio) + ancho - 1));
        for (int i = 1; i < ver.size(); i++) {

//            System.out.println(ver.get(i).nombre);
            int x_1 = ver.get(i - 1).posicion.x;
            int y_1 = ver.get(i - 1).posicion.y;

            int x_2 = ver.get(i).posicion.x;
            int y_2 = ver.get(i).posicion.y;

            if (ver.get(i).nombre.equals("v56") ) {
//                System.out.println("entro");
//                graphics.setColor(Color.BLACK);
//                graphics.setStroke(new BasicStroke((int) (radio) + ancho + 1));
//                graphics.fillOval((int) (x_1 - (radio / 2)), (int) (y_1 - (radio / 2)), radio + ancho+2, radio + ancho+2);
//                graphics.setColor(color);
//                graphics.setStroke(new BasicStroke((int) (radio) + ancho - 1));
            }
            graphics.drawLine(x_1, y_1, x_2, y_2);
        }
//        System.out.println("Pintó");
    }

    private void pintarPuntosInteres(Graphics2D graphics) {

    }

    private void pintarUbicacion(Graphics2D graphics, Color color) {

    }

    private void pintarRuta(Graphics2D graphics) {

    }

    private void pintarVertices(Graphics2D graphics, int ancho, Color color) {
//        System.out.println("-------------- pintando vertices --------------");
        for (String nom_ver : grafo.getClaveVertices()) {
            Vertice vertice = grafo.getVertices().get(nom_ver);
            // Vertices
            graphics.setColor(Color.CYAN);
            double x = (int) vertice.posicion.x;
            double y = (int) vertice.posicion.y;
            graphics.fillOval((int) (x - (radio / 2)), (int) (y - (radio / 2)), radio + ancho, radio + ancho);
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println(MouseInfo.getPointerInfo().getLocation().getX());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        released = false;
        startPoint = MouseInfo.getPointerInfo().getLocation();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        released = true;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        Point curPoint = e.getLocationOnScreen();
        xDiff = curPoint.x - startPoint.x;
        yDiff = curPoint.y - startPoint.y;
//        System.out.println(xDiff + "    " + yDiff);
//        System.out.println(xOffset + "    " + yOffset);
//        System.out.println(getLocationOnScreen().getY());
//        System.out.println(getLocationOnScreen().getX());
        dragger = true;
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomer = true;

        //Zoom in
        if (e.getWheelRotation() < 0) {

            if (zoomFactor < 2.6) {
                zoomFactor *= 1.1;
            }
            repaint();
        }
        //Zoom out
        if (e.getWheelRotation() > 0) {

            if (zoomFactor > 1.1) {
                zoomFactor /= 1.1;
            }
//            System.out.println(this.getSize().width + "    " + this.getSize().height);

            repaint();
        }
    }

    public ArrayList<Vertice> getVer() {
        return ver;
    }

    public void setVer(ArrayList<Vertice> ver) {
        this.ver = ver;
    }

    public boolean isPintarCamino() {
        return pintarCamino;
    }

    public void setPintarCamino(boolean pintarCamino) {
        this.pintarCamino = pintarCamino;
    }

}
