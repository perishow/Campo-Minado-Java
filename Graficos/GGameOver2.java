package Graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class GGameOver2 extends JFrame implements ActionListener{
    
    private JPanel panelCentral;
    private JPanel panelSuperior;
    private JLabel labelSuperior;
    private JLabel labelCentral;
    private JButton button;
    private GTabuleiro gTabuleiro;

    //icons
    private ImageIcon bomberIcon = new ImageIcon("images/bomber.png");
    private ImageIcon player1x50Icon = new ImageIcon("images/singleplayerx100.png");
    private ImageIcon player2x50Icon = new ImageIcon("images/secondplayerx100.png");

    public GGameOver2(int resto,GTabuleiro gTabuleiro){
        
        int x = Toolkit.getDefaultToolkit().getScreenSize().width;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height;

        this.setLocation((x/2) - 200,(y/2) - 100);
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setIconImage(bomberIcon.getImage());
        this.gTabuleiro = gTabuleiro;

        Border bordaDourada = BorderFactory.createLineBorder(new Color(255,184,28), 2);

        panelCentral = new JPanel();
        panelCentral.setBackground(Color.black);
        panelCentral.setBorder(bordaDourada);

        panelSuperior = new JPanel(new BorderLayout());

        labelSuperior = new JLabel();
        labelSuperior.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
        labelSuperior.setForeground(Color.white);
        labelSuperior.setOpaque(true);
        labelSuperior.setBackground(Color.black);
        labelSuperior.setBorder(bordaDourada);
        labelSuperior.setHorizontalAlignment(JLabel.CENTER);
        labelSuperior.setVerticalAlignment(JLabel.CENTER);

        labelCentral = new JLabel();
        labelCentral.setHorizontalAlignment(JLabel.CENTER);
        labelCentral.setVerticalAlignment(JLabel.CENTER);

        if(resto == 1){
            labelSuperior.setText("Vitória do Player 1!");
            labelSuperior.setForeground(new Color(0,162,232));
            labelCentral.setIcon(player1x50Icon);    
        }
        else if(resto == 0){

            labelSuperior.setText("Vitória do Player 2!");
            labelSuperior.setForeground(new Color(237,28,36));
            labelCentral.setIcon(player2x50Icon);    
        }

        button = new JButton("jóia!");
        button.addActionListener(this);
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        button.setPreferredSize(new Dimension(120,60));
        button.setForeground(Color.white);
        button.setBackground(Color.black);
        button.setBorder(BorderFactory.createLineBorder(new Color(255,184,28), 4));

        panelSuperior.add(labelSuperior);
        panelCentral.add(labelCentral);
        panelCentral.add(Box.createRigidArea(new Dimension(20,0)));
        panelCentral.add(button);
    

        labelSuperior.setHorizontalAlignment(JLabel.CENTER);
        labelSuperior.setVerticalAlignment(JLabel.CENTER);
        labelSuperior.setHorizontalTextPosition(SwingConstants.LEFT);

        this.add(labelSuperior, BorderLayout.NORTH);
        this.add(panelCentral, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){
            this.dispose();
            this.gTabuleiro.dispose();
        }
    }
}
