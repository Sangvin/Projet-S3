package objet;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import autre.Outils;

/**
 * @author Alex
 * Caractérise une face par 3 points
 */
public class Face implements Comparable<Face> {
	/**
	 * premier point de la face
	 */
	public Point a;
	/**
	 * Deuxième point de la face
	 */
	public Point b;
	/**
	 * Troisième point de la face
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
	 * couleur de la face
	 */
	private Color color;

	/**
	 * Constructeur qui prend trois points en paramètres et calcule le baricentre
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
	 * Constructeur qui prend trois segments en paramètres et calcule le baricentre
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
	 * retourne la couleur de la face
	 * @return
	 */
	public Color getColor(){
		return this.color;
	}

	/**
	 * calcule la couleur de l'image en prenant compte la lumière
	 * @param col
	 */
	public void setColor(Color col){
		Point ab = new Point(b.x-a.x,b.y-a.y,b.z-a.z);
		Point ac = new Point(c.x-a.x,c.y-a.y,c.z-a.z);
		Point normal = new Point((ab.y*ac.z-ab.z*ac.y),-(ab.x*ac.z-ab.z*ac.x),(ab.x*ac.y-ab.y*ac.x));
		Point lumiere = new Point(0,0,-1);
		double percent = Outils.scalaire(normal, lumiere);
		percent = percent / (Outils.norme(normal)*Outils.norme(lumiere));
		float[] hsbCol = Color.RGBtoHSB(col.getRed(), col.getGreen(), col.getBlue(), null);
		hsbCol[2] = (float) percent;
		this.color = Color.getHSBColor(hsbCol[0], hsbCol[1], hsbCol[2]);
	}

	/**
	 * retourne un tableau contenant les coordonnées de tous les points en x
	 * @return
	 */
	public double[] getAllX(){
		return new double[]{a.x,b.x,b.x};
	}

	/**
	 * retourne un tableau contenant les coordonnées de tous les points en y
	 * @return
	 */
	public double[] getAllY(){
		return new double[]{a.y,b.y,b.y};
	}

	/**
	 * retourne un tableau contenant les coordonnées de tous les points en z
	 * @return
	 */
	public double[] getAllZ(){
		return new double[]{a.z,b.z,b.z};
	}

	/**
	 * retourne un tableau contenant les coordonnées de tous les points en x
	 * chaque coordonée est multiplié par zoom puis cast en entier pour un affichage graphique
	 * @param vector 
	 * @return
	 */
	public int[] getAllPosX(Point vector){
		double v_X = vector.x;
		int[] tmp = new int[]{(int)(a.x+v_X),(int)(b.x+v_X),(int)(c.x+v_X)};
		return tmp;
	}

	/**
	 * retourne un tableau contenant les coordonnées de tous les points en y
	 * chaque coordonée est multiplié par un zoom puis cast en entier pour un affichage graphique
	 * @param vector
	 * @return
	 */
	public int[] getAllPosY(Point vector){
		double v_Y = vector.y;
		int[] tmp = new int[]{(int)(a.y+v_Y),(int)(b.y+v_Y),(int)(c.y+v_Y)};
		return tmp;
	}

	/**
	 * retourne un tableau contenant les coordonnées de tous les points en z
	 * chaque coordonée est multiplié par zoom puit cast en entier pour un affichage graphique
	 * @param vector
	 * @return
	 */
	public int[] getAllPosZ(Point vector){
		double v_Z = vector.z;
		int[] tmp = new int[]{(int)(a.z+v_Z),(int)(b.z+v_Z),(int)(c.z+v_Z)};
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
