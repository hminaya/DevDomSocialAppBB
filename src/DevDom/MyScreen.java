package DevDom;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;


/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */

public final class MyScreen extends MainScreen {
	HorizontalFieldManager managerTop;
	VerticalFieldManager managerContent;
	HorizontalFieldManager managerBottom;
	BitmapField _bitmap;
	Bitmap topImage;

	/**
	 * Creates a new MyScreen object
	 */
	public MyScreen() {
		
		
		// Set the displayed title of the screen
		setTitle("Titulo");
		this.setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		managerTop = new HorizontalFieldManager();
		managerContent = new VerticalFieldManager();
		managerBottom = new HorizontalFieldManager();
		managerTop.setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		add(managerTop);
		add(new SeparatorField());
		add(managerContent);
		add(new SeparatorField());
		add(managerBottom);

		topImage = Bitmap.getBitmapResource("logo_horizontal.png");
		_bitmap = new BitmapField();
		_bitmap.setBitmap(topImage);
		managerTop.add(_bitmap);

	}
}
