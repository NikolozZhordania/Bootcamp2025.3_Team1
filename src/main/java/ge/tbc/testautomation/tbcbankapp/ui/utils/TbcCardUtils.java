package ge.tbc.testautomation.tbcbankapp.ui.utils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.microsoft.playwright.Locator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class TbcCardUtils {

    public static String decodeQRCode(byte[] imageBytes) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(bufferedImage)));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }

    public static String readQRCode(Locator qrCanvas) throws Exception {
        return decodeQRCode(qrCanvas.screenshot());
    }

}