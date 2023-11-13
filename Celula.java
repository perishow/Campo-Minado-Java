//unidade de celula que faz parte do tabuleiro

public class Celula{

    private int estado;
    private boolean visibilidade;
    private String aparencia;

    //Construtor
    public Celula()
    {
        this.estado = 0;
        this.visibilidade = true;
        this.aparencia = "!";
    }

    public void setEstado(int estado)
    {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setAparencia(String aparencia)
    {
        this.aparencia = aparencia;
    }
    public String getAparenciaCelula()
    {
        return this.aparencia;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public boolean getVisibilidade()
    {
        return this.visibilidade;
    }
}