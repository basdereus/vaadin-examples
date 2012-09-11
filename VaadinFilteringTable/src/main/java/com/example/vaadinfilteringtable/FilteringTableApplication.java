package com.example.vaadinfilteringtable;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class FilteringTableApplication extends Application {
	private static final long serialVersionUID = 7210810186874398112L;

	@Override
	public void init() {
		Window mainWindow = new Window("FilteringTable");
		mainWindow.addComponent(new Label("FilteringTable PlaceHolder"));
		setMainWindow(mainWindow);
	}

}
