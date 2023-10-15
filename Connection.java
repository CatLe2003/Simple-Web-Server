package webserver;
import java.util.Arrays;

public class Connection extends Thread {
    WebSocket socket;
    public Connection(WebSocket s) {
        socket = s;
    }

    @Override
    public void run() {
        while (true) {
            HttpPackage request = socket.read();
            if (request == null)
                break;
            String[] typeline = request.getTypeLine();
            
            String contentType = Utils.getResponseDataType(request);
            HttpPackage res;
            StringBuilder builder = new StringBuilder();
            builder.append(socket.info());
            builder.append(" ");
            builder.append(Arrays.toString(typeline));
            System.out.println(builder.toString());

            if (typeline[0].equals("POST")) {
                String line = new String(request.content());
                String[] tokens = line.split("&");
                String uname = tokens[0].split("=")[1];
                String psw = tokens[1].split("=")[1];
                if (!uname.equals("admin") || !psw.equals("123456")) {
                    res = HttpPackage.unauthorize();
                    socket.send(res);
                    continue;
                }
            } else if (typeline[0].equals("GET")) {
                if (typeline[1].equals("/images.html")) {
                    res = HttpPackage.unauthorize();
                    socket.send(res);
                    continue;
                }
            }

            String fileName = typeline[1].substring(1);
            if (fileName.isEmpty())
                fileName = "index.html";
            fileName = "resources\\" + fileName;
            byte[] data = Utils.readAllFile(fileName);

            if (data == null)
                res = HttpPackage.fileNotFound();
            else
                res = HttpPackage.response(typeline[2], "200", "OK",
                        contentType, data);

            socket.send(res);
        }
        socket.close();
        System.out.println(socket.info() + " CLOSED");
    }

}
