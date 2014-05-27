package cs886.w14.proj.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cs886.w14.proj.util.ANEWEntry;


public class ANEWDicWrapper {

	String _anewDic;
	private final Logger logger = Logger.getLogger(ANEWDicWrapper.class.getName());
	private List<ANEWEntry> entries;

	public List<ANEWEntry> getEntries() {
		return entries;
	}

	public ANEWEntry getEntrybyWord(String word) {
	    for (ANEWEntry entry : entries) {
	    	// match word or wordstem
	        if (entry.getWord().equals(word) || entry.getWordstem().equals(word)) {
	            return entry;
	        }
	    }
	    return null;
	}
	
	public ANEWDicWrapper(String dicPath)  {
		try {
			BufferedReader br = new BufferedReader(new FileReader(dicPath));
			entries = new ArrayList<ANEWEntry>();
			
			String line;
			br.readLine(); // read in header
			while ((line = br.readLine()) != null) {
				String[] splited = line.split("\\s+");
				ANEWEntry entry = new ANEWEntry(splited[0], Short.parseShort(splited[1]), Float.parseFloat(splited[2]),Float.parseFloat(splited[3]),Float.parseFloat(splited[4]),Float.parseFloat(splited[5]),Float.parseFloat(splited[6]),Float.parseFloat(splited[7]));
				entries.add(entry);		
			}
			br.close();
			logger.log(Level.INFO, "Input finished with total of "+entries.size());
		} catch (IOException e) {
			logger.log(Level.WARNING, "File not found "+e.toString());
		}

	}

}
