#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.infra.logging.aspect;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${package}.infra.logging.LogLevel;
import ${package}.infra.logging.Loggable;
import ${package}.infra.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final String BEFORE_STRING = "[ entering < {0} > ]";

    private static final String BEFORE_WITH_PARAMS_STRING =
            "[ entering < {0} > with params {1} ]";

    private static final String AFTER_THROWING =
            "[ exception thrown < {0} > exception message {1} with params {2} ]";

    private static final String AFTER_RETURNING = "[ leaving < {0} > returning {1} ]";

    private static final String AFTER_RETURNING_VOID = "[ leaving < {0} > ]";

    @Autowired
    private Logger logger;

    @Before(value = "@annotation(loggable)", argNames = "joinPoint, loggable")
    public void before(final JoinPoint joinPoint, final Loggable loggable) {
        
        final Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        final String name = joinPoint.getSignature().getName();
        
        if (ArrayUtils.isEmpty(joinPoint.getArgs())) {
            
            logger.log(loggable.value(), clazz, null, BEFORE_STRING, name,
                    constructArgumentsString(clazz, joinPoint.getArgs()));
            
        } else {
            
            logger.log(loggable.value(), clazz, null, BEFORE_WITH_PARAMS_STRING, name,
                    constructArgumentsString(clazz, joinPoint.getArgs()));
            
        }
    }

    @AfterThrowing(value = "@annotation(${package}.logging.Loggable)",
            throwing = "throwable", argNames = "joinPoint, throwable")
    public void afterThrowing(final JoinPoint joinPoint, final Throwable throwable) {

        final Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        final String name = joinPoint.getSignature().getName();
        
        logger.log(LogLevel.ERROR, clazz, throwable, AFTER_THROWING, name,
                throwable.getMessage(), constructArgumentsString(clazz, joinPoint.getArgs()));
    }

    @AfterReturning(value = "@annotation(loggable)", returning = "returnValue",
            argNames = "joinPoint, loggable, returnValue")
    public void afterReturning(final JoinPoint joinPoint, final Loggable loggable,
            final Object returnValue) {

        final Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        final String name = joinPoint.getSignature().getName();
        
        if (joinPoint.getSignature() instanceof MethodSignature) {
            
            final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            final Class<?> returnType = signature.getReturnType();
            
            if (returnType.getName().compareTo("void") == 0) {
                logger.log(loggable.value(), clazz, null, AFTER_RETURNING_VOID, name,
                        constructArgumentsString(clazz, returnValue));

                return;
            }
        }

        logger.log(loggable.value(), clazz, null, AFTER_RETURNING, name,
                constructArgumentsString(clazz, returnValue));
    }

    private String constructArgumentsString(final Class<?> clazz, final Object... arguments) {

        StringBuffer buffer = new StringBuffer();
        for (final Object object : arguments) {
            if (buffer.length() > 0) {
                buffer.append(", ");
            }
            
            buffer.append(object);
        }
        
        return buffer.toString();
    }
}