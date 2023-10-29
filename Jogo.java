//classe onde o jogo vai rodar

public class Jogo {
    public static void main(String[] args){
        
        Tabuleiro tabuleiro = new Tabuleiro();

        tabuleiro.printTabuleiro();
        tabuleiro.posicionarMinas();

        System.out.println();
        tabuleiro.printTabuleiro();

        System.out.println();
        tabuleiro.posicionarDicas();
        tabuleiro.printTabuleiro();

        System.out.println();
        tabuleiro.esconderCelulas();
        tabuleiro.printTabuleiro();
  } 
}
