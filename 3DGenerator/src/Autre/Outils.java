package Autre;

import java.util.Collections;
import java.util.List;

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
	
	public static List<Face> peintre(List<Face> faces){
		Face tmp;
		for(int i = 0; i < faces.size(); i++){
			tmp = faces.get(i);
			faces.get(i).baricentre = new Point((tmp.a.x+tmp.b.x+tmp.c.x)/3,(tmp.a.y+tmp.b.y+tmp.c.y)/3,(tmp.a.z+tmp.b.z+tmp.c.z)/3);
		}
		Collections.sort(faces);
		return faces;
	}
}
