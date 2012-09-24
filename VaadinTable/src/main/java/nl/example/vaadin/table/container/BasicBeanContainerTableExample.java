package nl.example.vaadin.table.container;

import java.util.Date;
import java.util.Random;

import nl.example.vaadin.pojo.SimpleBean;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

/**
 * 
 * This custom component uses a table and a bean container with three columns of different types as a data source.
 *
 */
public class BasicBeanContainerTableExample extends CustomComponent {
	private static final long serialVersionUID = 4075473560200820694L;
	
	private Table beanContainerTable; 

	public BasicBeanContainerTableExample() {
		beanContainerTable = new Table();
		beanContainerTable.setContainerDataSource(createExampleData());
		setCompositionRoot(beanContainerTable);
	}
	
	/**
	 * 
	 * Creates a {@link BeanContainer} with two columns "name", "age" as example data.
	 * 
	 */
	private static BeanContainer<String, SimpleBean> createExampleData() {

		String[] fieldNameData = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
				"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
                "Lisa", "Marge" };
		
		// a String as itemId
		BeanContainer<String, SimpleBean> beanContainer = new BeanContainer<String, SimpleBean>(SimpleBean.class);
		
		// use "name" property as itemId of {@link SimpleBean}
		beanContainer.setBeanIdProperty("name");
		
		for (int i = 0; i < fieldNameData.length; i++) {
			beanContainer.addBean(new SimpleBean(fieldNameData[i], new Random().nextInt(100), new Date()));
		}
		
		return beanContainer;
	}

}
