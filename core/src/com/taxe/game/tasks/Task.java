package com.taxe.game.tasks;

import com.taxe.game.cargo.Cargo;
import com.taxe.game.nodes.City;
import com.taxe.game.GameCore;
import com.taxe.game.player.Player;
import com.taxe.game.trains.Train;
import com.taxe.Main;

import static com.taxe.Main.*;

/**
 * Top-level class for representing tasks. Different types of tasks are implemented by extending this class.
 * <p>
 * Each task has a name, objective and time when it can be completed. When the time runs out, task should not be
 * available for completion anymore.
 */
public abstract class Task {
    private final String name, objective;
    private City endCity;
    private Cargo cargoType;
    private int taskTime;


    /**
     * Creates an instance of Task with given name, objective and time during which it can be completed.
     *
     * @param name      Name of a task.
     * @param objective String describing task
     * @param endCity   The ending city of the task
     * @param cargoType The type of cargo to be delivered
     * @param taskTime  time when tasks is available for completion
     */

    public Task(String name, String objective, City endCity, Cargo cargoType, int taskTime) {
        this.name = name;
        this.objective = objective;
        this.endCity = endCity;
        this.cargoType = cargoType;
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
     * Returns end city of the task.
     *
     * @return end city of task.
     */
    public City getEndCity() { return endCity; }

    /**
     * Returns the type of cargo to be transported.
     *
     * @return type of cargo to be transported.
     */
    public Cargo getCargoType() { return cargoType; }

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
     *
     * @return Boolean value according to whether the task has been completed.
     */
    public boolean isComplete() {
        for (Player player: Main.getGameCore().getPlayers()) {
            for (Train train : player.getTrains()) {
                if (train.getNode() == this.getEndCity()) {
                    return train.getCargo() == this.getCargoType();
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}




