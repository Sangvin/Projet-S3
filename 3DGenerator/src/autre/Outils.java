package autre;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import objet.Face;
import objet.Point;

/**
 * @author Alex
 *
 */
public class Outils {
	/**
	 * permet de calculer le produit de deux matrice
	 * @param point 
	 * est une matrice colonne
	 * @param matrice 
	 * est une matrice carré
	 * @return
	 */
	public static double[] produit_matriciel(double[] point,double[][] matrice){
		double[] resultat = new double[point.length];
		for(int i = 0; i < matrice.length; i++){
			for(int j = 0; j < matrice[i].length; j++){
				resultat[i] += matrice[i][j]*point[j];
			}
		}
		return resultat;
	}

	/**
	 * algorithme du peintre qui tri les faces selon les baricentres
	 * @param faces
	 * @return
	 */
	public static List<Face> peintre(List<Face> faces){
		Face tmp;
		for(int i = 0; i < faces.size(); i++){
			tmp = faces.get(i);
			faces.get(i).baricentre = new Point((tmp.a.x+tmp.b.x+tmp.c.x)/3,(tmp.a.y+tmp.b.y+tmp.c.y)/3,(tmp.a.z+tmp.b.z+tmp.c.z)/3);
		}
		Collections.sort(faces);
		return faces;
	}

	/**
	 * retourne un vecteur de translation entre les deux point
	 * le vecteur est retourné sous la forme d'un point 
	 * @param center
	 * @param p
	 * @return
	 */
	public static Point getVector(Point center, Point p) {
		Point tmp = new Point(center.x-p.x,center.y-p.y,center.z-p.z);
		return tmp;
	}

	/**
	 * retourne le scalaire de deux vecteur passé sous la forme de deux points
	 * @param vector1
	 * @param vector2
	 * @return
	 */
	public static double scalaire(Point vector1, Point vector2) {
		double scalaire = vector1.x*vector2.x+vector1.y*vector2.y+vector1.z*vector2.z;
		return Math.abs(scalaire);
	}

	/**
	 * retourne la norme d'un vecteur
	 * @param vector
	 * @return
	 */
	public static double norme(Point vector){
		double norme = Math.abs(Math.sqrt(Math.pow(vector.x, 2)+Math.pow(vector.y, 2)+Math.pow(vector.z, 2)));
		return norme;
	}
	
	/**
	 * retourne une couleur aléatoire
	 * @return
	 */
	public static Color randomColor(){
		Random r = new Random();
		int red = r.nextInt(256);
		int green = r.nextInt(256);
		int blue = r.nextInt(256);
		return new Color(red,green,blue);
	}
}
