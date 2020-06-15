package com.kwojewod.contra;

import com.badlogic.gdx.Gdx;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContraTest {

    @Test
    public void create() {
        assertTrue("Muzyka z 1lvl mozliwa do wczytania", Gdx.files.internal("../core/assets/audio/music/lvl1.mp3").exists());
        assertTrue("Muzyka menu mozliwa do wczytania", Gdx.files.internal("../core/assets/audio/music/main_menu.mp3").exists());
        assertTrue("Muzyka konca gry mozliwa do wczytana", Gdx.files.internal("../core/assets/audio/music/game-over.mp3").exists());

    }

    @Test
    public void render() {
    }
}