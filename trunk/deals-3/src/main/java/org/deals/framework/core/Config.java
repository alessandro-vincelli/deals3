package org.deals.framework.core;

import org.apache.cocoon.configuration.Settings;

public class Config {
	
	/// IL CONFIG RILEGGE AD OGNI CHIAMATA I DATI DAL FILE DI CONFIGURAZIONE
	/// IN QUESTO MODO SI POSSONO CAMBIARE I PARAMETRI A RUNTIME, VOLENDO
	
	/// SE I PARAMETRI DIVENTASSERO NUMEROSI SAREBBE IL CASO DI VALUTARE
	/// L'USO DI UNA HASHMAP CHE VERREBBE CARICATA ALL'INIZIO, MA AGGIORNATA
	/// SOLTANTO AL NUOVO CARICAMENTO DELLE CLASSI
	
	private static Settings settings;
	//private static String CONFIG_PATH = null;
	
	/*static {
		if (CONFIG_PATH==null) 
			CONFIG_PATH = Config.class.getClassLoader().getResource("deals.properties").getPath();
	}
	
	
	public static String getString(String key) {
		try {
			File f = new File(CONFIG_PATH);
			BufferedReader bis = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = bis.readLine()) != null) {
				String[] keyVal = line.split("=");
				if (keyVal[0].trim().equals(key.trim())) return keyVal[1];				
			}
			return '!' + key + "! NOT FOUND!!!!";
		} catch (FileNotFoundException e) {
			return "!!! Config file not found !!!";
		} catch (IOException e) {
			return "!!! Error reading config file !!!";
		}
	}*/

	public static String getProperty(String key){
		return settings.getProperty(key);
	}
	// a private constructor
    private Config() { 
    }
    
    // a static factory method; the arguments to this method can be
    // considered the dependencies of the bean that is returned,
    // regardless of how those arguments are actually used.
    public static Config createInstance (
    		Settings set) {

    	Config eb = new Config ();
    	eb.settings = set;
        return eb;
    }
	
}
