// package org.blog.demopublicapi.service;
//
// import com.google.common.base.Stopwatch;
// import com.google.common.util.concurrent.RateLimiter;
//
// import java.util.concurrent.ThreadLocalRandom;
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.atomic.AtomicInteger;
//
// public class TokenBucket {
//     private AtomicInteger serialNumber = new AtomicInteger(0);
//     private static final int LIMIT = 20;
//     private RateLimiter rateLimiter = RateLimiter.create(1);
//     private final int saleLimit;
//
//     public TokenBucket() {
//         this(LIMIT);
//     }
//
//     public TokenBucket(int limit) {
//         this.saleLimit = limit;
//     }
//
//     public int buy() {
//         Stopwatch started = Stopwatch.createStarted();
//         boolean success = rateLimiter.tryAcquire(1, TimeUnit.SECONDS);
//         if (success) {
//             if (serialNumber.get() >= saleLimit) {
//                 throw new IllegalStateException("Hết hàng, vui lòng thử lại sau.");
//             }
//             int phoneNo = serialNumber.getAndIncrement();
//             handleOrder();
//             System.out.println(Thread.currentThread() + " người dùng mua được điện thoại: " + phoneNo + ", ELT: " + started.stop());
//             return phoneNo;
//         } else {
//             started.stop();
//             throw new RuntimeException("Rất tiếc, xảy ra lỗi khi mua điện thoại");
//         }
//     }
//
//     public void handleOrder() {
//         try {
//             TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//     }
//
//     public static void main(String[] args) {
//         final TokenBucket tokenBucket = new TokenBucket();
//         for (int i = 0; i < 50; i++) {
//             new Thread(tokenBucket::buy).start();
//         }
//     }
//
// }
