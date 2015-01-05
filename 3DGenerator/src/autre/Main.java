package autre;

import graphic.Frame;
import mvc.Model;
import mvc.ObjectController;

/**
 * contient la methode main du logiciel
 * @author Alex
 *
 */
public class Main{

	/**
	 * Methode principale du l'ogiciel
	 * @param args
	 */
	public static void main(String[] args){
		new Configure();
		Model m = new Model();
		new Frame(m,new ObjectController(m));
	}
}
