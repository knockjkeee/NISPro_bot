package ru.newsystems.nispro_bot.webservice.config.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class CacheStore<T> {
    private Cache<Long, T> cache;

    public CacheStore(int expiryDuration, TimeUnit timeUnit) {

        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiryDuration, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public T get(Long key) {
        return cache.getIfPresent(key);
    }

    public void add(Long key, T value) {
        if (key != null && value != null) {
            cache.put(key, value);
            System.out.println("Record stored in "
                    + value.getClass().getSimpleName()
                    + " Cache with Key = " + key);
        }
    }

    public void update(Long key, T value) {
        T t = get(key);
        if (t != null) {
            cache.invalidate(key);
        }
        add(key, value);
    }
}
