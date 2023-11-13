//classe onde o jogo vai rodar

import java.util.Scanner;

public class Jogo {

    private Tabuleiro tabuleiro = new Tabuleiro();
    private Scanner input = new Scanner(System.in);
    private boolean jogoEmAndamento = true;
    private int rodada = 1;

    //configurações iniciais do tabuleiro
  public Jogo()
  {
    tabuleiro.posicionarMinas();
    tabuleiro.posicionarDicas();
    tabuleiro.printTabuleiro();

    tabuleiro.esconderTabuleiro();
  }

  public void jogar()
  {
    //loop onde o jogo irá rodar;
    while(jogoEmAndamento)
    {
        System.out.println("-------------------------");
        System.out.printf("Rodada : %d%n%n", this.rodada);

        tabuleiro.printTabuleiro();
        System.out.println();
        
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
    input.close();

  }
  
  public void gameOver()
  {
    this.jogoEmAndamento = false;
  }

  public void testeJogo()
  {
    tabuleiro.printTabuleiro();

  }
}
