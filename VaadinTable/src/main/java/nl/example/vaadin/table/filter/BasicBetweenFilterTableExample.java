package nl.example.vaadin.table.filter;

import java.util.Date;
import java.util.Random;

import com.vaadin.data.Container.Filterable;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare.Greater;
import com.vaadin.data.util.filter.Compare.Less;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * This example shows the basic use of a {@link Greater} and a {@link Less} filters on a {@link Table} with and {@link IndexedContainer}
 * 
 */
public class BasicBetweenFilterTableExample extends CustomComponent {
	private static final long serialVersionUID = -2118302821848406044L;

	private static String NAME_COLUMN_ID = "name";
	private static String AGE_COLUMN_ID = "age";
	private static String DATE_COLUMN_ID = "date";

	private TextField secundaryFilterField;
	private TextField directFilterField;
	private Table basicFilterTable;
	
	/**
	 * 
	 * Create a direct typing filter {@link TextField}, a no direct typing filter {@link TextField} and the table to be filtered and add them in a vertical layout to the composition root.
	 * 
	 */
	public BasicBetweenFilterTableExample() {
		VerticalLayout vLayout = new VerticalLayout();
		
		createBetweenFilterField();		
		createTable();

		secundaryFilterField = new TextField("No direct Filter TextField", "80");
		
		vLayout.addComponent(directFilterField);
		vLayout.addComponent(secundaryFilterField);
		vLayout.addComponent(basicFilterTable);		
		setCompositionRoot(vLayout);
	}

	private void createTable() {
		basicFilterTable = new Table();
		basicFilterTable.setContainerDataSource(createExampleData());
	}

	/**
	 * 
	 * Create a {@link TextField} which attaches a {@link Between} conditional filter to a {@link Table} based on text changed in the first {@link TextField}.
	 * The filter is applied to the AGE_COLUMN_ID column and matches on a table cell based on an age value between the value in the first {@link TextField} and
	 * the value in the second {@link TextField}. The second {@link TextField} is unresponsive to text changes reflected in filtering the table.
	 * 
	 */
	private void createBetweenFilterField() {
		directFilterField = new TextField("Filter on Typing TextField");
		directFilterField.addListener(new TextChangeListener() {
			private static final long serialVersionUID = -2394671950449799553L;
			
			Between betweenFilter = null;
			
			public void textChange(TextChangeEvent textChangeEvent) {
				Filterable filterable = (Filterable) basicFilterTable.getContainerDataSource();
				
				if (betweenFilter != null) {
					filterable.removeContainerFilter(betweenFilter);
				}
				
				try {
					int firstAgeValue = Integer.parseInt(textChangeEvent.getText());
					int secondAgeValue = Integer.parseInt(secundaryFilterField.getValue().toString());
					betweenFilter = new Between(AGE_COLUMN_ID, firstAgeValue, secondAgeValue);					
				} catch (Exception e) {
					betweenFilter = new Between(AGE_COLUMN_ID, 0, 0);
				}
				filterable.addContainerFilter(betweenFilter);
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
