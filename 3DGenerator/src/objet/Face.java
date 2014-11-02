package objet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Alex
 * Caract�rise une face par 3 points
 */
public class Face implements Comparable<Face> {
	/**
	 * premier point de la face
	 */
	public Point a;
	/**
	 * Deuxi�me point de la face
	 */
	public Point b;
	/**
	 * Troisi�me point de la face
	 */
	public Point c;
	/**
	 * Baricentre de la face
	 */
	public Point baricentre;
	/**
	 * identifiant de la face
	 */
	public int id;
	
	/**
	 * Constructeur qui prend trois points en param�tres et calcule le baricentre
	 * @param a
	 * @param b
	 * @param c
	 */
	public Face(Point a, Point b, Point c, int id){
		this.a = a;
		this.b = b;
		this.c = c;
		this.id = id;
		this.baricentre = null;
	}
	
	/**
	 * Constructeur qui prend trois segments en param�tres et calcule le baricentre
	 * @param d
	 * @param e
	 * @param f
	 */
	public Face(Segment d, Segment e, Segment f, int id){
		Set<Point> set = new HashSet<Point>();
		set.addAll(d.getPoints());
		set.addAll(e.getPoints());
		set.addAll(f.getPoints());
		Iterator<Point> it =set.iterator();
		this.a = it.next();
		this.b = it.next();
		this.c = it.next();
		this.id = id;
		this.baricentre = null;
	}
	
	/**
	 * retourne un tableau contenant les coordonn�es de tous les points en x
	 * @return
	 */
	public double[] getAllX(){
		return new double[]{a.x,b.x,b.x};
	}

	/**
	 * retourne un tableau contenant les coordonn�es de tous les points en y
	 * @return
	 */
	public double[] getAllY(){
		return new double[]{a.y,b.y,b.y};
	}
	
	/**
	 * retourne un tableau contenant les coordonn�es de tous les points en z
	 * @return
	 */
	public double[] getAllZ(){
		return new double[]{a.z,b.z,b.z};
	}
	
	/**
	 * retourne un tableau contenant les coordonn�es de tous les points en x
	 * chaque coordon�e est multipli� par zoom puis cast en entier pour un affichage graphique
	 * @return
	 */
	public int[] getAllPosX(Point origine,int zoom){
		int o_X = (int)origine.x;
		int[] tmp = new int[]{(int)(a.x*zoom)+o_X,(int)(b.x*zoom)+o_X,(int)(c.x*zoom)+o_X};
		return tmp;
	}

	/**
	 * retourne un tableau contenant les coordonn�es de tous les points en y
	 * chaque coordon�e est multipli� par un zoom puis cast en entier pour un affichage graphique
	 * @return
	 */
	public int[] getAllPosY(Point origine, int zoom){
		int o_Y = (int)origine.y;
		int[] tmp = new int[]{(int)(a.y*zoom)+o_Y,(int)(b.y*zoom)+o_Y,(int)(c.y*zoom)+o_Y};
		return tmp;
	}
	
	/**
	 * retourne un tableau contenant les coordonn�es de tous les points en z
	 * chaque coordon�e est multipli� par zoom puit cast en entier pour un affichage graphique
	 * @return
	 */
	public int[] getAllPosZ(Point origine, int zoom){
		int o_Z = (int)origine.z;
		int[] tmp = new int[]{(int)(a.z*zoom)+o_Z,(int)(b.z*zoom)+o_Z,(int)(c.z*zoom)+o_Z};
		return tmp;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Face [a=" + a + ", b=" + b + ", c=" + c + ", baricentre="
				+ baricentre + ", id=" + id + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Face o) {
		return Point.compare(this.baricentre,o.baricentre);
	}
}
