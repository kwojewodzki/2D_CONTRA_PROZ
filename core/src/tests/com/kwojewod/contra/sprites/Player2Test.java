package com.kwojewod.contra.sprites;

import com.badlogic.gdx.Gdx;
import org.junit.Test;

import static org.junit.Assert.*;

public class Player2Test {

    @Test
    public void textureExists(){
        assertTrue("Mozna zaladowac tekstury bohatera", Gdx.files.internal("../core/assets/graphics/Sprites/Hero_and_Enemies.png").exists());
    }

}