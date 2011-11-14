package DevDom;

import java.util.Vector;

import DevDom.models.Category;
import DevDom.models.Tutorial;
import net.rim.blackberry.api.browser.Browser;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class TutorialScreen extends MainScreen {

	/**
	 * 
	 */
	private ListField _listField;
    private Vector _listElements;
	public TutorialScreen(final Category cat) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		this.getMainManager().setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		
		Bitmap imgLogo = Bitmap.getBitmapResource("logo_horizontal_transp.png");
		
		BitmapField bmapImagen = new BitmapField(imgLogo);
			
		setTitle(bmapImagen);
		
		LabelField lblTitulo = new LabelField("Tutoriales disponibles en esta categoria",LabelField.USE_ALL_WIDTH){
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
		 ListCallback _callback = new ListCallback();
		_listField.setCallback(_callback);
		add(_listField);
		
		
		
		Thread threadToRun = new Thread() {
			public void run() {

				UiApplication.getUiApplication().invokeAndWait(new Runnable() {
					public void run() {
						LoadTutorials(cat.getTutorials());
					}
				});
				
			}
		};
		threadToRun.start();
		
	}
	
	public void LoadTutorials(Vector tutorials) {

		try {
			
			_listElements = tutorials;
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
	    	Tutorial tut = (Tutorial)_listElements.elementAt(index);
	    	g.setColor(Color.WHITE);
	        g.drawText(tut.getName(), 0, y, 0, w);
	        g.setDrawingStyle(DrawStyle.ELLIPSIS,true);
	        g.setColor(Color.LIGHTGRAY);
	        g.drawText(tut.getDescription(), 0, y + 20, 0, w);
	        
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
		Tutorial tut = (Tutorial)_listElements.elementAt(selectedindex);
		Browser.getDefaultSession().displayPage(tut.getTutorialUrl());
		return true;
	}

}
