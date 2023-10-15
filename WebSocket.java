package webserver;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class WebSocket {
    private Socket socket;
    private BufferedReader datain;
    private DataOutputStream dataout;

    public String info() {
        return socket.toString();
    }

    WebSocket(Socket _socket) {
        socket = _socket;
        try {
            // socket.setSoTimeout(1000);
            datain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataout = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void send(HttpPackage httpPackage) {
        try {
            dataout.write(httpPackage.build());
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    public HttpPackage read() {
        HttpPackage httpPackage = new HttpPackage();
        String line = null;
        try {
            line = datain.readLine();
            if (line == null)
                return null;
            String[] startLine = line.split(" ");
            httpPackage.setTypeLine(startLine[0], startLine[1], startLine[2]);

            while (true) {
                line = datain.readLine();
                if (line.isEmpty())
                    break;
                int seperator = line.indexOf(": ");
                String header = line.substring(0, seperator);
                String value = line.substring(seperator + 2);
                httpPackage.addHeader(header, value);
            }
            
            String contentLength = httpPackage.get("Content-Length");
            if (contentLength != null) {
                int length = Integer.parseInt(contentLength);
                char[] content = new char[length];
                datain.read(content);
                String temp = new String(content);
                httpPackage.setContent(temp.getBytes());
            }
        } catch (IOException e) {
            return null;
        }

        return httpPackage;
    }

    public void close() {
        try {
            dataout.close();
            datain.close();
            socket.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

}
