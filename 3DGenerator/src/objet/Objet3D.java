/**
 * 
 */
package objet;

import java.awt.Color;
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
	 * vecteur qui ramène la figure au centre de l'écran
	 */
	private Point vector;
	/**
	 * Zoom d'origine de la figure calculé automatiquement
	 */
	private double zoomOrigine;
	/**
	 * Couleur d'origine de l'objet
	 */
	private Color color;

	/**
	 * Constructeur qui stock les différentes données de l'objet
	 * @param accesFichier
	 * @param c 
	 */
	public Objet3D(String accesFichier, Color c){
		try {
			this.fichier = new File(accesFichier);
			BufferedReader br = new BufferedReader(new FileReader(fichier));
			initData(br);
			this.color = c;
			this.setColor(this.color);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * initialise les données
	 * @param br
	 * @throws Exception
	 */
	private void initData(BufferedReader br) throws Exception{
		points = new HashMap<Integer,Point>();
		segments = new HashMap<Integer,Segment>();
		faces = new ArrayList<Face>();

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
		this.zoomAuto();
		double screenX = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
		double screenY = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;
		this.vector = new Point(screenX,screenY,0);
	}


	/**
	 * Calcule automatiquement le zoom optimal
	 */
	public void zoomAuto(){
		Point temp;
		double coord_tmp = 0;
		for(Integer i : this.points.keySet()){
			temp = this.points.get(i);
			if(temp.x < coord_tmp)
				coord_tmp = temp.z;
			if(temp.y < coord_tmp)
				coord_tmp = temp.y;
			if(temp.z < coord_tmp)
				coord_tmp = temp.z;
		}
		this.zoomOrigine = 100 / coord_tmp;
		this.zoom(this.zoomOrigine);
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
	public Point getVector() {
		return this.vector;
	}

	/**
	 * modifie l'origine de la figure
	 * @param origine
	 */
	public void setVector(Point vector) {
		this.vector = vector;
	}

	/**
	 * retourne la couleur de l'objet
	 * @return
	 */
	public Color getColor(){
		return this.color;
	}

	/**
	 * permet de calculer la couleur de chaques faces en prennant en compte la lumière
	 * @param c
	 */
	public void setColor(Color c){
		this.color = c;
		for(int i = 0; i < this.faces.size(); i++)
			this.faces.get(i).setColor(c);
	}

	/**
	 * récupère le zoom d'origine de la figure
	 * @return
	 */
	public double getZoomOrigine(){
		return this.zoomOrigine;
	}

	/**
	 * modifie le zoom d'origine de la figure
	 * @param zoom
	 */
	public void setZoomOrigine(double zoom){
		this.zoomOrigine = zoom;
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
	
	/**
	 * permet de zoomer/dézoomer une figure
	 * @param d
	 */
	public void zoom(double d) {
		for(Integer i : this.points.keySet())
			this.points.get(i).zoom(d);
	}

	public static void main(String[] args){
		Objet3D o = new Objet3D("lugia.gts",Color.BLUE);
//		o.rotationX(-Math.PI/2);
//		o.rotationY(Math.PI/2);
		o.setColor(o.getColor());
		@SuppressWarnings("unused")
		Test t = new Test(o);
//		while(true){
//			try {
//				Thread.sleep(50);
//			}catch(Exception e){e.printStackTrace();}
//			o.rotationX(Math.PI/100);
//			o.rotationY(Math.PI/100);
//			o.rotationZ(Math.PI/100);
//			o.setColor(o.getColor());
//			t.repaint();
//		}
	}
}
