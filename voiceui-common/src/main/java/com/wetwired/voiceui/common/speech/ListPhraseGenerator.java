package com.wetwired.voiceui.common.speech;

import java.util.List;
import java.util.ListIterator;
import com.wetwired.voiceui.common.services.PseudonymService;

/**
 * @author Nik Cross
 */
public class ListPhraseGenerator {

	private static final String COMMA = ",";
	private static final String AND = "and";
	private static final String OR = "or";


	public static String getListDescription(PseudonymService pseudonymService, List<String> items, String singlarTemplate, String pluralTemplate) throws ResponseBuilderException {
		if(items.size()==1) {
			return ResponseBuilder.createASpokenResponse(pseudonymService,singlarTemplate)
					.with("n","1")
					.build();
		} else  {
			return ResponseBuilder.createASpokenResponse(pseudonymService,pluralTemplate)
					.with("n",""+items.size())
					.build();
		}
	}

	public static String getAndList(List<String> items) {
		return getList( COMMA,AND,items );
	}

	public String getOrList(List<String> items) {
		return getList( COMMA,OR,items );
	}

	public static String getList(String itemSeparator, String finalSeparator, List<String> items) {
		String speechText = "";
		ListIterator<String> iterator = items.listIterator();
		while(iterator.hasNext()) {
			boolean isFirst = !iterator.hasPrevious();
			String item = iterator.next();
			boolean isLast = !iterator.hasNext();


			if(isLast && !isFirst) {
				speechText += " "+finalSeparator+" ";
			} else if(!isFirst) {
				speechText += itemSeparator+" ";
			}

			speechText += item;
		}
		return speechText;
	}
}
