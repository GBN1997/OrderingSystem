package com.xxx.ordersystem.handler;

import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.exception.SellerAuthorizeException;
import com.xxx.ordersystem.utils.ResultVOUtil;
import com.xxx.ordersystem.viewobject.ResultVO;
import io.netty.util.internal.ResourcesUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/18 15:50
 * @Version: 1.0
 * @Description:
 */
@ControllerAdvice
public class SellExceptionHandler {
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public String handlerAuthorizeException(){
        return "/login/login";
    }

    @ExceptionHandler(value = OrderSystemException.class)
    @ResponseBody
    public ResultVO handlerOrderSystemException(OrderSystemException e){
        return ResultVOUtil.fail(e.getCode(), e.getMessage());
    }
}
