## Introduction

Vaadin is a Java Web Application Framework based on GWT (Google Web Toolkit) for building Rich Internet Applications.

This repository provides examples of certain component/techniques used in Vaadin.
All projects contain `gradle.build` files to setup separate eclipse web application projects.

Note: This page is under construction which means that it may change regularly.

Examples use 
- Java 1.6
- Vaadin 6.8.2
- Gradle 1.0
- Git 1.7.9.mysysgit.0 
- Eclipse Indigo SR2 JEE (64bit)
- Windows 7 (64bit)

## Examples

### MinimalVaadinApplication

This is `hello world` eclipse gradle setup. To create an eclipse web application project out of this project, run :

`gradle clean cleanEclipse eclipse`

To build and create a war file, run :

`gradle build`

Now you can `import` the project into eclipse by :

`import > general > Existing Projects into workspace`

For all the other vaadin examples use the same procedure.

### VaadingFilteringTable

Still an empty shell (later in progress).

### VaadinTable

Example for usage of the Vaadin `Table` component. 

All examples take a single concept to work out in a custom component. Code of these components are as much decoupled working instances for educational purposes. All custom components are attached to the root application to be show on startup.

Different Containers

- `BasicBeanContainerTableExample` : Uses a BeanContainer as data source.
- `BasicBeanItemContainerTableExample` : Uses a BeanItemContainer as data source.
- `BasicBeanItemContainerTableExample` : Uses a BeanItemContainer as data source.
- `BasicFilesystemContainerTableExample` : Uses a FilesystemContainer as data source.
- `BasicIndexedContainerTableExample` : Uses a IndexedContainer as data source.

Basic usage of a Filter

- `BasicSimpleStringFilterTableExample` : A Textfield to filter a table (on one column) while typing based on the entered String.
- `BasicDayFilterTableExample` : A PopupDateField to filter a table (on one column) on day while changing the date field value by means of the date picker popup (selecting a day from a calendar).
- `DoubleSimpleStringFilterTableExample` : Two Textfields to filter a table (on two columns) while typing those two Textfields.
- `BasicCustomFilterTableExample` : A Custom filter example resulting in a Textfield accepting a Regular Expression to filter a table (on one column) while typing.
- `BasicLikeFilterTableExample` : A Textfield with a Like filter to filter a table (on one column) while typing a String or a wildcard of type "%".
- `BasicGreaterAndLessFilterTableExample` : A Textfield with a Greater filter to filter a table (on one column) on an (column with an) integer value greater than the entered value. When no integer provided use a Less filter to empty the table. 
- `BasicBetweenFilterTableExample` : Two Textfields to filter a table (on one columns) using a Between filter to filter between two values while typing into the first Textfield. The second Textfield determines the second value in the range, but is unresponsive to typing as input for the filter.
- `BasicComboBoxFilterTableExample` : A NativeSelect (combobox) to filter a table (on one column) based on unique values in a column. An extra 'Show All' items, removes all filters.
- `GridColumnFiltersTableExample` : A NativeSelect (combobox) and two Textfields in a grid above the table to filter a table (on one column).


