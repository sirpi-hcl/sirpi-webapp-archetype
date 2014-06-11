#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.handler;

import javax.faces.lifecycle.Lifecycle;

import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.expression.Expression;
import org.springframework.binding.expression.ExpressionParser;
import org.springframework.faces.webflow.FlowLifecycle;
import org.springframework.faces.webflow.JsfViewFactory;
import org.springframework.faces.webflow.JsfViewFactoryCreator;
import org.springframework.validation.Validator;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.execution.ViewFactory;

import ${package}.web.handler.GreenFieldViewExpressionDecorator;

/**
 * 
 * @author ashrafs
 */

public class GreenFieldJsfViewFactoryCreator extends JsfViewFactoryCreator {

	private Lifecycle lifecycle2;

	public ViewFactory createViewFactory(Expression viewIdExpression,
			ExpressionParser expressionParser,
			ConversionService conversionService,
			BinderConfiguration binderConfiguration, Validator validator) {

		GreenFieldViewExpressionDecorator expressionDecorator = new GreenFieldViewExpressionDecorator(
				viewIdExpression);
		return new JsfViewFactory(expressionDecorator, getLifecycle());
	}

	private Lifecycle getLifecycle() {
		if (lifecycle2 == null) {
			lifecycle2 = FlowLifecycle.newInstance();
		}
		return lifecycle2;
	}

}
