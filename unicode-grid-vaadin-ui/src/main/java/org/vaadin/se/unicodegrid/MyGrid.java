/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.se.unicodegrid;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Grid extension
 *
 * @author Sami Ekblad
 */
class MyGrid extends Grid {

    public MyGrid() {
        setWidth("720px");
        setSelectionMode(SelectionMode.NONE);
        setCellStyleGenerator(new CellStyleGenerator() {

            @Override
            public String getStyle(CellReference cellReference) {
                return (Integer) cellReference.getPropertyId() == 0
                        ? "idx"
                        : "code";
            }
        });
        setContainerDataSource(new UnicodeContainer(0xffff));
        getColumn(0).setRenderer(new HtmlRenderer(), new UnicodeToString(false));
        getColumn(0).setMinimumWidth(74);
        getColumn(0).setMaximumWidth(74);
        getColumn(0).setHeaderCaption("");
        for (int i = 1; i < 17; i++) {
            Column c = getColumn(i);
            c.setRenderer(new HtmlRenderer(), new UnicodeToString(true));
            c.setHeaderCaption("" + Integer.toHexString(i - 1).toUpperCase());
            c.setMinimumWidth(39);
            c.setMaximumWidth(39);
        }
    }

    private static class UnicodeContainer implements Container.Indexed {

        private final int size;
        //private final Collection<Integer> cols;
        private final List<Integer> cols;

        public UnicodeContainer(int size) {
            this.size = size;

            this.cols = new ArrayList<>(16);
            for (int i = 0; i < 17; i++) {
                this.cols.add(i);
            }
        }

        @Override
        public int indexOfId(Object itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getIdByIndex(int index) {
            return index;
        }

        @Override
        public List<?> getItemIds(int startIndex, int numberOfItems) {
            ArrayList<Integer> res = new ArrayList<>(numberOfItems);
            for (int i = startIndex; i < startIndex + numberOfItems; i++) {
                res.add(i);
            }
            return res;
        }

        @Override
        public Object addItemAt(int index) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Item addItemAt(int index, Object newItemId) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object nextItemId(Object itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object prevItemId(Object itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object firstItemId() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object lastItemId() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isFirstId(Object itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isLastId(Object itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object addItemAfter(Object previousItemId) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Item addItemAfter(Object previousItemId, Object newItemId) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Item getItem(Object itemId) {
            return new UnicodeRowItem((Integer) itemId);
        }

        @Override
        public Collection<?> getContainerPropertyIds() {
            return cols;
        }

        @Override
        public Collection<?> getItemIds() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Property getContainerProperty(Object itemId, Object propertyId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Class<?> getType(Object propertyId) {
            return Integer.class;
        }

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public boolean containsId(Object itemId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Item addItem(Object itemId) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object addItem() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean removeItem(Object itemId) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean removeAllItems() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class UnicodeRowItem implements Item {

        private final Integer row;

        public UnicodeRowItem(Integer row) {
            this.row = row;
        }

        @Override
        public Property getItemProperty(Object id) {
            return new UnicodeProperty(row, (Integer) id);
        }

        @Override
        public Collection<?> getItemPropertyIds() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean addItemProperty(Object id, Property property) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean removeItemProperty(Object id) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class UnicodeProperty implements Property {

        private final Integer value;

        public UnicodeProperty(Integer row, Integer col) {
            if (col == 0) {
                this.value = row * 16;
            } else {
                this.value = row * 16 + col - 1;
            }
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public void setValue(Object newValue) throws ReadOnlyException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Class getType() {
            return Integer.class;
        }

        @Override
        public boolean isReadOnly() {
            return true;
        }

        @Override
        public void setReadOnly(boolean newStatus) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class UnicodeToString implements Converter<String, Integer> {

        private final boolean htmlEntity;

        public UnicodeToString(boolean htmlEntity) {
            this.htmlEntity = htmlEntity;
        }

        @Override
        public Class getModelType() {
            return Integer.class;
        }

        @Override
        public Class getPresentationType() {
            return String.class;
        }

        @Override
        public Integer convertToModel(String value, Class<? extends Integer> targetType, Locale locale) throws ConversionException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String convertToPresentation(Integer value, Class<? extends String> targetType, Locale locale) throws ConversionException {
            if (htmlEntity) {
                return "&#x" + String.format("%04x", value).toUpperCase() + ";";
            } else {
                return String.format("%04x", value).toUpperCase();
            }
        }
    }

}
