package nl.example.vaadin.table.filter;

import java.util.Date;
import java.util.Random;
import java.util.regex.PatternSyntaxException;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * This example shows the basic use of a custom filter, in this case {@link RegexFilter} on a {@link Table} with and {@link IndexedContainer}
 * 
 */
public class BasicCustomFilterTableExample extends CustomComponent {
	private static final long serialVersionUID = 8903778701375018306L;

	private static String NAME_COLUMN_ID = "name";
	private static String AGE_COLUMN_ID = "age";
	private static String DATE_COLUMN_ID = "date";

	private TextField customField;
	private Table filterTable;
	
	/**
	 * 
	 * Create custom filter field and the table to be filtered and add them in a vertical layout to the composition root.
	 * 
	 */
	public BasicCustomFilterTableExample() {
		VerticalLayout vLayout = new VerticalLayout();
		
		createCustomFilterField();		
		createTable();

		vLayout.addComponent(customField);
		vLayout.addComponent(filterTable);		
		setCompositionRoot(vLayout);
	}

	private void createTable() {
		filterTable = new Table();
		filterTable.setContainerDataSource(createExampleData());
	}

	/**
	 * 
	 * Create a {@link TextField} which attaches a {@link RegexFilter} to a {@link Table} based on text changed in the {@link TextField}.
	 * The filter is applied to the NAME_COLUMN_ID column and matches strings against the regular expression out of the {@link TextField}.
	 * 
	 */
	private void createCustomFilterField() {
		customField = new TextField("Name Filter");
		customField.addListener(new TextChangeListener() {
			private static final long serialVersionUID = -2394671950449799553L;
			
			RegexFilter regexFilter = null;
			
			public void textChange(TextChangeEvent textChangeEvent) {
				Filterable filterable = (Filterable) filterTable.getContainerDataSource();
				
				if (regexFilter != null) {
					filterable.removeContainerFilter(regexFilter);
				}
				
				regexFilter = new RegexFilter(NAME_COLUMN_ID, textChangeEvent.getText());
				filterable.addContainerFilter(regexFilter);
			}
		});	
	}

	/**
	 * 
	 * A regular expression filter as an example of basic use of creating a custom filter.
	 * This filter takes as regular expression to be applied as filter on String values
	 * 
	 */
	class RegexFilter implements Container.Filter {
		private static final long serialVersionUID = 6662349619070400538L;

		final private String propertyId;
		final private String regexFilterString;
		
		public RegexFilter(String propertyId, String regexFilterString) {
			this.propertyId = propertyId;
			this.regexFilterString = regexFilterString;
		}
		
		/**
		 * 
		 * This method is called by the container to decide which property should be filtered.
		 * Only returns true if propertyId matches the constructor provided propertyId of the filter.
		 * 
		 */
		public boolean appliesToProperty(Object propertyId) {
			return propertyId != null && propertyId.equals(this.propertyId);
		}
		
		/**
		 * 
		 * This method is called by the {@link Container} to decide whether a container {@link Item} passes the filter.
		 * Only passes items with properties of type String and property values which match the constructor provided regular expression
		 * 
		 */
		public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
			Property property = item.getItemProperty(propertyId);
			
			// property should exist and have a type String
			if (property == null || !property.getType().equals(String.class)) {
				return false;
			}
			
			String propertyValue = (String) property.getValue();
			
			boolean match = false;
			
			try {
				match = propertyValue.matches(regexFilterString);
			} catch (PatternSyntaxException e) {
				// Don't show values if the Regular Expression is in valid.
				match = false;
			}
			return match;
		}
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
