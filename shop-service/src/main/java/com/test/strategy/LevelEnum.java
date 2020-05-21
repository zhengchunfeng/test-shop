package com.test.strategy;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className LevelEnum
 * @description
 * @date 2020/5/21 16:01
 */
@Getter
public enum LevelEnum {

    /**
     * @description 1级粉丝
     * @author zhengchunfeng
     * @date 2020/5/14 10:46
     * @param null 1
     * @return
     **/
    LEVEL_ONE(1, "com.test.strategy.LevelOneStrategy"),

    /**
     * @description 2级粉丝
     * @author zhengchunfeng
     * @date 2020/5/14 10:46
     * @param null 1
     * @return
     **/
    LEVEL_TWO(2, "com.test.strategy.LevelTwoStrategy");


    private int level;

    private String clazz;

    LevelEnum(int level, String clazz) {
        this.level = level;
        this.clazz = clazz;
    }

    public static Map<Integer, String> getAllClazz() {
        Map<Integer, String> map = new HashMap<>();
        for (LevelEnum levelEnum : LevelEnum.values()) {
            map.put(levelEnum.getLevel(), levelEnum.getClazz());
        }
        return map;
    }

}
