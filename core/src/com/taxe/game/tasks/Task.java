package com.taxe.game.tasks;

/**
 * Base-class representing tasks in the game.
 */
public abstract class Task {
    private final String name, objective;
    private int taskTime;

    /**
     * Creates an instance of Task with given name, objective and time during which it can be completed.
     * @param name name of a task.
     * @param objective objective of a task.
     * @param taskTime number of turns when tasks is available for completion.
     */
    public Task(String name, String objective, int taskTime) {
        this.name = name;
        this.objective = objective;
        this.taskTime = taskTime;
    }

    /**
     * Returns name of task.
     * @return name of task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns objective of a task.
     * @return objective of a task.
     */
    public String getObjective() {
        return objective;
    }

    /**
     * Returns remaining time when task can be completed.
     * @return number of turns when tasks is available for completion.
     */
    public int getTasktime() {
        return taskTime;
    }

    /**
     * Sets task time to a given duration
     * @param tasktime number of turns when task is available for completion.
     */
    public void setTasktime(int tasktime) {
        this.taskTime = tasktime;
    }
}




