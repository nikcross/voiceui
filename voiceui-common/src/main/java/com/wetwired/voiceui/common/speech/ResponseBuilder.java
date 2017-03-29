package com.wetwired.voiceui.common.speech;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wetwired.voiceui.common.services.PseudonymService;
import com.wetwired.voiceui.common.utils.Randomizer;

/**
 * @author Nik Cross
 */
public class ResponseBuilder {

	public static ResponseBuilder createASpokenResponse(PseudonymService pseudonymService,String... templates) {
		String template = randomFrom(templates);
		return new ResponseBuilder(pseudonymService,template);
	}

	public static String randomFrom(String... options) {
		return options[Randomizer.getRandom(0,options.length)];
	}

	private final String template;
	private Map<String,String> elements;
	private PseudonymService pseudonymService;

	private ResponseBuilder(PseudonymService pseudonymService,String... templates) {
		String template = randomFrom(templates);
		this.pseudonymService = pseudonymService;
		this.template = template;
		elements = new HashMap<>();
	}

	public ResponseBuilder with(String key, String value) {
		String phoneticName = pseudonymService.getPhoneticNameFor(value);
		if(phoneticName!=null) {
			value = phoneticName;
		}

		elements.put(key,value);
		return this;
	}

	public ResponseBuilder with(String key, List<String> items) {
		elements.put(key, ListPhraseGenerator.getAndList(items));
		return this;
	}


	public String build() throws ResponseBuilderException {
		return ResponseBuilder.getResponse(template,elements);
	}

	public static String getResponse(String template, Map<String,String> elements) throws ResponseBuilderException {
		String speechText = "";

		String[] parts = template.split("\\{|\\}");
		boolean isElement = false;
		for(String part: parts) {
			if(!isElement) {
				speechText += part;
			} else {
				String element = elements.get(part);
				if(element!=null) {
					speechText += element;
				} else {
					throw new ResponseBuilderException("Could not find element {"+part+"} for use in template "+template);
				}
			}
			isElement = !isElement;
		}

		return speechText;
	}
}
