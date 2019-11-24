import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class bestEstim implements estimateur {
    private int nb_estimation ;
    public bestEstim(){
        this.nb_estimation = 0 ;
    }


    public int estimation(Graph G, Path path){

        //System.out.print(" evaluation de : "+path);


        // available intermediary heads
        LinkedList<Integer> ls = path.availeble_val() ;

        TreeSet<Integer> exclude = new TreeSet<Integer>(path.getPath());

        this.nb_estimation ++ ;
        if(path.solution()){
            return 0 ;
        }
        if(path.getPath().size()==G.getNb_sommets()){
            return G.get_arret(path.getPath().getLast(),1) ;
        }
        if(ls.size()==1){
            int z = ls.getFirst() ;
            return G.get_arret(path.getPath().getLast(),z)+G.get_arret(z,1)  ;
        }


        int total = G.get_min_go_to(1,exclude) + G.get_min_leave_from(path.getPath().getLast(),exclude) ;

        int target ;

        for(int i=0;i<ls.size();i++){
            target = ls.get(i) ;
            total = total + G.get_min_leave_from(target,exclude);
        }
        exclude.remove(path.getPath().getLast()) ;

        for(int i=0;i<ls.size();i++){
            target = ls.get(i) ;
            total = total + G.get_min_go_to(target,exclude);
        }


        //System.out.println("gives : "+total/2);

        return total/2 ;


    }
    public int estimation(Graph G){
        nb_estimation ++ ;

        return  0 ;
    }
    public int getNb_estimation(){
        return nb_estimation ;
    }
}
