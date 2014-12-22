package mvc;

import graphic.PanelObjet;

import java.awt.Color;

import objet.Objet3D;
import objet.Point;

/**
 * permet de controller la figure
 * @author Alex
 *
 */
public class ObjectController {
	/**
	 * Contient le modèle
	 */
	private Model model;
	/**
	 * contient la vue
	 */
	private PanelObjet view;
	
	/**
	 * Constructeur du controller
	 * @param model
	 */
	public ObjectController(Model model){
		this.model = model;
	}
	
	/**
	 * permet d'ordonner un changement de couleur de la figure
	 * @param e
	 */
	public void setColor(Color e){
		this.model.setColor(e);
	}
	
	/**
	 * permet la rotation en x de la figure
	 * @param d
	 */
	public void rotationX(double d){
		this.model.rotationX(d);
	}
	
	/**
	 * permet la rotation en y de la figure
	 * @param d
	 */
	public void rotationY(double d){
		this.model.rotationY(d);
	}
	
	/**
	 * permet la rotation en z de la figure
	 * @param d
	 */
	public void rotationZ(double d){
		this.model.rotationZ(d);
	}
	
	/**
	 * permet le zoom de la figure
	 * @param d
	 */
	public void zoom(double d){
		this.model.zoom(d);
	}
	
	/**
	 * permet de déplacer la figure
	 * @param p
	 */
	public void deplacement(Point p){
		this.model.deplacement(p);;
	}
	
	/**
	 * permet d'attacher un nouvel objet3d
	 * @param o
	 */
	public void attachObjet3D(Objet3D o){
		this.model.attachObjet3D(o);
	}
	
	/**
	 * permet d'ajouter une vue au controller
	 * @param view
	 */
	public void addView(PanelObjet view){
		this.view = view;
	}
	
	/**
	 * permet de récupérer la vue
	 * @return
	 */
	public PanelObjet getView(){
		return this.view;
	}
	
	/**
	 * permet de changer le vecteur de déplacement
	 * @param p
	 */
	public void setVector(Point p){
		this.model.setVector(p);
	}
}
