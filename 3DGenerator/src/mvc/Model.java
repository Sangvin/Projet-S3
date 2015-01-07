package mvc;

import java.awt.Color;
import java.util.Observable;

import objet.Objet3D;
import objet.Point;

/**
 * mod�le contenant les datas du programe
 * @author Alex
 *
 */
public class Model extends Observable{
	/**
	 * objet � afficher
	 */
	private Objet3D object;
	/**
	 * Permet de conserver le nom de l'objet
	 */
	private String name;
	/**
	 * Permet de conserver le nom de l'auteur
	 */
	private String auteur;
	/**
	 * Permet de connaitre le nombre de point
	 */
	private int nbPoint;
	/**
	 * Permet de connaitre le nombre de segments
	 */
	private int nbSeg;
	/**
	 * Permet de connaitre le nombre d'objets
	 */
	private int nbFace;
	
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
	}
	
	/**
	 * permet de renseigner un auteur et un nom en m�me temps que d'attacher un objet
	 *  au mod�le
	 * @param o
	 * @param name
	 * @param auteur
	 */
	public void attachObjet3D(Objet3D o,String name,String auteur){
		this.object = o;
		this.name = name;
		this.auteur = auteur;
		this.nbPoint = this.object.getNbPoints();
		this.nbSeg = this.object.getNbSegments();
		this.nbFace = this.object.getNbFaces();
		this.setChanged();
		this.notifyObservers();
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
			this.notifyObservers();
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
			this.notifyObservers();
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
			this.notifyObservers();
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
			this.notifyObservers();
		}
	}

	/**
	 * permet d'effectuer un d�placement de la figure
	 * @param p
	 */
	public void deplacement(Point p){
		if(this.object != null){
			this.object.getVector().add(p);
			this.setChanged();
			this.notifyObservers();
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
			this.notifyObservers();
		}
	}
	
	/**
	 * permet de r�cup�rer la couleur de l'objet
	 * @return
	 */
	public Color getColor(){
		if(this.object != null)
			return this.object.getColor();
		return null;
	}
	
	/**
	 * permet de changer le vecteur de d�placement
	 * @param p
	 */
	public void setVector(Point p){
		if(this.object != null){
			this.object.setVector(p);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**
	 * permet de r�cup�rer l'objet � imprimer
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
		this.notifyObservers();
	}
	
	/**
	 * Permet de modifier l'auteur de l'objet
	 * @param auteur
	 */
	public void setAuteur(String auteur){
		this.auteur = auteur;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * permet de r�cup�rer le nom de l'objet
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * permet de r�cup�rer l'auteur de l'objet
	 * @return
	 */
	public String getAuteur(){
		return this.auteur;
	}
	
	/**
	 * permet de r�cup�rer le nombre de points
	 * @return
	 */
	public int getNBPoint(){
		return this.nbPoint;
	}
	
	/**
	 * permet de r�cup�rer le nombre de segments
	 * @return
	 */
	public int getNBSeg(){
		return this.nbSeg;
	}
	
	/**
	 * permet de r�cup�rer le nombre de faces
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
		this.nbFace = 0;
		this.nbPoint = 0;
		this.nbSeg = 0;
		this.setChanged();
		this.notifyObservers();
	}
}
