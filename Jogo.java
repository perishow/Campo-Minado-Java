/**Classe que engloba os modos de jogo
 * V1 - Jogo base
 * V2 - 2 jogadores
 * V3 - modo maluco
 */

import tabuleiros.*;
import java.util.Scanner;
import java.util.Random;

import Exceptions.AchouMinaException;
import Exceptions.ForaDoTabuleiroException;
import Exceptions.MaluquiceException;

public class Jogo {

  private Tabuleiro tabuleiro;
  private boolean jogoEmAndamento;
  private Scanner input = new Scanner(System.in);

  public Jogo(Tabuleiro tabuleiro)
  {
      this.tabuleiro = tabuleiro;
      this.jogoEmAndamento = true;
  }

  private void jogada(int linha, int coluna) throws ForaDoTabuleiroException
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
      catch(IndexOutOfBoundsException e)
      {
        throw new ForaDoTabuleiroException();
      }
  }
  
  private void posicionarBandeira(int linha, int coluna) throws ForaDoTabuleiroException
  {
    try
    {
      tabuleiro.setBandeira(linha, coluna);
    }
    catch(IndexOutOfBoundsException e)
    {
      throw new ForaDoTabuleiroException();
    }
  }
  
  private void gameOver()
  {
    this.jogoEmAndamento = false;
  }

  public void jogoBase()
  {
    int contador = 1;

    tabuleiro.posicionarMinas();
    tabuleiro.posicionarDicas();
    tabuleiro.revelarTabuleiro();
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
        try{
          jogada(linha, coluna);
        }
        catch(ForaDoTabuleiroException e)
        {
          System.out.println(e.getMessage());
          contador--;
        }
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

        try
        {
          posicionarBandeira(linha, coluna);
        }
        catch(ForaDoTabuleiroException e)
        {
          System.out.println(e.getMessage());
        }
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
    tabuleiro.revelarTabuleiro();
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

      System.out.println("1 - clicar");
      System.out.println("2 - bandeira");
      int escolha = input.nextInt();

      if(escolha == 1)
      {
        System.out.print("Linha: ");
        int linha = input.nextInt();
        
        System.out.print("Coluna: ");
        int coluna = input.nextInt();

        try{
          jogada(linha, coluna);
          if(!jogoEmAndamento)
          {
            contador++;
          }
        }
        catch(ForaDoTabuleiroException e)
        {
          System.out.println(e.getMessage());
          contador--;
        }
      }
      else if(escolha == 2)
      {
        System.out.print("Linha: ");
        int linha = input.nextInt();
        linha--;

        System.out.print("Coluna: ");
        int coluna = input.nextInt();
        coluna--;

        try
        {
          posicionarBandeira(linha, coluna);
          contador--;
        }
        catch(ForaDoTabuleiroException e)
        {
          System.out.println(e.getMessage());
        }
      }

      if(tabuleiro.checagemDeVitoria())
      {
        tabuleiro.revelarMinas();
        tabuleiro.printTabuleiro();
        gameOver();
      }
      
      if(!jogoEmAndamento)
      {
        if(contador % 2 == 1){
        System.out.println("Vitoria do jogador 1");
        }
        else
        {
          System.out.println("Vitoria do jogador 2");
        }
      }

      contador++;
      System.out.println();
    }  
  }

  private int configuraçãoMaluquice() throws MaluquiceException
  {
    System.out.print("Digite um nivel de maluquice entre 1 e 10: ");
    int nivelMaluquice = input.nextInt();
    
    if(nivelMaluquice < 1 || nivelMaluquice > 10)
    {
      throw new MaluquiceException();
    }
    
    return nivelMaluquice;
  }

  public void modoMaluco()
  {
    int contador = 1;
    Random random = new Random();
    int nivelMaluquice = 0;
    boolean inputvalido = false;
    
    while(!inputvalido)
    {
      try
      {
        nivelMaluquice = configuraçãoMaluquice();
        inputvalido = true;
      }
      catch(MaluquiceException e)
      {
        System.out.println(e.getMessage());
      } 
    }
    System.out.printf("Iniciando jogo com nivel %d . . .%n%n", nivelMaluquice);


    tabuleiro.posicionarMinas();
    tabuleiro.posicionarDicas();
    tabuleiro.posicionarMaluquice(nivelMaluquice);
    tabuleiro.revelarTabuleiro();
    tabuleiro.printTabuleiro();
    tabuleiro.esconderTabuleiro();

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
        try{
          jogada(linha, coluna);
        }
        catch(ForaDoTabuleiroException e)
        {
          System.out.println(e.getMessage());
        }
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

        if(!tabuleiro.getVisibilidade(linha, coluna))
        {
          int chance = random.nextInt(10);

          if(tabuleiro.celulaMaluca(linha,coluna) && (chance <= nivelMaluquice))
          {
            tabuleiro.colocarMina(linha, coluna);
          }
          
          if(!tabuleiro.getVisibilidade(linha, coluna)){
            try
            {
              posicionarBandeira(linha, coluna);
              tabuleiro.atualizarDicas();
            }
            catch(ForaDoTabuleiroException e)
            {
              System.out.println(e.getMessage());
            }
          }
        }
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
}  


