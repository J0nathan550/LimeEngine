package limeEngine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width, height;
    private String title;
    private long glfwWindow;

    private float r,g,b,a;
    public static Window window = null;

    private Window(){
        this.width = 1920;
        this.height = 1080;
        this.title = "LimeEngine";
        r = 1;
        g = 1;
        b = 1;
        a = 1;
    }
    public static Window get(){
        if(Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    public void run(){
        System.out.println("Hello LWJGL" + Version.getVersion() + "!");
        init();
        loop();

        //Free memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        //Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    public void init(){
        //Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

        //Init GLFW
        if(!glfwInit()){
            throw new IllegalStateException("Unable to Initialize GLFW");
        }

        //Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //Create Window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if(glfwWindow == NULL){
            throw new IllegalStateException("Unable to create GLFW window!");
        }

        //Configure Mouse
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallBack);

        //Configure Keyboard
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallBack);

        //Make the OpenGL current context
        glfwMakeContextCurrent(glfwWindow);

        //Enabling V-Sync
        glfwSwapInterval(1);

        //Window Visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

    }
    private boolean fadeToBlack;
    public void loop(){
        while(!glfwWindowShouldClose(glfwWindow)){
            //Poll Events
            glfwPollEvents();

            glClearColor(r,g,b,a);
            glClear(GL_COLOR_BUFFER_BIT);

            if(KeyListener.isKeyPressed(GLFW_KEY_SPACE) || MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_1)){
                if(fadeToBlack == false){
                    fadeToBlack = true;
                }
                else{
                    fadeToBlack = false;
                }
            }

            if (fadeToBlack){
                r = Math.max(r - 0.01f, 0);
                g = Math.max(r - 0.01f, 0);
                b = Math.max(r - 0.01f, 0);
                a = Math.max(r - 0.01f, 0);
            } else {
                r = Math.max(r + 0.01f, 0);
                g = Math.max(r + 0.01f, 0);
                b = Math.max(r + 0.01f, 0);
                a = Math.max(r + 0.01f, 0);
            }

            glfwSwapBuffers(glfwWindow);
        }
    }

}