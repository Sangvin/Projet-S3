package open;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * permet de controller le mod�le de recherche dans la BDD
 * @author Alex
 *
 */
public class ControllerRecherche{
	/**
	 * Contient le mod�le
	 */
	private ModelRecherche model;
	/**
	 * Contient une vue
	 */
	private Observer view;

	/**
	 * constructeur du controller
	 * @param model
	 */
	public ControllerRecherche(ModelRecherche model){
		this.model = model;
	}

	/**
	 * modifie le nom
	 * @param nom
	 */
	public void setNom(String nom) {
		model.setNom(nom);
	}

	/**
	 * modifie l'url
	 * @param url
	 */
	public void setUrl(String url) {
		model.setUrl(url);
	}

	/**
	 * modifie la date
	 * @param date
	 */
	public void setDate(String date) {
		model.setDate(date);
	}

	/**
	 * modifie l'auteur
	 * @param auteur
	 */
	public void setAuteur(String auteur) {
		model.setAuteur(auteur);
	}

	/**
	 * change l'utilisation
	 * @param utilisation
	 */
	public void setUtilisation(String utilisation) {
		model.setUtilisation(utilisation);
	}

	/**
	 * modifie la forme
	 * @param forme
	 */
	public void setForme(String forme) {
		model.setForme(forme);
	}

	/**
	 * modifie la liste des tag
	 * @param tag
	 */
	public void setTag(List<String> tag) {
		model.setTag(tag);
	}

	/**
	 * modifie le nombre de points
	 * @param nb_points
	 */
	public void setNb_points(int nb_points) {
		model.setNb_points(nb_points);
	}

	/**
	 * modifie le nombre de segments
	 * @param nb_segments
	 */
	public void setNb_segments(int nb_segments) {
		model.setNb_segments(nb_segments);
	}

	/**
	 * modifie le nombre de faces
	 * @param nb_faces
	 */
	public void setNb_faces(int nb_faces) {
		model.setNb_faces(nb_faces);
	}

	/**
	 * permet de mettre � jour tout le mod�le avec seulement le nom du fichier en param�tre
	 * @param nom
	 */
	public void setInformation(String nom){
		model.setInformation(nom);
		view.update(null, null);
	}
	
	/**
	 * permet d'ajouter une vue
	 * @param view
	 */
	public void addView(Observer view){
		this.view = view;
	}

	/**
	 * permet d'ajouter des tag suuivant une chaine de caract�re pass�e en param�tre
	 * @param text
	 */
	public void setTag(String text) {
		if(text.replaceAll(" ", "").length() != 0){
			List<String> tag = new ArrayList<String>();
			String[] tmp = text.split(" ");
			for(int i = 0; i < tmp.length; i++){
				tag.add(tmp[i]);
			}
			model.setTag(tag);
		}else{
			model.setTag(new ArrayList<String>());
		}
	}
	
	/**
	 * permet de reset le mod�le
	 */
	public void reset(){
		model.reset();
	}
	
	/**
	 * permet de recharger les vues
	 */
	public void recharger(){
		model.recharger();
	}

	/**
	 * permet de r�cup�rer la vue associ�e au controller
	 * @return
	 */
	public Observer getView() {
		return view;
	}
}
