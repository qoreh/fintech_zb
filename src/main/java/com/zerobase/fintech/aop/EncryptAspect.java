package com.zerobase.fintech.aop;

import com.zerobase.fintech.domain.UserInfo;
import com.zerobase.fintech.encrypt.EncryptComponent;
import com.zerobase.fintech.exception.CustomException;
import com.zerobase.fintech.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import static com.zerobase.fintech.type.ErrorCode.ALREADY_REGISTERED_USER;


/*
 AOP 부분이 적용이 안 되는 문제로 인하여 암호화, 복호화 부분은 서비스에 구현하였습니다! ㅜㅜ
 어떤 이유로 적용이 안 되는지 알 수 있을까요?
 */
@Aspect
@Component
@RequiredArgsConstructor
public class EncryptAspect {
    private final EncryptComponent encryptComponent;
    private final UserInfoRepository userInfoRepository;

    @Pointcut("execution(* com.zerobase.fintech.service.UserInfoServiceImpl.save*(..))")
    private void savePointcut() {
    }

    @Pointcut("execution(* com.zerobase.fintech.service.UserInfoServiceImpl.find*(..))")
    private void findPointcut() {
    }

    @Around("savePointcut()")
    public Object saveAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof UserInfo) {
                UserInfo userInfo = (UserInfo) arg;

                Field[] fields = userInfo.getClass().getDeclaredFields();
                for (Field field : fields) {
                    Object fieldVal = field.get(userInfo);

                    if (field.isAnnotationPresent(Encrypt.class) &&
                            fieldVal instanceof String) {
                        String encrypted = encryptComponent.encryptString((String) fieldVal);
                        if (userInfoRepository.findByUserRegistrationNumber(encrypted).isPresent()) {
                            throw new CustomException(ALREADY_REGISTERED_USER);
                        }
                        field.set(userInfo, encrypted);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
    @Around("findPointcut()")
    public Object findAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof UserInfo) {
                UserInfo userInfo = (UserInfo) arg;

                Field[] fields = userInfo.getClass().getDeclaredFields();
                for (Field field : fields) {
                    Object fieldVal = field.get(userInfo);

                    if (field.isAnnotationPresent(Encrypt.class) &&
                            fieldVal instanceof String) {
                        String decrypted = encryptComponent.decryptString((String) fieldVal);
                        field.set(userInfo, decrypted);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }



}
