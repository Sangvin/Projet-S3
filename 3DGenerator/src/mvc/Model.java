package mvc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import objet.Objet3D;
import objet.Point;

/**
 * modèle contenant les datas du programe
 * @author Alex
 *
 */
public class Model extends Observable{
	/**
	 * objet à afficher
	 */
	private Objet3D object;
	/**
	 * Contient le nom de l'objet
	 */
	private String name;
	/**
	 * Contient son auteur;
	 */
	private String auteur;
	/**
	 * Contient l'utilisation de l'objet
	 */
	private String utilisation;
	/**
	 * Contient la forme forme de l'objet
	 */
	private String forme;
	/**
	 * Contient la liste des tag de l'objet
	 */
	private List<String> tag;
	/**
	 * Contient la description de l'objet
	 */
	private String description;
	/**
	 * Contient le nombre de points
	 */
	private int nbPoint;
	/**
	 * Contient le nombre de segments
	 */
	private int nbSeg;
	/**
	 * Contient le nombre de faces
	 */
	private int nbFace;
	/**
	 * Permet de choisir le mode d'affichage
	 * mode normal = 0
	 * mode squelete = 1
	 * mode lumiere = 2
	 */
	private int mode;
	
	/**
	 * constructeur
	 */
	public Model(){
		super();
		this.object = null;
		this.name = "";
		this.auteur = "";
		this.nbFace = 0;
		this.nbPoint = 0;
		this.nbSeg = 0;
		this.tag = new ArrayList<String>();
		this.description = "";
		this.utilisation = "";
		this.forme = "";
	}
	
	/**
	 * permet de renseigner un auteur et un nom en même temps que d'attacher un objet
	 *  au modèle
	 * @param o
	 * @param name
	 * @param auteur
	 */
	public void attachObjet3D(Objet3D o){
		this.auteur = "";
		this.description = "";
		this.utilisation = "";
		this.forme = "";
		this.name = "";
		this.tag.clear();
		this.object = o;
		this.nbPoint = this.object.getNbPoints();
		this.nbSeg = this.object.getNbSegments();
		this.nbFace = this.object.getNbFaces();
		this.setChanged();
		this.notifyObservers(new Integer(0));
	}
	
	/**
	 * permet une rotation en x par une action externe au panel
	 * @param d
	 */
	public void rotationX(double d) {
		if(this.object != null){
			this.object.rotationX(d);
			this.object.setColor(this.object.getColor());
			this.setChanged();
			this.notifyObservers(new Integer(1));
		}
	}

	/**
	 * permet une rotation en y par une action externe au panel
	 * @param d
	 */
	public void rotationY(double d) {
		if(this.object != null){
			this.object.rotationY(d);
			this.object.setColor(this.object.getColor());
			this.setChanged();
			this.notifyObservers(new Integer(1));
		}
	}

	/**
	 * permet une rotation en z par une action externe au panel
	 * @param d
	 */
	public void rotationZ(double d) {
		if(this.object != null){
			this.object.rotationZ(d);
			this.object.setColor(this.object.getColor());
			this.setChanged();
			this.notifyObservers(new Integer(1));
		}
	}

	/**
	 * permet d'effectuer un zoom de la figure
	 * @param d
	 */
	public void zoom(double d) {
		if(this.object != null){
			this.object.zoom(d);
			this.setChanged();
			this.notifyObservers(new Integer(1));
		}
	}

	/**
	 * permet d'effectuer un déplacement de la figure
	 * @param p
	 */
	public void deplacement(Point p){
		if(this.object != null){
			this.object.getVector().add(p);
			this.setChanged();
			this.notifyObservers(new Integer(1));
		}
	}
	
	/**
	 * permet de changer la couleur de la figure
	 * @param e
	 */
	public void setColor(Color e){
		if(this.object != null){
			this.object.setColor(e);
			this.setChanged();
			this.notifyObservers(new Integer(1));
		}
	}
	
	/**
	 * permet de récupérer la couleur de l'objet
	 * @return
	 */
	public Color getColor(){
		if(this.object != null)
			return this.object.getColor();
		return null;
	}
	
	/**
	 * permet de changer le vecteur de déplacement
	 * @param p
	 */
	public void setVector(Point p){
		if(this.object != null){
			this.object.setVector(p);
			this.setChanged();
			this.notifyObservers(new Integer(1));
		}
	}
	
	/**
	 * permet de récupérer l'objet à imprimer
	 * @return
	 */
	public Objet3D getObject(){
		return this.object;
	}
	
	/**
	 * Permet de modifier le nom de l'objet
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
		this.setChanged();
		this.notifyObservers(new Integer(2));
	}
	
	/**
	 * Permet de modifier l'auteur de l'objet
	 * @param auteur
	 */
	public void setAuteur(String auteur){
		this.auteur = auteur;
		this.setChanged();
		this.notifyObservers(new Integer(2));
	}
	
	/**
	 * permet de récupérer le nom de l'objet
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * permet de récupérer l'auteur de l'objet
	 * @return
	 */
	public String getAuteur(){
		return this.auteur;
	}
	
	/**
	 * permet de récupérer le nombre de points
	 * @return
	 */
	public int getNBPoint(){
		return this.nbPoint;
	}
	
	/**
	 * permet de récupérer le nombre de segments
	 * @return
	 */
	public int getNBSeg(){
		return this.nbSeg;
	}
	
	/**
	 * permet de récupérer le nombre de faces
	 * @return
	 */
	public int getNBFace(){
		return this.nbFace;
	}
	
	/**
	 * permet de fermet un objet
	 */
	public void fermerObjet(){
		this.object = null;
		this.auteur = "";
		this.name = "";
		this.forme = "";
		this.utilisation = "";
		this.description = "";
		this.nbFace = 0;
		this.nbPoint = 0;
		this.nbSeg = 0;
		this.tag.clear();
		this.setChanged();
		this.notifyObservers(new Integer(0));
	}
	
	/**
	 * permet de choisi le mode d'affichage de la figure
	 * lumiere = 2
	 * squelete = 1
	 * normal = 0
	 * @param mode
	 */
	public void setMode(int mode){
		this.mode = mode;
		this.setChanged();
		this.notifyObservers(new Integer(1));
	}
	
	/**
	 * permet de récupérer le mode d'affichage
	 * @return
	 */
	public int getMode(){
		return this.mode;
	}

	/**
	 * permet de récupéré l'utilisation de l'objet
	 * @return
	 */
	public String getUtilisation() {
		return utilisation;
	}

	/**
	 * permet de modifier l'utilisation de l'objet
	 * @param utilisation
	 */
	public void setUtilisation(String utilisation) {
		this.utilisation = utilisation;
		this.setChanged();
		this.notifyObservers(new Integer(3));
	}

	/**
	 * permet de récupérer la forme de l'objet
	 * @return
	 */
	public String getForme() {
		return forme;
	}

	/**
	 * permet de modifier la forme de l'objet
	 * @param forme
	 */
	public void setForme(String forme) {
		this.forme = forme;
		this.setChanged();
		this.notifyObservers(new Integer(3));
	}

	/**
	 * permet de récupérer une liste de tag
	 * @return
	 */
	public List<String> getTag() {
		return tag;
	}
	
	/**
	 * permet de récupérer les tags sous forme de string
	 * @return
	 */
	public String getTags(){
		String str = "";
		for(String tmp : tag)
			str += tmp + " ";
		return str;
	}

	/**
	 * permet de changer la liste des tag par celle passée en paramètre
	 * @param tag
	 */
	public void setTag(List<String> tag) {
		this.tag = tag;
		this.setChanged();
		this.notifyObservers(new Integer(3));
	}

	/**
	 * permet de récupérer la description de l'objet
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * permet de modifier la description de l'objet
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
		this.setChanged();
		this.notifyObservers(new Integer(3));
	}
}
