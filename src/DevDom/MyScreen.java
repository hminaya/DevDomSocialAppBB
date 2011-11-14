package DevDom;



import DevDom.Utils.CustomBitmapField;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;



/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */

public final class MyScreen extends MainScreen implements FieldChangeListener {
	private CustomBitmapField bmapTutorial;
	private CustomBitmapField bmapEventos;
	private CustomBitmapField bmapNoticias;
	private CustomBitmapField bmapEmpleos;
	private CustomBitmapField bmapColaborador;
	private CustomBitmapField bmapComunidad;
	
	public MyScreen() {
		
		this.getMainManager().setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		
		
		Bitmap imgLogo = Bitmap.getBitmapResource("logo_horizontal_transp.png");
	
		BitmapField bmapImagen = new BitmapField(imgLogo);
	
		
		setTitle(bmapImagen);
		
		add(new SeparatorField());
		
		GridFieldManager gvManager = new GridFieldManager(3, 2, GridFieldManager.USE_ALL_HEIGHT | GridFieldManager.USE_ALL_WIDTH| GridFieldManager.FIXED_SIZE);
		gvManager.setRowPadding(30);
		gvManager.setColumnProperty(0,GridFieldManager.FIXED_SIZE,Display.getWidth()/2);
		gvManager.setColumnProperty(1,GridFieldManager.FIXED_SIZE,Display.getWidth()/2);
		
		bmapTutorial = new CustomBitmapField(Bitmap.getBitmapResource("IconTutoriales.png"),Bitmap.getBitmapResource("IconTutoriales.png"),0);
		bmapTutorial.setChangeListener(this);
		
		bmapEventos = new CustomBitmapField(Bitmap.getBitmapResource("IconEventos.png"),Bitmap.getBitmapResource("IconEventos.png"),0);
		bmapEventos.setChangeListener(this);
		
			
		bmapNoticias = new CustomBitmapField(Bitmap.getBitmapResource("IconNoticias.png"),Bitmap.getBitmapResource("IconNoticias.png"),0);
		bmapNoticias.setChangeListener(this);
	
		
		bmapEmpleos = new CustomBitmapField(Bitmap.getBitmapResource("IconEmpleos.png"),Bitmap.getBitmapResource("IconEmpleos.png"),0);
		bmapEmpleos.setChangeListener(this);
		
		
		bmapColaborador = new CustomBitmapField(Bitmap.getBitmapResource("IconColaboradores.png"),Bitmap.getBitmapResource("IconColaboradores.png"),0);
		bmapColaborador.setChangeListener(this);
		
	
		bmapComunidad = new CustomBitmapField(Bitmap.getBitmapResource("IconComunidades.png"),Bitmap.getBitmapResource("IconComunidades.png"),0);
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
		//Hizo Click
		if (context == 0) {
			if (field == bmapTutorial) {
				UiApplication.getUiApplication().pushScreen(new CategoryScreen());
			} else if (field == bmapEventos) {
				Dialog.alert("Aun estamos en Beta :),no todo esta listo.");
			} else if (field == bmapNoticias) {
				Dialog.alert("Aun estamos en Beta :),no todo esta listo.");
			} else if (field == bmapEmpleos) {
				Dialog.alert("Aun estamos en Beta :),no todo esta listo.");
			} else if (field == bmapColaborador) {
				Dialog.alert("Aun estamos en Beta :),no todo esta listo.");
			} else if (field == bmapComunidad) {
				Dialog.alert("Aun estamos en Beta :),no todo esta listo.");
			}
		}

	}
	
	 public boolean onClose() {
		  return super.onClose();
		}
		        
	 public boolean onSavePrompt() { return true; } 

	
	 
}
