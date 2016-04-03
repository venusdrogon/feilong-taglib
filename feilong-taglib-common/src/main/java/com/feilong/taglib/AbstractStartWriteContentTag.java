/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.taglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.servlet.http.RequestUtil;
import com.feilong.servlet.http.entity.RequestLogSwitch;
import com.feilong.tools.jsonlib.JsonUtil;
import com.feilong.tools.slf4j.Slf4jUtil;

/**
 * start输出.
 *
 * @author feilong
 * @version 1.3.0 2015年7月23日 下午9:03:54
 * @since 1.3.0
 */
public abstract class AbstractStartWriteContentTag extends AbstractWriteContentTag{

    /** The Constant serialVersionUID. */
    private static final long   serialVersionUID = 20290570902030061L;

    /** The Constant log. */
    private static final Logger LOGGER           = LoggerFactory.getLogger(AbstractStartWriteContentTag.class);

    /**
     * 标签开始.
     *
     * @return the int
     */
    @Override
    public int doStartTag(){
        try{
            execute();
        }catch (Exception e){
            //XXX 默认处理异常,让页面正常执行,但是以错误log显示
            String formatMessage = Slf4jUtil.formatMessage(
                            "request info:{},tag is:[{}]",
                            JsonUtil.format(
                                            RequestUtil.getRequestInfoMapForLog(
                                                            getHttpServletRequest(),
                                                            RequestLogSwitch.NORMAL_WITH_IDENTITY_INCLUDE_FORWARD)),
                            getClass().getSimpleName());
            LOGGER.error(formatMessage, e);
        }
        return SKIP_BODY; // 跳过开始和结束标签之间的代码。
    }
}