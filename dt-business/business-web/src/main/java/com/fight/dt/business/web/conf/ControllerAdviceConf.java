package com.fight.dt.business.web.conf;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tpx on 2017/7/13.
 */
@ControllerAdvice()
public class ControllerAdviceConf {

    @ExceptionHandler(Exception.class)
    @ResponseBody()
    Map<String, Object> exception(HttpServletRequest request, Exception ex) {
        HttpStatus status = getStatus(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.value());
        map.put("message", ex.getMessage());
        return map;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody()
    Map<String, Object> authenticationException(HttpServletRequest request, AuthenticationException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 401);
        map.put("message", ex.getMessage());
        return map;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody()
    Map<String, Object> authenticationException(HttpServletRequest request, AccessDeniedException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 403);
        map.put("message", ex.getMessage());
        return map;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            //@see org.springframework.security.web.WebAttributes
            //request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
