package com.myexperiment.game;


import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenshotFactory {
	
  

    public void takeScreenshot() {
    	
    	int mousePositionX = Gdx.input.getX();
    	int mousePositionY = Gdx.graphics.getHeight() - Gdx.input.getY();
    	
    	int cameraLeftEdge = mousePositionX -192;
    	int cameraBottomEdge = mousePositionY - 141;
    	// width = 384, height = 262
        int width = 384;
        int height = 262;
        
        
        
        Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(cameraLeftEdge, cameraBottomEdge, width, height);
        
        /* Reverse */
        ByteBuffer pixels = pixmap.getPixels();
        int amountOfBytes = width * height * 4;
        byte[] lines = new byte[amountOfBytes];
        int amountOfBytesPerLine = width * 4;
        for (int i = 0; i < height; i++) {
            pixels.position((height - i - 1) * amountOfBytesPerLine);
            pixels.get(lines, i * amountOfBytesPerLine, amountOfBytesPerLine);
        }
        pixels.clear();
        pixels.put(lines);

        FileHandle fileHandle;
        int index = 0;
        do {
            fileHandle = Gdx.files.absolute("/home/evan/Pictures/screen" + index++ + ".png");
        } while(fileHandle.exists());
        PixmapIO.writePNG(fileHandle, pixmap);

        pixmap.dispose();

    }
}