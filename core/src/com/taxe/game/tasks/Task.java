package com.taxe.game.tasks;

import com.taxe.game.cargo.Cargo;
import com.taxe.game.nodes.City;
import com.taxe.game.player.Player;
import com.taxe.game.trains.Train;
import com.taxe.Main;

/**
 * Top-level class for representing tasks. Different types of tasks are implemented by extending this class.
 * <p>
 * Each task has a name, objective and time when it can be completed. When the time runs out, task should not be
 * available for completion anymore.
 *
 * A task is available to all players.
 */
public class Task {
    private City endCity;
    private Cargo cargo;
    private int taskTime;

    /**
     * Creates an instance of Task with given name, objective and time during which it can be completed.
     *
     * @param endCity   The ending city of the task
     * @param cargo The type of cargo to be delivered
     * @param taskTime  time when tasks is available for completion
     */

    public Task(City endCity, Cargo cargo, int taskTime) {
        this.endCity = endCity;
        this.cargo = cargo;
        this.taskTime = taskTime;
    }

    /**
     * Returns end city of the task.
     *
     * @return end city of task.
     */
    public City getEndCity() {
        return endCity;
    }

    /**
     * Returns the type of cargo to be transported.
     *
     * @return type of cargo to be transported.
     */
    public Cargo getCargo() {
        return cargo;
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

    /**
     * Checks whether the goal has been completed
     * @param player The player that may have completed the goal.
     * @return Boolean value according to whether the task has been completed.
     */
    public boolean isComplete(Player player) {
        for (Train train : player.getTrains()) {
            if (train.getNode() == this.getEndCity()) {
                if (train.getCargo() == this.getCargo()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}