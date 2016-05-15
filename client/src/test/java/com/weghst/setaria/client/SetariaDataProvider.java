package com.weghst.setaria.client;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;
import org.testng.annotations.DataProvider;

import com.weghst.setaria.client.util.ObjectMapperUtils;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SetariaDataProvider {

    @DataProvider
    public static Object[][] setariaBean() {
        return new Object[][]{{getSetariaBean()}};
    }

    /**
     * @return
     */
    public static SetariaBean getSetariaBean() {
        try {
            File file = ResourceUtils.getFile("classpath:setaria.json");
            return ObjectMapperUtils.readValue(file, SetariaBean.class);
        } catch (FileNotFoundException e) {
            throw new SetariaConfigException(e);
        }
    }
}
