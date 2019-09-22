package Base64Boy;

import javafx.scene.image.Image;

import java.io.*;
import java.util.Base64;

public class Base64Decoder
{
    private String encoded;
    private FileOutputStream fos;
    private File outFile;
    private byte[] bytes;

    public Base64Decoder(String encodedData,String outputFilePath)
    {
        this.encoded = encodedData;
        this.outFile = new File(outputFilePath);
        initFileOutputStream(this.outFile);
    }

    public Base64Decoder(String encodedData)
    {
        this.encoded = encodedData;

        /**
         *  The path can be modified to suit your needs
         */
        File dir = new File("Base46BoyTemp");
        dir.mkdir();
        this.outFile = new File(dir,"Temp.b64b");

        initFileOutputStream(this.outFile);
    }

    public Base64Decoder(String encodedData,File outputFile)
    {
        this.encoded = encodedData;
        this.outFile = outputFile;
        initFileOutputStream(this.outFile);
    }

    public File getOutFile()
    {
        return this.outFile;
    }

    public Image getImage()
    {
        decodeData();
        Image image = new Image(new ByteArrayInputStream(bytes));
        return  image;
    }

    public byte[] decode()
    {
        decodeData();
        return bytes;
    }

    private void decodeData()
    {
        encoded = encoded.replaceAll("\\s","+"); // all the + are replaced with space, so I placed back all the + where they belong
        bytes = Base64.getDecoder().decode(encoded);

        try
        {
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    private void initFileOutputStream(File file)
    {
        try
        {
            fos = new FileOutputStream(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

}