package com.stone.shop.utils.cache;

import java.util.Map;

public interface ICache {

	/**
	 * 保存数据
	 * 
	 * @param key
	 * @param value
	 */
	public void setObject(String key, Object value);

	/**
	 * 修改数据
	 * 
	 * @param key
	 * @param value
	 */
	public void replaceObject(String key, Object value);

	/**
	 * 修改数据
	 * 
	 * @param key
	 * @param value
	 */
	public void replaceObject(String key, Object value, long time);

	/**
	 * 保存有有效期的数据
	 * 
	 * @param key
	 * @param value
	 * @param 保存时长
	 *            （毫秒）
	 */
	public void setObject(String key, Object value, long time);

	/**
	 * 获取单个key对应的值
	 * 
	 * @param key
	 * @return
	 */
	public Object getObject(String key);

	/**
	 * 获取单个key对应的值
	 * 
	 * @param key
	 * @param 本地缓存失效时间单位秒
	 * @return
	 */
	public Object getObject(String key, int localTTL);

	/**
	 * 获取多个keys对应的值
	 * 
	 * @param keys
	 * @return
	 */
	public Object[] getObjectsArr(String[] keys);

	/**
	 * 获取多个keys对应的key&value Entrys
	 * 
	 * @param keys
	 * @return
	 */
	public Map<String, Object> getObjectsMap(String[] keys);

	/**
	 * 清除缓存
	 * 
	 * @param key
	 */
	public void removeObject(String key);

	/**
	 * 获取大小
	 * 
	 * @param key
	 */
	public int getSize();

	/**
	 * 清空
	 * 
	 * @param key
	 */
	public void clear();

}
