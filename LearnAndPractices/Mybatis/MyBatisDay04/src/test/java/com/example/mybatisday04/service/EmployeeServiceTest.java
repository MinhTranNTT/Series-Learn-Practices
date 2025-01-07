package com.example.mybatisday04.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisday04.pojo.Employee;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@MapperScan("com.example.mybatisday04.mapper")
@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void getEmployeeByName() {
        List<Employee> employees = employeeService.listAllByLastName("tom");
        employees.forEach(System.out::println);
    }

    @Test
    public void saveEmployee1() {
        Employee employee = new Employee();
        employee.setLastName("Trump");
        employee.setEmail("trump@qq.com");
        employee.setAge(21);
        employeeService.save(employee);
    }

    @Test
    public void saveEmployee2() {
        Employee employee = Employee.builder()
                .lastName("Stephen")
                .email("stephen@qq.com")
                .age(22)
                .gmtCreate(LocalDateTime.now())
                .gmtModified(LocalDateTime.now()).build();
        employeeService.save(employee);
    }

    @Test
    public void testSaveBulkEmployee() {
        Employee employee = null;
        for (int i = 0; i < 10; i++) {
            employee = Employee.builder()
                    .age(i+2)
                    .lastName("Vayne "+i)
                    .email("vayne" + i + "@gmail.com")
                    .build();
            employeeService.save(employee);
        }
    }

    @Test
    public void updateByEmployeeId() {
        Employee employee = Employee.builder()
                .id(1L)
                .lastName("jacky")
                .gmtModified(LocalDateTime.now()).build();
        employeeService.updateById(employee);
    }

    @Test
    public void testInsertEmployeeWithMetaObject() {
        Employee employee = Employee.builder()
                .lastName("Jax1")
                .email("jax1@gmail.com")
                .age(22).build();
        employeeService.save(employee);
    }

    @Test
    public void testSoftDeleteByLogic() {
        employeeService.removeById(7);
    }

    @Test
    public void testLoadInterceptor() {
        Page<Employee> page = new Page<>(1,5);
        // Page<Employee> page = new Page<>(2,5);
        // Page<Employee> page = new Page<>(3,5);
        // Page<Employee> page = new Page<>(4,5);
        employeeService.page(page, null);
        List<Employee> employeeList = page.getRecords();
        employeeList.forEach(System.out::println);
        System.out.println("getTotal() :" + page.getTotal());
        System.out.println("getCurrent() :" + page.getCurrent());
        System.out.println("getPages() :" + page.getPages());
        System.out.println("getSize() :" + page.getSize());    // number date in a page
        System.out.println("hasPrevious() :" + page.hasPrevious());
        System.out.println("hasNext() :" + page.hasNext());
    }

    @Test
    public void testLoadInterceptor2() {
        Page<Employee> page = new Page<>(2,5);
        employeeService.page(page, null);
        List<Employee> employeeList = page.getRecords();
        employeeList.forEach(System.out::println);
        System.out.println("getTotal() :" + page.getTotal());
        System.out.println("getCurrent() :" + page.getCurrent());
        System.out.println("getPages() :" + page.getPages());
        System.out.println("getSize() :" + page.getSize());    // number date in a page
        System.out.println("hasPrevious() :" + page.hasPrevious());
        System.out.println("hasNext() :" + page.hasNext());
    }

    @Test
    public void testLoadInterceptor3() {
        Page<Employee> page = new Page<>(1, 5);
        employeeService.page(page, new QueryWrapper<Employee>()
                .between("age", 2, 11)
                .eq("gender", 1));
        List<Employee> employeeList = page.getRecords();
        employeeList.forEach(System.out::println);
    }

    @Test
    public void testGetIds() {
        List<Integer> lstId = Arrays.asList(1,2);
        List<Employee> employees = employeeService.listAllByLstId(lstId);
        System.out.println(employees);
    }
    @Test
    public void testGetList() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Integer> lstData = getLstData(10);
        List<List<Integer>> partitions = Lists.partition(lstData, 3);

        List<CompletableFuture<List<Employee>>> futures =partitions.stream()
                .map(partition -> CompletableFuture.supplyAsync(() -> employeeService.listAllByLstId(partition), executor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        CompletableFuture<List<Employee>> collect = allOf.thenApply(future -> futures.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Employee::getId))
                .collect(Collectors.toList()));

        List<Employee> employeeList = collect.join();
        executor.shutdownNow();

        List<Integer> list2 = Arrays.asList(2);
        List<Integer> list4 = Arrays.asList(4);
        List<Integer> list6 = Arrays.asList(6);

        long start = System.currentTimeMillis();
        handleList2(list2, employeeList);
        handleList4(list4, employeeList);
        handleList6(list6, employeeList);
        long end = System.currentTimeMillis();
        double costTime = (end - start) / 1000.0;
        System.out.printf("cost %.2f second \n",costTime);
    }

    @Test
    public void testGetListV2() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Integer> lstData = getLstData(10);
        List<List<Integer>> partitions = Lists.partition(lstData, 3);

        List<CompletableFuture<List<Employee>>> futures =partitions.stream()
                .map(partition -> CompletableFuture.supplyAsync(() -> employeeService.listAllByLstId(partition), executor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        CompletableFuture<List<Employee>> collect = allOf.thenApply(future -> futures.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Employee::getId))
                .collect(Collectors.toList()));

        List<Employee> employeeList = collect.join();
        executor.shutdownNow();

        List<Integer> list2 = Arrays.asList(2);
        List<Integer> list4 = Arrays.asList(4);
        List<Integer> list6 = Arrays.asList(6);
        System.out.println(employeeList);

        long start = System.currentTimeMillis();
        CompletableFuture<Void> futureList2 = CompletableFuture.runAsync(() -> handleList2(list2, employeeList));
        CompletableFuture<Void> futureList4 = CompletableFuture.runAsync(() -> handleList4(list4, employeeList));
        CompletableFuture<Void> futureList6 = CompletableFuture.runAsync(() -> handleList6(list6, employeeList));
        CompletableFuture<Void> finalFuture = CompletableFuture.allOf(futureList2, futureList4, futureList6);
        finalFuture.join();
        long end = System.currentTimeMillis();
        double costTime = (end - start) / 1000.0;
        System.out.printf("cost %.2f second \n",costTime);
        System.out.println(employeeList);
    }

    private void handleList2(List<Integer> list2, List<Employee> employeeList) {
        list2.forEach(i -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Optional<Employee> match = employeeList.stream()
                    .filter(employee -> String.valueOf(employee.getId()).equals(i.toString()))
                    .findFirst();
            if (match.isPresent()) {
                Employee employee = match.get();
                employee.setEmail(employee.getId() + "-2-" + employee.getEmail());
            }
        });
    }

    private void handleList4(List<Integer> list4, List<Employee> employeeList) {
        list4.forEach(i -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Optional<Employee> match = employeeList.stream()
                    .filter(employee -> String.valueOf(employee.getId()).equals(i.toString()))
                    .findFirst();
            if (match.isPresent()) {
                Employee employee = match.get();
                employee.setEmail(employee.getId() + "-4-" + employee.getEmail());
            }
        });
    }

    private void handleList6(List<Integer> list6, List<Employee> employeeList) {
        list6.forEach(i -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Optional<Employee> match = employeeList.stream()
                    .filter(employee -> String.valueOf(employee.getId()).equals(i.toString()))
                    .findFirst();
            if (match.isPresent()) {
                Employee employee = match.get();
                employee.setEmail(employee.getId() + "-6-" + employee.getEmail());
            }
        });
    }


//                for (Employee employee : employeeList) {
//                if (!String.valueOf(employee.getId()).equals(i.toString())) continue;
//                employee.setEmail(employee.getId() + "-" + employee.getEmail());
//            }

    private List<Integer> getLstData(int n) {
        List<Integer> result = IntStream.rangeClosed(0, n)
                .boxed()
                .sorted()
                .collect(Collectors.toList());
        return result;
    }
}

