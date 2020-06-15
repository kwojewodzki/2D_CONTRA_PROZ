package com.kwojewod.contra.screens;

import com.badlogic.gdx.Gdx;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainMenuTest {

    @Test
    public void textureExists(){
        assertTrue("Mozna zaladowac tekstury nieaktywnego przycisku singleplayer", Gdx.files.internal("../core/assets/graphics/Menus_Texture/1_player_active.png").exists());
        assertTrue("Mozna zaladowac tekstury aktywnego przycisku singleplayer", Gdx.files.internal("../core/assets/graphics/Menus_Texture/1_player_inactive.png").exists());
        assertTrue("Mozna zaladowac tekstury nieaktywnego przycisku multiplayer", Gdx.files.internal("../core/assets/graphics/Menus_Texture/2_players_inactive.png").exists());
        assertTrue("Mozna zaladowac tekstury aktywnego przycisku mulitplayer", Gdx.files.internal("../core/assets/graphics/Menus_Texture/2_players_active.png").exists());
        assertTrue("Mozna zaladowac tekstury niekatywnego przycisku wyjścia", Gdx.files.internal("../core/assets/graphics/Menus_Texture/exit_active.png").exists());
        assertTrue("Mozna zaladowac tekstury aktywnego przycisku wyjścia", Gdx.files.internal("../core/assets/graphics/Menus_Texture/exit_inactive.png").exists());
        assertTrue("Mozna zaladowac tekstury tła", Gdx.files.internal("../core/assets/graphics/Menus_Texture/Layout.png").exists());
        assertTrue("Mozna zaladowac tekstury nieaktywnego przycisku tutorial", Gdx.files.internal("../core/assets/graphics/Menus_Texture/tutorial_inactive.png").exists());
        assertTrue("Mozna zaladowac tekstury aktywnego przycisku tutorial", Gdx.files.internal("../core/assets/graphics/Menus_Texture/tutorial_active.png").exists());
        assertTrue("Mozna zaladowac tekstury tytułu gry", Gdx.files.internal("../core/assets/graphics/Menus_Texture/Name.png").exists());


    }

}