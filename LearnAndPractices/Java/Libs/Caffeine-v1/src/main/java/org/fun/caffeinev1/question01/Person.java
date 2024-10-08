package org.fun.caffeinev1.question01;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 8711922740433840551L;
    private String name;
    private int age;

    public static String par1 = "FIELD STATIC";
    transient String par2 = "FIELD TRANSIENT";
    transient int high = 175;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", par1=" + par1 +
                ", high=" + high +
                ", par2='" + par2 + '\'' +
                '}';
    }
}
