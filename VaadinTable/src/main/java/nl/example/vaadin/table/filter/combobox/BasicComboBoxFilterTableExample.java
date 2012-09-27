package nl.example.vaadin.table.filter.combobox;

import java.util.Date;
import java.util.Random;
import java.util.TreeSet;

import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * This example shows the basic use of a {@link NativeSelect} to use a {@link SimpleStringFilter} on a {@link Table} with and {@link IndexedContainer}
 * 
 */
public class BasicComboBoxFilterTableExample extends CustomComponent {
	private static final long serialVersionUID = 2470173765108139415L;

	private static String NAME_COLUMN_ID = "name";
	private static String AGE_COLUMN_ID = "age";
	private static String DATE_COLUMN_ID = "date";

	String[] fieldNameData = { "Peter", "Peter", "Joshua", "Mike", "Mike",
			"Mike", "Mike", "Rita", "Rita", "Rita", "Rita", "Rita",
            "Rita", "Rita" };

	private Table indexedContainerTable; 

	public BasicComboBoxFilterTableExample() {
		VerticalLayout vLayout = new VerticalLayout();
				
		indexedContainerTable = new Table();
		indexedContainerTable.setContainerDataSource(createExampleData());
		
		vLayout.addComponent(createExampleNativeSelect());
		vLayout.addComponent(indexedContainerTable);

		setCompositionRoot(vLayout);
	}
	
	/**
	 * 
	 * Creates a {@link IndexedContainer} with three columns of type String, Integer and Date as example data.
	 * 
	 */
	private IndexedContainer createExampleData() {
		
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
	
	/**
	 * Creates a {@link NativeSelect} filter obtained from the unique ordered values of a String array used for one 'to be filtered' column of a {@link Table}.
	 * The 'Null' value for the {@link NativeSelect} with caption 'Show All' does only remove the filter from the {@link Table}. 
	 * 
	 * @return {@link NativeSelect} filter for the table
	 */
	private NativeSelect createExampleNativeSelect() {
		TreeSet<String> selectItems = new TreeSet<String>();
		
		for (String name : fieldNameData) {
			selectItems.add(name);
		}
		
		NativeSelect nativeSelect = new NativeSelect("Filter on FieldName");
		nativeSelect.setInvalidAllowed(false);
		
		// Set 'Null' value as 'Show All'
		nativeSelect.setNullSelectionAllowed(true);
		nativeSelect.setNullSelectionItemId("Show All");
		
		for (String selectItem : selectItems) {
			nativeSelect.addItem(selectItem);
		}
				
		nativeSelect.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = -4820170898280727113L;

			SimpleStringFilter simpleStringFilter;
			
			public void valueChange(ValueChangeEvent valueChangeEvent) {
				Filterable filterable = (Filterable)indexedContainerTable.getContainerDataSource();
				
				if (simpleStringFilter != null) {
					filterable.removeContainerFilter(simpleStringFilter);
				}
				
				Object selectedPropertyValue = valueChangeEvent.getProperty().getValue();
				
				// Only adds a {@link SimpleStringFilter} when no 'Null' value is selected.
				if (selectedPropertyValue != null) {
					simpleStringFilter = new SimpleStringFilter(NAME_COLUMN_ID, (String)selectedPropertyValue, false, true);
					
					filterable.addContainerFilter(simpleStringFilter);					
				}
			}
		});
		nativeSelect.setImmediate(true);
		
		return nativeSelect;
	}
}
