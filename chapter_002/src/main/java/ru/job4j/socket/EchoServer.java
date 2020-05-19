package ru.job4j.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.logs.UsageLog4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = "-1";
                    String str;
                    boolean containsExit = false;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        //Проверяем, что есть команда Exit
                        containsExit = (str.contains("msg=Exit")) || containsExit;
                        //Если str содержить msg=, значит в ней есть команда,
                        //Вычленяем команду, она находится между msg= и HTT,
                        //если команды в str нет, то сохраняем ранее найденую команду.
                        answer = (str.contains("msg=")) ? str.split("msg=")[1].split("HTT")[0] : answer;
                    }
                    //Если ранее была найдена команда Exit, то выйдем из цикла.
                    if (containsExit) {
                        break;
                    }
                    //Если команда Hello, то вставляем специальное приветствие, иначе дублируем запрос.
                    answer = (answer.equals("Hello ")) ? "welcome to the rice fields, buddy" : answer;
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                }
            }
        } catch (IOException ioe) {
            LOG.error("IOException - ", ioe);
        }
    }
}