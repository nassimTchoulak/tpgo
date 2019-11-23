

public class explorateur {

    private Graph g ;
    private estimateur S ;
    private Path racine ;

    public explorateur(int nb){
        this.g = new Graph(nb) ;
        this.S = new sortie_entre_estimateur() ;
        racine = new Path(this.S,this.g) ;

    }
    public void afficher(){
        g.afficher();
    }
    public void explore(){

    }

}
