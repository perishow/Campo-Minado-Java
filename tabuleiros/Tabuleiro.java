//classe que representa o tabuleiro formado pelo conjunto de celulas

package tabuleiros;

import java.util.Random;
import celulas.*;
import Exceptions.*;

public class Tabuleiro implements TabuleiroInterface{
    
    private Celula[][] tabuleiro;
    private int dimensao;
    private int minas;
    private int maluquice;

    public Tabuleiro(int dimensao, int minas)
    {
        this.dimensao = dimensao;
        this.minas = minas;
        this.tabuleiro = new Celula[dimensao][dimensao];

        for(int i = 0; i < dimensao ; i++){
        for(int j = 0; j < dimensao; j++){
            tabuleiro[i][j] = new CelulaVazia();
        }
        }
        this.posicionarMinas();
        this.posicionarDicas();
    }

    public int getDimensao() {
        return dimensao;
    }

    public int getMinas() {
        return minas;
    }

    public Celula[][] getTabuleiro() {
        return tabuleiro;
    }
    public void printTabuleiro()
    {
        for(int i = 0; i < dimensao; i++){
        for(int j = 0; j < dimensao; j++){

            if(tabuleiro[i][j].getVisibilidade()){
            System.out.printf(" %s ", tabuleiro[i][j].getAparenciaCelula());
            }
            else if(!(tabuleiro[i][j].getVisibilidade()) && tabuleiro[i][j].getBandeira())
            {
                System.out.print(" @ ");
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
    
        int minasRestantes = this.minas;

        while(minasRestantes > 0)
        {
            int linha = random.nextInt(dimensao);
            int coluna = random.nextInt(dimensao);

            //checagem se já há uma mina
            if(!(tabuleiro[linha][coluna].isMina()))
            {
                Mina mina = new Mina();
                tabuleiro[linha][coluna] = mina;
                minasRestantes -= 1;
            }
        }
    }

    public void colocarMina(int linha, int coluna)
    {
        Mina mina = new Mina();
        tabuleiro [linha][coluna] = mina;
    }

    public void posicionarDicas()
    {
        int contador = 0;
        
        //iteração sob cada celula do tabuleiro
        for(int i = 0; i < dimensao ; i++){
        for(int j = 0; j < dimensao ; j++){
            contador = 0;
        //leitura dos ajacentes
        for(int linha = i - 1; linha <= i + 1; linha++){
        for(int coluna = j - 1; coluna <= j + 1; coluna++){

            //checagem se a celula existe no tabuleiro e se não é uma mina
            if(linha >= 0 && linha < dimensao && coluna >= 0 && coluna < dimensao && (linha != i || coluna != j) && !(tabuleiro[i][j].isMina()))
            {
                if(tabuleiro[linha][coluna].isMina())
                {
                    contador++;
                }
            }

        }
        }
        
        if(contador > 0){
        VizinhaMina dica = new VizinhaMina();
        String contadorString = Integer.toString(contador);
        dica.setEstado(contador);
        dica.setAparencia(contadorString);
        tabuleiro[i][j] = dica;
        }

        }
        }
    }

    public void atualizarDicas() {
        int contador = 0;
        
        
        //iteração sob cada celula do tabuleiro
        for(int i = 0; i < dimensao ; i++){
            for(int j = 0; j < dimensao ; j++){
                contador = 0;
                
                //leitura dos ajacentes
                for(int linha = i - 1; linha <= i + 1; linha++){
                    for(int coluna = j - 1; coluna <= j + 1; coluna++){

                            //checagem se a celula existe no tabuleiro e se não é uma mina
                            if(linha >= 0 && linha < dimensao && coluna >= 0 && coluna < dimensao && (linha != i || coluna != j) && !(tabuleiro[i][j].isMina()))
                            {
                                if(tabuleiro[linha][coluna].isMina())
                                {
                                    contador++;
                                }
                            }

                        }
                    }
                    
                if(contador > 0 && !tabuleiro[i][j].isMina()){

                    String contadorString = Integer.toString(contador);
                    tabuleiro[i][j].setEstado(contador);
                    tabuleiro[i][j].setAparencia(contadorString);
                }
                
            }
        }

        System.out.println("feito");
    }

    public void esconderTabuleiro()
    {
        for(int i = 0; i < dimensao; i++){
        for(int j = 0; j < dimensao; j++){

            tabuleiro[i][j].setVisibilidade(false);

        }
        }
    }

    public void revelarTabuleiro()
    {
        for(int i = 0; i < dimensao; i++){
        for(int j = 0; j < dimensao; j++){

            tabuleiro[i][j].setVisibilidade(true);

        }
        }
    }

    public void revelarCelulas(int linha, int coluna) throws AchouMinaException, ForaDoTabuleiroException
    {
        
        //if else para não revelar o entorno quando a dica na celula for diferente de 0
        if(linha < 0 || linha > dimensao || coluna < 0 || linha > dimensao)
        {
            throw new ForaDoTabuleiroException();
        }
        
        if(tabuleiro[linha][coluna].isMina())
        {
            throw new AchouMinaException();
        }
        else if(tabuleiro[linha][coluna].isVizinhaMina() || (tabuleiro[linha][coluna].isMaluca() && !tabuleiro[linha][coluna].getAparenciaCelula().equals("0"))){
            tabuleiro[linha][coluna].setVisibilidade(true);
        }
        else if(tabuleiro[linha][coluna].isCelulaVazia()){
        //iteração sob as celulas adjacentes
        for(int i = linha - 1; i <= linha + 1; i++){
        for(int j = coluna - 1; j <= coluna + 1; j++){

            //checagem se a celula é valida, se é uma mina ou se ja está sendo mostrada
            if(i >= 0 && i < dimensao && j >= 0 && j < dimensao && !(tabuleiro[i][j].isMina()) && !(tabuleiro[i][j].getVisibilidade())){
                tabuleiro[i][j].setVisibilidade(true);

                if(tabuleiro[i][j].isCelulaVazia() && i >=0 && i < dimensao && j >= 0 && j < dimensao)
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
        return tabuleiro[linha][coluna].isMina();
    }

    public void revelarMinas()
    {
        for(int i = 0; i < dimensao ; i++){
        for(int j = 0; j < dimensao; j++){
            if(tabuleiro[i][j].isMina())
            {
                tabuleiro[i][j].setVisibilidade(true);
            }
        }
        }
    }

    public boolean checagemDeVitoria()
    {

        for(int i = 0; i < dimensao; i++){
        for(int j = 0; j < dimensao; j++){
        if(!(tabuleiro[i][j].getVisibilidade()) && !(tabuleiro[i][j].isMina()))
        {
            return false;
        }
        }
        }
        return true;
    }

    public void setBandeira(int linha, int coluna)
    {
        if(tabuleiro[linha][coluna].getVisibilidade() == false)
        {
            if(tabuleiro[linha][coluna].getBandeira()){
            tabuleiro[linha][coluna].setBandeira(false);
            }
            else if(!(tabuleiro[linha][coluna].getBandeira()))
            {
                tabuleiro[linha][coluna].setBandeira(true);
            }
        }
        
    }

    public void posicionarMaluquice(int nivelMaluquice)
    {
        this.maluquice = nivelMaluquice;
        Random random = new Random();

        for(int i = 0; i < this.dimensao; i++)
        for(int j = 0; j < this.dimensao; j++)
        {
        {
            int chance = random.nextInt(11); //de 0 a 10
            if(chance <= nivelMaluquice)
            {
                tabuleiro[i][j].tornarMaluca();
            }
        }
        }
    }

    public int getMaluquice() {
        return maluquice;
    }

    public boolean celulaMaluca(int linha, int coluna)
    {
        return tabuleiro[linha][coluna].isMaluca();
    }

    public boolean getVisibilidade(int linha, int coluna)
    {
        return tabuleiro[linha][coluna].getVisibilidade();
    }
}
