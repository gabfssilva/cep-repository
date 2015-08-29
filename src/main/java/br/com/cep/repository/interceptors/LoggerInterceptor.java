package br.com.cep.repository.interceptors;

import br.com.cep.repository.exceptions.CepRepositoryException;
import br.com.cep.repository.util.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Interceptor
@Log
public class LoggerInterceptor {

    @AroundInvoke
    public Object log(InvocationContext invocationContext) throws Exception {
        Map<String, Object> mapLog = new HashMap<>();
        final Method method = invocationContext.getMethod();
        Logger logger = LoggerFactory.getLogger(method.getDeclaringClass());

        mapLog.put("method", method.getName());
        mapLog.put("class", method.getDeclaringClass());

        Map<String, String> parameters = new HashMap<>();

        for(int i = 0; i< invocationContext.getParameters().length; i++){
            final String paramName = method.getParameters()[i].getName();
            final Object paramValue = invocationContext.getParameters()[i];
            parameters.put(paramName, paramValue == null ? null : paramValue.toString());
        }

        mapLog.put("parameters", parameters.toString());
        mapLog.put("when", "before");

        logger.info(mapLog.toString());

        Timer timer = new Timer();
        timer.start();

        try {
            final Object result = invocationContext.proceed();
            timer.stop();

            mapLog.put("result", result == null ? null : result.toString());
            mapLog.put("when", "after");
            mapLog.put("ms", timer.totalInMillis());

            logger.info(mapLog.toString());

            return result;
        } catch (CepRepositoryException e) {
            timer.stop();

            mapLog.put("exception", e.getClass().getSimpleName());
            mapLog.put("exceptionMessage", e.getMessage());
            mapLog.put("when", "onException");
            mapLog.put("ms", timer.totalInMillis());

            logger.warn(mapLog.toString());

            throw e;
        } catch (Exception e) {
            timer.stop();

            mapLog.put("exception", e.getClass().getSimpleName());
            mapLog.put("exceptionMessage", e.getMessage());
            mapLog.put("when", "onException");
            mapLog.put("ms", timer.totalInMillis());

            logger.error(mapLog.toString());

            throw e;
        }
    }
}
