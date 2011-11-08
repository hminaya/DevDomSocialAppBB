package DevDom;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;


/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */

public final class MyScreen extends MainScreen implements FieldChangeListener {
	private BitmapField bmapTutorial;
	private BitmapField bmapEventos;
	private BitmapField bmapNoticias;
	private BitmapField bmapEmpleos;
	private BitmapField bmapColaborador;
	private BitmapField bmapComunidad;
	
	public MyScreen() {
		
		this.getMainManager().setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		
		
		Bitmap imgLogo = Bitmap.getBitmapResource("logo_horizontal_transp.png");
		BitmapField bmapImagen = new BitmapField(imgLogo, BitmapField.USE_ALL_WIDTH);
		
		setTitle(bmapImagen);
		
		GridFieldManager gvManager = new GridFieldManager(3, 2, GridFieldManager.USE_ALL_HEIGHT | GridFieldManager.USE_ALL_WIDTH| GridFieldManager.FIXED_SIZE);
		gvManager.setRowPadding(30);
		gvManager.setColumnProperty(0,GridFieldManager.FIXED_SIZE,Display.getWidth()/2);
		gvManager.setColumnProperty(1,GridFieldManager.FIXED_SIZE,Display.getWidth()/2);
		
		Bitmap imgTutorial = Bitmap.getBitmapResource("icon_tutorial.png");
		bmapTutorial = new BitmapField(imgTutorial,BitmapField.HIGHLIGHT_SELECT |BitmapField.FOCUSABLE)
		{
            protected void onFocus(int direction) {
                super.onFocus(direction);
                System.out.println("Touched Tutorial...");
            }
            protected void onUnfocus() {
                super.onUnfocus();
               
            }
        };
		bmapTutorial.setChangeListener(this);
		
		Bitmap imgEventos = Bitmap.getBitmapResource("icon_calendar.png");
		bmapEventos = new BitmapField(imgEventos,BitmapField.HIGHLIGHT_SELECT |BitmapField.FOCUSABLE);
		bmapEventos.setChangeListener(this);
		
		Bitmap imgNoticias = Bitmap.getBitmapResource("icon_news.png");
		bmapNoticias = new BitmapField(imgNoticias,BitmapField.HIGHLIGHT_SELECT |BitmapField.FOCUSABLE);
		bmapNoticias.setChangeListener(this);
		
		Bitmap imgEmpleos = Bitmap.getBitmapResource("icon_empleos.png");
		bmapEmpleos = new BitmapField(imgEmpleos,BitmapField.HIGHLIGHT_SELECT |BitmapField.FOCUSABLE);
		bmapEmpleos.setChangeListener(this);
		
		Bitmap imgColaborador = Bitmap.getBitmapResource("icon_colaboradores.png");
		bmapColaborador = new BitmapField(imgColaborador,BitmapField.HIGHLIGHT_SELECT |BitmapField.FOCUSABLE);
		bmapColaborador.setChangeListener(this);
		
		Bitmap imgComunidad= Bitmap.getBitmapResource("icon_community.png");
		bmapComunidad = new BitmapField(imgComunidad,BitmapField.HIGHLIGHT_SELECT |BitmapField.FOCUSABLE);
		bmapComunidad.setChangeListener(this);
		
		gvManager.add(bmapTutorial);
		gvManager.add(bmapEventos);
		gvManager.add(bmapNoticias);
		gvManager.add(bmapEmpleos);
		gvManager.add(bmapColaborador);
		gvManager.add(bmapComunidad);
		
		add(gvManager);
		
	}
	
	 public void fieldChanged(Field field, int context) {
	    	if (field == bmapTutorial) {
	    		System.out.println("Touched Tutorial...");
	    	} else if (field == bmapEventos) {
	    		System.out.println("Touched Eventos...");
	    	} else if (field == bmapNoticias) {
	    		System.out.println("Touched Noticias...");
	    	}
	    	else if (field == bmapEmpleos) {
	    		System.out.println("Touched Empleos...");
	    	}
	    	else if (field == bmapColaborador) {
	    		System.out.println("Touched Colaborador...");
	    	}
	    	else if (field == bmapComunidad) {
	    		System.out.println("Touched Comunidad...");
	    	}
	    }
	 
}
