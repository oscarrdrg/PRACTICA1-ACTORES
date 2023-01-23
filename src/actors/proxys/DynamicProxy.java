package actors.proxys;

import interfaces.InsultService;
import message.Message;
import services.InsultServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    private final Object target;
    private final ActorProxy proxy;

    public static Object newInstance(Object target, ActorProxy proxy) {
        Class<?> targetClass = target.getClass();
        Class[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target, proxy));
    }

    public DynamicProxy(Object target, ActorProxy proxy) {
        this.target = target;
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object target, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}