/**Classe que engloba os modos de jogo
 * V1 - Jogo base
 * 2 - 2 jogadores
 * 3 - modo maluco
 */

import tabuleiros.*;
import java.util.Scanner;

import Exceptions.AchouMinaException;

public class Jogo {

  private Tabuleiro tabuleiro;
  private boolean jogoEmAndamento;
  private Scanner input = new Scanner(System.in);

  public Jogo(Tabuleiro tabuleiro)
  {
      this.tabuleiro = tabuleiro;
      this.jogoEmAndamento = true;
  }

  public void jogada(int linha, int coluna)
  {
    //ajuste no input
    linha--;
    coluna--;

    try
      {
      this.tabuleiro.revelarCelulas(linha, coluna);
      }
      catch(AchouMinaException e)
      {
        System.out.println();
        tabuleiro.revelarMinas();
        tabuleiro.printTabuleiro();
        System.out.println(e.getMessage());
        gameOver();
      }
  }
  
  
  public void gameOver()
  {
    this.jogoEmAndamento = false;
  }

  
  public void jogoBase()
  {
    int contador = 1;

    tabuleiro.posicionarMinas();
    tabuleiro.posicionarDicas();
    tabuleiro.printTabuleiro();

    System.out.println();

    tabuleiro.esconderTabuleiro();

    while(this.jogoEmAndamento)
    {
      System.out.printf("Rodada %d%n",contador);

      tabuleiro.printTabuleiro();
      System.out.println();

      System.out.println("1 - clicar");
      System.out.println("2 - bandeira");
      int escolha = input.nextInt();

      if(escolha == 1)
      {
        System.out.print("Linha: ");
        int linha = input.nextInt();
        
        System.out.print("Coluna: ");
        int coluna = input.nextInt();

        jogada(linha, coluna);
        contador++;
      }
      else if(escolha == 2)
      {
        System.out.print("Linha: ");
        int linha = input.nextInt();
        linha--;

        System.out.print("Coluna: ");
        int coluna = input.nextInt();
        coluna--;
        
        tabuleiro.setBandeira(linha, coluna);
      }

      if(tabuleiro.checagemDeVitoria())
      {
        tabuleiro.revelarMinas();
        tabuleiro.printTabuleiro();
        System.out.println("Vitoria, Parabens!!!");
        System.out.println("Total de rodadas = " + contador);
        gameOver();
      }
      System.out.println();
    }  
  }
  public void doisJogadores()
  {
    int contador = 1;

    tabuleiro.posicionarMinas();
    tabuleiro.posicionarDicas();
    tabuleiro.printTabuleiro();

    System.out.println();

    tabuleiro.esconderTabuleiro();

    while(this.jogoEmAndamento)
    {
      if(contador % 2 == 1)
      {
        System.out.println("Jogador 1");
      }
      else
      {
        System.out.println("Jogador 2");
      }

      tabuleiro.printTabuleiro();
      System.out.println();

      System.out.print("Linha: ");
      int linha = input.nextInt();
      
      System.out.print("Coluna: ");
      int coluna = input.nextInt();

      jogada(linha, coluna);

      if(tabuleiro.checagemDeVitoria())
      {
        tabuleiro.revelarMinas();
        tabuleiro.printTabuleiro();
        if(contador % 2 == 1){
        System.out.println("Vitoria do jogador 1");
        }
        else
        {
          System.out.println("Vitoria do jogador 2");
        }
        gameOver();
      }
      contador++;
      System.out.println();
    }  
  }
}  


