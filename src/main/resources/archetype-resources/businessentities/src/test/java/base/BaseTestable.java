#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * 
 * Abstract base testcase class to provide abstraction to the end testcases.
 * 
 * */
@ContextConfiguration(locations={"/META-INF/spring/applicationContext-persistence.xml"})
public abstract class BaseTestable extends AbstractTestNGSpringContextTests {}
