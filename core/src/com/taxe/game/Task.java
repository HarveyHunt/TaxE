package com.taxe.game;

/**
 * Created by Owen on 19/11/2014.
 */
public class Task {
    private String name, objective;
    private int tasktime;

    public Task(String name, String objective) {
        this.name = name;
        this.objective = objective;
        this.tasktime = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public int getTasktime() {
        return tasktime;
    }

    public void setTasktime(int tasktime) {
        this.tasktime = tasktime;
    }
}




