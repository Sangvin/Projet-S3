package autre;

/**
 * contient toutes les erreurs possibles sur un fichier
 * @author Alex
 *
 */
public enum FileError{
	/**
	 * Erreur de type 1000, concerne le fichier lui même
	 * Erreur de type 1100, concerne le fichier dans sa globalité
	 * Erreur de type 1101, fichier non reconnu
	 * ERREUR1101
	 */
	ERROR1101("Erreur Fichier: non reconnu"),
	/**
	 * Erreur de type 1000, concerne le fichier lui même
	 * Erreur de type 1100, concerne le fichier dans sa globalité
	 * Erreur de type 1102, fichier vide
	 * ERREUR1102
	 */
	ERROR1102("Erreur Fichier: vide"),
	/**
	 * Erreur de type 1000, concerne le fichier lui même
	 * Erreur de type 1200, concerne le fichier contenu global du fichier
	 * Erreur de type 1201, des caractères autre que numériques sont présents
	 * ERREUR1201
	 */
	ERROR1201("Erreur Fichier: caractère autre que numérique présent"),
	/**
	 * Erreur de type 1000, concerne le fichier lui même
	 * Erreur de type 1200, concerne le fichier contenu global du fichier
	 * Erreur de type 1202, des descripteur de figure sont manquants (point,segment,face)
	 * ERREUR1202
	 */
	ERROR1202("Erreur Fichier: descripteur de figure manquant"),
	/**
	 * Erreur de type 1000, concerne le fichier lui même
	 * Erreur de type 1200, concerne le fichier contenu global du fichier
	 * Erreur de type 1203, des points figure ou face sont manquants
	 * ERREUR1203
	 */
	ERROR1203("Erreur Fichier: points,segments ou faces manquantes"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2100, concerne les points
	 * Erreur de type 2101, une coordonnee est manquante
	 * ERREUR2101
	 */
	ERROR2101("Erreur Contenu: Coordonnée de points manquants"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2100, concerne les points
	 * Erreur de type 2102, point en double
	 * ERREUR2102
	 */
	ERROR2102("Erreur Contenu: Point en double"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2200, concerne les segments
	 * Erreur de type 2201, une coordonnee est manquante
	 * ERREUR2201
	 */
	ERROR2201("Erreur Contenu: Coordonnée de segment manquants"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2200, concerne les segments
	 * Erreur de type 2202, segment en double
	 * ERREUR2202
	 */
	ERROR2202("Erreur Contenu: Segment en double"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2200, concerne les segments
	 * Erreur de type 2203, extrémité confondu
	 */
	ERROR2203("Erreur Contenu: Extrémité de segment confondus"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2300, concerne les faces
	 * Erreur de type 2301, une coordonnee est manquante
	 * ERREUR2301
	 */
	ERROR2301("Erreur Contenu: Coordonnée de face manquants"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2300, concerne les faces
	 * Erreur de type 2302, face en double
	 * ERREUR2302
	 */
	ERROR2302("Erreur Contenu: Face en double"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2300, concerne les faces
	 * Erreur de type 2303, Segments de face confondus
	 * ERREUR2303
	 */
	ERROR2303("Erreur Contenu: Segments de face confondus"),
	/**
	 * Erreur de type 2000, concerne le contenu de fichier
	 * Erreur de type 2300, concerne les faces
	 * Erreur de type 2304, sommet de face confondu ou en trops
	 */
	ERROR2304("Erreur Contenu: Sommet de face en trops ou confondu"),
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
	 * récupère une message d'erreur
	 * @return
	 */
	public String message(){
		return this.message;
	}
}
