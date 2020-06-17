package org.example;

import org.example.beans.LocalStatisticList;
import org.example.simulator.PM10Simulator;
import org.example.simulator.Simulator;
import org.example.utils.SlidingWindowBuffer;

public class SensorThread implements Runnable {
    SlidingWindowBuffer buffer;
    Simulator simulator;

    public SensorThread() {
        buffer = new SlidingWindowBuffer();
        simulator = new PM10Simulator(buffer);

        simulator.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                LocalStatisticList.getInstance().addMeasurement(buffer.getLocalStatistic());
                System.out.println("New local statistic (" + LocalStatisticList.getInstance().getList().size() + " in buffer)");
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("Shutting down sensor thread...");

        simulator.stopMeGently();
    }
}
