package autre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import launcher.Launcher;

/**
 * cette classe vérifie la présence du dossier de configuration contenant la BDD et les fichiers de modélisation 3D
 * @author Alex.
 *
 */
public class Configure{
	
	/**
	 * ponstructeur du launcher
	 */
	public Configure(){
		Launcher l = new Launcher();
		l.setText("Configuration");
		/*if(!configIsOk()){
		    l.setText("Création du dossier");
			installConfig();
			l.setValue(33);
			l.setText("Création de la BDD");
			installBDD();
			l.setValue(66);
			l.setText("Création des examples");
			this.setExample();
			l.setValue(100);
		}
		else if(!bddIsOk()){
			l.setText("Création de la BDD");
			installBDD();
			l.setValue(50);
			l.setText("Création des examples");
			this.setExample();
			l.setValue(100);
		}
		else if(!ExempleIsOk()){
			l.setText("Création des examples");
			this.setExample();
		}
		*/
		l.setText("Lancement");
		l.dispose();
	}

	/**
	 * regarde si le dossier de configuration existe
	 * @return
	 */
	private boolean configIsOk(){
		File f = new File("./conf");
		if(f.exists()){
			if(!f.isDirectory()) 
				return false;
			else
				return true;
		}
		else
			return false;
	}

	/**
	 * créer le dossier de configuration puis la basse de donnée
	 */
	private void installConfig(){
		new File("./config").mkdir();
		installBDD();
		setExample();
	}

	/**
	 * regarde si la BDD existe bien dans le dossier config
	 * @return
	 */
	private boolean bddIsOk(){
		File f = new File("");
		if(f.exists())
			return true;
		else
			return false;
	}

	/**
	 * regarde la présence des examples
	 * @return
	 */
	private boolean ExampleIsOk(){
		File f = new File("drone_example.gts");
		if(!f.exists())
			return false;
		f = new File("lugia_example.gts");
		if(!f.exists())
			return false;	
		f = new File("space_station_example.gts");
		if(!f.exists())
			return false;
		return true;
	}

	/**
	 * créer la base de donnée
	 */
	private void installBDD(){
		try {
			new File("").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * colle des example dans le dossier de configuration
	 */
	private void setExample() {
		List<String> example = new ArrayList<String>();
		example.add("drone_example.gts");
		example.add("lugia_example.gts");
		example.add("space_station_example.gts");
		String tmp;
		BufferedReader input = null;
		PrintWriter out = null;
		for(int i = 0; i < example.size(); i++){
			try {
				tmp = this.getClass().getResource(example.get(i)).getPath();
				input = new BufferedReader(new FileReader(tmp));
				out = new PrintWriter(new File("./config/"+example.get(i)));
				while((tmp = input.readLine()) != null)
					out.println(tmp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.close();
		}
	}

	public static void main(String[] args){
		new Configure();
	}
}
