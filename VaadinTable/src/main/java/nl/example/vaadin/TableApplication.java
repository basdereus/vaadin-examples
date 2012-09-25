package nl.example.vaadin;

import nl.example.vaadin.table.container.BasicBeanContainerTableExample;
import nl.example.vaadin.table.container.BasicBeanItemContainerTableExample;
import nl.example.vaadin.table.container.BasicFilesystemContainerTableExample;
import nl.example.vaadin.table.container.BasicIndexedContainerTableExample;
import nl.example.vaadin.table.filter.BasicCustomFilterTableExample;
import nl.example.vaadin.table.filter.BasicGreaterAndLessFilterTableExample;
import nl.example.vaadin.table.filter.BasicLikeFilterTableExample;
import nl.example.vaadin.table.filter.BasicSimpleStringFilterTableExample;
import nl.example.vaadin.table.filter.DoubleSimpleStringFilterTableExample;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class TableApplication extends Application {
	private static final long serialVersionUID = 7210810186874398112L;
	
	
	@Override
	public void init() {
		Window mainWindow = new Window("TableApplication");

		mainWindow.addComponent(new Label("Greater Than Filter Table"));
		mainWindow.addComponent(new BasicGreaterAndLessFilterTableExample());

		mainWindow.addComponent(new Label("Basic Like Filter Table"));
		mainWindow.addComponent(new BasicLikeFilterTableExample());

		mainWindow.addComponent(new Label("Basic Custom Filter Table"));
		mainWindow.addComponent(new BasicCustomFilterTableExample());

		mainWindow.addComponent(new Label("Double Simple String Filter Table"));
		mainWindow.addComponent(new DoubleSimpleStringFilterTableExample());

		mainWindow.addComponent(new Label("Basic Simple String Filter Table"));
		mainWindow.addComponent(new BasicSimpleStringFilterTableExample());

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