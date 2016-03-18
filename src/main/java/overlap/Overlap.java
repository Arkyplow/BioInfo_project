package overlap;

import java.util.ArrayList;


/**
 * @author Jannou Brohee
 */
public class Overlap {

    private ArrayList<Sommet> sommets = new ArrayList<Sommet>();
    private ArrayList<Arc> arcs = new ArrayList<Arc>();
    public Overlap(){}

    public void addSommet(Sommet s ){
        sommets.add(s);
    }


}
