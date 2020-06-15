package com.kwojewod.contra.screens;

import com.badlogic.gdx.Gdx;
import org.junit.Test;

import static org.junit.Assert.*;

public class TutorialScreenTest {

    @Test
    public void texturexists() {
        assertTrue("Mozna zaladowac tekstury", Gdx.files.internal("../core/assets/graphics/Menus_Texture/Tutorial_screen_active.png").exists());
        assertTrue("Mozna zaladowac tekstury", Gdx.files.internal("../core/assets/graphics/Menus_Texture/Tutorial_screen_inactive.png").exists());


    }
}