//classe onde o jogo vai rodar

import java.util.Scanner;
import tabuleiros.TabuleiroFacil;
import tabuleiros.*;

public class Jogo {

    private Tabuleiro tabuleiro = new Tabuleiro(12, 10);
    private Scanner input = new Scanner(System.in);
    private boolean jogoEmAndamento = true;
    private int rodada = 1;

    //configurações iniciais do tabuleiro
  public Jogo(int dificuldade)
  {
    
    switch(dificuldade)
    {
      case 0: tabuleiro = new TabuleiroFacil();
                break; 
        
      case 1: tabuleiro = new Tabuleiro(8, 10);
                break;
        
      case 2: tabuleiro = new TabuleiroDificil();
                break;
    }
    tabuleiro.posicionarMinas();
    tabuleiro.posicionarDicas();
    tabuleiro.printTabuleiro();

    tabuleiro.esconderTabuleiro();
  }

  public void jogoBase()
  {
    //loop onde o jogo irá rodar;
    while(jogoEmAndamento)
    {
        System.out.println("-------------------------");
        System.out.printf("Rodada : %d%n%n", this.rodada);

        tabuleiro.printTabuleiro();
        System.out.println();
        
      System.out.println("Selecione uma ação\n1 - revelar celula\n2 - posicionar bandeira");
        int escolha = input.nextInt();

        if(escolha == 1)
        {
            //input + ajustes 
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
            gameOver();
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
            this.rodada++;
            if(tabuleiro.checagemDeVitoria())
            {
              System.out.printf("PARABENS, VOCE VENCEU!!%nTotal de Rodadas: %d%n%n", this.rodada);
              tabuleiro.printTabuleiro();
              gameOver();
            }

          }
          }
        }
        else if(escolha == 2)
        {
              //input + ajustes 
          System.out.print("Linha: ");
          int linha = input.nextInt();
          linha -= 1;
          
          System.out.print("Coluna: ");
          int coluna = input.nextInt();
          coluna -= 1;

          tabuleiro.setBandeira(linha, coluna);
        }
        
    }
    input.close();

  }
  
  public void gameOver()
  {
    this.jogoEmAndamento = false;
  }
}
