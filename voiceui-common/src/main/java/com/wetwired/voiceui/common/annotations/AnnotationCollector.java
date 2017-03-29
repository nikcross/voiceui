package com.wetwired.voiceui.common.annotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

/**
 * @author Nik Cross
 */
public class AnnotationCollector {

	public static Set<BeanDefinition> getBeans(String rootPackage) {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
		Set<BeanDefinition> beans = scanner.findCandidateComponents(rootPackage);
		return beans;
	}

	public static List<ContentIntentConfig> getContentIntents(BeanDefinition bean) throws ClassNotFoundException {
		List<ContentIntentConfig> contentIntentList = new ArrayList<>();
		ContentIntents contentIntents = Class.forName(bean.getBeanClassName()).getAnnotation(ContentIntents.class);
		if (contentIntents != null) {
			contentIntentList.addAll(Arrays.asList(contentIntents.value()));
		}
		ContentIntentConfig contentIntentConfig = Class.forName(bean.getBeanClassName()).getAnnotation(ContentIntentConfig.class);
		if (contentIntentConfig != null)
			contentIntentList.add(contentIntentConfig);

		return contentIntentList;
	}

	public static List<IntentConfig> getIntents(BeanDefinition bean) throws ClassNotFoundException {
		List<IntentConfig> intentList = new ArrayList<>();
		Intents intents = Class.forName(bean.getBeanClassName()).getAnnotation(Intents.class);
		if (intents != null) {
			intentList.addAll(Arrays.asList(intents.value()));
		}
		IntentConfig intentConfig = Class.forName(bean.getBeanClassName()).getAnnotation(IntentConfig.class);
		if (intentConfig != null)
			intentList.add(intentConfig);

		return intentList;
	}
}
