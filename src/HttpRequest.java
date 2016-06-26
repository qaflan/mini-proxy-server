/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.StringTokenizer;

/**
 *
 * @author Cavad
 */
public class HttpRequest {

    enum Methods {

        GET, PUT, POST, CONNECT;
    };

    enum HTTPVersions {

        HTTP_1, HTTP_1_1;
    }

    enum ProxyConnectionTypes {

        KEEP_ALIVE, OTHER;
    }
    private String requestString = "";
    private String header="";
    private String acceptLanguage = "";
    private String defaultString = "";
    private Methods method;
    private String objectAddress = "";
    private String connectionType = "";
    private String acceptPart = "";
    HTTPVersions httpVersion;
    private String userAgent = "";
    private String acceptEncoding = "";
    private ProxyConnectionTypes proxyConnectionType;
    private String host = "";
    private String cookie = "";

    boolean getKeepAlive() {
        return proxyConnectionType == ProxyConnectionTypes.KEEP_ALIVE;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public HttpRequest(String requestString) {
        this.requestString = requestString;
        StringTokenizer tokenizer = new StringTokenizer(requestString, "\r\n", false);
        while (tokenizer.hasMoreElements()) {
            String nextLine = tokenizer.nextToken();
            if (nextLine.startsWith("GET")) {
                //1st Line
                method = Methods.GET;
                StringTokenizer internalTokenizer = new StringTokenizer(nextLine, " ");
                internalTokenizer.nextToken();
                objectAddress = internalTokenizer.nextToken();
                String sHttpVersion = internalTokenizer.nextToken();
                if (sHttpVersion.equals("HTTP/1.1")) {
                    httpVersion = HTTPVersions.HTTP_1_1;
                } else if (sHttpVersion.equals("HTTP/1.0")) {
                    httpVersion = HTTPVersions.HTTP_1;
                }
            } else if (nextLine.startsWith("CONNECT")) {
                method = Methods.CONNECT;
            } else if (nextLine.startsWith("Accept:")) {
                acceptPart = nextLine.substring(8);
            } else if (nextLine.startsWith("Accept-Language:")) {
                acceptLanguage = nextLine.substring("Accept.Language:".length() + 1);
            } else if (nextLine.startsWith("User-Agent:")) {
                userAgent = nextLine.substring("User-Agent:".length() + 1);
            } else if (nextLine.startsWith("Accept-Encoding:")) {
                acceptEncoding = nextLine.substring("Accept-Encoding:".length() + 1);
            } else if (nextLine.startsWith("Proxy-Connection:")) {
                StringTokenizer internalTokenizer = new StringTokenizer(nextLine, " ");
                internalTokenizer.nextToken();
                if (internalTokenizer.nextToken().equals("Keep-Alive")) {
                    proxyConnectionType = ProxyConnectionTypes.KEEP_ALIVE;
                } else {
                    proxyConnectionType = ProxyConnectionTypes.OTHER;
                }
            } else if (nextLine.startsWith("Host:")) {
                host = nextLine.substring("Host:".length() + 1);
            } else if (nextLine.startsWith("Cookie:")) {
                cookie = nextLine.substring("Cookie:".length() + 1);
            }
        }
    }

    @Override
    public String toString() {
        return requestString;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public String getAcceptPart() {
        return acceptPart;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public String getCookie() {
        return cookie;
    }

    public String getDefaultString() {
        return defaultString;
    }

    public String getHost() {
        return host;
    }

    public HTTPVersions getHttpVersion() {
        return httpVersion;
    }

    public Methods getMethod() {
        return method;
    }

    public String getObjectAddress() {
        return objectAddress;
    }

    public ProxyConnectionTypes getProxyConnectionType() {
        return proxyConnectionType;
    }

    public String getRequestString() {
        return requestString;
    }

    public String getUserAgent() {
        return userAgent;
    }
    //Set &7 Get functions
}
