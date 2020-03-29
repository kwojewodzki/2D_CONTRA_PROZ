package com.kwojewod.contra.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kwojewod.contra.Contra;

import java.awt.*;

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    private Integer lifeCounter;
    private float timeCount;
    private static Integer score;

    Label countdownLabel;
    private static Label scoreLabel;
    private Label livesLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label contraLabel;

    public Hud(SpriteBatch sb){
        lifeCounter = 3;
        timeCount = 0;
        score = 0;

        //
        viewport = new FitViewport(Contra.V_WIDTH, Contra.V_HIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //Initializing tables
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        //Setting labels
        countdownLabel = new Label(String.format("%03d", lifeCounter), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        contraLabel = new Label("POINTS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        //Adding labels to table
        table.add(contraLabel).expandX().padTop(5);
        table.add(worldLabel).expandX().padTop(5);
        table.add(livesLabel).expandX().padTop(5);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        //Adding tables to main scene
        stage.addActor(table);
    }
    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
