package Graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import playerRank.RankMaster;

public class GRanking extends JFrame {

    private JLabel[] posicoesLabels;
    private JLabel[] nomesLabels;
    private JLabel[] pontosLabels;
    private JLabel labelSuperior;
    private JPanel panelSuperior;
    private JPanel panelCentral;
    private JPanel panelEsquerdo;
    private RankMaster rankMaster = new RankMaster();
    private ArrayList<String> nomes;
    private ArrayList<String> pontos;

    //icons
    private ImageIcon bomberIcon = new ImageIcon("images/bomber.png");
    
    public GRanking() {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = ((screenSize.width - this.getWidth()) / 2) - 620;
        int y = ((screenSize.height - this.getHeight()) / 2) - 210;

        this.setLocation(x,y);

        nomes = rankMaster.getNomes();
        pontos = rankMaster.getPontos();

        Border bordaDourada = BorderFactory.createLineBorder(new Color(255,184,28), 2);

        panelCentral = new JPanel(new GridLayout(10, 3)); // Alteração na quantidade de colunas
        panelCentral.setAlignmentX(CENTER_ALIGNMENT);
        panelCentral.setBackground(Color.BLACK);
        panelCentral.setBorder(bordaDourada);

        panelEsquerdo = new JPanel(new GridLayout(10, 1)); // Alteração na quantidade de colunas
        panelEsquerdo.setAlignmentX(CENTER_ALIGNMENT);
        panelEsquerdo.setBackground(Color.BLACK);
        panelEsquerdo.setBorder(bordaDourada);

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setPreferredSize(new Dimension(0, 70));
        panelSuperior.setBackground(Color.BLACK);
        panelSuperior.setBorder(bordaDourada);

        labelSuperior = new JLabel("RANKING");
        labelSuperior.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        labelSuperior.setForeground(new Color(255,250,250));
        labelSuperior.setHorizontalAlignment(JLabel.CENTER);
        labelSuperior.setVerticalAlignment(JLabel.CENTER);
        panelSuperior.add(labelSuperior);

        if (nomes.size() < 10) {
            posicoesLabels = new JLabel[nomes.size()];
            nomesLabels = new JLabel[nomes.size()];
            pontosLabels = new JLabel[nomes.size()];
        } else {
            posicoesLabels = new JLabel[10];
            nomesLabels = new JLabel[10];
            pontosLabels = new JLabel[10];
        }

        for (int i = 0; i < posicoesLabels.length; i++) {
            posicoesLabels[i] = new JLabel("  " + Integer.toString(i + 1) + "  ");
            posicoesLabels[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            posicoesLabels[i].setHorizontalAlignment(JLabel.CENTER);
            posicoesLabels[i].setForeground(Color.WHITE);
            posicoesLabels[i].setAlignmentX(CENTER_ALIGNMENT);

            panelEsquerdo.add(posicoesLabels[i]);

            nomesLabels[i] = new JLabel(nomes.get(i));
            nomesLabels[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            nomesLabels[i].setHorizontalAlignment(JLabel.CENTER);
            nomesLabels[i].setForeground(Color.WHITE);
            nomesLabels[i].setAlignmentX(CENTER_ALIGNMENT);

            panelCentral.add(nomesLabels[i]);

            pontosLabels[i] = new JLabel(pontos.get(i) + " pts");
            pontosLabels[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            pontosLabels[i].setHorizontalAlignment(JLabel.CENTER);
            pontosLabels[i].setForeground(new Color(255,184,28));
            pontosLabels[i].setAlignmentX(CENTER_ALIGNMENT);

            panelCentral.add(pontosLabels[i]);
        }

        posicoesLabels[0].setForeground(new Color(255,184,28));
        posicoesLabels[1].setForeground(new Color(170,169,173));
        posicoesLabels[2].setForeground(new Color(205,127,50));


        this.setIconImage(bomberIcon.getImage());
        this.setLayout(new BorderLayout());
        this.add(panelCentral, BorderLayout.CENTER);
        this.add(panelSuperior, BorderLayout.NORTH);
        this.add(panelEsquerdo, BorderLayout.WEST);
        this.setSize(420, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

}