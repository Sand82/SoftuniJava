package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheableInvocationHandler implements InvocationHandler {

    private Object realObject;
    private Map<String, Object> cache = new HashMap<>();

    public CacheableInvocationHandler(Object realService) {
        this.realObject = realService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Cacheable cacheableAnnotation = realObject
                .getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Cacheable.class);

        if (cacheableAnnotation == null) {

            return method.invoke(realObject, args);
        } else {

            String cacheName = cacheableAnnotation.value();

            if (cache.get(cacheName) != null) {

                return  cache.get(cacheName);
            } else {

                Object result = method.invoke(realObject, args);
                cache.put(cacheName, result);

                return result;
            }
        }
    }
}
