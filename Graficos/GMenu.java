package Graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;

import playerRank.Jogador;

public class GMenu extends JFrame implements ActionListener {

    private JButton jogarButton;
    private JButton rankButton;
    private JButton instrucoesButton;
    private JPanel panelCima;
    private JPanel panelMeio;

    private Jogador jogador;

    //icons
    private ImageIcon bomberIcon = new ImageIcon("images/bomber.png");
    private ImageIcon playerIcon = new ImageIcon("images/singleplayer.png");

    public GMenu() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = ((screenSize.width - this.getWidth()) / 2) - 210;
        int y = ((screenSize.height - this.getHeight()) / 2) - 210;

        this.setLocation(x, y);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 440);

        Border bordaDourada = BorderFactory.createLineBorder(new Color(255,184,28), 2);

        JLabel labelCima = new JLabel("CAMPO MINADO");
        labelCima.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        labelCima.setForeground(Color.WHITE);

        panelCima = new JPanel();
        panelCima.setBackground(Color.black);
        panelCima.setBorder(bordaDourada);
        panelCima.setPreferredSize(new Dimension(0, 70));
        panelCima.add(labelCima);

        panelMeio = new JPanel(new GridBagLayout());
        panelMeio.setBackground(Color.black);
        panelMeio.setBorder(bordaDourada);

        jogarButton = new JButton("Jogar");
        jogarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        jogarButton.setBackground(Color.black);
        jogarButton.setForeground(Color.white);
        jogarButton.setBorder(BorderFactory.createLineBorder(new Color(255,184,28), 5));
        jogarButton.setPreferredSize(new Dimension(150, 70));
        jogarButton.setFocusable(false);
        jogarButton.addActionListener(this);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        panelMeio.add(jogarButton, gbc);

        rankButton = new JButton("Ranking");
        rankButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        rankButton.setBackground(Color.black);
        rankButton.setForeground(Color.white);
        rankButton.setBorder(BorderFactory.createLineBorder(new Color(255,184,28), 5));
        rankButton.setPreferredSize(new Dimension(150, 70));
        rankButton.setFocusable(false);
        rankButton.addActionListener(this);
        gbc.gridy = 1;
        panelMeio.add(rankButton, gbc);

        instrucoesButton = new JButton("Instruções");
        instrucoesButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        instrucoesButton.setBackground(Color.black);
        instrucoesButton.setForeground(Color.white);
        instrucoesButton.setBorder(BorderFactory.createLineBorder(new Color(255,184,28), 5));
        instrucoesButton.setPreferredSize(new Dimension(150, 70));
        instrucoesButton.setFocusable(false);
        instrucoesButton.addActionListener(this);
        gbc.gridy = 2;
        panelMeio.add(instrucoesButton, gbc);

        this.setResizable(false);
        this.setIconImage(bomberIcon.getImage());
        this.add(panelCima, BorderLayout.NORTH);
        this.add(panelMeio, BorderLayout.CENTER);
        this.setVisible(true);

        //configuração do JOptionPane
        Font comicSans = new Font("Comic Sans MS", Font.PLAIN,20);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", comicSans);
        UIManager.put("OptionPane.buttonFont", comicSans);
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.border", BorderFactory.createLineBorder(new Color(255,184,28), 3));
        UIManager.put("TextField.font", comicSans);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jogarButton) {
            String nome = (String) JOptionPane.showInputDialog(null, "Nome do jogador", "💣Jogador", JOptionPane.PLAIN_MESSAGE, playerIcon, null, null);
            this.jogador = new Jogador(nome);
            if (nome != null) {
                new GModosDeJogo(this.jogador);
            }

        }
        if (e.getSource() == rankButton) {
            new GRanking();
        }
        if(e.getSource() == instrucoesButton){
            new GInstrucoes();
        }
    }
}
