#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.handler;

import org.springframework.web.servlet.View;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.mvc.builder.FlowResourceFlowViewResolver;

public class GreenFieldFlowViewResolver extends FlowResourceFlowViewResolver {

	public View resolveView(String viewId, RequestContext context) {

		return super.resolveView(viewId, context);
	}

}
