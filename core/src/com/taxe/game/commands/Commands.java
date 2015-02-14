package com.taxe.game.commands;

/**
 * Describes all commands used in the game.
 */
public abstract class Commands {

    public static final ActivatePlayerCommand activatePlayerCommand = new ActivatePlayerCommand();
    public static final ContinuePathCommand continuePathCommand = new ContinuePathCommand();
    public static final DealDamageCommand dealDamageCommand = new DealDamageCommand();
    public static final EndGameCommand endGameCommand = new EndGameCommand();
    public static final EndMovementCommand endMovementCommand = new EndMovementCommand();
    public static final ExitToMenuCommand exitToMenuCommand = new ExitToMenuCommand();
    public static final LockCityCommand lockCityCommand = new LockCityCommand();
    public static final MoveTrainsCommand moveTrainsCommand = new MoveTrainsCommand();
    public static final ResetPathCommand resetPathCommand = new ResetPathCommand();
    public static final SavePathCommand savePathCommand = new SavePathCommand();
    public static final StartPathCommand startPathCommand = new StartPathCommand();
    public static final SwitchPlayerCommand switchPlayerCommand = new SwitchPlayerCommand();
    public static final UndoPathCommand undoPathCommand = new UndoPathCommand();
    public static final UnlockCityCommand unlockCityCommand = new UnlockCityCommand();
    public static final LoadCargoCommand loadCargoCommand = new LoadCargoCommand();
    public static final UnloadCargoCommand unloadCargoCommand = new UnloadCargoCommand();
}
