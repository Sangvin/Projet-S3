package autre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import launcher.Launcher;

/**
 * cette classe vérifie la présence du dossier de configuration contenant la BDD et les fichiers de modélisation 3D
 * @author Alex.
 *
 */
public class Configure{
	private JFrame outLine;
	/**
	 * vérifie ou corrige la configuration
	 */
	public  Configure(){
		outLine = new JFrame("OutLine");
		outLine.setVisible(true);
		outLine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		outLine.setLayout(new BoxLayout(outLine.getContentPane(),BoxLayout.Y_AXIS));
		Launcher l = new Launcher(null);
		l.setText("Configuration");
		dossier(l);
		l.setValue(33);
		outLine.add(new JLabel("debut de la phase critique"));
		outLine.pack();
		bdd(l);
		l.setValue(66);		
		outLine.add(new JLabel("phase critique"));
		outLine.pack();
		examples(l);
		l.setValue(100);
		l.setText("Lancement");
		l.dispose();
	}

	/**
	 * regarde si le dossier de configuration existe
	 * si non le crée
	 * @return
	 */
	private void dossier(Launcher l){
		File f = new File("./config");
		l.setText("Dossier de configuration");
		if(!f.exists()){
			f.mkdir();
		}
	}

	/**
	 * regarde si la BDD existe bien dans le dossier config
	 * @return
	 */
	private void bdd(Launcher l){
		File f = new File("./config/bibliotheque.db");
		l.setText("Présence de la BDD");
		if(f.exists()){
			configBDD(l);
		
		}
		else{
			createBDD(l);
			configBDD(l);
		}
	}
	
	/**
	 * regarde si la configuration minimale de la bdd est minimale
	 * c'est à dire la présence des examples
	 * @param l
	 */
	private void configBDD(Launcher l){
		l.setText("Configuration des examples BDD");
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:sqlite:./config/bibliotheque.db");
				Statement stmt = con.createStatement();
				String req = "select name from object where name='drone_example'";
				ResultSet rs = stmt.executeQuery(req);
				if(rs.next() == false)
					insertExample("drone_example.gts");
				req = "select name from object where name='lugia_example'";
				rs = stmt.executeQuery(req);
				if(rs.next() == false)
					insertExample("lugia_example.gts");
				req = "select name from object where name='space_station_example'";
				rs = stmt.executeQuery(req);
				if(rs.next() == false)
					insertExample("space_station_example.gts");
				con.close();
			}catch(Exception e){
				e.printStackTrace();
				con.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * créer la base de donnée
	 */
	private void createBDD(Launcher l){
		l.setText("Création de la BDD");
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:sqlite:./config/bibliotheque.db");
				Statement stmt = con.createStatement();
				String req = "create table object (name text, url text, date text, auteur text, utilisation text, forme text, nb_points integer, nb_segments integer, nb_faces integer,constraint pk_object primary key (name))";
				stmt.executeUpdate(req);
				req = "create table tag (cle text,name text,constraint pk_tag primary key (cle,name), constraint fk_object foreign key (name) references object(name))";
				stmt.executeUpdate(req);
			}catch(Exception e){
				e.printStackTrace();
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * regarde les trois examples
	 * @param l
	 */
	private void examples(Launcher l){
		l.setText("Vérification des examples");		
		outLine.add(new JLabel("exemple 1 le drone"));
		outLine.pack();
		example("drone_example.gts");		
		outLine.add(new JLabel("exemple 2 le pokemon"));
		outLine.pack();
		example("lugia_example.gts");		
		outLine.add(new JLabel("exemple 3 la station"));
		outLine.pack();
		example("space_station_example.gts");
	}

	/**
	 * vérifie la présence de l'example dans le fichier config
	 * si non créer le fichier
	 * @param file
	 */
	private void example(String file){
		outLine.add(new JLabel("./config/"+file));
		outLine.pack();
		File f = new File("./config/"+file);
		if(!f.exists()){
			outLine.add(new JLabel("il n'existe pas"));
			outLine.pack();
			copyExample(file);
		}
	}
	
	/**
	 * copie l'example passé en paramètre dans le dossier de configuration
	 * @param file
	 */
	private void copyExample(String file){
		BufferedReader input =null;
		PrintWriter out = null;
		outLine.add(new JLabel(this.getClass().getResource(file).getPath()));
		outLine.pack();
		String tmp = this.getClass().getResource(file).getPath();
		try{
			input = new BufferedReader(new FileReader(new File(tmp)));
			out = new PrintWriter(new File("./config/"+file));
			while((tmp = input.readLine()) != null)
				out.println(tmp);
		}catch(Exception e){
			e.printStackTrace();
		}
		out.close();
	}
	
	/**
	 * insert un example dans la base de donnée
	 * attention l'insertion n'implique pas la copie du fichier mais juste
	 * un ajout dns la bdd
	 * @param file
	 * fichier à ajouter à la bdd
	 */
	private void insertExample(String file){
		BufferedReader input = null;
		String descripeurFigure = "0";
		try{
			input = new BufferedReader(new FileReader(this.getClass().getResource(file).getPath()));
			descripeurFigure = input.readLine();
			input.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:sqlite:./config/bibliotheque.db");
				Statement stmt = con.createStatement();
				String req = "insert into object values('"+file.substring(0, file.length()-4)+"','./config/"+file+"','00/00/0000',"
						+ "'3DGenerator','example','example','"+Integer.parseInt(descripeurFigure.split(" ")[0])+"','"+
						Integer.parseInt(descripeurFigure.split(" ")[1])+"','"+Integer.parseInt(descripeurFigure.split(" ")[2])
						+"')";
				stmt.executeUpdate(req);
				req = "insert into tag values('Example','"+file.substring(0,file.length()-4)+"')";
				stmt.executeUpdate(req);
			}catch(Exception e){
				e.printStackTrace();
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
