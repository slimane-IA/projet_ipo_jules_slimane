package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;

import javax.imageio.ImageIO;

public class ImageG {
    private BufferedImage image = null;


    public ImageG( String imageName){
        try {
			this.image = ImageIO.read(new File("/images/"+imageName));
		}
		catch(IOException exc) {
            exc.printStackTrace();
		}
    }

    public BufferedImage getImage() {
        return image;
    }

}
