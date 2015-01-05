package open;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Modèle mvc sépicalement prévu pour la recherche de fichier dans la bdd
 * @author Alex
 *
 */
public class ModelRecherche extends Observable{
	/**
	 * Contient le nom de l'objet contenu dans le modèle 
	 */
	private String nom;
	/**
	 * Contient l'url de l'objet
	 */
	private String url;
	/**
	 * Contient sa date
	 */
	private String date;
	/**
	 * Contient son auteur;
	 */
	private String auteur;
	/**
	 * Contient son utilisation 
	 */
	private String utilisation;
	/**
	 * Contient sa forme
	 */
	private String forme;
	/**
	 * Contient la liste des tag
	 */
	private List<String> tag;
	/**
	 * Contient le nombre de points
	 */
	private int nb_points;
	/**
	 * Contient le nombre de segments
	 */
	private int nb_segments;
	/**
	 * Contient le nombre de faces
	 */
	private int nb_faces;
	
	/**
	 * constructeur du modèle
	 */
	public ModelRecherche(){
		super();
		this.nom = "";
		this.auteur = "";
		this.utilisation = "";
		this.date = "//";
		this.forme = "";
		this.nb_faces = 0;
		this.nb_points = 0;
		this.nb_segments = 0;
		this.tag = new ArrayList<String>();
		this.url = "";
	}

	/**
	 * retourne le nom
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * modifie le nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * récupère l'url
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * modifie l'url
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * récupère la date
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * modifie la date
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * récupère l'auteur
	 * @return
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * modifie l'auteur
	 * @param auteur
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/**
	 * récupère l'utilisation
	 * @return
	 */
	public String getUtilisation() {
		return utilisation;
	}

	/**
	 * change l'utilisation
	 * @param utilisation
	 */
	public void setUtilisation(String utilisation) {
		this.utilisation = utilisation;
	}

	/**
	 * récupère la forme
	 * @return
	 */
	public String getForme() {
		return forme;
	}

	/**
	 * modifie la forme
	 * @param forme
	 */
	public void setForme(String forme) {
		this.forme = forme;
	}

	/**
	 * récupère la liste des tag
	 * @return
	 */
	public List<String> getTag() {
		return tag;
	}

	/**
	 * modifie la liste des tag
	 * @param tag
	 */
	public void setTag(List<String> tag) {
		this.tag = tag;
	}

	/**
	 * récupère le nombre de points
	 * @return
	 */
	public int getNb_points() {
		return nb_points;
	}

	/**
	 * modifie le nombre de points
	 * @param nb_points
	 */
	public void setNb_points(int nb_points) {
		this.nb_points = nb_points;
	}

	/**
	 * réupère le nombre de segments
	 * @return
	 */
	public int getNb_segments() {
		return nb_segments;
	}

	/**
	 * modifie le nombre de segments
	 * @param nb_segments
	 */
	public void setNb_segments(int nb_segments) {
		this.nb_segments = nb_segments;
	}

	/**
	 * récupère le nombre de faces
	 * @return
	 */
	public int getNb_faces() {
		return nb_faces;
	}

	/**
	 * modifie le nombre de faces
	 * @param nb_faces
	 */
	public void setNb_faces(int nb_faces) {
		this.nb_faces = nb_faces;
	}
	
	/**
	 * permet de mettre à jour tout le modèle avec seulement le nom du fichier en paramètre
	 * @param nom
	 */
	public void setInformation(String nom){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:sqlite:./config/bibliotheque.db");
				PreparedStatement ps = con.prepareStatement("select * from object where name=?");
				ps.setString(1, nom);
				ResultSet rs = ps.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				if(rs.next())
					for(int i = 1; i <= rsmd.getColumnCount(); i++){
						if(rsmd.getColumnName(i).equals("name"))
							this.nom = rs.getString(i);
						if(rsmd.getColumnName(i).equals("url"))
							this.url = rs.getString(i);
						if(rsmd.getColumnName(i).equals("date"))
							this.date = rs.getString(i);
						if(rsmd.getColumnName(i).equals("auteur"))
							this.auteur = rs.getString(i);
						if(rsmd.getColumnName(i).equals("utilisation"))
							this.utilisation = rs.getString(i);
						if(rsmd.getColumnName(i).equals("forme"))
							this.forme = rs.getString(i);
						if(rsmd.getColumnName(i).equals("nb_points"))
							this.nb_points = Integer.parseInt(rs.getString(i));
						if(rsmd.getColumnName(i).equals("nb_faces"))
							this.nb_faces = Integer.parseInt(rs.getString(i));
						if(rsmd.getColumnName(i).equals("nb_segments"))
							this.nb_segments = Integer.parseInt(rs.getString(i));
					}
				ps = con.prepareStatement("select * from tag where name=?");
				ps.setString(1, nom);
				rs = ps.executeQuery();
				this.tag.clear();
				while(rs.next()){
					this.tag.add(rs.getString(1));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String str = "";
		str += this.nom + "\n";
		str += this.url + "\n";
		str += this.date + "\n";
		str += this.auteur + "\n";
		str += this.utilisation + "\n";
		str += this.forme + "\n";
		for(int i = 0; i < tag.size(); i++){
			str += tag.get(i) + "\n";
		}
		str += this.nb_points + "\n";
		str += this.nb_segments + "\n";
		str += this.nb_faces;
		return str;
	}
	
	/**
	 * permet de reset le modèle
	 */
	public void reset(){
		this.auteur = "";
		this.date = "";
		this.forme = "";
		this.nb_faces = 0;
		this.nb_points = 0;
		this.nb_segments = 0;
		this.nom = "";
		this.tag.clear();
		this.url = "";
		this.utilisation = "";
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * permet de rechercher les vues
	 */
	public void recharger(){
		this.setChanged();
		this.notifyObservers();
	}
}
