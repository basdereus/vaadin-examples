package nl.example.vaadin.table.filter;

import java.util.Date;
import java.util.Random;

import com.vaadin.data.Container.Filterable;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * This example shows the basic use of a {@link SimpleStringFilter} on a {@link Table} with and {@link IndexedContainer}
 * 
 */
public class BasicSimpleStringFilterTableExample extends CustomComponent {
	private static final long serialVersionUID = -2118302821848406044L;

	private static String NAME_COLUMN_ID = "name";
	private static String AGE_COLUMN_ID = "age";
	private static String DATE_COLUMN_ID = "date";

	private TextField nameField;
	private Table basicFilterTable;
	
	/**
	 * 
	 * Create name filter field and the table to be filtered and add them in a vertical layout to the composition root.
	 * 
	 */
	public BasicSimpleStringFilterTableExample() {
		VerticalLayout vLayout = new VerticalLayout();
		
		createNameFilterField();		
		createTable();

		vLayout.addComponent(nameField);
		vLayout.addComponent(basicFilterTable);		
		setCompositionRoot(vLayout);
	}

	private void createTable() {
		basicFilterTable = new Table();
		basicFilterTable.setContainerDataSource(createExampleData());
	}

	/**
	 * 
	 * Create a {@link TextField} which attaches a {@link SimpleStringFilter} to a {@link Table} based on text changed in the {@link TextField}.
	 * The filter is applied to the NAME_COLUMN_ID column, is case-insensitive and matches on a table cell containing a specified string.
	 * 
	 */
	private void createNameFilterField() {
		nameField = new TextField("Name Filter");
		nameField.addListener(new TextChangeListener() {
			private static final long serialVersionUID = -2394671950449799553L;
			
			SimpleStringFilter simpleStringFilter = null;
			
			public void textChange(TextChangeEvent textChangeEvent) {
				Filterable filterable = (Filterable) basicFilterTable.getContainerDataSource();
				
				if (simpleStringFilter != null) {
					filterable.removeContainerFilter(simpleStringFilter);
				}
				
				simpleStringFilter = new SimpleStringFilter(NAME_COLUMN_ID, textChangeEvent.getText(), true, false);
				filterable.addContainerFilter(simpleStringFilter);
			}
		});	
	}
	
	/**
	 * 
	 * Creates a {@link IndexedContainer} with three columns of type String, Integer and Date as example data.
	 * 
	 */
	private static IndexedContainer createExampleData() {
		String[] fieldNameData = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
				"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
                "Lisa", "Marge" };
		
		IndexedContainer indexedContainer = new IndexedContainer();
		
		
		indexedContainer.addContainerProperty(NAME_COLUMN_ID, String.class, "");
		indexedContainer.addContainerProperty(AGE_COLUMN_ID, Integer.class, "");
		indexedContainer.addContainerProperty(DATE_COLUMN_ID, Date.class, null);
		
		for (int i = 0; i < fieldNameData.length; i++) {
			Object itemId = indexedContainer.addItem();
			indexedContainer.getContainerProperty(itemId, NAME_COLUMN_ID).setValue(fieldNameData[i]);
			indexedContainer.getContainerProperty(itemId, AGE_COLUMN_ID).setValue(new Random().nextInt(100));
			indexedContainer.getContainerProperty(itemId, DATE_COLUMN_ID).setValue(new Date((new Random().nextInt(1000)*60*60*24*365*50 + new Date().getTime() - 1000*60*60*24*365*50))); // random Date between now + or - 50 years.		
		}
		
		return indexedContainer;
	}
}
