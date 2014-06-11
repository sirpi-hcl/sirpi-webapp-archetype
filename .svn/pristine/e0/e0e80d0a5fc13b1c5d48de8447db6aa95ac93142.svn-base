#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.handler;

/**
 * 
 * @author ashrafs
 */

import org.springframework.binding.expression.EvaluationException;
import org.springframework.binding.expression.Expression;

public class GreenFieldViewExpressionDecorator implements Expression {

	private Expression expressionDelegate;

	public static final String PAGE_CONVENTION_PREFIX_PATH = "WEB-INF/page/";
	public static final String PAGE_CONVENTION_SUFFIX_PATH = ".xhtml";

	public GreenFieldViewExpressionDecorator(Expression expressionDelegate) {
		this.expressionDelegate = expressionDelegate;

	}

	@Override
	public Object getValue(Object context) throws EvaluationException {

		String viewPath = (String) expressionDelegate.getValue(context);

		viewPath = "/" + PAGE_CONVENTION_PREFIX_PATH + viewPath
				+ PAGE_CONVENTION_SUFFIX_PATH;

		return viewPath;
	}

	@Override
	public void setValue(Object context, Object value)
			throws EvaluationException {

		expressionDelegate.setValue(context, value);

	}

	@Override
	public Class getValueType(Object context) throws EvaluationException {
		// TODO Auto-generated method stub
		return expressionDelegate.getValueType(context);
	}

	@Override
	public String getExpressionString() {

		return expressionDelegate.getExpressionString();
	}

}
