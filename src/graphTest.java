import org.junit.*;


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
}
