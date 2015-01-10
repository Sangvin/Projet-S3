package mvc;

import graphic.PanelObjet;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import objet.Objet3D;
import objet.Point;
import open.ModelRecherche;

/**
 * permet de controller la figure
 * @author Alex
 *
 */
public class ObjectController{
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
	
	/**
	 * permet de modifier le nom de l'objet
	 * @param name
	 */
	public void setName(String name){
		this.model.setName(name);
	}
	
	/**
	 * permet de modifier l'auteur de l'objet
	 * @param auteur
	 */
	public void setAuteur(String auteur){
		this.model.setAuteur(auteur);
	}
	
	/**
	 * Permet de fermer l'objet
	 */
	public void fermerObjet(){
		this.model.fermerObjet();
	}
	
	/**
	 * Permet de choisir le mode d'affichage pour la figure
	 * @param mode
	 */
	public void setMode(int mode){
		this.model.setMode(mode);
	}
	
	/**
	 * permet de modifier l'utilisation de l'objet
	 * @param utilisation
	 */
	public void setUtilisation(String utilisation){
		if(utilisation.replaceAll(" ", "").length() != 0){
			if(utilisation.charAt(0) == ' ')
				utilisation = utilisation.substring(1);
			if(utilisation.charAt(utilisation.length()-1) == ' ')
				utilisation = utilisation.substring(0, utilisation.length()-1);
			this.model.setUtilisation(utilisation);
		}
		else
			this.model.setUtilisation("");
	}
	
	/**
	 * permet de modifier la forme de l'objet
	 * @param forme
	 */
	public void setForme(String forme){
		if(forme.replaceAll(" ", "").length() != 0){
			if(forme.charAt(0) == ' ')
				forme = forme.substring(1);
			if(forme.charAt(forme.length()-1) == ' ')
				forme = forme.substring(0, forme.length()-1);
			this.model.setForme(forme);
		}
		else
			this.model.setForme("");
	}
	
	/**
	 * permet de modifier la description de l'objet
	 * @param description
	 */
	public void setDescription(String description){
		if(description.replaceAll(" ", "").length() != 0){
			if(description.charAt(0) == ' ')
				description = description.substring(1);
			if(description.charAt(description.length()-1) == ' ')
				description = description.substring(0, description.length()-1);
			this.model.setDescription(description);
		}
		else
			this.model.setDescription("");
	}
	
	/**
	 * permet de modifier la liste des tag avec celle passée en paramètres
	 * @param tag
	 */
	public void setTag(String[] tag){
		Set<String> tmp = new HashSet<String>();
		List<String> list = new ArrayList<String>();
		if(tag != null){
			for(int i = 0; i < tag.length; i++)
				if(tag[i].length() != 0)
					tmp.add(tag[i]);
			Iterator<String> it = tmp.iterator();
			while(it.hasNext())
				list.add(it.next());
			this.model.setTag(list);
		}
		else
			this.model.setTag(list);
	}
	
	public void copyData(ModelRecherche model){
		this.model.setAuteur(model.getAuteur());
		this.model.setDescription(model.getDescription());
		this.model.setForme(model.getForme());
		this.model.setName(model.getNom());
		this.model.setTag(model.getTag());
		this.model.setUtilisation(model.getUtilisation());
	}
}
