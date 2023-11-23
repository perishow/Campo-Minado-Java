//MOTHER OF ALL CELLS

package celulas;

abstract class MOAC {

    public abstract void setEstado(int estado);
    
    public abstract int getEstado();
    
    public abstract void setAparencia(String aparencia);

    public abstract String getAparenciaCelula();
    
    public abstract void setVisibilidade(boolean visibilidade);

    public abstract boolean getVisibilidade();

    public abstract void setBandeira(boolean bandeira);

    public abstract boolean getBandeira();

    public abstract boolean isMina();
}
