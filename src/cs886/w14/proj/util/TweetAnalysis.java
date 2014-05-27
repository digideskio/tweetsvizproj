package cs886.w14.proj.util;

import java.util.List;
import java.util.ArrayList;
import cs886.w14.proj.util.ANEWEntry;
import cs886.w14.proj.util.Gaussian;
public class TweetAnalysis {
	public List<ANEWEntry> words;
	
	
	public TweetAnalysis() {
		words = new ArrayList<ANEWEntry>();
	}
	public int getNumofValidWords() {
		return words.size();
	}
	private double getVal() {
		double fuse = 0.0d;
		double weights = 0.0d;
		for(ANEWEntry word: words) {
			double weight = Gaussian.phi(word.getValMn(), word.getValMn(), word.getValSD());
			fuse += word.getValMn()*weight;
			weights += weight;
		}
		return fuse/weights;
	}
	
	private double getAro() {
		double fuse = 0.0d;
		double weights = 0.0d;
		for(ANEWEntry word: words) {
			double weight = Gaussian.phi(word.getAroMn(), word.getAroMn(), word.getAroSD());
			fuse += word.getAroMn()*weight;
			weights += weight;
		}
		return fuse/weights;
	}
	
	public double getFusedVal() {
		return (getAro()+getVal())/2;
	}
	
	
	
	public void addWord(ANEWEntry word) {
		if(!words.contains(word)) {
			words.add(word);
		}	
	}
	
	public String wordsToString() {
		String wordsStr = "";
		for(ANEWEntry word: words) {
			wordsStr+=word.getWord() + ", ";
		}
		return wordsStr;
	}
}
