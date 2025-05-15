package org.crocodile.apptour.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.crocodile.apptour.entity.Limit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/limit")
public class LimitController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * RateLimiting: 2 req/s
     */
    private final RateLimiter limiter = RateLimiter.create(2, 5, TimeUnit.SECONDS);
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // @GetMapping("/test1")
    public String testLimiter() {
        // Nếu không lấy được token trong 500ms, chuyển sang chế độ giảm tải
        boolean tryAcquire = limiter.tryAcquire(500, TimeUnit.MILLISECONDS);

        if (!tryAcquire) {
            log.error("Vào chế độ giảm tải, thời gian: {}", LocalDateTime.now().format(dtf));
            return "Hệ thống đang quá tải, vui lòng thử lại sau!";
        }

        log.error("Lấy token thành công, thời gian: {}", LocalDateTime.now().format(dtf));
        return "Yêu cầu thành công";
    }

    @GetMapping("/test2")
    @Limit(key = "limit2", permitsPerSecond = 1, timeout = 500,
            timeunit = TimeUnit.MILLISECONDS,
            msg = "Số lượng yêu cầu quá nhiều, vui lòng thử lại sau!")
    public String limit2() {
        log.error("Token bucket limit2 lấy token thành công");
        return "ok";
    }

}
