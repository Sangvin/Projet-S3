package objet;

import Autre.Outils;


/**
 * @author Alex
 * caractérise un point
 */
public class Point {
	/**
	 * coordonnée en x
	 */
	public double x;
	/**
	 * coordonnée en y
	 */
	public double y;
	/**
	 * coordonnée en z
	 */
	public double z;
	/**
	 * Projeté orthogonal de z sur le plan défini par XY
	 */
	public double projeteZ;
	
	/**
	 * constructeur avec 3 entiers contenu dans un tableau de string
	 * @param coord
	 */
	public Point(String[] coord){
		this.x = Double.parseDouble(coord[0]);
		this.y = (Double.parseDouble(coord[1]))*-1;
		this.z = Double.parseDouble(coord[2]);
	}
	
	/**
	 * constructeur par 3 entiers
	 * @param d
	 * @param e
	 * @param f
	 */
	public Point(double u, double v, double w){
		this.x = u;
		this.y = v;
		this.z = w;
	}
	
	public Point(int u, int v, int w){
		this.x = u;
		this.y = v;
		this.z = w;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	/**
	 * compare deux point selon l'axe z
	 * retourne -1 si b1 < b2 , 1 si b1 > b2, 0 si non 
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static int compare(Point b1, Point b2) {
		if(b1.z < b2.z)
			return -1;
		if(b1.z > b2.z)
			return 1;
		return 0;
	}
	
	/**
	 * additionne les coordonnées du point p au point courant
	 * @param p
	 */
	public void add(Point p){
		this.x += p.x;
		this.y += p.y;
		this.z += p.z;
	}
	
	/**
	 * calcul et change les coordonnée d'un point suite à une rotation autour de l'axe X
	 * @param rad
	 * angle de rotation;
	 */
	public void rotationX(double rad){
		double[] point = new double[]{x,y,z};
		double[][] outil = new double[][]{{1,0,0},{0,Math.cos(rad),-Math.sin(rad)},{0,Math.sin(rad),Math.cos(rad)}};
		point = Outils.produit_matriciel(point, outil);
		this.x = point[0];
		this.y = point[1];
		this.z = point[2];
	}
	
	/**
	 * calcul et change les coordonnée d'un point suite à une rotation autour de l'axe Y
	 * @param rad
	 * angle de rotation;
	 */
	public void rotationY(double rad){
		double[] point = new double[]{x,y,z};
		double[][] outil = new double[][]{{Math.cos(rad),0,Math.sin(rad)},{0,1,0},{-Math.sin(rad),0,Math.cos(rad)}};
		point = Outils.produit_matriciel(point, outil);
		this.x = point[0];
		this.y = point[1];
		this.z = point[2];
	}
	
	/**
	 * calcul et change les coordonnée d'un point suite à une rotation autour de l'axe Z
	 * @param rad
	 * angle de rotation;
	 */
	public void rotationZ(double rad){
		double[] point = new double[]{x,y,z};
		double[][] outil = new double[][]{{Math.cos(rad),-Math.sin(rad),0},{Math.sin(rad),Math.cos(rad),0},{0,0,1}};
		point = Outils.produit_matriciel(point, outil);
		this.x = point[0];
		this.y = point[1];
		this.z = point[2];
	}
}
