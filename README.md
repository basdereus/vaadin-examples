## Introduction

Vaadin is a Java Web Application Framework based on GWT (Google Web Toolkit) for building Rich Internet Applications.

This repository provides examples of certain component/techniques used in Vaadin.
All projects contain `gradle.build` files to setup separate eclipse web application projects.

Examples use Vaadin 6.8.2 and Java 1.6

## Examples

### MinimalVaadinApplication

This is `hello world` eclipse gradle setup. To create an eclipse web application project out of this project, run :

`gradle clean cleanEclipse eclipse`

To build and create a war file, run :

`gradle build`

Now `import` the project into eclipse by :

`import > general > Existing Projects into workspace`

For all the other vaadin example use the same procedure

### VaadingFilteringTable

Still an empty shell (later in progress).

### VaadinTable

Example for usage of the Vaadin `Table` component. 

All example are made in a custom component.

Different Containers

- `BasicBeanContainerTableExample` : Uses a BeanContainer as data source.
- `BasicBeanItemContainerTableExample` : Uses a BeanItemContainer as data source.
- `BasicBeanItemContainerTableExample` : Uses a BeanItemContainer as data source.
- `BasicFilesystemContainerTableExample` : Uses a FilesystemContainer as data source.
- `BasicIndexedContainerTableExample` : Uses a IndexedContainer as data source.

Basic usage of a Filter

- `BasicFilterTableExample` : A Textfield to filter a table (on one column) while typing.

