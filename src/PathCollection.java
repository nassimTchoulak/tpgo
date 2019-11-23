import java.util.LinkedList;

public class PathCollection {
    private LinkedList<Path> llist ;


    public PathCollection(){
        llist = new LinkedList<Path>() ;
    }

    public LinkedList<Path> getlist(){
        return this.llist ;
    }

    public void addValue(Path val) {


        if (this.llist.size() == 0) { // if empty
            this.llist.add(val);
        } else if (this.llist.get(0).compareTo(val)>0) { // val is the smallest then insert in top (this.llist.get(0) > val)
            this.llist.add(0, val);
        } else if (this.llist.get(this.llist.size() - 1).compareTo(val)<0 ) { // val is the biggest then insert in bottom (this.llist.get(this.llist.size() - 1) < val)
            this.llist.add(this.llist.size(), val);
        } else { // generale case (this.llist.get(i) < val)
            int i = 0;
            while (this.llist.get(i).compareTo(val)<0 ) {
                i++;
            }
            this.llist.add(i, val);
        }

    }
    public String toString(){
        return this.llist.toString() ;
    }
}
