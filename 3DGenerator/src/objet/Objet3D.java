/**
 * 
 */
package objet;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Autre.Outils;
import frame.Test;

/**
 * @author Alex
 * Cette classe charge et stock les données du objet à afficher
 */
public class Objet3D {
	/**
	 * Fichier à charger
	 */
	private File fichier;
	/**
	 * Liste des points
	 */
	private Map<Integer,Point> points;
	/**
	 * Liste des segments
	 */
	private Map<Integer,Segment> segments;
	/**
	 * Liste des faces
	 */
	private List<Face> faces;
	/**
	 * Origine du repère pour l'affichage de l'objet
	 */
	private Point origine;
	
	/**
	 * Constructeur qui stock les différentes données de l'objet
	 * @param accesFichier
	 */
	@SuppressWarnings("resource")
	public Objet3D(String accesFichier){
		points = new HashMap<Integer,Point>();
		segments = new HashMap<Integer,Segment>();
		faces = new ArrayList<Face>();
		
		try {
			fichier = new File(accesFichier);
			BufferedReader br = new BufferedReader(new FileReader(fichier));
			String tmp = br.readLine();
			String[] tmpSplit;
			String[] infoObjet = tmp.split(" ");
			for(int i = 0; i < infoObjet.length; i++){
				for(int j = 1; j<=Integer.parseInt(infoObjet[i]) ; j++){
					tmp = br.readLine();
					tmpSplit = tmp.split(" ");
					if(i == 0)
						try{
							points.put(j, new Point(tmp.split(" ")));
						}catch(Exception e){
							e.printStackTrace();
						}
					if(i == 1)
						try{
							segments.put(j, new Segment(points.get(Integer.parseInt(tmpSplit[0])),points.get(Integer.parseInt(tmpSplit[1]))));
						}catch(Exception e){
							e.printStackTrace();
						}
					if(i == 2)
						try{
							faces.add(new Face(segments.get(Integer.parseInt(tmpSplit[0])),segments.get(Integer.parseInt(tmpSplit[1])),segments.get(Integer.parseInt(tmpSplit[2])),j));
						}catch(Exception e){
							e.printStackTrace();
						}
				}
			}
			faces = Outils.peintre(faces);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void calculOrigine(int zoom, Point update){
		double yMax=0;
		double yMin=0;
		Point tmp;
		for(Integer i : points.keySet()){
			tmp = points.get(i);
			if(zoom > 0){
				if(tmp.y * zoom > yMax)
					yMax = tmp.y * zoom;
				if(tmp.y * zoom < yMin)
					yMin = tmp.y * zoom;
			}
			else{
				if(tmp.y * -zoom > yMax)
					yMax = tmp.y * zoom;
				if(tmp.y * -zoom < yMin)
					yMin = tmp.y * zoom;
			}
		}
		double screenX = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
		double screenY = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		screenY = screenY/2 - yMax-yMin/2;
		this.setOrigine(new Point(screenX,screenY,0));
		this.origine.add(update);
	}
	
	void verif_Integralite_Fichier(){
		
	}
	
	/**
	 * retourne le Fichier chargé
	 * @return
	 */
	public File getFichier(){
		return this.fichier;
	}
	
	/**
	 * retourne les points de l'objet
	 * @return
	 */
	public Map<Integer, Point> getPoints(){
		return this.points;
	}
	
	/**
	 * retourne les segments de l'objet
	 * @return
	 */
	public Map<Integer, Segment> getSegments(){
		return this.segments;
	}
	
	/**
	 * retourne les faces de l'objet
	 * @return
	 */
	public List<Face> getFaces(){
		return this.faces;
	}
	
	/**
	 * affecte un nouveau fichier
	 * @param file
	 */
	public void setFichier(File file){
		this.fichier = file;
	}
	
	/**
	 * ajoute le point d'indice p avec le point point
	 * retourne false si l'indice p existe déjà existe déjà
	 * @param p
	 * @param point
	 * @return
	 */
	public boolean putPoint(int p, Point point){
		if(points.containsKey(p))
			return false;
		points.put(p, point);
		return true;
	}
	
	/**
	 * retourne l'origine de la figure
	 * @return
	 */
	public Point getOrigine() {
		return origine;
	}

	/**
	 * modifie l'origine de la figure
	 * @param origine
	 */
	public void setOrigine(Point origine) {
		this.origine = origine;
	}
	
	/**
	 * permet de faire tourner la figure sur l'axe x
	 * @param rad
	 */
	public void rotationX(double rad){
		for(Integer val : points.keySet()){
			points.get(val).rotationX(rad);
		}
		faces = Outils.peintre(faces);
	}
	
	/**
	 * permet de faire tourner la figure sur l'axe y
	 * @param rad
	 */
	public void rotationY(double rad){
		for(Integer val : points.keySet()){
			points.get(val).rotationY(rad);
		}
		faces = Outils.peintre(faces);
	}
	
	
	/**
	 * permet de faire tournée la figure sur l'axe z
	 * @param rad
	 */
	public void rotationZ(double rad){
		for(Integer val : points.keySet()){
			points.get(val).rotationZ(rad);
		}
		faces = Outils.peintre(faces);
	}
	
	public static void main(String[] args){
		Objet3D o = new Objet3D("horse.gts");
		Test t = new Test(o);
		while(true){
			try {
				Thread.sleep(50);
			}catch(Exception e){e.printStackTrace();}
			o.rotationX(Math.PI/100);
			o.rotationY(Math.PI/100);
			//o.rotationZ(Math.PI/100);
			t.repaint();
		}
	}
}
