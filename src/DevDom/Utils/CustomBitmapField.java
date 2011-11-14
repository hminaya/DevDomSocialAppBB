package DevDom.Utils;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.component.BitmapField;

public class CustomBitmapField extends BitmapField{
	Bitmap normal,focused;
	
	public CustomBitmapField(Bitmap bitmap,Bitmap bitmap2,long style) {
		super(bitmap,style);
		normal = bitmap;
		focused = bitmap2;
    }

    public boolean isFocusable() {
        return true;
    }

    protected boolean navigationClick(int status, int time) {
       fieldChangeNotify(0);
       return true;
    }

    protected void onFocus(int direction) {
       this.setBitmap(focused);
       super.onFocus(direction);
       invalidate();
    }

    protected void onUnfocus() {
       this.setBitmap(normal);
       super.onUnfocus();
       invalidate();
    }

}