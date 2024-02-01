package com.example.way02.controller;

import com.example.way02.advice.ResponseResultBody;
import com.example.way02.exception.ResultException;
import com.example.way02.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/helloResult")
@ResponseResultBody
@Slf4j
public class HelloResultController {
    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy cinema");
        INFO.put("age", "70");
    }

    @GetMapping("hello")
    public HashMap<String, Object> hello() {
        return INFO;
    }

    @GetMapping("result")
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }

    @GetMapping("helloError")
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new ResultException();
    }

    @GetMapping("resultStudent")
    public Result<?> helloStudent() {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student(1, "A");
        Student student2 = new Student(2, "B");
        Student student3 = new Student(3, "C");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        return Result.success(list);
    }

    // private boolean isPerformTask7 = false;
    // private boolean isPerformTask15 = false;
    //
    // @Scheduled(fixedRate = 3000)
    // public void performTask7() {
    //     System.out.println("Scheduled task executed at 3s");
    //     isPerformTask7 = true;
    //     this.checkBothDone();
    //     isPerformTask7 = false;
    // }
    //
    // private void checkBothDone() {
    //     if (isPerformTask7 && isPerformTask15) {
    //         System.out.println("Done both function");
    //     } else {
    //         System.out.println("Not Done both function");
    //     }
    // }

    // @Scheduled(fixedRate = 10000)
    // public void performTask10() {
    //     System.out.println("Scheduled task executed at 10s");
    //     isPerformTask15 = true;
    // }

    private CountDownLatch latch = new CountDownLatch(2);

    // @Scheduled(fixedRate = 10000)
    public void performTask7() throws InterruptedException {
        log.info("Scheduled task executed at 10s");
        // TimeUnit.SECONDS.sleep(7);
        latch.countDown();
        checkBothDone();
    }

    // @Scheduled(fixedRate = 60000)
    public void performTask10() {
        log.info("Scheduled task executed at 1p");
        latch.countDown();
        // checkBothDone();
    }

    private void checkBothDone() {
        if (latch.getCount() == 0) {
            log.info(".Done");
            // latch = new CountDownLatch(2);
        } else {
            log.info(".Not done");
        }
        latch = new CountDownLatch(2);
    }


    @GetMapping("merge1")
    public void mergeV() {
        List<String> pdfUrls = initListUrl();
        try {
            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
            List<InputStream> inputStreamsFromUrls = createInputStreamsFromUrls(pdfUrls);
            pdfMergerUtility.addSources(inputStreamsFromUrls);
            String name = "D:\\aaaaaaa\\TestPdfMerge\\" + "Merge2PDF.pdf";
            pdfMergerUtility.setDestinationFileName(name);

            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

            for (InputStream i : inputStreamsFromUrls) {
                i.close();
            }
            inputStreamsFromUrls.clear();

            System.out.println("Merge Successfully: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("merge2")
    public void mergeV2() throws IOException {
        List<String> pdfUrls = initListUrl();
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        WeakReference<Object> weakReference = new WeakReference<>(pdfMergerUtility);
        InputStream inputStream = null;
        WeakReference<Object> weakReference2 = new WeakReference<>(inputStream);
        try {

            String name = "D:\\aaaaaaa\\TestPdfMerge\\" + "Merge2PDF.pdf";
            for (String pdfUrl : pdfUrls) {
                // try (InputStream inputStream = createInputStreamFromUrl(pdfUrl);) {
                //     pdfMergerUtility.addSource(inputStream);
                // }

                inputStream = createInputStreamFromUrl(pdfUrl);
                pdfMergerUtility.addSource(inputStream);
            }
            pdfMergerUtility.setDestinationFileName(name);
            // pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

            System.out.println("Merge Successfully: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pdfMergerUtility = null;
            inputStream = null;
        }
    }

    @GetMapping("merge5")
    public void mergeV5() {
        List<String> pdfUrls = initListUrl().stream().limit(2).collect(Collectors.toList());
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        InputStream inputStream = null;
        try {

            String name = "D:\\aaaaaaa\\TestPdfMerge\\" + "Merge2PDF.pdf";
            for (String pdfUrl : pdfUrls) {
                inputStream = createInputStreamFromUrl(pdfUrl);
                pdfMergerUtility.addSource(inputStream);
            }
            pdfMergerUtility.setDestinationFileName(name);
            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

            System.out.println("Merge Successfully: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pdfMergerUtility = null;
            inputStream = null;
        }
    }

    @GetMapping("merge3")
    public void mergeV3() {
        List<String> pdfUrls = initListUrl();
        String destinationPath = "D:\\aaaaaaa\\TestPdfMerge\\Merge2PDF.pdf";

        try {
            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

            for (String pdfUrl : pdfUrls) {
                try {
                    pdfMergerUtility.addSource(pdfUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            pdfMergerUtility.setDestinationFileName(destinationPath);
            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

            System.out.println("Merge Successfully: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("merge4")
    public void mergeV4() {
        try {
            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

            pdfMergerUtility.addSource("Path");
            pdfMergerUtility.addSource("Path");

            String name = "D:\\aaaaaaa\\TestPdfMerge\\" + "Merge2PDF.pdf";
            pdfMergerUtility.setDestinationFileName(name);

            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

            System.out.println("Merge Successfully: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<InputStream> createInputStreamsFromUrls(List<String> urls) {
        List<InputStream> inputStreams = new ArrayList<>();
        for (String url : urls) {
            try {
                inputStreams.add(new URL(url).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inputStreams;
    }

    public static List<String> initListUrl() {
        List<String> lst = new ArrayList<>();

        return lst;
    }

    private static InputStream createInputStreamFromUrl(String url) throws IOException {
        return new URL(url).openStream();
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>(1_000_000);
        for (int i = 0; i < 1_000_000; i++) {
            Student student = new Student(++i, "Student"+i);
            students.add(student);
        }
        return students;
    }

    public static void main(String[] args) {
        List<Integer> listChan = new ArrayList<Integer>();
        List<Integer> listLe = new ArrayList<Integer>();
        List<Integer> listChiaHetCho5 = new ArrayList<Integer>();
        for (int i = 1; i < 11; i++) {

            if (i%2 == 0) {
                listChan.add(i);
                if (i%10 == 0) {
                    listChiaHetCho5.add(i);
                }
            } else {
                if (i%5 == 0) {
                    listChiaHetCho5.add(i);
                }
                listLe.add(i);
            }
        }

        System.out.println(listChan);
        System.out.println(listLe);
        System.out.println(listChiaHetCho5);
    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Student {
    private int id;
    private String name;
}