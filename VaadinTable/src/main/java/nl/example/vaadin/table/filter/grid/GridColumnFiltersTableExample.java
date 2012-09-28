package nl.example.vaadin.table.filter.grid;

import java.util.Date;
import java.util.Random;
import java.util.TreeSet;

import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * 
 * This example shows the basic use of a {@link GridLayout} with one {@link NativeSelect} and two {@link SimpleStringFilter} fields to filter on a {@link Table} with and {@link IndexedContainer}
 * 
 */
public class GridColumnFiltersTableExample extends CustomComponent {
	private static final long serialVersionUID = 5766280152862650111L;

	private static String NAME_COLUMN_ID = "name";
	private static String AGE_COLUMN_ID = "age";
	private static String DATE_COLUMN_ID = "date";

	private static final String[] fieldNameData = { "Peter", "Peter", "Joshua", "Mike", "Mike",
			"Mike", "Mike", "Rita", "Rita", "Rita", "Rita", "Rita",
            "Rita", "Rita" };

	private static Panel filterFieldGrid;
	private static TextField ageField;
	private static TextField dateField;
	private static Table indexedContainerTable; 

	public GridColumnFiltersTableExample() {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setSizeUndefined();

		createAgeFilterField();
		createDateFilterField();

		filterFieldGrid = createFilterFieldGrid();

		indexedContainerTable = new Table();
		indexedContainerTable.setContainerDataSource(createExampleData());
		indexedContainerTable.setColumnWidth(NAME_COLUMN_ID, 200);
		indexedContainerTable.setColumnWidth(AGE_COLUMN_ID, 200);
		indexedContainerTable.setColumnWidth(DATE_COLUMN_ID, 200);

		vLayout.addComponent(filterFieldGrid);
		vLayout.addComponent(indexedContainerTable);
		setCompositionRoot(vLayout);
	}
	
	/**
	 * 
	 * Creates a {@link IndexedContainer} with three columns of type String, Integer and Date as example data.
	 * 
	 */
	private static IndexedContainer createExampleData() {
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
	 * Creates a {@link Panel} with a {@link GridLayout} and adds filter fields to it.
	 * 
	 */
	private static Panel createFilterFieldGrid() {
		Panel filterFieldGrid = new Panel();
		filterFieldGrid.setStyleName(Runo.PANEL_LIGHT);
		GridLayout gLayout = new GridLayout(3, 1);
		gLayout.setSizeFull();
		gLayout.addComponent(createExampleNativeSelect(), 0, 0);
		gLayout.addComponent(ageField, 1, 0);
		gLayout.addComponent(dateField, 2, 0);
		filterFieldGrid.addComponent(gLayout);
		return filterFieldGrid;
	}
	
	/**
	 * Creates a {@link NativeSelect} filter obtained from the unique ordered values of a String array used for one 'to be filtered' column of a {@link Table}.
	 * The 'Null' value for the {@link NativeSelect} with caption 'Show All' does only remove the filter from the {@link Table}. 
	 * 
	 * @return {@link NativeSelect} filter for the table
	 */
	private static NativeSelect createExampleNativeSelect() {
		TreeSet<String> selectItems = new TreeSet<String>();
		
		for (String name : fieldNameData) {
			selectItems.add(name);
		}
		
		NativeSelect nativeSelect = new NativeSelect();
		nativeSelect.setWidth(100, TextField.UNITS_PIXELS);
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
	
	/**
	 * 
	 * Create a {@link TextField} which attaches a {@link SimpleStringFilter} to a {@link Table} based on text changed in the {@link TextField}.
	 * The filter is applied to the NAME_COLUMN_ID column, is case-insensitive and matches on a table cell containing a specified string.
	 * 
	 */
	private static void createAgeFilterField() {
		ageField = new TextField();
		ageField.setWidth(100, TextField.UNITS_PIXELS);
		ageField.addListener(new TextChangeListener() {
			private static final long serialVersionUID = -2394671950449799553L;
			
			SimpleStringFilter simpleStringFilter = null;
			
			public void textChange(TextChangeEvent textChangeEvent) {
				Filterable filterable = (Filterable) indexedContainerTable.getContainerDataSource();
				
				if (simpleStringFilter != null) {
					filterable.removeContainerFilter(simpleStringFilter);
				}
				
				simpleStringFilter = new SimpleStringFilter(AGE_COLUMN_ID, textChangeEvent.getText(), true, false);
				filterable.addContainerFilter(simpleStringFilter);
			}
		});	
	}

	/**
	 * 
	 * Create a {@link TextField} which attaches a {@link SimpleStringFilter} to a {@link Table} based on text changed in the {@link TextField}.
	 * The filter is applied to the DATE_COLUMN_ID column, is case-insensitive and matches on a table cell containing a specified string.
	 * 
	 */
	private static void createDateFilterField() {
		dateField = new TextField();
		dateField.setWidth(100, TextField.UNITS_PIXELS);
		dateField.addListener(new TextChangeListener() {
			private static final long serialVersionUID = -2394671950449799553L;
			
			SimpleStringFilter simpleStringFilter = null;
			
			public void textChange(TextChangeEvent textChangeEvent) {
				Filterable filterable = (Filterable) indexedContainerTable.getContainerDataSource();
				
				if (simpleStringFilter != null) {
					filterable.removeContainerFilter(simpleStringFilter);
				}
				
				simpleStringFilter = new SimpleStringFilter(DATE_COLUMN_ID, textChangeEvent.getText(), true, false);
				filterable.addContainerFilter(simpleStringFilter);
			}
		});	
	}

}
