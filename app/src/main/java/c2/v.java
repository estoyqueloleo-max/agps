package c2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.giobat.AgpsTrackerPP.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class v extends BaseAdapter {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public List<w> f3198h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Context f3199i;

    public v(Context context, List<w> list) {
        this.f3198h = null;
        this.f3199i = null;
        this.f3199i = context;
        this.f3198h = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<w> list = this.f3198h;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i7) {
        List<w> list = this.f3198h;
        if (list != null) {
            return list.get(i7);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i7) {
        return i7;
    }

    @Override // android.widget.Adapter
    public View getView(int i7, View view, ViewGroup viewGroup) {
        x xVar;
        if (view != null) {
            xVar = (x) view.getTag();
        } else {
            view = View.inflate(this.f3199i, R.layout.activity_list_view_with_checkbox_item, null);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);
            TextView textView = (TextView) view.findViewById(R.id.list_view_item_text);
            ImageView imageView = (ImageView) view.findViewById(R.id.list_view_item_image);
            x xVar2 = new x(view);
            xVar2.f3225t = checkBox;
            xVar2.f3226u = textView;
            xVar2.f3227v = imageView;
            view.setTag(xVar2);
            xVar = xVar2;
        }
        w wVar = this.f3198h.get(i7);
        xVar.f3225t.setChecked(wVar.f3214a);
        xVar.f3226u.setText(wVar.f3215b);
        xVar.f3227v.setImageBitmap(wVar.f3216c);
        return view;
    }
}
