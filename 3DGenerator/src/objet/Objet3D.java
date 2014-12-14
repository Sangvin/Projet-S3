/**
 * 
 */
package objet;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import launcher.Launcher;
import autre.FileError;
import autre.Outils;

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
	public Objet3D(String accesFichier, Color c) throws Exception{
		if(accesFichier.length() < 4)
			throw new Exception(FileError.ERROR1101.message());
		if(!accesFichier.substring(accesFichier.length()-4, accesFichier.length()).equals(".gts"))
			throw new Exception(FileError.ERROR1101.message());
		this.fichier = new File(accesFichier);
		BufferedReader br = new BufferedReader(new FileReader(fichier));
		initData(br);
		this.color = c;
		this.setColor(this.color);
	}

	/**
	 * initialise les données
	 * @param br
	 * @throws Exception
	 */
	private void initData(BufferedReader br) throws Exception{
		Launcher l = new Launcher();
		points = new HashMap<Integer,Point>();
		segments = new HashMap<Integer,Segment>();
		faces = new ArrayList<Face>();

		String tmp = br.readLine();
		if(tmp == null) throw new Exception(FileError.ERROR1102.message());
		String[] tmpSplit;
		String[] infoObjet = tmp.split(" ");
		for(int i = 0; i < infoObjet.length; i++)
			try{
				Integer.parseInt(infoObjet[i]);
			}catch(Exception e){throw new Exception(FileError.ERROR1201.message());}
		if(infoObjet.length != 3) throw new Exception(FileError.ERROR1202.message());
			
		BigDecimal nbOperation = new BigDecimal(infoObjet[0]).add(new BigDecimal(infoObjet[1])).add(new BigDecimal(infoObjet[2]));
		nbOperation = nbOperation.add(new BigDecimal(infoObjet[2])).add(new BigDecimal(infoObjet[0]));
		nbOperation = nbOperation.add(new BigDecimal(infoObjet[0]));
		l.setIncrement(new BigDecimal(100).divide(nbOperation,MathContext.DECIMAL128));
		
//		Set<String> pointsUniques = new HashSet<String>();
//		Set<String> segmentsUniques = new HashSet<String>();
//		Set<String> facesUniques = new HashSet<String>();
//		List<Integer> organizeID = new ArrayList<Integer>();
//		Set<Integer> uniqueID = new HashSet<Integer>();
//		Set<Point> triangle = new HashSet<Point>();
		
		for(int i = 0; i < infoObjet.length; i++){
			if(i == 0)
				l.setText("Lecture des points");
			if(i == 1)
				l.setText("Lecture des segments");
			if(i == 2)
				l.setText("Lecture des faces");
			for(int j = 1; j<=Integer.parseInt(infoObjet[i]) ; j++){
//				organizeID.clear();
				tmp = br.readLine();
				if(tmp == null) throw new Exception(FileError.ERROR1203.message());
				
				tmpSplit = tmp.split(" ");
				for(int z = 0; z < tmpSplit.length; z++)
					try{
						Double.parseDouble(tmpSplit[z]);
					}catch(Exception e){throw new Exception(FileError.ERROR1201.message());}
				
				if(i == 0){
					if(tmp.split(" ").length != 3) throw new Exception(FileError.ERROR2101.message());
//					if(!pointsUniques.add(tmp)) throw new Exception(FileError.ERROR2102.message());
					try{
						points.put(j, new Point(tmpSplit));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				if(i == 1){
					if(tmpSplit.length != 2) throw new Exception(FileError.ERROR2201.message());
//					organizeID.add(Integer.parseInt(tmpSplit[0]));
//					organizeID.add(Integer.parseInt(tmpSplit[1]));
//					Collections.sort(organizeID);
//					tmp = organizeID.get(0) + " " + organizeID.get(1);
//					if(organizeID.get(0) == organizeID.get(1))
//						throw new Exception(FileError.ERROR2203.message());
//					if(!segmentsUniques.add(tmp))
//						throw new Exception(FileError.ERROR2202.message());
					try{
						segments.put(j, new Segment(points.get(Integer.parseInt(tmpSplit[0])),points.get(Integer.parseInt(tmpSplit[1]))));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				if(i == 2){
					if(tmp.split(" ").length != 3) throw new Exception(FileError.ERROR2301.message());
//					organizeID.add(Integer.parseInt(tmpSplit[0]));
//					organizeID.add(Integer.parseInt(tmpSplit[1]));
//					organizeID.add(Integer.parseInt(tmpSplit[2]));
//					
//					uniqueID.addAll(organizeID);
//					if(uniqueID.size() != 3) throw new Exception(FileError.ERROR2303.message());
//					uniqueID.clear();
//					
//					for(Integer z : organizeID)
//						triangle.addAll(segments.get(z).getPoints());
//					if(triangle.size() != 3) throw new Exception(FileError.ERROR2304.message());
//					triangle.clear();
//					
//					Collections.sort(organizeID);
//					tmp = organizeID.get(0)+" "+organizeID.get(1)+" "+organizeID.get(2);
//					if(!facesUniques.add(tmp))
//						throw new Exception(FileError.ERROR2302.message());
					try{
						faces.add(new Face(segments.get(Integer.parseInt(tmpSplit[0])),segments.get(Integer.parseInt(tmpSplit[1])),segments.get(Integer.parseInt(tmpSplit[2])),j));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				l.increment();
			}
		}
		l.setText("Organisation des faces");
		this.faces = Outils.peintre(this.faces);
		this.centrerFigure(l);
		this.zoomAuto(l);
		double screenX = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
		double screenY = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;
		this.vector = new Point(screenX,screenY,0);
		l.setValue(100);
		l.setText("Affichage");
		l.dispose();
	}

	private void centrerFigure(Launcher l){
		l.setText("Recentrage de la figure");
		int i;
		Point baricentre = new Point(0,0,0);
		for(i = 0; i < this.faces.size(); i++){
			baricentre.add(this.faces.get(i).baricentre);
			l.increment();
		}
		baricentre.x = baricentre.x/i;
		baricentre.y = baricentre.y/i;
		baricentre.z = baricentre.z/i;
		for(Integer in : this.points.keySet()){
			this.points.get(in).add(new Point(-baricentre.x,-baricentre.y,-baricentre.z));
			l.increment();
		}
	}

	/**
	 * Calcule automatiquement le zoom optimal
	 */
	public void zoomAuto(Launcher l){
		l.setText("Calcul du zoom automatique");
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
			l.increment();
		}
		this.zoomOrigine = 100 / coord_tmp;
		l.setText("Application du zoom");
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
}
