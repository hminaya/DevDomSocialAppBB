package DevDom;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.system.*;
import java.util.*;

public class SplashScreen extends MainScreen {
	private MainScreen next;
	private UiApplication application;
	private Timer timer = new Timer();
	private static final Bitmap _bitmap = Bitmap
			.getBitmapResource("logo_horizontal_transp.png");

	public SplashScreen(UiApplication ui, MainScreen next) {
		super(Field.USE_ALL_HEIGHT | Field.FIELD_LEFT);
		this.application = ui;
		this.next = next;

		// Mi Codigo
		HorizontalFieldManager managerTop = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH  | HorizontalFieldManager.USE_ALL_HEIGHT);
		managerTop.setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		
		LabelField label = new LabelField("Version ") {
			public void paint(Graphics g) {
				g.setColor(Color.RED);
				super.paint(g);
			}
		};
		this.add(managerTop);
		
		managerTop.add(label);
		managerTop.add(new BitmapField(_bitmap));
		
		
		//
		SplashScreenListener listener = new SplashScreenListener(this);
		this.addKeyListener(listener);
		timer.schedule(new CountDown(), 5000);
		application.pushScreen(this);
	}

	public void dismiss() {
		timer.cancel();
		application.popScreen(this);
		application.pushScreen(next);
	}

	private class CountDown extends TimerTask {
		public void run() {
			DismissThread dThread = new DismissThread();
			application.invokeLater(dThread);
		}
	}

	private class DismissThread implements Runnable {
		public void run() {
			dismiss();
		}
	}

	protected boolean navigationClick(int status, int time) {
		dismiss();
		return true;
	}

	protected boolean navigationUnclick(int status, int time) {
		return false;
	}

	protected boolean navigationMovement(int dx, int dy, int status, int time) {
		return false;
	}

	public static class SplashScreenListener implements KeyListener {
		private SplashScreen screen;

		public boolean keyChar(char key, int status, int time) {
			// intercept the ESC and MENU key - exit the splash screen
			boolean retval = false;
			switch (key) {
			case Characters.CONTROL_MENU:
			case Characters.ESCAPE:
				screen.dismiss();
				retval = true;
				break;
			}
			return retval;
		}

		public boolean keyDown(int keycode, int time) {
			return false;
		}

		public boolean keyRepeat(int keycode, int time) {
			return false;
		}

		public boolean keyStatus(int keycode, int time) {
			return false;
		}

		public boolean keyUp(int keycode, int time) {
			return false;
		}

		public SplashScreenListener(SplashScreen splash) {
			screen = splash;
		}
	}
}