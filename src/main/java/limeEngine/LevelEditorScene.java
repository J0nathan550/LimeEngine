package limeEngine;

import org.lwjgl.system.windows.WindowProcI;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {

    private boolean changingScene = false;
    private float timeToChangeScene = 1.0f;

    public LevelEditorScene(){
        Window.get().r = 1;
        Window.get().g = 1;
        Window.get().b = 1;
        Window.get().a = 1;
        System.out.println("Inside of Level Editor Scene!");
    }

    @Override
    public void update(float dt) {
        System.out.println("FPS:" + (1.0f/dt));
        if(!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
            changingScene = true;
        }
        if (changingScene && timeToChangeScene > 0){
            timeToChangeScene -= dt;
            Window.get().r -= 1.0f * dt;
            Window.get().g -= 2.0f * dt;
            Window.get().b -= 3.0f * dt;
            Window.get().a -= 5.0f * dt;
        } else if (changingScene) {
            Window.get().r += 1.0f * dt;
            Window.get().g += 2.0f * dt;
            Window.get().b += 3.0f * dt;
            Window.get().a += 5.0f * dt;
            Window.changeScene(1);
        }
    }


}