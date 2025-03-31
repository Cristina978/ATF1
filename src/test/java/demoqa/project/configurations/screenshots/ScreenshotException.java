package demoqa.project.configurations.screenshots;

import java.io.IOException;

public class ScreenshotException extends Exception {
    public ScreenshotException(String message, IOException e) {
        super(message, e);     //keeps the original exception IOException
    }
}
