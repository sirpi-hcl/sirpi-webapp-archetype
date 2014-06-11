#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.common;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable(value="enumTypes")
public class EnumTypeServiceImpl implements IEnumTypeService {

	// Master data lookup servies
}
