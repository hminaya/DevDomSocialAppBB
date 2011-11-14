package DevDom;

import java.util.Vector;

import DevDom.models.Category;
import DevDom.models.Memory;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class CategoryScreen extends MainScreen {

	/**
	 * 
	 */
		private ListField _listField;
	    private Vector _listElements;
	public CategoryScreen() {
		this.getMainManager().setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		
		Bitmap imgLogo = Bitmap.getBitmapResource("logo_horizontal_transp.png");
		
		BitmapField bmapImagen = new BitmapField(imgLogo);
			
		setTitle(bmapImagen);
		
		LabelField lblTitulo = new LabelField("Tutoriales",LabelField.USE_ALL_WIDTH){
			public void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		
		lblTitulo.setBackground(BackgroundFactory.createSolidBackground(Color.GRAY));
		
		add(lblTitulo);
		
		_listElements = new Vector(); 
		_listField = new ListField();
		_listField.setRowHeight(50);
		//_listField.setBackground(BackgroundFactory.createSolidBackground(Color.WHITE));
		ListCallback _callback = new ListCallback();
		_listField.setCallback(_callback);
		add(_listField);
		
		
		
		Thread threadToRun = new Thread() {
			public void run() {

				UiApplication.getUiApplication().invokeAndWait(new Runnable() {
					public void run() {
						LoadCategories();
					}
				});
				
			}
		};
		threadToRun.start();
		
	}
	
	public void LoadCategories() {

		try {
			
			_listElements = Memory.categorias;
			reloadList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Dialog.alert("No se pudo acceder informacion. Tratar otra vez.");
		}

	}
	
	private class ListCallback implements ListFieldCallback 
	{
	    public void drawListRow(ListField list, Graphics g, int index, int y, int w) 
	    { 
	    	Category cat = (Category)_listElements.elementAt(index);
	    	g.setColor(Color.WHITE);
	        g.drawText(cat.getCategoryName(), 60, y, 0, w);
	        g.setColor(Color.LIGHTGRAY);
	        g.drawText(cat.getDescription(), 60, y + 20, 0, w);
	        g.drawBitmap(0, y, cat.getImage().getWidth(), cat.getImage().getHeight(), cat.getImage(), 0, 2);
	    } 
	    public Object get(ListField list, int index) 
	    {
	        return _listElements.elementAt(index); 
	    } 
	    public int indexOfList(ListField list, String prefix, int string) 
	    { 
	        return _listElements.indexOf(prefix, string); 
	    } 
	    public int getPreferredWidth(ListField list) 
	    { 
	        return Display.getWidth(); 
	    } 
	}
	
	private void reloadList()
	{
	    _listField.setSize(_listElements.size());
	}
	
	protected boolean navigationClick(int status, int time)
	{
		int selectedindex = _listField.getSelectedIndex();
		Category cat = (Category)_listElements.elementAt(selectedindex);
		UiApplication.getUiApplication().pushScreen(new TutorialScreen(cat));
		return true;
	}

}
