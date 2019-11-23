import java.util.LinkedList;

public interface estimateur {
    public int estimation(Graph G,Path path) ;
    public int estimation(Graph G) ;
    public int getNb_estimation() ;
}
