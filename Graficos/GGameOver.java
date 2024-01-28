package Graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import playerRank.Jogador;
import playerRank.RankMaster;

public class GGameOver extends JFrame implements ActionListener{
    
    private JPanel panel;
    private JPanel panelBotao;
    private JLabel labelGameOver;
    private JLabel labelPontuacao;
    private JButton button;
    
    private int x;
    private int y;
    private int pontuacao;
    private JFrame GTabuleiro;
    private Jogador jogador;

    //icons
    ImageIcon bomberIcon = new ImageIcon("images/bomber.png");

    public GGameOver(Jogador jogador,int pontuacao, JFrame GTabuleiro){
        this.GTabuleiro = GTabuleiro;
        this.pontuacao = pontuacao;
        this.jogador = jogador;

        this.x = getToolkit().getScreenSize().width;
        this.y = getToolkit().getScreenSize().height;

        this.setLocation((x/2)-250, (y/2)-100);
        
        this.setSize(500,240);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        labelGameOver = new JLabel("GAME OVER!");
        labelGameOver.setFont(new Font("Comic Sans MS",Font.BOLD, 70));
        labelGameOver.setForeground(Color.white);
        labelGameOver.setHorizontalAlignment(JLabel.CENTER);
        labelGameOver.setVerticalAlignment(JLabel.CENTER);

        labelPontuacao = new JLabel("Pontuação : " + this.pontuacao);
        labelPontuacao.setFont(new Font("Comic Sans MS",Font.PLAIN, 35));
        labelPontuacao.setForeground(new Color(255,184,28));
        labelPontuacao.setHorizontalAlignment(JLabel.CENTER);
        labelPontuacao.setVerticalAlignment(JLabel.CENTER);

        button = new JButton("ok");
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(100,50));
        button.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        button.setBackground(Color.lightGray);
        button.addActionListener(this);

        panelBotao = new JPanel();
        panelBotao.setPreferredSize(new Dimension(170,0));
        panelBotao.setBackground(Color.RED);
        panelBotao.add(Box.createRigidArea(new Dimension(0,90)));
        panelBotao.add(button);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.red);
        panel.add(labelGameOver,BorderLayout.NORTH);
        panel.add(labelPontuacao,BorderLayout.CENTER);
        panel.add(panelBotao,BorderLayout.EAST);        


        this.setIconImage(bomberIcon.getImage());
        this.add(panel);
        //this.setUndecorated(true);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){
            this.dispose();
            this.GTabuleiro.dispose();
            this.jogador.setPontuacao(pontuacao);
            new RankMaster(this.jogador);
        }
    }
}

