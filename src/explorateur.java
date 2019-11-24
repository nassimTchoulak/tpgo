import org.junit.Assert;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class explorateur {

    private Graph g ;
    private estimateur S ;
    private Path racine ;
    private PathCollection all_cas = new PathCollection();

    public explorateur(int nb){
        this.g = new Graph(nb) ;
        this.S = new sortie_entre_estimateur() ;
        racine = new Path(this.S,this.g) ;
        all_cas.addValue(racine); ;

    }
    public void afficher() throws Exception{
        g.afficher();
        g.into_file();
    }


    public Path explore() throws Exception{
            Path Z ;
            Path fils ;
            boolean found = false ;
            LinkedList<Integer> aval ;
            int i ;
            int index ;

            while((!this.all_cas.getlist().isEmpty())&&(!found)){

                Z= all_cas.getlist().removeFirst() ;
                System.out.println(Z.total());
                if(Z.solution()){
                    found = true ;
                    return Z ;
                }
                else{
                    aval = Z.availeble_val() ;
                    i=0 ;
                    for(i=0;i<aval.size();i++){
                        fils = new Path(Z,aval.get(i)) ;
                        fils.setEstimation();



                         index  = all_cas.getlist().indexOf(fils) ;
                        if(index!=-1){
                            Path tmp = all_cas.getlist().remove(index) ;

                            if(tmp.compareTo(fils)>0){
                                all_cas.addValue(fils);
                            }
                            else{
                                all_cas.addValue(tmp);
                            }

                        }else{
                            all_cas.addValue(fils);
                        }


                    }
                    //System.out.println(aval.size());
                }


            }
            return null ;

    }

}
