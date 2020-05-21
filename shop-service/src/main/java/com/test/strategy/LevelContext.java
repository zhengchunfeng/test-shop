package com.test.strategy;

import java.util.Map;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className LevelContext
 * @description 通过属性反射获取相应策略
 * @date 2020/5/21 15:59
 */
public class LevelContext {

    public static LevelInterface getInstance(Integer levelId) {

        LevelInterface levelInterface = null;
        Map<Integer, String> allClazz = LevelEnum.getAllClazz();
        String clazz = allClazz.get(levelId);
        try {
            try {
                levelInterface = (LevelInterface) Class.forName(clazz).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return levelInterface;
    }
}
