package limeEngine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance;
    private boolean keyPressed[] = new boolean[350];

    private KeyListener(){

    }

    public static KeyListener get(){
        if(instance == null){
            KeyListener.instance = new KeyListener();
        }
        return KeyListener.instance;
    }

    public static void keyCallBack(long window, int key, int scancode, int action, int mods){
        if (action == GLFW_PRESS){
            get().keyPressed[key] = true;
        } else if(action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }
    public static boolean isKeyPressed(int key){
            return get().keyPressed[key];
    }
}