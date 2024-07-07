package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Aspect
@Component
public class NotEmptyAspect {
    @Before("@annotation(NotEmpty)")
    public void checkArguments(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        for (Object arg : arguments) {
            Optional.of(arg);
            if (arg instanceof String && ((String) arg).isEmpty()) {
                throw new IllegalArgumentException("Строка не может быть пустой");
            } else if (arg instanceof Collection && ((Collection<?>) arg).isEmpty()) {
                new IllegalArgumentException("Коллекция не может быть пустой");
            }
        }
    }

}
