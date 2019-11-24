public class Main {
    public static void main(String[] arg) throws Exception {

        explorateur exp = new explorateur(13) ;
         estimateur s= new bestEstim() ;

        System.out.println(exp.explore().toString());

        exp.afficher();
    }
}