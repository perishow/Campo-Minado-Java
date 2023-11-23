//unidade de celula que faz parte do tabuleiro

package celulas;


public class Celula{

    private int estado;
    private boolean visibilidade;
    private String aparencia;
    private boolean bandeira;

    //Construtor
    public Celula()
    {
        this.estado = 0;
        this.visibilidade = true;
        this.aparencia = "!";
        this.bandeira = false;
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

    public void setBandeira(boolean bandeira)
    {
        this.bandeira = bandeira;
    }

    public boolean getBandeira()
    {
        return this.bandeira;
    }
}