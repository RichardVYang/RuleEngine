package com.delta.occ.starter.greeting;

import com.delta.foundation.core.AbstractConsumerProducerParser;
import com.delta.occ.starter.greeting.GreetingConsumerStarter;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class GreetingConsumerStarterParser extends AbstractConsumerProducerParser {

  @Override
  protected BeanDefinitionBuilder parseConsumerProducer(
      Element element, ParserContext parserContext) {
    BeanDefinitionBuilder builder =
        BeanDefinitionBuilder.genericBeanDefinition(GreetingConsumerStarter.class);

    return builder;
  }
}
