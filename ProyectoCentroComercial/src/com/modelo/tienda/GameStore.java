package com.modelo.tienda;

/**
 *
 * @author emman
 */
public class GameStore extends Tienda
{

    private static GameStore instance;

    public static synchronized GameStore getInstance()
    {
        if (instance == null)
            instance = new GameStore();

        return instance;
    }

    private GameStore()
    {
        super("GameStore", "123GS");
    }

}
