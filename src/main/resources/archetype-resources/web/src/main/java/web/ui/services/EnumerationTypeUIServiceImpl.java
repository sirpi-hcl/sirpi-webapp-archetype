#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.ui.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ${package}.services.common.IEnumTypeService;



public class EnumerationTypeUIServiceImpl implements IEnumerationTypeUIService {

	@Autowired
	IEnumTypeService enumerationTypeService;

	
}
