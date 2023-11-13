//classe que representa o tabuleiro formado pelo conjunto de celulas

import java.util.Random;

public class Tabuleiro {
    
    private Celula[][] tabuleiro = new Celula[8][8];
    
    
    public Tabuleiro()
    {
        for(int i = 0; i < 8 ; i++){
        for(int j = 0; j < 8; j++){
            tabuleiro[i][j] = new CelulaVazia();
        }
        }
    }

    public void printTabuleiro()
    {
        for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){

            if(tabuleiro[i][j].getVisibilidade()){
            System.out.printf(" %s ", tabuleiro[i][j].getAparenciaCelula());
            }
            else if(!(tabuleiro[i][j].getVisibilidade()) && tabuleiro[i][j].getBandeira())
            {
                System.out.print(" ! ");
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
            if(tabuleiro[linha][coluna] instanceof Mina != true)
            {
                Mina mina = new Mina();
                tabuleiro[linha][coluna] = mina;
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

            //checagem se a celula existe no tabuleiro e se não é uma mina
            if(linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8 && (linha != i || coluna != j) && !(tabuleiro[i][j] instanceof Mina))
            {
                if(tabuleiro[linha][coluna] instanceof Mina)
                {
                    contador++;
                }
            }

        }
        }
        
        if(contador > 0){
        VizinhaMina dica = new VizinhaMina();
        String contadorString = Integer.toString(contador);
        
        dica.setAparencia(contadorString);
        tabuleiro[i][j] = dica;
        }

        }
        }
    }

    public void esconderTabuleiro()
    {
        for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){

            tabuleiro[i][j].setVisibilidade(false);

        }
        }
    }

    public void revelarTabuleiro()
    {
        for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){

            tabuleiro[i][j].setVisibilidade(true);

        }
        }
    }

    public void revelarCelulas(int linha, int coluna)
    {
        
        //if else para não revelar o entorno quando a dica na celula for diferente de 0
        if(tabuleiro[linha][coluna].getBandeira())
        {
            tabuleiro[linha][coluna].setBandeira(false);
        }
        else if(tabuleiro[linha][coluna] instanceof VizinhaMina){
            tabuleiro[linha][coluna].setVisibilidade(true);
        }
        else{
        //iteração sob as celulas adjacentes
        for(int i = linha - 1; i <= linha + 1; i++){
        for(int j = coluna - 1; j <= coluna + 1; j++){

            //checagem se a celula é valida, se é uma mina ou se ja está sendo mostrada
            if(i >= 0 && i < 8 && j >= 0 && j < 8 && !(tabuleiro[i][j] instanceof Mina) && !(tabuleiro[i][j].getVisibilidade())){
                tabuleiro[i][j].setVisibilidade(true);
                if(tabuleiro[i][j] instanceof CelulaVazia && i >=0 && i < 8 && j >= 0 && j < 8)
                {
                    revelarCelulas(i,j);
                }
            }            
        }
        }
        }
    }

    public boolean checarMina(int linha, int coluna)
    {
        return tabuleiro[linha][coluna] instanceof Mina;
    }

    public void revelarMinas()
    {
        for(int i = 0; i < 8 ; i++){
        for(int j = 0; j < 8; j++){
            if(tabuleiro[i][j] instanceof Mina)
            {
                tabuleiro[i][j].setVisibilidade(true);
            }
        }
        }
    }

    public boolean checagemDeVitoria()
  {
    
        int contador = 0;

        for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){
        if(tabuleiro[i][j].getVisibilidade() && !(tabuleiro[i][j] instanceof Mina))
        {
            contador++;
        }
        }
    }

        if(contador == 54)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setBandeira(int linha, int coluna)
    {
        tabuleiro[linha][coluna].setBandeira(true);
    }

    public void tiraBandeira(int linha, int coluna)
    {
        tabuleiro[linha][coluna].setBandeira(false);
    }
}
