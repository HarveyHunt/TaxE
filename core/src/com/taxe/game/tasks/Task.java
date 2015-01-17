package com.taxe.game.tasks;

/**
 * Top-level class for representing tasks. Different types of tasks are implemented by extending this class.
 * <p>
 * Each task has a name, objective and time when it can be completed. When the time runs out, task should not be
 * available for completion anymore.
 */
public abstract class Task {
    private final String name, objective;
    private int taskTime;

    /**
     * Creates an instance of Task with given name, objective and time during which it can be completed.
     *
     * @param name      name of a task.
     * @param objective objective of a task.
     * @param taskTime  time when tasks is available for completion.
     */
    public Task(String name, String objective, int taskTime) {
        this.name = name;
        this.objective = objective;
        this.taskTime = taskTime;
    }

    /**
     * Returns name of task.
     *
     * @return name of task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns objective of a task.
     *
     * @return objective of a task.
     */
    public String getObjective() {
        return objective;
    }

    /**
     * Returns remaining time when task can be completed.
     *
     * @return time when tasks is available for completion.
     */
    public int getTasktime() {
        return taskTime;
    }

    /**
     * Updates task time to a given duration
     *
     * @param taskTime new time
     */
    public void setTasktime(int taskTime) {
        this.taskTime = taskTime;
    }
}




