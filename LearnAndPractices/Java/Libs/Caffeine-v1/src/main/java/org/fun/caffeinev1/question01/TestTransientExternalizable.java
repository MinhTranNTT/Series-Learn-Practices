package org.fun.caffeinev1.question01;

import java.io.*;

public class TestTransientExternalizable implements Externalizable {
    private transient String text = "I can Serializable!!!";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TestTransientExternalizable test = new TestTransientExternalizable();

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\MinhTran\\Downloads\\TestTransientFile\\transient.txt"));
        out.writeObject(test);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\MinhTran\\Downloads\\TestTransientFile\\transient.txt"));
        test = (TestTransientExternalizable) in.readObject();
        System.out.println(test.text);
        out.close();
        in.close();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(text);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        text = (String) in.readObject();
    }
}
