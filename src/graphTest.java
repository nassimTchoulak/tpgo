import org.junit.*;

import java.util.LinkedList;


public class graphTest {
    @Test
    public void test1(){

        Graph g = new Graph(6);
        Assert.assertEquals(g.get_arret(6,6),0,0);
        Assert.assertEquals(g.get_arret(5,5),0,0);
        Assert.assertEquals(g.get_arret(1,1),0,0);
        Assert.assertNotEquals(g.get_arret(4,6),0);
        System.out.println(g.get_min_go_to(4));
        System.out.println(g.get_min_go_to(5));
        g.afficher();
    }
    @Test
    public void test2(){
        explorateur ex = new explorateur(6);
        ex.afficher();
    }
    @Test
    public void test_estimation(){
        Graph g = new Graph(6) ;
        //g.afficher();
        estimateur s = new sortie_entre_estimateur() ;
        Path pere  = new Path(s,g) ;
        LinkedList<Integer> li = pere.availeble_val() ;
        System.out.println(li);
        try {
            Path fils = new Path(pere, li.get(2));
            fils.setEstimation();
            System.out.println(fils.getCout());
            System.out.println(fils.total());

        }catch (Exception e){

        }

    }
}
