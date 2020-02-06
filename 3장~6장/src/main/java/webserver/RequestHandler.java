package webserver;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String header = br.readLine();
            String[] tokens = header.split(" ");
            String url = tokens[1];
            int index = url.indexOf("?");
            String requestPath = url.substring(0, index);
            String params = url.substring(index+1);
            List<String> a = new ArrayList();
            Map<String, String> map = HttpRequestUtils.parseQueryString(params);
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for(Map.Entry<String, String> entry : entries){
                a.add(entry.getValue());
            }
            String userID = a.get(0), userPassword = a.get(1), userName = a.get(2), userEmail = a.get(3);
            String decodeUserEmail = URLDecoder.decode(userEmail, "UTF-8");
            User user = new User(userID, userPassword, userName, decodeUserEmail);
//            String userID = temp("userID");
//            String userPassword = params.substring(c+1, d);
//            String userName = params.substring(e+1, f);
//            String userEmail = params.substring(g+1);
//            while(!"".equals(br.readLine())){
//                if(br.readLine() == null){
//                    return;
//                }
//                String body = br.readLine();
//                log.warn(body);
//            }

            byte[] body = Files.readAllBytes(new File("./web" + url).toPath());

            DataOutputStream dos = new DataOutputStream(out);
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
