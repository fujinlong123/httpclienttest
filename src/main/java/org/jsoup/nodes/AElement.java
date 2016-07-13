package org.jsoup.nodes;

import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.parser.Tag;

public class AElement  extends Element {

	private String hostname;
	
	public AElement(Tag tag, String baseUri) {
		super(tag, baseUri);
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	
	
	@Override
	public Element attr(String attributeKey, String attributeValue) {
		if("href".equals(attributeKey)){
			URI uri;
			try {
				uri = new URI(attributeValue);
				hostname=uri.getHost();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		
		return super.attr(attributeKey, attributeValue);
	}

}
