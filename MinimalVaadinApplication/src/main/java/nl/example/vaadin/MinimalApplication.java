package nl.example.vaadin;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class MinimalApplication extends Application {
	private static final long serialVersionUID = 7210810186874398112L;

	@Override
	public void init() {
		Window mainWindow = new Window("MinimalApplication");
		mainWindow.addComponent(new Label("Hello World!"));
		setMainWindow(mainWindow);
	}
}