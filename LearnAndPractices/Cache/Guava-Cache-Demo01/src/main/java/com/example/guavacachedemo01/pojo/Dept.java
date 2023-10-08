package com.example.guavacachedemo01.pojo;

import lombok.Data;

@Data
public class Dept {
    private Long id;
    // 1MB
    private byte[] bytes = new byte[1024 * 1024];

    public Dept(Long id) {
        this.id = id;
    }

    /**
     * When jvm wants to recycle your object, it will call back this method. You can complete the resource cleanup in this method.
     * Or complete self-rescue
     *
     * @throws Throwable exception
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println(id + "It's about to be recycled. Try to save yourself quickly....");
    }
}
