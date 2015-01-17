package com.taxe.game.commands;

/**
 * Created by Owen on 16/01/2015.
 */
public abstract class Commands {

    public static final ActivatePlayerCommand activatePlayerCommand = new ActivatePlayerCommand();
    public static final ContinuePathCommand continuePathCommand = new ContinuePathCommand();
    public static final DealDamageCommand dealDamageCommand = new DealDamageCommand();
    public static final EndMovementCommand endMovementCommand = new EndMovementCommand();
    public static final MoveTrainsCommand moveTrainsCommand = new MoveTrainsCommand();
    public static final ResetPathCommand resetPathCommand = new ResetPathCommand();
    public static final SavePathCommand savePathCommand = new SavePathCommand();
    public static final StartPathCommand startPathCommand = new StartPathCommand();
    public static final SwitchPlayerCommand switchPlayerCommand = new SwitchPlayerCommand();
    public static final UndoPathCommand undoPathCommand = new UndoPathCommand();

}
