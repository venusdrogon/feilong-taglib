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
package com.feilong.taglib.display.pager;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.coreextension.awt.DesktopUtil;
import com.feilong.io.IOWriteUtil;
import com.feilong.taglib.display.pager.command.PagerParams;
import com.feilong.taglib.display.pager.command.PagerType;

import static com.feilong.core.CharsetType.UTF8;
import static com.feilong.core.date.DateExtensionUtil.formatDuration;

public class PagerBuilderTest extends BasePagerTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagerBuilderTest.class);

    /**
     * Test get pager content.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testGetPagerContent1() throws IOException{
        Date beginDate = new Date();
        int j = 1;// 80000
        j = 100;
        //		j = 500;
        //		j = 20000;
        //		j = 40000;
        //		j = 80000;
        //j = 80000;
        for (int i = 0; i < j; ++i){
            // LOGGER.debug("===================================================");
            testGetPagerContent();
            // LOGGER.debug("the param content:\n\n{}", content);
            // LOGGER.debug("{} ", i);
        }
        LOGGER.debug("{}次\t{}", j, formatDuration(beginDate));
    }

    /**
     * Test get pager content.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testGetPagerContent() throws IOException{
        PagerParams pagerParams = getPagerParams();

        pagerParams.setPagerType(PagerType.NO_REDIRECT);

        String content = PagerBuilder.buildContent(pagerParams);

        //LOGGER.debug("the param content:\n\n{}", content);

        if (false){
            String filePath = "F://pagerTest.html";
            IOWriteUtil.writeStringToFile(filePath, content, UTF8);
            DesktopUtil.browse(filePath);
        }
    }
}