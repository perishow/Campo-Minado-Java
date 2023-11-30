package tabuleiros;

import Exceptions.AchouMinaException;
import Exceptions.ForaDoTabuleiroException;

public interface TabuleiroInterface {
    
    public abstract void printTabuleiro();

    public abstract void posicionarMinas();

    public abstract void colocarMina(int linha, int coluna);

    public abstract void posicionarDicas();

    public abstract void atualizarDicas();

    public abstract void esconderTabuleiro();

    public abstract void revelarTabuleiro();

    public abstract void revelarCelulas(int linha, int coluna) throws AchouMinaException, ForaDoTabuleiroException;

    public abstract boolean checarMina(int linha, int coluna);

    public abstract void revelarMinas();

    public abstract boolean checagemDeVitoria();

    public abstract void setBandeira(int linha, int coluna);

    public abstract boolean getVisibilidade(int linha, int coluna);

}
