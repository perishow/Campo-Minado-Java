//classe que servirá para indicar o jogador e guardar o ranking

public class Jogador {
    private String nome;
    private int pontuacao;

    public Jogador(String nome)
    {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    public void setPontuacao(int pontuacao)
    {
        this.pontuacao = pontuacao;
    }
    
    public int getPontuacao() {
        return pontuacao;
    }
}
