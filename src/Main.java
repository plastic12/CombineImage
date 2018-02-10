import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws IOException 
    {
    	String input;
    	BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Input number of image to combine");
    	int size=Integer.parseInt(stdin.readLine());
    	BufferedImage[] image=new BufferedImage[size];
    	for(int i=0;i<size;i++)
    	{
    		System.out.println("Input the path of image");
    		input=stdin.readLine();
    		image[i] = ImageIO.read(new File(input));
    		System.out.println("Image has been loaded");
    	}
    	int row,column;
    	do{
    		System.out.println("Input the row of the combined item");
    		row=Integer.parseInt(stdin.readLine());
    		System.out.println("Input the column of the combined item");
    		column=Integer.parseInt(stdin.readLine());
    		if(row*column==size)
    			break;
    		System.out.println("The row,column is not matched to the size");
    	}while(true);
        int chunkWidth = image[0].getWidth();
        int chunkHeight = image[0].getHeight();
        for(int i=0;i<size;i++)
        {
        	if(image[i].getWidth()!=chunkWidth)
        	{
        		System.out.println("Make sure images all have same width");
        		return;
        	}
        	if(image[i].getHeight()!=chunkHeight)
        	{
        		System.out.println("Make sure images all have same height");
        		return;
        	}
        }
        BufferedImage output=new BufferedImage(chunkWidth*column,chunkHeight*row,image[0].getType());
        Graphics2D gr =output.createGraphics();
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                //Initialize the image array with image chunks

                // draws the image chunk
                gr.drawImage(image[x*column+y], chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth,  chunkHeight * x + chunkHeight,0 ,0 ,chunkWidth ,chunkHeight, null);
            }
        }
        gr.dispose();
        System.out.println("Combining done");

        //writing mini images into image files
        ImageIO.write(output, "png", new File("output/output.png"));
    }
}
