package com.xxx.ordersystem.utils;

import com.xxx.ordersystem.viewobject.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(o);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO fail(Integer i, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(i);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
