package nl.example.vaadin.table.container;

import java.util.Date;
import java.util.Random;

import nl.example.vaadin.pojo.SimpleBean;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

/**
 * 
 * This custom component uses a table and a bean item container with three columns of different types as a data source.
 *
 */
public class BasicBeanItemContainerTableExample extends CustomComponent {
	private static final long serialVersionUID = 4075473560200820694L;

	Table beanItemContainerTable; 

	public BasicBeanItemContainerTableExample() {
		beanItemContainerTable = new Table();
		beanItemContainerTable.setContainerDataSource(createExampleData());
		setCompositionRoot(beanItemContainerTable);
	}
	
	/*
	 * Creates a {@link BeanItemContainer} with two columns "name", "age" as example data.
	 */
	private static BeanItemContainer<SimpleBean> createExampleData() {

		String[] fieldNameData = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
				"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
                "Lisa", "Marge" };
		
		BeanItemContainer<SimpleBean> beanItemContainer = new BeanItemContainer<SimpleBean>(SimpleBean.class);

		for (int i = 0; i < fieldNameData.length; i++) {
			beanItemContainer.addBean(new SimpleBean(fieldNameData[i], new Random().nextInt(100), new Date()));
		}
		
		return beanItemContainer;
	}
}
