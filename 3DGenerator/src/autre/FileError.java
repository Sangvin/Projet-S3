package autre;

/**
 * @author Alex
 *
 */
public enum FileError {
	/**
	 * Erreur de type 1000, concerne le fichier lui m�me
	 * Erreur de type 1100, concerne le fichier dans sa globalit�
	 * Erreur de type 1101, fichier non reconnu
	 * ERREUR1101
	 */
	ERROR1101("Erreur Fichier: non reconnu"),
	/**
	 * Erreur de type 1000, concerne le fichier lui m�me
	 * Erreur de type 1100, concerne le fichier dans sa globalit�
	 * Erreur de type 1102, fichier vide
	 * ERREUR1102
	 */
	ERROR1102("Erreur Fichier: vide"),
	/**
	 * Erreur de type 1000, concerne le fichier lui m�me
	 * Erreur de type 1200, concerne le fichier contenu global du fichier
	 * Erreur de type 1201, des caract�res autre que num�riques sont pr�sents
	 * ERREUR1201
	 */
	ERROR1201("Erreur Fichier: caract�re autre que num�rique pr�sent"),
	/**
	 * Erreur de type 1000, concerne le fichier lui m�me
	 * Erreur de type 1200, concerne le fichier contenu global du fichier
	 * Erreur de type 1202, des descripteur de figure sont manquants (point,segment,face)
	 * ERREUR1202
	 */
	ERROR1202("Erreur Fichier: descripteur de figure manquant"),
	/**
	 * Erreur de type 1000, concerne le fichier lui m�me
	 * Erreur de type 1200, concerne le fichier contenu global du fichier
	 * Erreur de type 1203, des points figure ou face sont manquants
	 * ERREUR1202
	 */
	ERROR1203("Erreur Fichier: points,segments ou faces manquantes"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2100, concerne les points
	 * Erreur de type 2101, une coordonnee est manquante
	 * ERREUR2101
	 */
	ERROR2101("Erreur Contenu: Coordonn�e de points manquants"),
	;
	
	/**
	 * Message d'erreur
	 */
	private String message;
	
	/**
	 * constructeur de la class enum
	 * @param message
	 */
	FileError(String message){
		this.message = message;
	}
	
	/**
	 * r�cup�re une message d'erreur
	 * @return
	 */
	public String message(){
		return this.message;
	}
}
