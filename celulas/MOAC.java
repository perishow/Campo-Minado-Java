//MOTHER OF ALL CELLS

package celulas;

public interface MOAC {


    public abstract void setEstado(int estado);
    
    public abstract int getEstado();
    
    public abstract void setAparencia(String aparencia);

    public abstract String getAparenciaCelula();
    
    public abstract void setVisibilidade(boolean visibilidade);

    public abstract boolean getVisibilidade();

    public abstract void setBandeira(boolean bandeira);

    public abstract boolean getBandeira();

    public abstract boolean isCelulaVazia();

    public abstract boolean isMina();

    public abstract boolean isVizinhaMina();

    public abstract boolean isMaluca();
    
}
