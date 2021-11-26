package util;

//import java.awt.*;
import java.awt.image.BufferedImage;
//import java.awt.image.ImageObserver;
//import java.awt.image.ImageProducer;
//import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageG {
    public BufferedImage image;
 

    public ImageG( String imageName){
        try {
            
			this.image = ImageIO.read(ImageG.class.getResource("/images/" + imageName));
		}
		catch(IOException exc) {
            exc.printStackTrace();
            this.image=null;
		}
    }


}
