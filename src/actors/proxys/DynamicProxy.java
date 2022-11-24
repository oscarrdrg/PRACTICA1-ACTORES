package actors.proxys;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    private final Object target;

    public static Object newInstance(Object target) {

        Class<?> targetClass = target.getClass();
        Class[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target));
    }

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invocationResult;
        invocationResult = method.invoke(this.target, args);
        System.out.println(method.getName());

        return invocationResult;
    }
}
