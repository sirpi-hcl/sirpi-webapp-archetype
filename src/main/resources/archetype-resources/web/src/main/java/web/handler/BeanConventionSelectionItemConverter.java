#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.handler;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.BeanUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component("beanSelectionItemConverter")
public class BeanConventionSelectionItemConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	public static final String CONVERTER_ID = "beanSelectionItemConverter";

	public BeanConventionSelectionItemConverter() {

	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		UISelectItems selectItems = getSelectItemsComponent(context, component);
		
		if(selectItems==null)
			return null;

		Collection collectionValues = (Collection) selectItems.getValue();
		Iterator<Object> selectItemBean = (Iterator<Object>) collectionValues
				.iterator();

		while (selectItemBean.hasNext()) {
			Object varBean = selectItemBean.next();
			Object evalValue = evaluateValue(context, component, varBean);
			String evalAsString = evalValue.toString();

			if (value.equals(evalAsString))
				return varBean;

		}

		return null;
	}

	protected UISelectItems getSelectItemsComponent(FacesContext context,
			UIComponent component) {
		for (final UIComponent childComponent : component.getChildren()) {

			if (childComponent instanceof UISelectItems) {
				return (UISelectItems) childComponent;
			}
		}

		return null;
	}

	@Override
	public String getAsString(final FacesContext facesContext,
			final UIComponent component, final Object value) {

		if (value == null) {
			return null;
		}

		if (BeanUtils.isSimpleProperty(value.getClass())) {
			return value.toString();
		} else {

			Object evalValue = evaluateValue(facesContext, component, value);
			return evalValue.toString();

		}
	}

	private Object evaluateValue(FacesContext context, UIComponent component,
			final Object rootContext) {

		UISelectItems selectItems = getSelectItemsComponent(context, component);
		String variableName = (String) selectItems.getAttributes().get("var");
		String valuePropertyName =   (String) selectItems.getAttributes().get("valueProperty");
		ValueExpression valueExpression = selectItems
				.getValueExpression("itemValue");
		
		String valueExpressionFull = valueExpression.getExpressionString()+"."+valuePropertyName;
		Expression expression = parseExpression(valueExpressionFull);
		final StandardEvaluationContext evalautionContext = new StandardEvaluationContext();
		evalautionContext.setVariable(variableName, rootContext);

		return (Object) expression.getValue(evalautionContext);
	}

	public Expression parseExpression(String valueExpression) {

		final ExpressionParser parser = new SpelExpressionParser();

		valueExpression = valueExpression.replaceAll("\\{", "");
		valueExpression = valueExpression.replaceAll("\\}", "");

		return parser.parseExpression(valueExpression);
	}

}
