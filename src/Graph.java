import java.util.HashMap;
import java.lang.Math;


public class Graph {
    private static final int range = 461;
    private static final int min = 1;

    private int nb_sommets ;
    private HashMap<String,Integer> graph ;

    public Graph(int nb){
        this.nb_sommets = nb ;
        graph = new HashMap<String,Integer>() ;

        int i = 1 ;
        int j=1 ;
        int val ;
        while(i<=nb){
            j=1;
            while(j<=nb){

                if(i==j){
                    val = 0 ;
                }
                else{
                    val  = (int)(Math.random() * range) + min;
                }
                graph.put(i+"|"+j,val) ;

                j++;
            }
            i++ ;
        }
    }

    public int get_min_leave_from(int from){
        int i = 1 ;
        int min = range ;
        while(i<=this.nb_sommets){
            if((i!=from)&&(min>graph.get(from+"|"+i))){
                min = graph.get(from+"|"+i) ;
            }
            i++;
        }
        return min ;
    }
    public int get_min_go_to(int to){
        int i = 1 ;
        int min = range ;
        while(i<=this.nb_sommets){
            if((i!=to)&&(min>graph.get(i+"|"+to))){
                min = graph.get(i+"|"+to) ;
            }
            i++;
        }
        return min ;
    }


    public int get_arret(int from , int to){
        if((from<=this.nb_sommets)&&(to<=this.nb_sommets)){
            return graph.get(from+"|"+to) ;
        }
        else {
            return -1 ;
        }
    }

    public int getNb_sommets() {
        return nb_sommets;
    }

    public void afficher(){
        int nb = this.nb_sommets ;
        int i = 1 ;
        int j=1 ;

        System.out.format("%3d -",0);
       for(i=1;i<=nb;i++){
           System.out.format("I %3d  ", i);
       }
       i=1 ;
       System.out.println();

        while(i<=nb){
            j=1;
            System.out.format("%3d -",i);
            while(j<=nb){

               // System.out.print( graph.get(i+"|"+j) +" | " ) ;
                System.out.format("| %3d |", graph.get(i+"|"+j));

                j++;
            }
            System.out.println();
            i++ ;
        }
    }
}
