package models;

/** 
 * A tag from an XML document
 * @author Nic Kelly
 */
public class Tag {
	private String line;
	private String tagName;
	private String type;
	
	/**
	 * Create a tag with set values
	 * @param tag the value of the tag with all special characters
	 * @param line that the tag was found on
	 * @param type of tag to set
	 */
	public Tag(String tag, String line, String type) {
		tagName = tag;
		this.line = line;
		this.type = type;
	}

	/**
	 * @return line the tag was on
	 */
	public String getLine() {
		return line;
	}
	
	/**
	 * @return the type of the tag
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Compute what the name of the tag is without extra tag characters
	 * @return properName the proper tag name without extra characters
	 */
	public String getTagName() {
		
		String properName = tagName.replace('<', ' ');
		properName = properName.replace('>', ' ');
		properName = properName.replace('/', ' ');
		properName = properName.trim();
		
		
		return properName;
	}
	
	@Override
	public String toString() {
		return tagName + " : " + type;
	}
}
