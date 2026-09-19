package e7;

import c2.e0;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f5090a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ByteBuffer f5091b;

    static {
        byte[] bArr = new byte[8];
        f5090a = bArr;
        f5091b = ByteBuffer.wrap(bArr);
    }

    public static synchronized int a(InputStream inputStream) {
        b(inputStream, 4);
        return e0.d(f5091b);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
        throw (E) e;
    }

    public static void b(InputStream inputStream, int i7) {
        try {
            if (inputStream.read(f5090a, 0, i7) != i7) {
                sneakyThrow(new EOFException());
            }
        } catch (IOException e) {
            sneakyThrow(e);
        }
    }

    public static synchronized double c(InputStream inputStream) {
        ByteBuffer byteBuffer;
        b(inputStream, 8);
        byteBuffer = f5091b;
        if (byteBuffer == null) {
            throw new RuntimeException("Cannot deserialize null buffer.");
        }
        if (byteBuffer.array().length < 8) {
            throw new RuntimeException("Cannot deserialize. Byte buffer must have at least 8 bytes.");
        }
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.position(0);
        return byteBuffer.getDouble();
    }

    public static synchronized int d(InputStream inputStream) {
        ByteBuffer byteBuffer;
        b(inputStream, 4);
        byteBuffer = f5091b;
        if (byteBuffer == null) {
            throw new RuntimeException("Cannot deserialize null byte buffer.");
        }
        if (byteBuffer.array().length < 4) {
            throw new RuntimeException("Cannot deserialize. Byte buffer must have at least 4 bytes.");
        }
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.position(0);
        return byteBuffer.getInt();
    }
}
