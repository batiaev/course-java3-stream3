package com.batiaev.java3.lesson5.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Race {
    private ArrayList<Stage> stages;
    private long startTime;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public long getTime() {
        return new Date().getTime() - getStartTime();
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public long getStartTime() {
        return startTime;
    }

    public void initStartTime() {
        this.startTime = new Date().getTime();
    }
}
