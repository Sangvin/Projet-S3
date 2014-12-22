package mvc;

import java.awt.Color;
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
	 * constructeur
	 */
	public Model(){
		super();
		this.object = null;
	}
	
	/**
	 * permet d'attaquer un nouvel objet au modèle
	 * @param o
	 */
	public void attachObjet3D(Objet3D o) {
		this.object = o;
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
	 * permet d'effectuer un déplacement de la figure
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
			this.notifyObservers();
		}
	}
	
	/**
	 * permet de récupérer l'objet à imprimer
	 * @return
	 */
	public Objet3D getObject(){
		return this.object;
	}
}
