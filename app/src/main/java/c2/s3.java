package c2;

import java.util.LinkedList;
import java.util.Queue;

public class s3 {
    public int f3169h;
    public Object f3170i;

    public s3() {
        this.f3170i = new LinkedList<String>();
    }

    public s3(int i) {
        this.f3169h = i;
        this.f3170i = new LinkedList<String>();
    }

    public s3(Object obj, int i) {
        this.f3169h = i;
        this.f3170i = obj != null ? obj : new LinkedList<String>();
    }

    public void a(String str) {
        if (this.f3170i instanceof Queue) {
            ((Queue) this.f3170i).add(str);
        }
    }
}
