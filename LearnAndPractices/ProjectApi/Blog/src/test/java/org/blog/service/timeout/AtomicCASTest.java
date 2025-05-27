package org.blog.service.timeout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class AtomicCASTest {

    /**
     * <pre>
     * 2. Biến nguyên tử: Từ JDK 1.5, gói java.util.concurrent.atomic đã cung cấp các biến nguyên tử phổ biến:
     * 1. Từ khóa volatile đảm bảo tính khả kiến của bộ nhớ
     * 2. Thuật toán CAS (Compare-And-Swap - So sánh và hoán đổi) đảm bảo tính nguyên tử của dữ liệu
     *    Thuật toán CAS được phần cứng hỗ trợ cho các thao tác đồng thời trên dữ liệu chia sẻ.
     *    CAS bao gồm ba toán hạng:
     *      Giá trị bộ nhớ V
     *      Giá trị dự tính A (giá trị được so sánh và thay thế, đọc lại giá trị của đối tượng cùng một thời điểm)
     *      Giá trị cập nhật B
     *    Khi và chỉ khi V == A, thì V sẽ được cập nhật thành B. Nếu không, không có hành động nào được thực hiện.
     * </pre>
     */
    @Test
    public void testCaseV1() throws InterruptedException {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
        TimeUnit.SECONDS.sleep(25);
    }

    class AtomicDemo implements Runnable {

        private AtomicInteger serialNumber = new AtomicInteger(0);

        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getSerialNumber());
        }

        public int getSerialNumber() {
            return serialNumber.getAndIncrement();
        }
    }
}
