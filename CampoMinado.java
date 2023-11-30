//arquivo onde o jogo roda

import tabuleiros.*;

public class CampoMinado{
    public static void main(String[] args)
    {
        Tabuleiro tabuleiroComum = new TabuleiroNormal();

        Jogo campoMinado = new Jogo(tabuleiroComum);

        campoMinado.modoMaluco();
    }
}