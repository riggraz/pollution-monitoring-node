package org.example;

import org.example.simulator.Measurement;
import org.example.simulator.PM10Simulator;
import org.example.simulator.Simulator;
import org.example.utils.SlidingWindowBuffer;

import java.util.ArrayList;
import java.util.List;

public class SensorThread implements Runnable {
    SlidingWindowBuffer buffer;
    Simulator simulator;
    List<Measurement> localStatistics;

    public SensorThread() {
        buffer = new SlidingWindowBuffer();
        simulator = new PM10Simulator(buffer);
        localStatistics = new ArrayList<Measurement>();

        simulator.start();
    }

    @Override
    public void run() {
        while (true) {
            localStatistics.add(buffer.getLocalStatistic());
            System.out.println("New local statistic: " + localStatistics);
        }
    }
}
