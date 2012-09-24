package nl.example.vaadin.table;

import java.io.File;

import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.ui.Table;

/**
 * 
 * This plain table extends the {@link Table} and uses a file system container with four columns as a data source.
 *
 */
public class BasicFilesystemContainerTable extends Table {
	private static final long serialVersionUID = 1615235309002700490L;

	public BasicFilesystemContainerTable() {
		setContainerDataSource(createExampleData());
	}
	
	/*
	 * Creates a {@link FilesystemContainer} with four columns "name", "icon", "size", "last modified" and 
	 * files of the current directory as example data (not recursive).
	 */
	private static FilesystemContainer createExampleData() {
		final File exampleDir = new File(".");
		FilesystemContainer filesystemContainer = new FilesystemContainer(exampleDir, false);
		return filesystemContainer;
	}
}
