package DevDom;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.system.*;
import java.util.*;


public class SplashScreen extends MainScreen {
	private UiApplication application;
	private Timer timer = new Timer();
	
	public SplashScreen(UiApplication ui) {
		super(Field.USE_ALL_HEIGHT | Field.FIELD_LEFT);
		this.application = ui;
		

		// Mi Codigo
		this.getMainManager().setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		GridFieldManager fManager = new GridFieldManager(4, 1, 0);
		
		
		LabelField lblVersion = new LabelField("Beta v0.9.1 ") {
			public void paint(Graphics g) {
				g.setColor(Color.RED);
				super.paint(g);
			}
		};
		
		lblVersion.setMargin(30,0,0,0);
		
		
		Bitmap imgLogo = Bitmap.getBitmapResource("logo_horizontal_transp.png");
		BitmapField bmapImagen = new BitmapField(imgLogo);
		bmapImagen.setMargin(50,0,0,0);
		
		
		GIFEncodedImage imgLoad = (GIFEncodedImage)GIFEncodedImage.getEncodedImageResource("spinner.gif");
		AnimatedGIFField bmapLoad = new AnimatedGIFField(imgLoad);
		bmapLoad.setMargin(70,0,0,0);
		
		
		
		LabelField lblInfo = new LabelField("http://developers.do\ntwitter:@DevelopersDO") {
			public void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		
		lblInfo.setMargin(90,0,0,0);
		
		fManager.add(lblVersion);
		fManager.add(bmapImagen);
		fManager.add(bmapLoad);
		fManager.add(lblInfo);
		
		
		this.add(fManager);
		//
		
		
		SplashScreenListener listener = new SplashScreenListener(this);
		this.addKeyListener(listener);
		timer.schedule(new CountDown(), 5000);
		application.pushScreen(this);
	}

	public void dismiss() {
		timer.cancel();
		//application.popScreen(this);
		UiApplication.getUiApplication().pushScreen(new MyScreen());
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