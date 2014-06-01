package cs886.w14.proj.sentiment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cs886.w14.proj.sentiment.ANEWEntry;


public class ANEWDicWrapper {
	//public static final String []_anewHighlight = {"nervous", "upset", "sad", "unhappy", "depressed", "relaxed", "satisfied", "pleasure", "happy", "excitement"};
	//public static final String []_anewHighlight = {"nervous", "upset", "sad", "unhappy", "depressed", "relaxed", "satisfied", "pleasure", "happy", "excitement"};
	public static final String []_anewHighlight = {"nervous", "upset", "sad", "depressed", "relaxed", "pleasure", "happy", "excitement", "satisfied", "tense", "serene", "unhappy"};
	public double fqAvg, fqMin, fqMax;
	
	private final Logger logger = Logger.getLogger(ANEWDicWrapper.class.getName());
	private List<ANEWEntry> entries, entriesHighlighted;
	private List<String> highlights = new ArrayList<String>();
	
	public List<ANEWEntry> getEntries() {
		return entries;
	}

	public List<ANEWEntry> getEntriesHighlighted() {
		return entriesHighlighted;
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
		initANEWDicWrapper();
		initANEWDicHighlights();
	}
	
	private void initANEWDicWrapper() {
		if (entries != null && entries.size() != 0) {
			double min = 100000, max = -1, sum = 0;
			for (ANEWEntry e : entries) {
				if (e.getWdnum() < min) min = e.getWdnum();
				if (e.getWdnum() > max) max = e.getWdnum();
				sum += e.getWdnum();
			}
			this.fqAvg = sum / entries.size(); 
			this.fqMin = min;
			this.fqMax = max;
		}
	}
	
	// words to be higlighted in 3d scatter plot
	private void initANEWDicHighlights() {
		for (int i = 0; i < _anewHighlight.length; i++) {
			highlights.add(_anewHighlight[i]);
		}
		entriesHighlighted = new ArrayList<ANEWEntry>();
		for (ANEWEntry e : entries) {
			if (highlights.contains(e.getWord())) {
				entriesHighlighted.add(e);
			}
		}
		logger.log(Level.INFO, "highlights size = " + highlights.size());
		logger.log(Level.INFO, "entriesHighlighted dic size = " + entriesHighlighted.size());
	}

}
