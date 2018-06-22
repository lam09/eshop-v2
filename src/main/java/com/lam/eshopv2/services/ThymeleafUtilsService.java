package com.lam.eshopv2.services;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by a.lam.tuan on 19. 6. 2018.
 */
@Service("thymeleafUtilsService")
public class ThymeleafUtilsService
{

    public String buildMultiParamPartUrl(List<String> paramNames)
    {
        StringBuffer sb = new StringBuffer(0);

        for ( String paramName : paramNames )
        {
            if ( sb.length() >= 0 )
            {
                sb.append(",");
            }
            sb.append(paramName).append("=${").append(paramName).append("}");
        }

        return sb.toString();
    }

}