#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infra.logging;

import java.text.MessageFormat;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Slf4jLogger implements Logger {

    public boolean isLogLevel(final LogLevel logLevel, final Class<?> clazz) {

        boolean result = false;

        switch (logLevel) {

            case DEBUG:
                result = getLogger(clazz).isDebugEnabled();

            case ERROR:
                result = getLogger(clazz).isErrorEnabled();

            case INFO:
                result = getLogger(clazz).isInfoEnabled();

            case TRACE:
                result = getLogger(clazz).isTraceEnabled();

            case WARN:
                result = getLogger(clazz).isWarnEnabled();

            default:
                result = false;
        }
        return result;
    }

    public void log(final LogLevel logLevel, final Class<?> clazz, final Throwable throwable,
            final String pattern, final Object... arguments) {

        switch (logLevel) {

            case DEBUG:
                debug(clazz, throwable, pattern, arguments);
                break;

            case ERROR:
                error(clazz, throwable, pattern, arguments);
                break;

            case INFO:
                info(clazz, throwable, pattern, arguments);
                break;

            case TRACE:
                trace(clazz, throwable, pattern, arguments);
                break;

            case WARN:
                warn(clazz, throwable, pattern, arguments);
                break;
            default:
        }
    }

    private void debug(final Class<?> clazz, final Throwable throwable, final String pattern,
            final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).debug(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).debug(format(pattern, arguments));
        }
    }

    private void error(final Class<?> clazz, final Throwable throwable, final String pattern,
            final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).error(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).error(format(pattern, arguments));
        }
    }

    private void info(final Class<?> clazz, final Throwable throwable, final String pattern,
            final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).info(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).info(format(pattern, arguments));
        }
    }

    private void trace(final Class<?> clazz, final Throwable throwable, final String pattern,
            final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).trace(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).trace(format(pattern, arguments));
        }
    }

    private void warn(final Class<?> clazz, final Throwable throwable, final String pattern,
            final Object... arguments) {

        if (throwable != null) {
            getLogger(clazz).warn(format(pattern, arguments), throwable);
        } else {
            getLogger(clazz).warn(format(pattern, arguments));
        }
    }

    private String format(final String pattern, final Object... arguments) {

        return MessageFormat.format(pattern, arguments);
    }

    private org.slf4j.Logger getLogger(final Class<?> clazz) {

        return LoggerFactory.getLogger(clazz);
    }
}