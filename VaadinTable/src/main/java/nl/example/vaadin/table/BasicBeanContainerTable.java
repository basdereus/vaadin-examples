package nl.example.vaadin.table;

import java.util.Date;
import java.util.Random;

import nl.example.vaadin.pojo.SimpleBean;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;

/**
 * 
 * This plain table extends the {@link Table} and uses a bean container with three columns of different types as a data source.
 *
 */
public class BasicBeanContainerTable extends Table {
	private static final long serialVersionUID = 4075473560200820694L;

	public BasicBeanContainerTable() {
		setContainerDataSource(createExampleData());
	}
	
	/*
	 * Creates a {@link BeanContainer} with two columns "name", "age" as example data.
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
