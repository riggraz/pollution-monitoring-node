package org.example.beans;

import org.example.simulator.Measurement;

import java.util.ArrayList;
import java.util.List;

public class LocalStatisticList {
    private List<Measurement> list;
    private static LocalStatisticList instance;

    private LocalStatisticList() {
        list = new ArrayList<Measurement>();
    }

    public synchronized static LocalStatisticList getInstance() {
        if (instance == null) instance = new LocalStatisticList();
        return instance;
    }

    public synchronized List<Measurement> getList() {
        return new ArrayList<Measurement>(list);
    }

    public synchronized void addMeasurement(Measurement newMeasurement) {
        list.add(newMeasurement);
    }

    public synchronized void deleteOldestMeasurement() {
        list.remove(0);
    }
}
