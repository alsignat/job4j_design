package ru.job4j.io;

import java.io.*;

public class Analyzer {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            boolean unavailableNow = false;
            String ln;
            while ((ln = in.readLine()) != null && !ln.isBlank()) {
                String[] data = ln.split(" ");
                int status = Integer.parseInt(data[0]);
                String time = data[1];
                if ((status == 400 || status == 500) && !unavailableNow) {
                    out.print(time + " ");
                    unavailableNow = true;
                } else if (status != 400 && status != 500 && unavailableNow) {
                    out.println(time);
                    unavailableNow = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer();
        analyzer.unavailable("./data/server_time_in.txt", "./data/target.txt");
    }

}
