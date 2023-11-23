//arquivo onde o jogo roda

import tabuleiros.*;

public class CampoMinado{
    public static void main(String[] args)
    {
        Tabuleiro tabuleiroComum = new TabuleiroFacil();

        Jogo campoMinado = new Jogo(tabuleiroComum);

        campoMinado.jogoBase();
    }
}