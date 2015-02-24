package com.myexperiment.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;
import java.nio.ByteBuffer;

public class ScreenshotFactory {
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();

    public void takeScreenshot() {
    	// width = 384, height = 262
        int width = 384;
        int height = 262;
        Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(0, 0, width, height);
        
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