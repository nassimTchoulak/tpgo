import org.junit.*;

import java.util.LinkedList;
import java.util.TreeSet;


public class graphTest {

    private Graph g ;
    private estimateur s ;



    @Before
    public void init(){
         g = new Graph(4);
        // s = new sortie_entre_estimateur() ;
         s= new bestEstim() ;
    }




    @Test
    public void test1(){


        Assert.assertEquals(g.get_arret(6,6),0,0);
        Assert.assertEquals(g.get_arret(5,5),0,0);
        Assert.assertEquals(g.get_arret(1,1),0,0);
        Assert.assertNotEquals(g.get_arret(4,6),0);
        System.out.println(g.get_min_go_to(4));
        System.out.println(g.get_min_go_to(5));
        g.afficher();
    }
    @Test
    public void test2() throws Exception{
        explorateur ex = new explorateur(6);
        ex.afficher();
    }
    @Test
    public void cout_test() throws Exception{
        Path pere  = new Path(s,g) ;
        Path fils = new Path(pere,2);
        Assert.assertEquals(g.get_arret(1,2),fils.getCout(),0);
        fils.setEstimation();
        Assert.assertNotEquals(pere.total(),fils.total());

    }
    @Test
    public void first_in(){
        Path pere  = new Path(s,g) ;
        Assert.assertEquals(pere.getPath().indexOf(1),0,0);
        Assert.assertEquals(pere.getPath().indexOf(2),-1,0);
    }

    @Test(expected = NotHamiltonException.class)
    public void test_boucle() throws Exception{
        Path pere  = new Path(s,g) ;
        Path fils = new Path(pere,1) ;
    }
    @Test(expected = PathNotInitializedException.class)
    public void test_not_init() throws Exception{
        Path pere = null ;
        Path fils = new Path(pere,1) ;
    }


    @Test
    public void ls_test(){

        LinkedList<Integer> le = new LinkedList<Integer>() ;
        le.add(2) ;
        le.add(5) ;
        le.add(2);
        TreeSet<Integer> set2 = new TreeSet<Integer>(le);
        TreeSet<Integer> set3 = new TreeSet<Integer>(le);
        Assert.assertEquals(set2,set3);
        Assert.assertTrue(set2.equals(set3));
        set3.add(8);
        Assert.assertNotEquals(set2,set3);
        Assert.assertTrue(!set3.equals(set2));

    }
    @Test
    public void ls() throws Exception{
        PathCollection ls = new PathCollection() ;
        Path pere  = new Path(s,g) ;
        Path fils = new Path(pere,3) ;;
        fils.setEstimation();
        ls.addValue(pere);
        ls.addValue(fils);
        fils = new Path(pere,2);
        ls.addValue(fils);
        fils = new Path(pere,4);
        ls.addValue(fils);
        fils = new Path(pere,4);
        ls.addValue(fils);
        System.out.println(ls.toString() ) ;
        //System.out.println(ls.getlist().removeLast().availeble_val()) ;
    }
    @Test
    public void Test_solution() throws Exception{
        Path pere  = new Path(s,g) ;
        Path fils = new Path(pere,2) ;
         fils = new Path(fils,3) ;
         g.afficher();
        Assert.assertTrue(fils.solution());
         Assert.assertEquals(fils.total()-fils.getCout(),g.get_arret(2,3));
         System.out.println(fils);

    }

    @Test
    public void test_list_delete(){


    }

    @Test
    public void test_exploring() throws Exception{
        explorateur exp = new explorateur(4) ;
        System.out.println(exp.explore().toString()+s.getNb_estimation());

        exp.afficher();
    }

}
