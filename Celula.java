//unidade de celula que faz parte do tabuleiro

public class Celula{

    private boolean mina;
    private int estado;
    private boolean visibilidade;

    //Construtor
    public Celula()
    {
        this.mina = false;
        this.estado = 0;
        this.visibilidade = true;
    }

    public void setMina(boolean mina) {
       this.mina = mina;
    }

    public boolean getMina()
    {
        return this.mina;
    }

    public void setEstado(int estado)
    {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public String getAparenciaCelula()
    {
        String aparencia = "";

        if(this.mina == true)
        {
            aparencia = "*";
        }
        else if(this.mina == false)
        {
            aparencia = Integer.toString(this.estado);
        }

        return aparencia;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public boolean getVisibilidade()
    {
        return this.visibilidade;
    }
}