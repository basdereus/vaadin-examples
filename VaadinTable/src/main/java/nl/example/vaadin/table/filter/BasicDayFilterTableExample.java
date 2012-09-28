package nl.example.vaadin.table.filter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.time.DateUtils;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class BasicDayFilterTableExample extends CustomComponent {
	private static final long serialVersionUID = 2674129139867513711L;

	private static String NAME_COLUMN_ID = "name";
	private static String AGE_COLUMN_ID = "age";
	private static String DATE_COLUMN_ID = "date";

	private PopupDateField popupDateField;
	private Table basicFilterTable;
	
	public BasicDayFilterTableExample() {
		VerticalLayout vLayout = new VerticalLayout();
		
		createDateFilterField();
		createTable();
		
		vLayout.addComponent(popupDateField);
		vLayout.addComponent(basicFilterTable);
		setCompositionRoot(vLayout);
	}

	/**
	 * 
	 * Creates a {@link Date} field formatted according to format "dd-MM-yyyy". 
	 * When the field value changes, a {@link DayFilter} is applied on the date column to filter out that Day.
	 * 
	 */
	private void createDateFilterField() {
		popupDateField = new PopupDateField();
		popupDateField.setResolution(PopupDateField.RESOLUTION_DAY);
		popupDateField.setDateFormat("dd-MM-yyyy");
		popupDateField.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = -2127694529547658106L;

			DayFilter dayFilter;
			
			@Override
			public void valueChange(ValueChangeEvent valueChangeEvent) {
				Filterable filterable = (Filterable) basicFilterTable.getContainerDataSource();
				
				if (dayFilter !=null) {
					filterable.removeContainerFilter(dayFilter);
				}
				
				Date filterDate = (Date)valueChangeEvent.getProperty().getValue();
				if (filterDate != null && filterDate instanceof Date) {
					dayFilter = new DayFilter(DATE_COLUMN_ID, (Date)valueChangeEvent.getProperty().getValue());
					filterable.addContainerFilter(dayFilter);
				}
			}
		});
		popupDateField.setImmediate(true);
	}

	/**
	 * 
	 * Creates a {@link Table} and adds data to it. {@link Property} values of type Date will be formatted according to {@link SimpleDate} "dd-MM-yyyy".
	 * 
	 */
	private void createTable() {
		basicFilterTable = new Table() {
			private static final long serialVersionUID = 1512633651106794832L;
			
			protected String formatPropertyValue(Object rowId, Object colId, Property property) {				
				if (property.getType() == Date.class) {
					DateFormat defaulDateFormat = new SimpleDateFormat("dd-MM-yyyy");
					return defaulDateFormat.format((Date)property.getValue());
				}
				return super.formatPropertyValue(rowId, colId, property);
			}
		};
		basicFilterTable.setContainerDataSource(createExampleData());
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
		indexedContainer.addContainerProperty(AGE_COLUMN_ID, String.class, "");
		indexedContainer.addContainerProperty(DATE_COLUMN_ID, Date.class, "");
		
		for (int i = 0; i < fieldNameData.length; i++) {
			Object itemId = indexedContainer.addItem();
			indexedContainer.getContainerProperty(itemId, NAME_COLUMN_ID).setValue(fieldNameData[i]);
			indexedContainer.getContainerProperty(itemId, AGE_COLUMN_ID).setValue(new Random().nextInt(100));
			indexedContainer.getContainerProperty(itemId, DATE_COLUMN_ID).setValue(new Date((new Random().nextInt(1000)*60*60*24*365*50 + new Date().getTime() - 1000*60*60*24*365*50))); // random Date between now + or - 50 years.
		}
		
		return indexedContainer;
	}

	/**
	 * 
	 * A {@link Date} filter as an example of an custom filter for filtering on {@link Date}.
	 * This filter takes as {@link Date} to be applied as filter on Date values.
	 * 
	 */
	class DayFilter implements Container.Filter {
		private static final long serialVersionUID = 8889706214772755564L;
		final private String propertyId;
		final private Date date;
		
		public DayFilter(String propertyId, Date date) {
			this.propertyId = propertyId;
			this.date = date;
		}
		
		public boolean appliesToProperty(Object propertyId) {
			return propertyId != null && propertyId.equals(this.propertyId);
		}
		
		public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
			Property property = item.getItemProperty(propertyId);

			// property should exist and have a type Date
			if (property == null || !property.getType().equals(Date.class)) {
				return false;
			}
			
			Date datePropertyValue = (Date) property.getValue();
			
			// usage of {@link DateUtils} to compare days
			return DateUtils.isSameDay(date, datePropertyValue);
		}
	}
}
