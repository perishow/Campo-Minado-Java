package celulas;

public class CelulaVazia extends Celula{

    public CelulaVazia(){
        super();
        setAparencia("0");
    }
    
    public boolean isCelulaVazia()
    {
        return true;
    }

    public boolean isMina()
    {
        return false;
    }

    public boolean isVizinhaMina()
    {
        return false;
    }
}
