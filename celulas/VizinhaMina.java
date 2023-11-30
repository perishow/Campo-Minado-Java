package celulas;

public class VizinhaMina extends Celula{

    public VizinhaMina()
    {
        super();
    }

    public boolean isCelulaVazia()
    {
        return false;
    }

    public boolean isMina()
    {
        return false;
    }

    public boolean isVizinhaMina()
    {
        return true;
    }
}

