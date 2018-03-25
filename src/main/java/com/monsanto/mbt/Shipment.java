package com.monsanto.mbt;

import java.util.ArrayList;
import java.util.List;

public class Shipment {
    public static final int MAX_SIZE = 10;
    private List<Widget> widgets = new ArrayList<>();

    public void addWidget(Widget widget) {
        if (isFull()) {
            throw new RuntimeException("Shipment cannot contain more than " + MAX_SIZE + " widgets");
        } else {
            widgets.add(widget);
        }
    }

    public boolean isFull() {
        if (widgets.size() == MAX_SIZE) {
            return true;
        } else {
            return false;
        }
    }

    public List<Widget> getWidgets() {
        return this.widgets;
    }

}
