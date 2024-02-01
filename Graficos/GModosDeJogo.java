package Graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import playerRank.Jogador;
import tabuleiros.*;

public class GModosDeJogo extends JFrame implements ActionListener{
    
    private JButton facilButton;
    private JButton normalButton;
    private JButton dificilButton;
    private JCheckBox checkBoxMaluco;
    private JCheckBox checkBox2P;
    private JSlider sliderMaluquice;

    private JPanel panelMeio;
    private JPanel panelSuperior;
    private JPanel panelInferior;
    private JPanel panelLabelInferior;
    private JPanel panelInferiorCentral;

    private JLabel labelSuperior;
    private JLabel labelInferior;

    private Tabuleiro tabuleiro;
    private GTabuleiro gTabuleiro;
    private Jogador jogador;

    private int x;
    private int y;

    //icons
    private ImageIcon vIcon = new ImageIcon("images/V.png");
    private ImageIcon xIcon = new ImageIcon("images/X.png");
    private ImageIcon singleplayerIcon = new ImageIcon("images/singleplayer.png");
    private ImageIcon multiplayerIcon = new ImageIcon("images/multiplayer.png");

    public GModosDeJogo(Jogador jogador){
        this.setSize(400,450);
        this.setResizable(false);    
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        x = (getToolkit().getScreenSize().width / 2) - 200; 
        y = (getToolkit().getScreenSize().height / 2) - 200;
        this.setLocation(x,y);

        this.jogador = jogador;

        Border bordaDourada = BorderFactory.createLineBorder(new Color(255,184,28), 2);

        labelSuperior = new JLabel("Configurações");
        labelSuperior.setFont(new Font("Comic Sans MS",Font.PLAIN,50));
        labelSuperior.setForeground(Color.white);
        labelSuperior.setHorizontalAlignment(JLabel.CENTER);
        labelSuperior.setVerticalAlignment(JLabel.CENTER);

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(Color.black);
        panelSuperior.add(labelSuperior);
        panelSuperior.setBorder(bordaDourada);

        checkBoxMaluco = new JCheckBox("Modo Maluco");
        checkBoxMaluco.setFocusable(false);
        checkBoxMaluco.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        checkBoxMaluco.setForeground(Color.white);
        checkBoxMaluco.setBackground(Color.black);
        checkBoxMaluco.setIcon(xIcon);
        checkBoxMaluco.setSelectedIcon(vIcon);

        sliderMaluquice = new JSlider(0,10,5);
        sliderMaluquice.setUI(new CustomSliderUI(sliderMaluquice));
        sliderMaluquice.setMajorTickSpacing(1);
        sliderMaluquice.setPaintLabels(true);
        sliderMaluquice.setFont(new Font("Comic Sans MS", Font.PLAIN,10));
        sliderMaluquice.setForeground(Color.white);
        sliderMaluquice.setBackground(Color.BLACK);

        checkBox2P = new JCheckBox("N° de jogadores");
        checkBox2P.setFocusable(false);
        checkBox2P.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        checkBox2P.setForeground(Color.white);
        checkBox2P.setBackground(Color.black);
        checkBox2P.setIcon(singleplayerIcon);
        checkBox2P.setSelectedIcon(multiplayerIcon);

        facilButton = new JButton("Fácil");
        facilButton.setFocusable(false);
        facilButton.setPreferredSize(new Dimension(100,50));
        facilButton.addActionListener(this);
        facilButton.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        facilButton.setForeground(Color.white);
        facilButton.setBackground(Color.black);
        facilButton.setBorder(BorderFactory.createLineBorder(new Color(255,184,28), 5));

        normalButton = new JButton("Normal");
        normalButton.setFocusable(false);
        normalButton.setPreferredSize(new Dimension(100,50));
        normalButton.addActionListener(this);
        normalButton.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        normalButton.setForeground(Color.white);
        normalButton.setBackground(Color.black);
        normalButton.setBorder(BorderFactory.createLineBorder(new Color(255,184,28), 5));

        dificilButton = new JButton("Difícil");
        dificilButton.setFocusable(false);
        dificilButton.setPreferredSize(new Dimension(100,50));
        dificilButton.addActionListener(this);
        dificilButton.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        dificilButton.setForeground(Color.white);
        dificilButton.setBackground(Color.black);
        dificilButton.setBorder(BorderFactory.createLineBorder(new Color(255,184,28), 5));

        panelMeio = new JPanel(new FlowLayout());
        panelMeio.setBackground(Color.black);
        panelMeio.setBorder(bordaDourada);
        
        panelMeio.add(checkBox2P);
        panelMeio.add(checkBoxMaluco);
        panelMeio.add(sliderMaluquice);

        labelInferior =  new JLabel("Dificuldade");
        labelInferior.setFont(new Font("Comic Sans MS",Font.PLAIN,50));
        labelInferior.setForeground(Color.white);
        labelInferior.setHorizontalAlignment(JLabel.CENTER);
        labelInferior.setVerticalAlignment(JLabel.CENTER);

        panelLabelInferior = new JPanel(new BorderLayout());
        panelLabelInferior.setBackground(Color.black);
        panelLabelInferior.add(labelInferior);

        panelInferiorCentral =  new JPanel();
        panelInferiorCentral.setBackground(Color.BLACK);
        panelInferiorCentral.add(facilButton);
        panelInferiorCentral.add(normalButton);
        panelInferiorCentral.add(dificilButton);

        panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(bordaDourada);
        panelInferior.add(panelLabelInferior,BorderLayout.NORTH);
        panelInferior.add(panelInferiorCentral);
        
        
        ImageIcon bomberIcon = new ImageIcon("images/bomber.png");

        this.setIconImage(bomberIcon.getImage());
        this.add(panelSuperior,BorderLayout.NORTH);
        this.add(panelMeio,BorderLayout.CENTER);
        this.add(panelInferior,BorderLayout.SOUTH);
        this.setVisible(true);
    
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == facilButton){
            this.tabuleiro = new TabuleiroFacil();
        }
        else if(e.getSource() == normalButton){
            this.tabuleiro = new TabuleiroNormal();
        }
        else if(e.getSource() == dificilButton){
            this.tabuleiro = new TabuleiroDificil();
        }

        ativarMaluquice(checkBoxMaluco.isSelected());
        
        gTabuleiro = new GTabuleiro(tabuleiro, this.jogador, checkBox2P.isSelected());
        gTabuleiro.setVisible(true);
        this.dispose();
    }

    private void ativarMaluquice(Boolean modoMaluco){
        if(modoMaluco)
        {
            System.out.println(sliderMaluquice.getValue());
            this.tabuleiro.posicionarMaluquice(sliderMaluquice.getValue());
        }
    }
}
