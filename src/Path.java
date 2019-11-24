
import java.util.LinkedList;
import java.util.Objects;
import java.util.TreeSet;

public class Path implements Comparable<Path> {

    private LinkedList<Integer> path ;
    private int cout ;
    private int estimation = 0 ;

    static private estimateur estimateur = null;
    static private Graph graph = null;
    private boolean is_sol ;



    public Path(estimateur estim,Graph g){
        Path.graph = g ;
        Path.estimateur = estim ;

        path = new LinkedList<Integer>() ;
        path.add(1);
        this.cout = 0 ;
        this.estimation = estim.estimation(g) ;



    }
    public Path(Path pere , int next ) throws Exception {
        if(graph==null){
            throw new  PathNotInitializedException() ;
        }
        if(pere.getPath().indexOf(next)>0){ // retest this which wont happen ever
            throw new NotHamiltonException() ;
        }

        this.path = new LinkedList<Integer>( pere.getPath() ) ;
        this.path.add(next);
        this.cout = pere.getCout() + Path.graph.get_arret(pere.getPath().getLast(),next) ;

    }
    public void setEstimation(){
        this.estimation = Path.estimateur.estimation(Path.graph,this) ;
    }


    public LinkedList<Integer> availeble_val(){
        LinkedList<Integer> ls = new LinkedList<Integer>() ;
        for(int i=2;i<=Path.graph.getNb_sommets();i++){

            if(!this.path.contains(i)){
                ls.add(i);
            }
        }
        if(ls.size()==0){
            ls.add(1) ;
        }
        return ls ;
    }

    public boolean solution(){
        if((this.getPath().getLast()==this.getPath().getFirst())&&(this.getPath().size()>1)){
            return true ;

        }
        else{
            return false ;
        }
    }


    public LinkedList<Integer> getPath(){
        return path ;
    }

    public int getCout(){
        return cout ;
    }


    public int total() {
        return cout + estimation;
    }


    @Override
    public int compareTo(Path o) {
        if(this.total() > o.total()) {
            return 1;
        } else if (this.total() < o.total()) {
            return -1;
        } else {
            return 0;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path pt = (Path) o;
        if(!this.getPath().getLast().equals(pt.getPath().getLast())){
            return false ;
        }
        else{
            if(this.getPath().size()!=this.getPath().size()){
                return false ;
            }

            TreeSet<Integer> set = new TreeSet<Integer>(this.getPath());
            TreeSet<Integer> set2 = new TreeSet<Integer>(pt.getPath());
            return set.equals(set2);

        }

    }

    public String toString(){
        if(this.solution()) // if(this.solution())
        return this.path.toString() +" | cost: "+this.cout+" | ";
        else{
            return this.path.toString() +" | cost: "+this.cout+" | estim: "+this.estimation +" total@@"+this.total();
        }
    }


    public int hashCode(){
        return Objects.hash(this.cout,this.path.hashCode());
    }

    public int real_cout(){
        return this.cout + graph.get_arret(path.getLast(),1) ;
    }

}
