//classe que representa o tabuleiro formado pelo conjunto de celulas

import java.util.Random;

public class Tabuleiro {
    
    private Celula[][] tabuleiro = new Celula[8][8];
    
    
    public Tabuleiro()
    {
        for(int i = 0; i < 8 ; i++){
        for(int j = 0; j < 8; j++){
            tabuleiro[i][j] = new Celula();
        }
        }
    }

    public void printTabuleiro()
    {
        for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){

            if(tabuleiro[i][j].getVisibilidade() == true){
            System.out.printf(" %s ", tabuleiro[i][j].getAparenciaCelula());
            }
            else{
                System.out.print(" _ ");
            }
        }
        System.out.println();
        }
    }

    public void posicionarMinas()
    {
        Random random = new Random();
        int minasRestantes = 10;

        while(minasRestantes > 0)
        {
            int linha = random.nextInt(8);
            int coluna = random.nextInt(8);

            //checagem se já há uma mina
            if(tabuleiro[linha][coluna].getMina() != true)
            {
                tabuleiro[linha][coluna].setMina(true);
                minasRestantes -= 1;
            }
        }
    }

    public void posicionarDicas()
    {
        int contador = 0;
        
        //iteração sob cada celula do tabuleiro
        for(int i = 0; i < 8 ; i++){
        for(int j = 0; j < 8 ; j++){
            contador = 0;
        //leitura dos ajacentes
        for(int linha = i - 1; linha <= i + 1; linha++){
        for(int coluna = j - 1; coluna <= j + 1; coluna++){

            //checagem se a celula existe no tabuleiro
            if(linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8 && (linha != i || coluna != j))
            {
                if(tabuleiro[linha][coluna].getMina() == true)
                {
                    contador++;
                }
            }

        }
        }
        tabuleiro[i][j].setEstado(contador);
        }
        }
    }

    public void esconderCelulas()
    {
        for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){

            tabuleiro[i][j].setVisibilidade(false);

        }
        }
    }

    public void revelarCelulas()
    {
        for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){

            tabuleiro[i][j].setVisibilidade(true);

        }
    }
    }
}
