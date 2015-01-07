/**
 * 
 */
package objet;

import graphic.Frame;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import autre.Error;
import autre.FileError;
import autre.Outils;

/**
 * Cette classe charge et stock les données du objet à afficher
 * @author Alex
 */
public class Objet3D{
	/**
	 * Fichier à charger
	 */
	private File fichier;
	/**
	 * Liste des points
	 */
	private List<Point> points;
	/**
	 * Liste des segments
	 */
	private List<Segment> segments;
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
	 * @param parent
	 * @throws Exception 
	 */
	public Objet3D(String accesFichier, Color c,Frame parent) throws Exception{
		Set<FileError> errorList = new HashSet<FileError>();
		
		if(accesFichier.length() < 4)
			errorList.add(FileError.ERROR1101);
		if(!accesFichier.substring(accesFichier.length()-4, accesFichier.length()).equals(".gts"))
			errorList.add(FileError.ERROR1101);
		this.fichier = new File(accesFichier);
		BufferedReader br = new BufferedReader(new FileReader(fichier));
		initData(br,errorList);
		this.color = c;
		this.setColor(this.color);
		if(errorList.size() != 0)
			new Error(parent,errorList,"Le fichier ouvert contient des erreurs");
	}

	/**
	 * initialise les données
	 * @param br
	 * @param errorList
	 * @throws IOException 
	 */
	private void initData(BufferedReader br, Set<FileError> errorList) throws IOException{
		points = new ArrayList<Point>();
		points.add(null);
		segments = new ArrayList<Segment>();
		segments.add(null);
		faces = new ArrayList<Face>();

		Set<String> uniques = new HashSet<String>();
		List<Integer> organizeID = new ArrayList<Integer>();
		Set<Integer> uniqueID = new HashSet<Integer>();
		Set<Point> triangle = new HashSet<Point>();

		String tmp = br.readLine();
		if(tmp == null){
			errorList.add(FileError.ERROR1102);
		}
		String[] tmpSplit;
		String[] infoObjet = tmp.split(" ");
		for(int i = 0; i < infoObjet.length; i++)
			try{
				Integer.parseInt(infoObjet[i]);
			}catch(Exception e){errorList.add(FileError.ERROR1201);}
		if(infoObjet.length != 3){
			errorList.add(FileError.ERROR1202);
		}
		
		for(int i = 0; i < infoObjet.length; i++){
			for(int j = 1; j<=Integer.parseInt(infoObjet[i]) ; j++){
				organizeID.clear();
				tmp = br.readLine();
				if(tmp == null){
					errorList.add(FileError.ERROR1203);
				}
				
				tmpSplit = tmp.split(" ");
				for(int z = 0; z < tmpSplit.length; z++)
					try{
						Double.parseDouble(tmpSplit[z]);
					}catch(Exception e){errorList.add(FileError.ERROR1201);}
				
				if(i == 0){
					if(tmp.split(" ").length != 3){
						errorList.add(FileError.ERROR2101);
					}
					if(!uniques.add(tmp)) errorList.add(FileError.ERROR2102);
					try{
						points.add(new Point(tmpSplit));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				if(i == 1){
					if(tmpSplit.length != 2){
						errorList.add(FileError.ERROR2201);
					}
					organizeID.add(Integer.parseInt(tmpSplit[0]));
					organizeID.add(Integer.parseInt(tmpSplit[1]));
					Collections.sort(organizeID);
					tmp = organizeID.get(0) + " " + organizeID.get(1);
					if(organizeID.get(0) == organizeID.get(1))
						errorList.add(FileError.ERROR2203);
					if(!uniques.add(tmp))
						errorList.add(FileError.ERROR2202);
					try{
						segments.add(new Segment(points.get(Integer.parseInt(tmpSplit[0])),points.get(Integer.parseInt(tmpSplit[1]))));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				if(i == 2){
					if(tmp.split(" ").length != 3){
						errorList.add(FileError.ERROR2301);
					}
					organizeID.add(Integer.parseInt(tmpSplit[0]));
					organizeID.add(Integer.parseInt(tmpSplit[1]));
					organizeID.add(Integer.parseInt(tmpSplit[2]));
					
					uniqueID.addAll(organizeID);
					if(uniqueID.size() != 3) errorList.add(FileError.ERROR2303);
					uniqueID.clear();
					
					for(Integer z : organizeID)
						triangle.addAll(segments.get(z).getPoints());
					if(triangle.size() != 3) errorList.add(FileError.ERROR2304);
					triangle.clear();
					
					Collections.sort(organizeID);
					tmp = organizeID.get(0)+" "+organizeID.get(1)+" "+organizeID.get(2);
					if(!uniques.add(tmp))
						errorList.add(FileError.ERROR2302);
					try{
						faces.add(new Face(segments.get(Integer.parseInt(tmpSplit[0])),segments.get(Integer.parseInt(tmpSplit[1])),segments.get(Integer.parseInt(tmpSplit[2])),j));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		this.faces = Outils.peintre(this.faces);
		this.centrerFigure();
		this.zoomAuto();
		this.vector = new Point(0,0,0);
	}

	private void centrerFigure(){
		int i;
		Point baricentre = new Point(0,0,0);
		for(i = 0; i < this.faces.size(); i++){
			baricentre.add(this.faces.get(i).baricentre);
		}
		baricentre.x = baricentre.x/i;
		baricentre.y = baricentre.y/i;
		baricentre.z = baricentre.z/i;
		for(int in = 1; in < this.points.size(); in++){
			this.points.get(in).add(new Point(-baricentre.x,-baricentre.y,-baricentre.z));
		}
	}

	/**
	 * Calcule automatiquement le zoom optimal
	 */
	public void zoomAuto(){
		Point temp;
		double coord_tmp = 0;
		for(int i = 1; i < this.points.size(); i++){
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
	public List<Point> getPoints(){
		List<Point> tmp = this.points;
		tmp.remove(0);
		return tmp;
	}

	/**
	 * retourne les segments de l'objet
	 * @return
	 */
	public List<Segment> getSegments(){
		List<Segment> tmp = this.segments;
		tmp.remove(0);
		return tmp;
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
		if(this.points.get(p) == null)
			return false;
		points.add(point);
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
		for(int val = 1; val < this.points.size(); val++){
			points.get(val).rotationX(rad);
		}
		faces = Outils.peintre(faces);
	}

	/**
	 * permet de faire tourner la figure sur l'axe y
	 * @param rad
	 */
	public void rotationY(double rad){
		for(int val = 1; val < this.points.size(); val++){
			points.get(val).rotationY(rad);
		}
		faces = Outils.peintre(faces);
	}

	/**
	 * permet de faire tournée la figure sur l'axe z
	 * @param rad
	 */
	public void rotationZ(double rad){
		for(int val = 1; val < this.points.size(); val++){
			points.get(val).rotationZ(rad);
		}
		faces = Outils.peintre(faces);
	}
	
	/**
	 * permet de zoomer/dézoomer une figure
	 * @param d
	 */
	public void zoom(double d) {
		for(int i = 1; i < this.points.size(); i++)
			this.points.get(i).zoom(d);
	}
	
	/**
	 * permet de récupérer le nombre de points
	 * @return
	 */
	public int getNbPoints(){
		return this.points.size() - 1;
	}
	
	/**
	 * permet de récupérer le nombre de segments
	 * @return
	 */
	public int getNbSegments(){
		return this.segments.size() - 1;
	}
	
	/**
	 * permet de récupérer le nombre de faces
	 * @return
	 */
	public int getNbFaces(){
		return this.faces.size();
	}
}
