#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.handler;

import javax.faces.application.ViewHandler;
import javax.faces.context.FacesContext;

import org.springframework.faces.webflow.Jsf2FlowViewHandler;

/**
 * 
 * @author ashrafs
 */

public class GreenFieldFlowViewHandler extends Jsf2FlowViewHandler {

	public static final String PAGE_CONVENTION_PREFIX_PATH = "WEB-INF/page/";
	public static final String PAGE_CONVENTION_SUFFIX_PATH = ".xhtml";

	public GreenFieldFlowViewHandler(ViewHandler delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	public String deriveViewId(FacesContext context, String rawViewId) {

		rawViewId = "/" + PAGE_CONVENTION_PREFIX_PATH + rawViewId
				+ PAGE_CONVENTION_SUFFIX_PATH;

		return super.deriveViewId(context, rawViewId);

	}

}
