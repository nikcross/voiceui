package com.wetwired.voiceui.common.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Nik Cross
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IntentConfig {

	String name();

	String[] utterances();
}
