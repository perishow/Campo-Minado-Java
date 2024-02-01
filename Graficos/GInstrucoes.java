package Graficos;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GInstrucoes extends JFrame{
    
    private JLabel label;

    ImageIcon bomberIcon = new ImageIcon("images/bomber.png");
    ImageIcon instrucoesIcon = new ImageIcon("images/instrucoes.png");

    public GInstrucoes(){
        int x = getToolkit().getScreenSize().width;
        int y = getToolkit().getScreenSize().height;
        
        this.setLocation((x/2) - 307, (y/2) - 317);
        this.setSize(615,635);
        this.setResizable(false);

        label = new JLabel(instrucoesIcon);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        this.add(label);
        this.setIconImage(bomberIcon.getImage());
        this.setVisible(true);
    }
}
