/**Classe de teste
 * V1 - criação e print
 * V2 - posicionar minas 
 * V3 - posicionar dicas 
 * V4 - esconder tabuleiro
 */

public class TesteJogo {
    
    public static void main(String[] args)
    {
        Tabuleiro tabuleiro = new Tabuleiro();
        
        tabuleiro.posicionarMinas();
        tabuleiro.printTabuleiro();
        System.out.println();

        tabuleiro.posicionarDicas();
        tabuleiro.printTabuleiro();
        System.out.println();

        tabuleiro.esconderTabuleiro();
        tabuleiro.printTabuleiro();
        System.out.println();
        
    }
}
