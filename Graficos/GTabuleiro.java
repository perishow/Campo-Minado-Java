package Graficos;

//prototipo de agrupamento de JButtons

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Exceptions.AchouMinaException;
import Exceptions.ForaDoTabuleiroException;
import celulas.Celula;
import celulas.Mina;
import playerRank.Jogador;
import tabuleiros.Tabuleiro;

public class GTabuleiro extends JFrame{
    
    private JLabel[][] label;
    private JButton[][] button;
    private JLayeredPane[][] layeredPane;
    private JPanel[][] panel;
    private JPanel panelPrincipal;
    private JPanel panelSuperior;
    private Celula[][] cTabuleiro;
    private int tamanho;
    private Jogador jogador;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel labelRodada;
    private int rodada;

    //icons
    private ImageIcon bombaIcon = new ImageIcon("images/bomba.png");
    private ImageIcon player1Icon = new ImageIcon("images/singleplayer.png");
    private ImageIcon player2Icon = new ImageIcon("images/secondplayer.png");
    private ImageIcon bomberIcon = new ImageIcon("images/bomber.png");

    public GTabuleiro(Tabuleiro tabuleiro, Jogador jogador, Boolean twoPLayers){
        
        this.tamanho = tabuleiro.getDimensao();
        this.cTabuleiro = tabuleiro.getTabuleiro();
        this.jogador = jogador;

        this.setSize(tamanho*60,(tamanho*60) + 70);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth())/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight())/2;

        this.setLocation(x,y);

        Border bordaDourada = BorderFactory.createLineBorder(new Color(255,184,28), 2);
        
        label = new JLabel[tamanho][tamanho];
        button = new JButton[tamanho][tamanho];
        panel = new JPanel[tamanho][tamanho];
        layeredPane = new JLayeredPane[tamanho][tamanho];
        panelPrincipal = new JPanel(new GridLayout(tamanho,tamanho));
        panelSuperior = new JPanel(new BorderLayout());
        rodada = 0;

        if(!twoPLayers){
            labelRodada = new JLabel("rodada " + Integer.toString(rodada));
            labelRodada.setFont(new Font("Comic Sans MS",Font.PLAIN, 40));
            labelRodada.setForeground(Color.white);
            labelRodada.setHorizontalAlignment(JLabel.CENTER);
            labelRodada.setVerticalAlignment(JLabel.CENTER);     

            /*panelSuperior.setBorder(BorderFactory.createEtchedBorder());
            panelSuperior.setPreferredSize(new Dimension(0,70));
            panelSuperior.setBackground(new Color(192,192,192));*/
            
            panelSuperior.add(labelRodada);
        }
        else if(twoPLayers){

            player1Label = new JLabel(player1Icon);
            player2Label = new JLabel(player2Icon);
            player2Label.setVisible(false);

            player1Label.setPreferredSize(new Dimension(50,50));
            
            player2Label.setPreferredSize(new Dimension(50,50));   

            panelSuperior.setBorder(bordaDourada);
            panelSuperior.setBackground(Color.BLACK);
            panelSuperior.add(player1Label,BorderLayout.WEST);
            panelSuperior.add(player2Label,BorderLayout.EAST);
        }

        panelPrincipal.setBorder(bordaDourada);
        panelSuperior.setBorder(bordaDourada);
        panelSuperior.setBackground(Color.BLACK);

        for(int i = 0; i < tamanho ; i++){
            for(int j = 0; j < tamanho; j++){
                final int linha = i;
                final int col = j;
                button[i][j] = new JButton();

                button[i][j].setBounds(0,0,60,60);
                button[i][j].setFocusable(false);
                button[i][j].setFont(new Font("fontegenerica", Font.PLAIN, 25));
                button[i][j].setBackground(Color.lightGray);
                button[i][j].setForeground(Color.black);
                button[i][j].setBorder(BorderFactory.createLineBorder(new Color(143,143,143), 3));

                button[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e){
                        if(e.getButton() == MouseEvent.BUTTON1 && !cTabuleiro[linha][col].getBandeira()){
                            try{
                                tabuleiro.revelarCelulas(linha, col);
                                rodada++;
                                if(!twoPLayers){
                                    labelRodada.setText("rodada " + Integer.toString(rodada));
                                }
                                else if(twoPLayers){
                                    if(rodada % 2 == 0){
                                        player1Label.setVisible(true);
                                        player2Label.setVisible(false);
                                    }
                                    else if(rodada % 2 == 1){
                                        player1Label.setVisible(false);
                                        player2Label.setVisible(true);
                                    }
                                }
                                if(tabuleiro.checagemDeVitoria()){

                                    if(!twoPLayers){
                                        gameOver();
                                    }
                                    else if(twoPLayers){
                                        gameOver2Vitoria();
                                    }
                                    desativarJogo();

                                }
                            }catch(AchouMinaException achoumina){
                                
                                desativarJogo();
                                if(!twoPLayers){
                                    gameOver();
                                }
                                else if(twoPLayers){
                                    gameOver2Derrota();
                                }
                                
                            }catch(ForaDoTabuleiroException f){
                                System.out.println("out of bounds aparentemente");
                            }

                            for(int i = 0; i < tamanho; i++){
                                for(int j = 0 ; j < tamanho; j++){
                                    if(cTabuleiro[i][j].getVisibilidade()){
                                        button[i][j].setVisible(false);
                                    }
                                }
                            }
                        }
                        else if(e.getButton() == MouseEvent.BUTTON3){
    
                            cTabuleiro[linha][col].setBandeira(!cTabuleiro[linha][col].getBandeira()); //inverte o bool da bandeira
                            
                            if(cTabuleiro[linha][col].getBandeira() == true){
                                button[linha][col].setText("🏁");
                            }
                            else if(cTabuleiro[linha][col].getBandeira() == false){
                                button[linha][col].setText("");
                            }

                            if(cTabuleiro[linha][col].isMaluca() && !cTabuleiro[linha][col].isMina()){
                                cTabuleiro[linha][col] = new Mina();
                                tabuleiro.atualizarDicas();
                                atualizarLabels();
                            }

                        }
                    }

                });
                label[i][j] = new JLabel(cTabuleiro[i][j].getAparenciaCelula());

                if(label[i][j].getText().equals("*")){
                    label[i][j].setText("");
                    label[i][j].setIcon(bombaIcon);
                }
                if(label[i][j].getText().equals("0")){
                    label[i][j].setText("");
                }
                if(label[i][j].getText().equals("1")){
                    label[i][j].setForeground(Color.BLUE);
                }
                if(label[i][j].getText().equals("2")){
                    label[i][j].setForeground(new Color(34,139,34));
                }
                if(label[i][j].getText().equals("3")){
                    label[i][j].setForeground(Color.RED);
                }
                if(label[i][j].getText().equals("4")){
                    label[i][j].setForeground(new Color(13,33,79));
                }
                if(label[i][j].getText().equals("5")){
                    label[i][j].setForeground(new Color(80,48,30));
                }
                if(label[i][j].getText().equals("6")){
                    label[i][j].setForeground(new Color(0, 139, 139));
                }
                if(label[i][j].getText().equals("7")){
                    label[i][j].setForeground(Color.BLACK);
                }
                if(label[i][j].getText().equals("8")){
                    label[i][j].setForeground(Color.DARK_GRAY);
                }

                label[i][j].setFont(new Font("Comic Sans MS", Font.BOLD,50));
                label[i][j].setBounds(0,0,60,60);
                label[i][j].setHorizontalAlignment(JLabel.CENTER);
                label[i][j].setVerticalAlignment(JLabel.CENTER);

                panel[i][j] = new JPanel(new BorderLayout());
                panel[i][j].setBounds(0,0,60,60);
                panel[i][j].setBorder(BorderFactory.createBevelBorder(1));
                panel[i][j].setBackground(new Color(170,170,170));

                if(cTabuleiro[i][j].isMaluca()){
                    panel[i][j].setBackground(new Color(122,198,198));
                }

                panel[i][j].add(label[i][j]);

                layeredPane[i][j] = new JLayeredPane();
                layeredPane[i][j].add(panel[i][j], JLayeredPane.DEFAULT_LAYER);
                layeredPane[i][j].add(button[i][j], JLayeredPane.DRAG_LAYER);
                
                panelPrincipal.add(layeredPane[i][j]);
            }
        }

        this.setResizable(false);
        this.setIconImage(bomberIcon.getImage());
        this.add(panelPrincipal);
        this.add(panelSuperior,BorderLayout.NORTH);
        this.setVisible(false);
    }

    private void gameOver(){
        new GGameOver(this.jogador, calcularPontuacao(), this);
    }

    private void gameOver2Vitoria(){
        new GGameOver2((rodada + 1) % 2, this);
    }

    private void gameOver2Derrota(){
        new GGameOver2(rodada%2,this);
    }

    private int calcularPontuacao(){
        
        int casasAbertas = 0;
        int pontosTotais = 0;

        for(int i = 0; i < this.tamanho; i++){
            for(int j = 0; j < this.tamanho; j++){
                if(cTabuleiro[i][j].getVisibilidade() && !cTabuleiro[i][j].isMina()){
                    casasAbertas++;
                }
            }
        }

        pontosTotais = casasAbertas * 100;
        return pontosTotais;
    }

    private void atualizarLabels(){
        for(int i = 0; i < tamanho; i++){
            for(int j = 0 ; j < tamanho; j++){
                label[i][j].setText(cTabuleiro[i][j].getAparenciaCelula());

                if(label[i][j].getText().equals("*")){
                    label[i][j].setText("");
                    label[i][j].setIcon(bombaIcon);
                }
                if(label[i][j].getText().equals("0")){
                    label[i][j].setText("");
                }
                if(label[i][j].getText().equals("1")){
                    label[i][j].setForeground(Color.BLUE);
                }
                if(label[i][j].getText().equals("2")){
                    label[i][j].setForeground(Color.GREEN);
                }
                if(label[i][j].getText().equals("3")){
                    label[i][j].setForeground(Color.RED);
                }
                if(label[i][j].getText().equals("4")){
                    label[i][j].setForeground(new Color(13,33,79));
                }
                if(label[i][j].getText().equals("5")){
                    label[i][j].setForeground(new Color(80,48,30));
                }
                if(label[i][j].getText().equals("6")){
                    label[i][j].setForeground(new Color(0, 139, 139));
                }
                if(label[i][j].getText().equals("7")){
                    label[i][j].setForeground(Color.BLACK);
                }
                if(label[i][j].getText().equals("8")){
                    label[i][j].setForeground(Color.DARK_GRAY);
                }
            }
        }
    }

    private void desativarJogo(){
        for(int i = 0; i < tamanho; i++){
            for(int j = 0; j < tamanho; j++){

                button[i][j].setVisible(false);
        }
    }
}
}


