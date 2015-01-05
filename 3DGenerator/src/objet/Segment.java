package objet;

import java.util.ArrayList;
import java.util.List;

/**
 * Caract�rise un segment
 * @author Alex
 */
public class Segment{
	/**
	 * point de d�but
	 */
	public Point a;
	/**
	 * point de fin
	 */
	public Point b;
	
	/**
	 * constructeur standard
	 * @param a
	 * @param b
	 */
	public Segment(Point a, Point b){
		this.a = a;
		this.b = b;
	}
	
	/**
	 * r�cup�re les deux points d'un segment sous forme de liste
	 * @return
	 */
	public List<Point> getPoints(){
		List<Point> tmp = new ArrayList<Point>();
		tmp.add(a);
		tmp.add(b);
		return tmp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Segment [a=" + a + ", b=" + b + "]";
	}
	
	
}
