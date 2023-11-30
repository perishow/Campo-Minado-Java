package celulas;

public class Mina extends Celula {

    public Mina(){
        super();
        setAparencia("*");
    }

    public boolean isCelulaVazia()
    {
        return false;
    }

    public boolean isMina()
    {
        return true;
    }

    public boolean isVizinhaMina()
    {
        return false;
    }
    
}

