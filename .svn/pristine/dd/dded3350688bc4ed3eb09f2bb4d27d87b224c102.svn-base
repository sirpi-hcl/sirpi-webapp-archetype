#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infra.validation.aspect;

import static org.springframework.util.Assert.notNull;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ParametersNotNullAspect {
		
	
	@Before(value="execution(* *.*(..))")
	public void handleParametersNull(JoinPoint joinPoint) {
		
		for(Object arg:joinPoint.getArgs()){
			notNull(arg);			
		}
	}
	
}
