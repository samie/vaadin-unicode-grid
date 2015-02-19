package org.vaadin.se.unicodegrid;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Unicode grid application.
 *
 */
@Theme("mytheme")
@Widgetset("org.vaadin.se.unicodegrid.MyAppWidgetset")
public class GridUI extends UI {

    final CssLayout layout = new CssLayout();
    final Grid grid = new MyGrid();
    final Panel panel = new Panel();
    VerticalLayout l = new VerticalLayout();

    Label uniLabel = new Label("Unicode: n/a");
    Label htmlLabel = new Label("HTML: n/a");
    Label hexLabel = new Label("Hex: n/a");
    Label decLabel = new Label("Decimal: n/a");
    Label charLabel = new Label("", ContentMode.HTML);
    Label nameLabel = new Label("”");

    @Override

    protected void init(VaadinRequest vaadinRequest) {
        setContent(layout);
        layout.setSizeFull();
        layout.addComponent(grid);
        layout.addComponent(panel);
        panel.setWidth("200px");
        panel.setHeight("400px");
        l.setMargin(true);
        l.setSpacing(true);
        l.addComponents(charLabel, nameLabel, uniLabel, hexLabel, decLabel, htmlLabel);
        panel.setContent(l);
        charLabel.setStyleName(ValoTheme.LABEL_HUGE);
        nameLabel.setStyleName(ValoTheme.LABEL_BOLD);

        grid.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                Integer val = (Integer) grid.getContainerDataSource().getItem(event.getItemId()).getItemProperty(event.getPropertyId()).getValue();
                if (val != null) {
                    showCharacterInfo(val);
                }
            }

        });

        showCharacterInfo(71);

    }

    private void showCharacterInfo(Integer val) {
        String hex = String.format("%04x", val).toUpperCase();
        charLabel.setValue("&#x" + hex + ";");
        nameLabel.setValue("" + Character.getName(val));
        uniLabel.setValue("Unicode: U+" + hex);
        hexLabel.setValue("Hex: 0x" + hex + "");
        decLabel.setValue("Decimal: " + val + "");
        htmlLabel.setValue("HTML: &#" + val + ";");
    }

}
