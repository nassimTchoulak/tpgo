import java.util.LinkedList;
import java.util.Iterator;

public class sortie_entre_estimateur implements estimateur {
    private int nb_estimation ;
    public sortie_entre_estimateur(){
        this.nb_estimation = 0 ;
    }

    public int estimation(Graph G, Path path){
        this.nb_estimation ++ ;

        LinkedList<Integer> ls = path.availeble_val() ;
        Iterator<Integer> iterate = ls.iterator();

        int total =  G.get_min_go_to(1) + G.get_min_leave_from(path.getPath().getLast());
        int sommet ;

        while(iterate.hasNext()) {
           // System.out.print(iterate.next());
            sommet = iterate.next() ;
            total = G.get_min_go_to(sommet) + G.get_min_leave_from(sommet) ;

        }
        return total/2 ;
    }
    public int estimation(Graph G){
        nb_estimation ++ ;
        int i =   G.getNb_sommets() ;
        int total = 0 ;
        for(int j=1;j<=i;j++){
            total = total + G.get_min_go_to(j) + G.get_min_leave_from(j) ;
        }
        total = total / 2 ;
        return  total ;
    }
    public int getNb_estimation(){
        return nb_estimation ;
    }
}
