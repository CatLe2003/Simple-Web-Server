package webserver;
import java.util.HashMap;
import java.util.Map;

public class HttpPackage {
    private static final String endLine = "\r\n";
    private String[] _typeLine;
    private Map<String, String> _headers;
    private byte[] _content;

    public static HttpPackage response(
            String version, String code, String text,
            String contentType, byte[] content) {

        HttpPackage response = new HttpPackage();
        response.setTypeLine(version, code, text);
        if (content != null)
            response.addHeader("Content-Length", String.valueOf(content.length));
        response.addHeader("Content-Type", contentType);
        response.addHeader("Connection", "keep-alive");
        response.setContent(content);
        return response;
    }


    public static HttpPackage unauthorize() {
        return response("HTTP/1.1", "401", "Unauthorized", "text/html",
                "<!DOCTYPE html>\n<h1>401 Unauthorized</h1><p>This is a private area.</p>".getBytes());
    }

    public static HttpPackage fileNotFound() {
        return response("HTTP/1.1", "404", "Not Found", "text/html",
                "<!DOCTYPE html>\n<html>\n<head>\n<title> 404 Not Found </title>\n</head>\n<body>\n<p> The requested file cannot\nbe found. </p>\n</body>\n</html>"
                        .getBytes());

    }

    public String[] getTypeLine() {
        return _typeLine;
    }

    public byte[] content() {
        return _content;
    }

    public void setTypeLine(String a, String b, String c) {
        _typeLine[0] = a;
        _typeLine[1] = b;
        _typeLine[2] = c;
    }

    public void addHeader(String header, String value) {
        _headers.put(header, value);
    }

    public void setContent(byte[] content) {
        _content = content;
    }

    public String get(String header) {
        return _headers.get(header);
    }

    public byte[] build() {
        StringBuilder builder = new StringBuilder();
        builder.append(_typeLine[0]);
        builder.append(" ");
        builder.append(_typeLine[1]);
        builder.append(" ");
        builder.append(_typeLine[2]);
        builder.append(endLine);
        for (String header : _headers.keySet()) {
            builder.append(header);
            builder.append(": ");
            builder.append(_headers.get(header));
            builder.append(endLine);
        }
        builder.append(endLine);
        byte[] headers = builder.toString().getBytes();
        byte[] result = new byte[headers.length + _content.length];
        System.arraycopy(headers, 0, result, 0, headers.length);
        System.arraycopy(_content, 0, result, headers.length, _content.length);
        return result;
    }

    public HttpPackage() {
        _typeLine = new String[3];
        _headers = new HashMap<String, String>();
    }

}