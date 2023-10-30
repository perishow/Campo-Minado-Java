//classe onde o jogo vai rodar

import java.util.Scanner;

public class Jogo {
    public static void main(String[] args){
        
    Tabuleiro tabuleiro = new Tabuleiro();
    Scanner input = new Scanner(System.in);
    boolean jogo = true;

    //configurações iniciais do tabuleiro
      tabuleiro.posicionarMinas();
      tabuleiro.posicionarDicas();
      tabuleiro.printTabuleiro();

      tabuleiro.esconderTabuleiro();


    //loop onde o jogo irá rodar;
    while(jogo)
    {
        System.out.println("-------------------------");
        tabuleiro.printTabuleiro();
        System.out.println();
        
        //input
        System.out.print("Linha: ");
        int linha = input.nextInt();
        linha -= 1;
        
        System.out.print("Coluna: ");
        int coluna = input.nextInt();
        coluna -= 1;

        //checagem de bombas
        if(tabuleiro.checarMina(linha, coluna))
        {
          System.out.println();
          System.out.println(" Achou bomba game over!!");
          System.out.println();
          tabuleiro.revelarMinas();
          tabuleiro.printTabuleiro();
          jogo = false;
          System.out.println("-------------------------");
        }
        else{
          //revelar as celulas
        if(linha < 0 || linha > 7 || coluna < 0 || coluna > 7)
        {
          System.out.println("jogada invalida");
          System.out.println("-------------------------");
          break;
        }
        else
        {
          tabuleiro.revelarCelulas(linha, coluna);
          System.out.println("-------------------------");
        }
        }
    }
    input.close();

  }

}
