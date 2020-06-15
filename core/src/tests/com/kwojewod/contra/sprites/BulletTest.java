package com.kwojewod.contra.sprites;

import com.badlogic.gdx.Gdx;
import org.junit.Test;

import static org.junit.Assert.*;

public class BulletTest {
    @Test
    public void textureExists(){
        assertTrue("Mozna zaladowac tekstury pocisku", Gdx.files.internal("../core/assets/graphics/Sprites/Bullet.png").exists());
    }

}