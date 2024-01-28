package Graficos;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class CustomSliderUI extends BasicSliderUI {
    public CustomSliderUI(JSlider b) {
        super(b);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(255,184,28)); // Define a cor do polegar do slider
        g2d.fillRect(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
        g2d.dispose();
    }
    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE); // Define a cor da barra (track) do slider
        g2d.fillRect(trackRect.x, trackRect.y + trackRect.height / 2 - 2, trackRect.width, 4);
        g2d.dispose();
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(14, 14); // Ajusta o tamanho do polegar
    }

    @Override
    public void paintFocus(Graphics g) {
        // Evita que o foco padrão seja pintado
    }

    @Override
    protected Color getFocusColor() {
        return new Color(0, 0, 0, 0); // Define a cor do foco como transparente
    }
}
