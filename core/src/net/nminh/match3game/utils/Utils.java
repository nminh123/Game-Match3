package net.nminh.match3game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Utils
{
    public static FileHandle getInternalPath(String filepath){return Gdx.files.internal(filepath);}
}