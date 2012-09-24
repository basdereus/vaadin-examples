package nl.example.vaadin;

import nl.example.vaadin.table.container.BasicBeanContainerTableExample;
import nl.example.vaadin.table.container.BasicBeanItemContainerTableExample;
import nl.example.vaadin.table.container.BasicFilesystemContainerTableExample;
import nl.example.vaadin.table.container.BasicIndexedContainerTableExample;
import nl.example.vaadin.table.filter.BasicFilterTableExample;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class TableApplication extends Application {
	private static final long serialVersionUID = 7210810186874398112L;
	
	
	@Override
	public void init() {
		Window mainWindow = new Window("TableApplication");

		mainWindow.addComponent(new Label("Basic Filter Table"));
		mainWindow.addComponent(new BasicFilterTableExample());

		mainWindow.addComponent(new Label("BeanItem Container Table"));
		mainWindow.addComponent(new BasicBeanItemContainerTableExample());

		mainWindow.addComponent(new Label("Bean Container Table"));
		mainWindow.addComponent(new BasicBeanContainerTableExample());

		mainWindow.addComponent(new Label("Basic IndexedContainer Table"));
		mainWindow.addComponent(new BasicIndexedContainerTableExample());

		mainWindow.addComponent(new Label("Basic FilesystemContainer Table"));
		mainWindow.addComponent(new BasicFilesystemContainerTableExample());

		setMainWindow(mainWindow);
	}
	
}