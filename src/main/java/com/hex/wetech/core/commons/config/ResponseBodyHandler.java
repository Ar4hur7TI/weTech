package com.hex.wetech.core.commons.config;

import com.hex.wetech.core.models.R;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * ResponseBodyHandler
 *
 * @author Guofeng Lin
 * @since 2023/11/17
 */
//@Component
public class ResponseBodyHandler implements HandlerMethodReturnValueHandler {
    protected final HandlerMethodReturnValueHandler handlerMethodReturnValueHandler;

    public ResponseBodyHandler(HandlerMethodReturnValueHandler handlerMethodReturnValueHandler) {
        this.handlerMethodReturnValueHandler = handlerMethodReturnValueHandler;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
                returnType.hasMethodAnnotation(ResponseBody.class)
        );
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        if (returnValue instanceof R) {
            R res = ((R) returnValue);
            HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
            if (response != null) response.setStatus(res.getCode());
            handlerMethodReturnValueHandler.handleReturnValue(res, returnType, mavContainer, webRequest);
        }
        else {
            handlerMethodReturnValueHandler.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        }
    }

}
