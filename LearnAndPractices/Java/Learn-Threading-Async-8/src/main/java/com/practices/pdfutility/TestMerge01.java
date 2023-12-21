package com.practices.pdfutility;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class TestMerge01 {
    public static void mainMergeFile(String[] args) {
        try {
            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

            pdfMergerUtility.addSource("D:\\aaaaaaa\\TestPdfMerge\\GTK231222157213.pdf");
            // https://s3-ap-northeast-1.amazonaws.com/japantopken-staging1006/voucher/GTK231222157213.pdf
            pdfMergerUtility.addSource("D:\\aaaaaaa\\TestPdfMerge\\GTK231224157250.pdf");
            // https://s3-ap-northeast-1.amazonaws.com/japantopken-staging1006/voucher/GTK231224157250.pdf

            String name = "D:\\aaaaaaa\\TestPdfMerge\\" + "Merge2PDF.pdf";
            pdfMergerUtility.setDestinationFileName(name);

            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

            System.out.println("Merge Successfully: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
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

    // private static InputStream createInputStreamFromUrl(String url) throws IOException {
    //     return new URL(url).openStream();
    // }

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
        lst.add("https://s3-ap-northeast-1.amazonaws.com/japantopken-staging1006/voucher/GTK231222157213.pdf");
        lst.add("https://s3-ap-northeast-1.amazonaws.com/japantopken-staging1006/voucher/GTK231224157250.pdf");
        return lst;
    }

    // private static void mergePDFs(List<String> pdfUrls) {
    //     try {
    //         List<CompletableFuture<InputStream>> tasks = pdfUrls.stream()
    //                 .map(url -> CompletableFuture.supplyAsync(() -> loadPdfFromUrl(url)))
    //                 .collect(Collectors.toList());
    //
    //         CompletableFuture<Void> allOf = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));
    //
    //         allOf.thenAccept(v -> {
    //             try {
    //                 List<InputStream> inputStreams = tasks.stream()
    //                         .map(task -> {
    //                             try {
    //                                 return task.get();
    //                             } catch (InterruptedException | ExecutionException e) {
    //                                 e.printStackTrace();
    //                                 return null;
    //                             }
    //                         })
    //                         .filter(inputStream -> inputStream != null)
    //                         .collect(Collectors.toList());
    //
    //                 mergeAndSavePDF(inputStreams, "D:\\aaaaaaa\\TestPdfMerge\\Merge2PDF.pdf");
    //             } catch (IOException e) {
    //                 e.printStackTrace();
    //             }
    //         }).get();
    //     } catch (InterruptedException | ExecutionException e) {
    //         e.printStackTrace();
    //     }
    // }
    //
    // private static InputStream loadPdfFromUrl(String url) {
    //     try {
    //         return new URL(url).openStream();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }
    //
    // private static void mergeAndSavePDF(List<InputStream> inputStreams, String destinationPath) throws IOException {
    //     PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
    //     inputStreams.forEach(pdfMergerUtility::addSource);
    //
    //     pdfMergerUtility.setDestinationFileName(destinationPath);
    //     pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    //
    //     System.out.println("Merge Successfully: " + destinationPath);
    // }
    //
    // public static void main(String[] args) {
    //     List<String> pdfUrls = initListUrl();
    //     mergePDFs(pdfUrls);
    // }

}
