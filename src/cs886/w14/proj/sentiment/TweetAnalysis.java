package cs886.w14.proj.sentiment;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import cs886.w14.proj.TweetsVizJSPServlet;
import cs886.w14.proj.sentiment.ANEWEntry;
import cs886.w14.proj.util.Gaussian;

public class TweetAnalysis {
	public List<ANEWEntry> words;
	private final Logger logger = Logger.getLogger(TweetAnalysis.class.getName());
	
	public TweetAnalysis() {
		words = new ArrayList<ANEWEntry>();
	}

	public int getNumofValidWords() {
		return words.size();
	}

	public double getFusedVal() {
		return (getAro() + getVal()) / 2;
	}

	public void addWord(ANEWEntry word) {
		if (!words.contains(word)) {
			words.add(word);
		}
	}

	public String wordsToString() {
		String wordsStr = "";
		for (ANEWEntry word : words) {
			wordsStr += word.getWord() + ", ";
		}
		return wordsStr;
	}

	public double getVal() {
		double fuse = 0.0d;
		double weights = 0.0d;
		for (ANEWEntry word : words) {
			double weight = Gaussian.phi(word.getValMn(), word.getValMn(),
					word.getValSD());
			fuse += word.getValMn() * weight;
			weights += weight;
		}
		double rtn = fuse / weights;
		DecimalFormat df = new DecimalFormat("#.##");      
		rtn = Double.valueOf(df.format(rtn));
		return rtn;
	}

	public double getAro() {
		double fuse = 0.0d;
		double weights = 0.0d;
		for (ANEWEntry word : words) {
			double weight = Gaussian.phi(word.getAroMn(), word.getAroMn(),
					word.getAroSD());
			fuse += word.getAroMn() * weight;
			weights += weight;
		}
		double rtn = fuse / weights;
		DecimalFormat df = new DecimalFormat("#.##");      
		rtn = Double.valueOf(df.format(rtn));
		return rtn;
	}
	
	public double getDom() {
		double fuse = 0.0d;
		double weights = 0.0d;
		for (ANEWEntry word : words) {
			double weight = Gaussian.phi(word.getDomMn(), word.getDomMn(),
					word.getDomSD());
			fuse += word.getDomMn() * weight;
			weights += weight;
		}
		double rtn = fuse / weights;
		DecimalFormat df = new DecimalFormat("#.##");      
		rtn = Double.valueOf(df.format(rtn));
		return rtn;
	}
}
