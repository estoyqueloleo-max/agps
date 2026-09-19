package d;

import android.content.Context;
import android.content.Intent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a<String[], Map<String, Boolean>> {
    @Override // d.a
    public Intent a(Context context, String[] strArr) {
        return new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr);
    }

    @Override // d.a
    public a.C0053a<Map<String, Boolean>> b(Context context, String[] strArr) {
        String[] strArr2 = strArr;
        if (strArr2 == null || strArr2.length == 0) {
            return new a.C0053a<>(Collections.emptyMap());
        }
        Map<String, Boolean> aVar = new HashMap<>();
        boolean z = true;
        for (String str : strArr2) {
            boolean z7 = androidx.core.content.ContextCompat.checkSelfPermission(context, str) == 0;
            aVar.put(str, Boolean.valueOf(z7));
            if (!z7) {
                z = false;
            }
        }
        if (z) {
            return new a.C0053a<>(aVar);
        }
        return null;
    }

    @Override // d.a
    public Map<String, Boolean> c(int i7, Intent intent) {
        if (i7 != -1) {
            return Collections.emptyMap();
        }
        if (intent == null) {
            return Collections.emptyMap();
        }
        String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
        if (intArrayExtra == null || stringArrayExtra == null) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap();
        int length = stringArrayExtra.length;
        for (int i8 = 0; i8 < length; i8++) {
            map.put(stringArrayExtra[i8], Boolean.valueOf(intArrayExtra[i8] == 0));
        }
        return map;
    }
}
