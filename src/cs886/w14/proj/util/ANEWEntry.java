package cs886.w14.proj.util;

import org.tartarus.snowball.EnglishSnowballStemmerFactory;
import org.tartarus.snowball.util.StemmerException;

public class ANEWEntry {

	private String _word, _wordstem;
	private short _wdnum;
	private float _valMn;
	private float _valSD;
	private float _aroMn;
	private float _aroSD;
	private float _domMn;
	private float _domSD;

	public ANEWEntry(String word, short wdnum, float valMn, float valSD,
			float aroMn, float aroSD, float domMn, float domSD) {
		super();
		this._word = word;
		this._wdnum = wdnum;
		this._valMn = valMn;
		this._valSD = valSD;
		this._aroMn = aroMn;
		this._aroSD = aroSD;
		this._domMn = domMn;
		this._domSD = domSD;
		this._wordstem = computeWordstem(this._word);
	}
	
	public String getWord() {
		return _word;
	}

	public short getWdnum() {
		return _wdnum;
	}

	public float getValMn() {
		return _valMn;
	}

	public float getValSD() {
		return _valSD;
	}

	public float getAroMn() {
		return _aroMn;
	}

	public float getAroSD() {
		return _aroSD;
	}

	public float getDomMn() {
		return _domMn;
	}

	public float getDomSD() {
		return _domSD;
	}

	public String getWordstem() {
		return _wordstem;
	}

	private String computeWordstem(String str) {
		String stem = null;
		try {
			stem = EnglishSnowballStemmerFactory.getInstance().process(str);
		} catch (StemmerException e) {
			e.printStackTrace();
		}
		return stem;
	}

}
