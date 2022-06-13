package limeEngine;

import java.awt.event.KeyEvent;

public class LevelScene extends Scene{
    private boolean changingScene = false;
    private float timeToChangeScene = 1.0f;

    public LevelScene(){
        Window.get().r = 0;
        Window.get().g = 0;
        Window.get().b = 0;
        Window.get().a = 0;
        System.out.println("Inside of LevelScene!");
    }

    @Override
    public void update(float dt) {
        System.out.println("FPS:" + (1.0f/dt));
        if(!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
            changingScene = true;
        }
        if (changingScene && timeToChangeScene > 0){
            timeToChangeScene -= dt;
            Window.get().r += 1.0f * dt;
            Window.get().g += 2.0f * dt;
            Window.get().b += 3.0f * dt;
            Window.get().a += 5.0f * dt;
        } else if (changingScene) {
            Window.changeScene(0);
        }
    }


}