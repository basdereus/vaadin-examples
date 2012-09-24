package nl.example.vaadin;

import nl.example.vaadin.table.BasicFilesystemContainerTable;
import nl.example.vaadin.table.BasicIndexedContainerTable;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class TableApplication extends Application {
	private static final long serialVersionUID = 7210810186874398112L;
	
	
	@Override
	public void init() {
		Window mainWindow = new Window("TableApplication");

		mainWindow.addComponent(new Label("Basic IndexedContainer Table"));
		mainWindow.addComponent(new BasicIndexedContainerTable());

		mainWindow.addComponent(new Label("Basic FilesystemContainer Table"));
		mainWindow.addComponent(new BasicFilesystemContainerTable());

		setMainWindow(mainWindow);
	}
	
}