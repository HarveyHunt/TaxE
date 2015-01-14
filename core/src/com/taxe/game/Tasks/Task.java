package com.taxe.game.Tasks;

/**
 * Created by Owen on 19/11/2014.
 */
public abstract class Task {
    private String name, objective;
    private int taskTime;

    public Task(String name, String objective, int taskTime) {
        this.name = name;
        this.objective = objective;
        this.taskTime = taskTime;
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
        return taskTime;
    }

    public void setTasktime(int tasktime) {
        this.taskTime = tasktime;
    }
}




