package r.andres.serviciosweb_android.utilitarios;

import android.content.Context;
import android.widget.Toast;

public class singleToast {


    private static Toast mToast;

    public static void show(Context context, String text) {
        if (mToast != null)
            mToast.cancel();
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            mToast.show();
            }
}
