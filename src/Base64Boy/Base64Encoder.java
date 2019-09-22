package Base64Boy;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class Base64Encoder
{
    private byte[] bytes; // its a large value because who knows how large a file can be
    private FileInputStream fis;
    private File file;

    public Base64Encoder(File file)
    {
        this.file = file;
        bytes = new byte[(int)file.length()];
        initInputStream(this.file);
    }

    // Path Location of the file
    public Base64Encoder(String path)
    {
        this.file = new File(path);
        bytes = new byte[(int)file.length()];
        initInputStream(this.file);
    }

    private String encodeFile()
    {
        try {
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(bytes);
    }

    public String getEncodedString()
    {
        return encodeFile();
    }

    private void initInputStream(File file)
    {
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}