#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.infra.logging;



public interface Logger {

    boolean isLogLevel(LogLevel logLevel, Class<?> clazz);

    void log(LogLevel logLevel, Class<?> clazz, Throwable throwable, String pattern,
            Object... arguments);
}
