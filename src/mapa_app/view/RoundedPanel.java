/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa_app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RoundedPanel extends JPanel implements Patron {

    private Color backgroundColor;
    private Color borderColor;

    private int cornerRadius;

    private JPanel pnFondo;
    private JScrollPane spTxtField;


    public RoundedPanel(int radius, Color bgColor, Color brColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
        borderColor = brColor;
//        this.setOpaque(false);
    }
    
    public void agregar(Component component){
        this.setLayout(new BorderLayout());

        pnFondo = new JPanel(new BorderLayout(MARGEN, MARGEN));
//        pnFondo.setOpaque(false);
        
        spTxtField = new JScrollPane(component,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        spTxtField.setBorder(null);
//        spTxtField.setOpaque(false);
//        spTxtField.getViewport().setOpaque(false);
        spTxtField.setViewportView(component);
        
//        spTxtField.getHorizontalScrollBar().setPreferredSize(new Dimension(4,4));
//        spTxtField.getHorizontalScrollBar().setBorder(null);
        hacerMagenes();
//        pnFondo.add(spTxtField, BorderLayout.CENTER);
        
        
        this.add(spTxtField);
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
//        int width = getWidth();
//        int height = getHeight();
//        Graphics2D graphics = (Graphics2D) g;
//        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        //Draws the rounded panel with borders.
//        if (backgroundColor != null) {
//            graphics.setColor(backgroundColor);
//        } else {
//            graphics.setColor(getBackground());
//        }
//
//        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
//
//        if (borderColor != null) {
//            graphics.setColor(borderColor);
//        } else {
//            graphics.setColor(getForeground());
//        }
//        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint border
    }

    private void hacerMagenes() {
        int i = 0;
        JPanel izq = new JPanel();
        izq.setOpaque(false);
        izq.setPreferredSize(new Dimension(i, i));
        this.add(izq, BorderLayout.WEST);

        JPanel der = new JPanel();
        der.setOpaque(false);
        der.setPreferredSize(new Dimension(i, i));
        this.add(der, BorderLayout.EAST);

        JPanel nor = new JPanel();
        nor.setOpaque(false);
        nor.setPreferredSize(new Dimension(i, i));
        this.add(nor, BorderLayout.NORTH);

        JPanel sur = new JPanel();
        sur.setOpaque(false);
        sur.setPreferredSize(new Dimension(i, i));
        this.add(sur, BorderLayout.SOUTH);
    }

    public void actualizarPanel(int radius, Color bgColor, Color fgColor) {
        cornerRadius = radius;
        backgroundColor = bgColor;
        borderColor = fgColor;
        this.paintComponent(this.getGraphics());
        this.updateUI();
    }
}
