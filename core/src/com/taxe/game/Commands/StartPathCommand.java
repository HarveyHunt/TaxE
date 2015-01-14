package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Util.Textures;
import com.taxe.game.Trains.Train;

/**
 * Created by vlad on 11/01/15.
 */
public class StartPathCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        if (!(target instanceof Train)) {
            throw new IllegalArgumentException("target must be an instance of Train");
        }
        new ResetPathCommand().executeCommand(game, target);
        Train t = (Train) target;
        t.setState(Textures.SELECTED);
        new ContinuePathCommand().executeCommand(game, t.getNode());
    }

}
