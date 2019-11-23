import com.sun.org.apache.xpath.internal.operations.Equals;

import java.net.Inet4Address;
import java.util.LinkedList;

public class Path implements Comparable<Path> {

    private LinkedList<Integer> path ;
    private int cout ;
    private int estimation ;

    static private estimateur estimateur = null;
    static private Graph graph = null;



    public Path(estimateur estim,Graph g){
        Path.graph = g ;
        Path.estimateur = estim ;

        path = new LinkedList<Integer>() ;
        path.add(1);
        this.cout = 0 ;
        this.estimation = estim.estimation(g) ;


    }
    public Path(Path pere , int next ) throws PathNotInitializedException {
        if(graph==null){
            throw new  PathNotInitializedException() ;
        }
        this.path = new LinkedList<Integer>( pere.getPath() ) ;
        this.cout = pere.getCout() + Path.graph.get_arret(pere.getPath().getLast(),next) ;

    }
    public void setEstimation(){
        this.estimation = Path.estimateur.estimation(Path.graph,this) ;
    }


    public LinkedList<Integer> availeble_val(){
        LinkedList<Integer> ls = new LinkedList<Integer>() ;
        for(int i=1;i<=Path.graph.getNb_sommets();i++){

            if(!this.path.contains(i)){
                ls.add(i);
            }
        }
        return ls ;
    }
    public boolean solution(){
        boolean all = true ;
        int i = 1 ;
        while(all&&(i<=graph.getNb_sommets())){
            if(!this.path.contains(i)){
                all  =false ;
            }
            else{
                i++ ;
            }
        }
        if(all){
            this.cout = this.cout + graph.get_arret(path.getLast(),1) ;
        }
        return all ;
    }


    public LinkedList<Integer> getPath(){
        return path ;
    }

    public int getCout(){
        return cout ;
    }


    public int total(){
        return cout + estimation;
    }


    @Override
    public int compareTo(Path o) {
      return   Integer.compare(this.total(),o.total()) ;
    }
    public int hashCode(){
        return path.hashCode() ;
    }

}
