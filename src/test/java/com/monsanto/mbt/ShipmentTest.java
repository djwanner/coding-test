package com.monsanto.mbt;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShipmentTest {
    private List<Widget> widgets;
    private long widgetCount;
    private long shipmentCount;

    @Before
    public void setUp() {
        widgets = WidgetUtils.getSampleWidgets();
        widgetCount = widgets.size();
        shipmentCount = (long) Math.ceil((double) widgetCount / Shipment.MAX_SIZE);
    }

    @Test
    public void testShipment_Sorted_By_Color(){
        //Implement your solution to get list of shipments
        List<Widget> widgetsSortedByColor = widgets
                .stream()
                .sorted(Comparator.comparing(Widget::getColor))
                .collect(Collectors.toList());

        List<Shipment> shipments = new ArrayList<>();
        for (int i = 0; i < shipmentCount; i++) {
            Shipment shipment = new Shipment();
            while (!shipment.isFull() && widgetsSortedByColor.size() > 0) {
                Widget widget = widgetsSortedByColor.get(0);
                shipment.addWidget(widget);
                widgetsSortedByColor.remove(widget);
            }
            shipments.add(shipment);
        }
        assertThat(shipments.size(), is(3));

        Shipment firstShipment = shipments.get(0);
        Shipment secondShipment = shipments.get(1);
        Shipment thirdShipment = shipments.get(2);

        List<String> firstShipmentColorsActual = firstShipment.getWidgets().stream().map(Widget::getColor).collect(Collectors.toList());
        List<String> firstShipmentColorsExpected = Arrays.asList("Blue", "Blue", "Blue", "Blue",
                "Blue", "Blue", "Green", "Green", "Green", "Green");
        assertThat(firstShipmentColorsActual, is(firstShipmentColorsExpected));

        List<String> secondShipmentColorsActual = secondShipment.getWidgets().stream().map(Widget::getColor).collect(Collectors.toList());
        List<String> secondShipmentColorsExpected = Arrays.asList("Green", "Green", "Green", "Green",
                "Red", "Red", "Red", "Red", "Red", "Red");
        assertThat(secondShipmentColorsActual, is(secondShipmentColorsExpected));

        List<String> thirdShipmentColorsActual = thirdShipment.getWidgets().stream().map(Widget::getColor).collect(Collectors.toList());
        List<String> thirdShipmentColorsExpected = Arrays.asList("Red", "Red", "Red", "Red", "Red");
        assertThat(thirdShipmentColorsActual, is(thirdShipmentColorsExpected));
    }

    @Test
    public void testShipment_Sorted_By_Date(){
        //Implement your solution to get list of shipments
        List<Widget> widgetsSortedByDate = widgets
                .stream()
                .sorted(Comparator.comparing(Widget::getProductionDate))
                .collect(Collectors.toList());

        List<Shipment> shipments = new ArrayList<>();
        for (int i = 0; i < shipmentCount; i++) {
            Shipment shipment = new Shipment();
            while (!shipment.isFull() && widgetsSortedByDate.size() > 0) {
                Widget widget = widgetsSortedByDate.get(0);
                shipment.addWidget(widget);
                widgetsSortedByDate.remove(widget);
            }
            shipments.add(shipment);
        }
        assertThat(shipments.size(), is(3));

        Shipment firstShipment = shipments.get(0);
        Shipment secondShipment = shipments.get(1);
        Shipment thirdShipment = shipments.get(2);

        List<String> firstShipmentDatesActual = firstShipment.getWidgets().stream().map(Widget::getProductionDate).map(Object::toString).collect(Collectors.toList());
        List<String> firstShipmentDatesExpected = Arrays.asList("Thu Sep 01 00:00:00 CDT 2005", "Thu Sep 01 00:00:00 CDT 2005",
                "Sun Jan 01 00:00:00 CST 2006", "Thu Jan 12 00:00:00 CST 2006", "Thu Mar 09 00:00:00 CST 2006",
                "Tue Mar 14 00:00:00 CST 2006", "Sat Feb 03 00:00:00 CST 2007", "Tue Feb 13 00:00:00 CST 2007",
                "Tue Jul 01 00:00:00 CDT 2008", "Tue Jul 01 00:00:00 CDT 2008");
        assertThat(firstShipmentDatesActual,  is(firstShipmentDatesExpected));

        List<String> secondShipmentDatesActual = secondShipment.getWidgets().stream().map(Widget::getProductionDate).map(Object::toString).collect(Collectors.toList());
        List<String> secondShipmentDatesExpected = Arrays.asList("Wed Apr 01 00:00:00 CDT 2009", "Tue Apr 07 00:00:00 CDT 2009",
                "Wed Apr 15 00:00:00 CDT 2009", "Sun Aug 02 00:00:00 CDT 2009", "Sat Aug 08 00:00:00 CDT 2009",
                "Sun May 01 00:00:00 CDT 2011", "Sat May 07 00:00:00 CDT 2011", "Mon May 16 00:00:00 CDT 2011",
                "Thu Mar 01 00:00:00 CST 2012", "Thu Jan 03 00:00:00 CST 2013");
        assertThat(secondShipmentDatesActual, is(secondShipmentDatesExpected));

        List<String> thirdShipmentDatesActual = thirdShipment.getWidgets().stream().map(Widget::getProductionDate).map(Object::toString).collect(Collectors.toList());
        List<String> thirdShipmentDatesExpected = Arrays.asList("Tue Apr 01 00:00:00 CDT 2014", "Sun Feb 01 00:00:00 CST 2015",
                "Wed Jun 01 00:00:00 CDT 2016", "Thu Jun 02 00:00:00 CDT 2016", "Thu Jan 05 00:00:00 CST 2017");
        assertThat(thirdShipmentDatesActual, is(thirdShipmentDatesExpected));

    }
}
