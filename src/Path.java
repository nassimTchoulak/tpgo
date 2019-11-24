
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

        this.is_sol = false ;


    }
    public Path(Path pere , int next ) throws Exception {
        if(graph==null){
            throw new  PathNotInitializedException() ;
        }
        if(pere.getPath().indexOf(next)!=-1){
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
        for(int i=1;i<=Path.graph.getNb_sommets();i++){

            if(!this.path.contains(i)){
                ls.add(i);
            }
        }
        return ls ;
    }
    public boolean solution(){
        if(this.is_sol){
            return true ;
        }
        boolean all = true ;
        int i = 1 ;
        while((all)&&(i<=graph.getNb_sommets())){
            if(!this.path.contains(i)){
                all  =false ;;
            }
            else{
                i++ ;
            }
        }
        if(all){
            this.cout = this.cout + graph.get_arret(path.getLast(),1) ;
            this.is_sol = true ;
        }
        return all ;
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
        if(this.solution())
        return this.path.toString() +" | cost: "+this.cout+" | ";
        else{
            return this.path.toString() +" | cost: "+this.cout+" | estim: "+this.estimation;
        }
    }


    public int hashCode(){
        return Objects.hash(this.cout,this.path.hashCode());
    }

}
