import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * Created by yangyu on 2017/2/13.
 */
public class TestA {

    private static HashMap hashMap = new HashMap();

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress);
    }

    private static <T> T get(String key){
        return (T) hashMap.get(key);
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream){
        int length;
        byte[] bytes = new byte[50];
        BufferedInputStream buffer = new BufferedInputStream(inputStream,50);
        try {
            while ((length=buffer.read(bytes))!=-1){
                outputStream.write(bytes,0,length);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                buffer.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
