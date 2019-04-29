package com.example.demo.config.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisCacheUtils {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //===================================和String缓存相关的===========================================

    /**
     * 放入字符串
     *
     * @param key   缓存的key    会覆盖原来的值
     * @param value 缓存的value---注意这里是String的类型
     */
    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取字符串,获取String ，Long，还有Double都是通过这个方式获取的
     *
     * @param key 缓存的key
     * @return 缓存的value---注意这里是String的类型
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置字符串缓存有效期，会覆盖原来的值
     *
     * @param key     缓存的key
     * @param value   缓存的value
     * @param timeOut 缓存有效期
     * @param unit    缓存有效期时间单位
     *                tips:当缓存时候到期之后，缓存数据库中会对应的把key和value全部删除
     */
    public void setStringLife(String key, String value, long timeOut, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeOut, unit);
    }

    /**
     * @param key   追加的key，
     * @param value 追加的内容
     */
    public void setStringAppend(String key, String value) {
        stringRedisTemplate.opsForValue().append(key, value);
    }

    /**
     * 截取,字符串
     *
     * @return
     */
    public String subString(String key, Long startIndex, Long endIndex) {
        return stringRedisTemplate.opsForValue().get(key, startIndex, endIndex);
    }


    /**
     * 设置新value并返回旧值。
     *
     * @param key
     * @param value
     * @return
     */
    public String getAndSetString(String key, String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 传入一堆key，返回一堆对应的value，批量查询用
     *
     * @param keys
     * @return
     */
    public List<String> getMoreString(Collection<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 批量插入数据用,会覆盖掉原来数据
     *
     * @param map
     */
    public void setMoreString(Map<String, String> map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }


    //另外还有不覆盖的API,如有用到，请自行查询文档，自行写方法。

//=======================================Long数据相关的====================================================

    /**
     * 储存Long型数字（储存类型是String，所以需要通过获取字符串的方式去获取这个值），如果缓存中存在，那么就使用原来的值加上累加这个value，如果不存在创建一个新的值是value
     *
     * @param key   储存的key
     * @param value 储存的value，
     *              功能类似 ：value +=value
     */
    public void setLongPlus(String key, Long value) {
        stringRedisTemplate.opsForValue().increment(key, value);
    }

    /**
     * 储存Long型数字（储存类型是String，所以需要通过获取字符串的方式去获取这个值），如果缓存中存在，那么就使用原来的值加上累加这1，如果不存在创建一个新的值是1
     *
     * @param key
     */
    public void setLongPlusOne(String key) {
        stringRedisTemplate.opsForValue().increment(key);
    }


    /**
     * 储存Long型数字（储存类型是String，所以需要通过获取字符串的方式去获取这个值），如果缓存中存在，那么就使用原来的值加上累减这个value，如果不存在创建一个新的值是负value
     *
     * @param key
     * @param value
     */
    public void setLongMinus(String key, Long value) {
        stringRedisTemplate.opsForValue().decrement(key, value);

    }

    /**
     * 储存Long型数字（储存类型是String，所以需要通过获取字符串的方式去获取这个值），如果缓存中存在，那么就使用原来的值加上累减这1，如果不存在创建一个新的值是负1
     *
     * @param key
     */
    public void setLongMinusOne(String key) {
        stringRedisTemplate.opsForValue().decrement(key);
    }

    /**
     * 通过key删除对应的缓存，这里用哪个对象都一样
     *
     * @param key
     * @return
     */
    public boolean DelectCache(String key) {
        return stringRedisTemplate.delete(key);
    }


    //=============================================Double类型参考Long类型的方法=====这里不再提供==============================================================================


    //==========================================================对于Map类型的操作==================================================================

    /**
     * 放入Map
     *
     * @param key   缓存的key    不会覆盖原来的值
     * @param value 缓存的value
     */
    public void setMapAppend(String key, Map value) {
        redisTemplate.opsForHash().putAll(key, value);

    }




    /**
     * 获取存的map
     *
     * @param key
     * @return
     */
    public Map getMap(String key) {

        return redisTemplate.opsForHash().entries(key);
    }

    //==========================================================对于List类型的操作==================================================================

    /**
     * 放入List
     *
     * @param key   缓存的key    会覆盖原来的值
     * @param value 缓存的value
     */
    public void setList(String key, List value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 获取存的List
     *
     * @param key
     * @return
     */
    public List getList(String key) {

        return redisTemplate.opsForList().range(key, 0L, -1L);
    }

    //==========================================================对于无序set类型的操作==================================================================


    /**
     * 放入set
     *
     * @param key   缓存的key    会覆盖原来的值
     * @param value 缓存的value
     */
    public void setSet(String key, Set value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取存的Set
     *
     * @param key
     * @return
     */
    public Set getSet(String key) {

        return redisTemplate.opsForSet().members(key);
    }


    //==========================================================对于有序set类型的操作==================================================================


    /**
     * 放入set
     *
     * @param key    缓存的key    会覆盖原来的值
     * @param values 缓存的value
     */
    public void setZSet(String key, Set values) {
        redisTemplate.opsForZSet().add(key, values);
    }

    /**
     * 获取存的Set
     *
     * @param key
     * @return
     */
    public Set getZSet(String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1);
    }

//==================================================================================================

    /**
     * 设置数据的声明周期
     *
     * @param key
     * @param timeOut
     * @param unit
     * @return
     */
    public boolean setLiveDate(String key, long timeOut, TimeUnit unit) {
        return redisTemplate.expire(key, timeOut, unit);
    }
}
