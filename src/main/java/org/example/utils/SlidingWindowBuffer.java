package org.example.utils;

import org.example.simulator.Buffer;
import org.example.simulator.Measurement;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowBuffer implements Buffer {
    List<Measurement> buffer;
    public final static int SW_SIZE = 12;
    public final static int SW_OVERLAPPING = SW_SIZE / 2;

    public SlidingWindowBuffer() {
        buffer = new ArrayList<Measurement>();
    }

    @Override
    public synchronized void addMeasurement(Measurement m) {
        buffer.add(m);
        notify();
    }

    public synchronized Measurement getLocalStatistic() {
        // wait for SW_SIZE measurements
        while (buffer.size() < SW_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // calc the average value of the first SW_SIZE measurements
        double sum = 0.0;
        for (int i = 0; i < SW_SIZE; i++) {
            sum += buffer.get(i).getValue();
        }
        double localStatisticValue = sum / SW_SIZE;
        Measurement localStatistic = new Measurement(
                buffer.get(0).getId(),
                buffer.get(0).getType(),
                localStatisticValue,
                buffer.get(0).getTimestamp()
        );

        // remove the first SW_OVERLAPPING measurements
        for (int i = 0; i < SW_OVERLAPPING; i++) { buffer.remove(0); }

        return localStatistic;
    }
}
