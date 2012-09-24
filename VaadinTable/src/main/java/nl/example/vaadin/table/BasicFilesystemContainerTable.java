package nl.example.vaadin.table;

import java.io.File;

import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.ui.Table;

public class BasicFilesystemContainerTable extends Table {
	private static final long serialVersionUID = 1615235309002700490L;

	public BasicFilesystemContainerTable() {
		setContainerDataSource(createExampleData());
	}
	
	/*
	 * Creates a {@link FilesystemContainer}.
	 */
	private static FilesystemContainer createExampleData() {
		final File exampleDir = new File(".");
		FilesystemContainer filesystemContainer = new FilesystemContainer(exampleDir, false);
		return filesystemContainer;
	}

}
