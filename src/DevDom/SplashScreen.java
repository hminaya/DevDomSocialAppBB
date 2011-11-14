package DevDom;

import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.system.*;
import java.util.*;

import DevDom.Utils.AnimatedGIFField;
import DevDom.Utils.BitmapFromURL;
import DevDom.Utils.DB;
import DevDom.json.*;
import DevDom.models.Category;
import DevDom.models.Memory;
import DevDom.models.Tutorial;

public class SplashScreen extends MainScreen {

	/**
	 * 
	 */
	public SplashScreen() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		this.getMainManager().setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		GridFieldManager fManager = new GridFieldManager(5, 1, 0);
		
		
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
		bmapLoad.setMargin(60,0,0,0);
		
		
		LabelField lblLoad = new LabelField("Cargando, Por Favor Espere...", Field.FIELD_HCENTER) {
			public void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		
		
		
		LabelField lblInfo = new LabelField("http://developers.do\ntwitter:@DevelopersDO") {
			public void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		
		lblInfo.setMargin(100,0,0,0);
		
		fManager.add(lblVersion);
		fManager.add(bmapImagen);
		fManager.add(bmapLoad);
		fManager.add(lblLoad);
		fManager.add(lblInfo);
		
		
		this.add(fManager);
		
		Thread threadToRun = new Thread() {
			public void run() {

				UiApplication.getUiApplication().invokeAndWait(new Runnable() {
					public void run() {
						LoadCategories();
					}
				});
							
				UiApplication.getUiApplication().invokeLater(new Runnable() {
		            public void run() {
		            	UiApplication.getUiApplication().pushScreen(new MyScreen());
		            }
				});
			
			}
		};
		threadToRun.start();
		
	}
	
	public void LoadCategories() {

		Vector categories = new Vector();
		
		try {
			
			JSONObject json = new JSONObject(DB.getResult());

			JSONArray categorias = json.getJSONArray("categorias");

			// /Buscar las categorias
			for (int i = 0; i < categorias.length(); i++) {

				JSONObject e = categorias.getJSONObject(i);

				// / Info general de la categoria
				Category catInfo = new Category(i, e.getString("categoryName"));
				catInfo.setDescription(e.getString("description"));
				catInfo.setImageUrl(e.getString("imageUrl"));
				catInfo.setImage(BitmapFromURL.getImageFromUrl(catInfo.getImageUrl()));

				try {
					// / Buscar los tutoriales dentro de cada categoria
					JSONArray ArrTutoriales = e.getJSONArray("tutorials");

					for (int j = 0; j < ArrTutoriales.length(); j++) {

						JSONObject t = ArrTutoriales.getJSONObject(j);

						String name = t.getString("name");
						String tutorialUrl = t.getString("tutorialUrl");
						String description = t.getString("description");

						catInfo.addTutorial(new Tutorial(name, tutorialUrl,
								description));
					}

				} catch (JSONException ex2) {
					Dialog.alert(ex2.toString());
				}

				// / Agregar todas las categorias
				categories.addElement(catInfo);

				
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Dialog.alert("No se pudo acceder informacion. Tratar otra vez.");
		}
		
		Memory.categorias = categories;

	}


}
