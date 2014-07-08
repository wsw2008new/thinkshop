package com.stone.shop.admin.utils.cache;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import com.stone.shop.admin.utils.logger.FuwuLogger;
import com.stone.shop.base.common.utils.EncryptUtil;
import com.stone.shop.base.common.utils.StringUtils;

public class XmemCacheImp implements ICache {

	private MemcachedClient memcachedClient;

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	@Override
	public void setObject(String key, Object value) {
		try {
			key = EncryptUtil.md5Hex(key);
			memcachedClient.setWithNoReply(key, 0, value);
		} catch (Exception e) {
			FuwuLogger.error("存入缓存错误", e, this.getClass());
		}
	}

	@Override
	public void setObject(String key, Object value, long time) {
		try {
			key = EncryptUtil.md5Hex(key);
			long second = time / 1000;
			memcachedClient.setWithNoReply(key, new Long(second).intValue(), value);
		} catch (Exception e) {
			FuwuLogger.error("存入缓存错误", e, this.getClass());
		}

	}

	@Override
	public Object getObject(String key) {
		Object result = null;
		key = EncryptUtil.md5Hex(key);
		try {
			result = memcachedClient.get(key);
		} catch (TimeoutException e) {
			FuwuLogger.error("读取缓存超时", e, this.getClass());
		} catch (InterruptedException e) {
			FuwuLogger.error("读取缓存错误", e, this.getClass());
		} catch (MemcachedException e) {
			FuwuLogger.error("读取缓存错误", e, this.getClass());
		}
		return result;
	}

	@Override
	public Object getObject(String key, int timeout) {
		Object result = null;
		key =EncryptUtil.md5Hex(key);
		try {
			result = memcachedClient.get(key, timeout);
		} catch (TimeoutException e) {
			FuwuLogger.error("读取缓存超时", e, this.getClass());
		} catch (InterruptedException e) {
			FuwuLogger.error("读取缓存错误", e, this.getClass());
		} catch (MemcachedException e) {
			FuwuLogger.error("读取缓存错误", e, this.getClass());
		}
		return result;
	}

	@Override
	public Object[] getObjectsArr(String[] keys) {
		if (keys == null || keys.length <= 0) {
			return null;
		}
		for (int i = 0; i < keys.length; i++) {
			if (StringUtils.isNotEmpty(keys[i])) {
				keys[i] = EncryptUtil.md5Hex(keys[i]);
			}
		}
		Map<String, Object> result = null;
		try {
			result = memcachedClient.get(Arrays.asList(keys));
		} catch (TimeoutException e) {
			FuwuLogger.error("读取缓存超时", e, this.getClass());
		} catch (InterruptedException e) {
			FuwuLogger.error("读取缓存错误", e, this.getClass());
		} catch (MemcachedException e) {
			FuwuLogger.error("读取缓存错误", e, this.getClass());
		}
		if (result != null) {
			return result.values().toArray();
		} else {
			return null;
		}

	}

	@Override
	public Map<String, Object> getObjectsMap(String[] keys) {
		if (keys == null || keys.length <= 0) {
			return null;
		}
		for (int i = 0; i < keys.length; i++) {
			if (StringUtils.isNotEmpty(keys[i])) {
				keys[i] = EncryptUtil.md5Hex(keys[i]);
			}
		}
		Map<String, Object> result = null;
		try {
			result = memcachedClient.get(Arrays.asList(keys));
		} catch (TimeoutException e) {
			FuwuLogger.error("读取缓存超时", e, this.getClass());
		} catch (InterruptedException e) {
			FuwuLogger.error("读取缓存错误", e, this.getClass());
		} catch (MemcachedException e) {
			FuwuLogger.error("读取缓存错误", e, this.getClass());
		}
		return result;
	}

	@Override
	public void removeObject(String key) {
		try {
			memcachedClient.deleteWithNoReply(key);
		} catch (InterruptedException e) {
			FuwuLogger.error("删除缓存错误", e, this.getClass());
		} catch (MemcachedException e) {
			FuwuLogger.error("删除缓存错误", e, this.getClass());
		}
	}


	@Override
	public void replaceObject(String key, Object value) {
		try {
			key = EncryptUtil.md5Hex(key);
			memcachedClient.replaceWithNoReply(key, 0, value);
		} catch (Exception e) {
			FuwuLogger.error("更新缓存错误", e, this.getClass());
		}
		
	}

	@Override
	public void replaceObject(String key, Object value, long time) {
		try {
			long second = time / 1000;
			key = EncryptUtil.md5Hex(key);
			memcachedClient.replaceWithNoReply(key, new Long(second).intValue(), value);
		} catch (Exception e) {
			FuwuLogger.error("更新缓存错误", e, this.getClass());
		}
		
	}
	
	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public void clear() {
		try {
			memcachedClient.flushAllWithNoReply();
		} catch (InterruptedException e) {
			FuwuLogger.error("刷新缓存错误", e, this.getClass());
		} catch (MemcachedException e) {
			FuwuLogger.error("刷新缓存错误", e, this.getClass());
		}
		
	}

}
