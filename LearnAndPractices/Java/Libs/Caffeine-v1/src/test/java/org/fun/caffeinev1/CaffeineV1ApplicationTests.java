package org.fun.caffeinev1;

// import com.github.benmanes.caffeine.cache.AsyncCache;
// import com.github.benmanes.caffeine.cache.Cache;
// import com.github.benmanes.caffeine.cache.Caffeine;
// import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class CaffeineV1ApplicationTests {
	// @Autowired
	// @Resource
	// private Cache<String, String> cacheCaffeineManager;

	@Test
	public void testExample() {
		System.out.println(1 == 1);
	}

	/*@Test
	public void testLoadAutomationCaffeine() throws InterruptedException {
		Cache<Object, String> cache = Caffeine.newBuilder()
				.expireAfterWrite(10, TimeUnit.SECONDS)
				.maximumSize(5)
				.build();
		// String apple = cache.getIfPresent("apple");
		// System.out.println(apple);
		//
		// cache.put("apple", " store");
		// System.out.println("value apple: " + cache.getIfPresent("apple"));
		//
		// cache.invalidate("apple");
		// System.out.println("Removed: " + cache.getIfPresent("apple"));
		//
		// String s = cache.get("apple", v -> "STORE");
		// System.out.println(s);
		log.warn("=========== START ===========");
		cache.put("apple", "APPLE");
		cache.put("orange", "ORANGE");
		cache.put("banana", "BANANA");
		cache.put("lemon", "LEMON");

		// Thread.sleep(10_000);
		log.warn("=========== END ===========");
		List<String> fruits = List.of("apple", "lemon", "soda");
		Map<Object, String> allPresent = cache.getAllPresent(fruits);
		log.warn("{}", allPresent);

		ConcurrentMap<Object, String> map = cache.asMap();
		log.warn("all valid {}", map);
		log.error("START cache {}", cache);

		log.info("===================================");
		Thread t1 = new Thread(() -> {
			log.info("{} : {}", Thread.currentThread().getName(), cache.getIfPresent("apple"));
		}, "Thread1");
		Thread t2 = new Thread(() -> {
            try {
				log.error("Cache Dead {}", cache);
                Thread.sleep(12_000);
            } catch (InterruptedException e) {
            }
            log.info("{} : {}", Thread.currentThread().getName(), cache.getIfPresent("lemon"));
		}, "Thread2");
		t1.start();
		t2.start();

		log.error("END cache {}", cache);
		Thread.sleep(13_000);
		// AsyncCache<String, String> cache2 = Caffeine.newBuilder()
		// 		.expireAfterWrite(10, TimeUnit.MINUTES)
		// 		.maximumSize(10_000)
		// 		.buildAsync();
		// cache2.synchronous();
	}*/

	@Test
	public void testLoadAutomationCaffeineBean() throws InterruptedException {
		// cacheCaffeineManager.put("apple", "APPLE");
		// cacheCaffeineManager.put("orange", "ORANGE");
		// cacheCaffeineManager.put("banana", "BANANA");
		// cacheCaffeineManager.put("lemon", "LEMON");
		//
		// log.info("apple : {}", cacheCaffeineManager.getIfPresent("apple"));
		// log.info("Thread sleep 7s");
		// Thread.sleep(17_000);
		//
		// log.info("sau 7s apple : {}", cacheCaffeineManager.getIfPresent("apple"));
		// log.info("cache dead manage {}", cacheCaffeineManager);
	}

	@Test
	public void testLoadingCacheGuava() throws InterruptedException {
		LoadingCache<String, String> cache = CacheBuilder.newBuilder()
				.expireAfterWrite(20, TimeUnit.SECONDS)
				.maximumSize(5)
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						return null;
					}
				});
		cache.put("ok1", "OKAY1");
		cache.put("ok2", "OKAY2");
		cache.put("ok3", "OKAY3");

		// System.out.println(cache.getIfPresent("ok1"));
		// Thread.sleep(20_000);
		// System.out.println(cache.getIfPresent("ok1"));

		Set<String> key = cache.asMap().keySet();
		System.out.println("Key " + key);

		cache.asMap().clear();
		System.out.println(cache.asMap());
	}

	@Test
	public void testCollectionSet() {
		Set<String> fruits = new HashSet<>();
		fruits.add("y");
		fruits.add("v");
		fruits.add("a");
		System.out.println(fruits);

		List<String> names = new ArrayList<>();
	}

}
