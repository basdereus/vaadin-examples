package nl.example.vaadin.table.container;

import java.io.File;

import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

/**
 * 
 * This custom component uses a table and a file system container with four columns as a data source.
 *
 */
public class BasicFilesystemContainerTableExample extends CustomComponent {
	private static final long serialVersionUID = 1615235309002700490L;

	private Table indexedContainerTable; 

	public BasicFilesystemContainerTableExample() {
		indexedContainerTable = new Table();
		indexedContainerTable.setContainerDataSource(createExampleData());
		setCompositionRoot(indexedContainerTable);
	}
	
	/**
	 * 
	 * Creates a {@link FilesystemContainer} with four columns "name", "icon", "size", "last modified" and 
	 * files of the current directory as example data (not recursive).
	 * 
	 */
	private static FilesystemContainer createExampleData() {
		final File exampleDir = new File(".");
		FilesystemContainer filesystemContainer = new FilesystemContainer(exampleDir, false);
		return filesystemContainer;
	}
}
