#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.infra.exception.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${package}.infra.annotations.HandleException;
import ${package}.infra.exception.ApplicationException;
import ${package}.infra.logging.LogLevel;
import ${package}.infra.logging.Logger; 

/**
 * Aspect to handle exception transparently from methods.
 * @author purushothaman.s
 */

@Aspect
@Component
public class ExceptionAspect {

	 static final String HANDLEEXCEPTION_ANNOTATION = "@annotation(${package}.infra.annotations.HandleException)";
	
    @Autowired
    private Logger logger;
	
	/**
	 * Utility method check method annotated with @HandleException.
	 * @param pjp
	 * @return
	 */
	private boolean isAnnotationPresent(ProceedingJoinPoint pjp) {
		MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        return (targetMethod.isAnnotationPresent(HandleException.class));
	}
	
	/**
	 * Check source exception present to match exception thrown from method with advice. If the source is not present,
	 * advice will be applied to all types of exception.
	 * @param marker
	 * @param cause
	 * @return
	 */
	private boolean canHandle(HandleException marker, Throwable cause) {
		String source = marker.source();
		if (( "".equals(source)) || 
			((source != null) && (source.equalsIgnoreCase(cause.getClass().getName())))
		) {
			return true;
		}
		return false;
	}
	

	/**
	 * Advice to handle exception transparently from domain methods.
	 * @param pjp
	 * @throws Throwable 
	 */
	@Around(HANDLEEXCEPTION_ANNOTATION)
	public Object handleException(ProceedingJoinPoint pjp) throws Throwable {
	  try {
		 return pjp.proceed();
	  } catch (Throwable cause) {
		  if (isAnnotationPresent(pjp)) {
			  HandleException marker = getAnnotation(pjp);
			  if (canHandle(marker, cause)) {
				  logger.log(LogLevel.ERROR, pjp.getTarget().getClass(), cause, HANDLEEXCEPTION_ANNOTATION, pjp.getArgs());
				  Throwable wrappedCause = WrapException(marker, cause);	
				  throw wrappedCause;
			  } else {
				  throw cause;
			  }
		 } else {
			 throw cause;
		 }
		 
	  }
	}
	
	/**
	 * Wrap exception with the target type. if target type isn't present, exception thrown will be 
	 * wrapped with default runtime exception.
	 * @param marker
	 * @param cause
	 * @return
	 */
	private Throwable WrapException(HandleException marker, Throwable cause) {
		
		String target = marker.target();
		
		if (target != null) {
    		try {
    			Class<?> targetClazz = Class.forName(target);
				return createTargetInstance(targetClazz, marker.message(), cause);
			} catch (Exception e) {
				throw new RuntimeException(String.format("Unable to create target class {0}",target), e);
			}

		} else {
			return createDefaultException(marker.message(), cause);
		}
	}
	
	/**
	 * Create target exception.
	 * @param target
	 * @param message
	 * @param cause
	 * @return
	 * @throws Exception
	 */
	private Throwable createTargetInstance(Class<?> target, String message, Throwable cause) throws Exception {

		   if (target.getConstructor(String.class, Throwable.class) != null) {
			   return (Throwable)target. getConstructor(String.class, Throwable.class).newInstance(message, cause);
		   } else if (target.getConstructor(Throwable.class, String.class) != null) {
			   return (Throwable)target.getConstructor(Throwable.class, String.class).newInstance(cause, message);
		   } else if (target.getConstructor(String.class) != null) {
			   return (Throwable)target.getConstructor(String.class).newInstance(message);
		   } else if (target.getConstructor(Throwable.class) != null) {
			   return (Throwable)target.getConstructor(Throwable.class).newInstance(cause);
		   } else {
			  return createDefaultException(message, cause);
		   }

	}
	
	/**
	 * Create default exception.
	 * @param message
	 * @param cause
	 * @return
	 */
	private ApplicationException createDefaultException(String message, Throwable cause) {
		
	  return (message != null) ?  new ApplicationException(message, cause) 
		  : new ApplicationException(cause);

	}
	
	
	/**
	 * Get marker annotation from method with advice.
	 * @param pjp
	 * @return HandleException
	 */
	private HandleException getAnnotation(ProceedingJoinPoint pjp) {
		MethodSignature methodSignature = (MethodSignature)  pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        
        if (targetMethod.getDeclaringClass().isInterface()) {
      	  	try {
      	  		targetMethod = pjp.getTarget().getClass().getDeclaredMethod(targetMethod.getName(), targetMethod.getParameterTypes());
			} catch (SecurityException e) {
				throw new RuntimeException(String.format("Unable to access method {0}",targetMethod.getName()), e);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(String.format("Unable to find method {0}",targetMethod.getName()), e);
			}  
        }
        
     	HandleException marker = targetMethod.getAnnotation(HandleException.class);
     	return marker;
	}
	
}
