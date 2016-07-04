package com.tsftsf.prolix.server;

import com.tsftsf.prolix.domain.RandomNumberPoolTimed;
import com.tsftsf.prolix.domain.RandomNumberRun;
import com.tsftsf.prolix.domain.RandomNumberRunSet;
import com.tsftsf.prolix.mapper.JSONMapper;

import spark.Spark;

/**
 * Created by tracy on 2/14/15.
 */
public class ProlixServer {

    public static ProlixServer prolixServer = new ProlixServer();

    private static JSONMapper jsonMapper = new JSONMapper();

    private static RandomNumberPoolTimed randomNumberPoolTimed = RandomNumberPoolTimed.getInstance();

    private Integer port = 4567;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void run() {

        Spark.port(port);

        Spark.get("/randomNumberRun", (req, res) -> {
            res.type("text/json");
            RandomNumberRun randomNumberRun = ProlixServer.randomNumberPoolTimed.getRandomNumberRun();
            return ProlixServer.jsonMapper.toJSON(randomNumberRun);
        });

        Spark.get("/randomNumberRunSet", (req, res) -> {
            res.type("text/json");
            RandomNumberRunSet randomNumberRunSet = ProlixServer.randomNumberPoolTimed.getRandomNumberRunSet();
            return ProlixServer.jsonMapper.toJSON(randomNumberRunSet);
        });

    }

    public static void main(String[] args) {

        ProlixServer currentServer = ProlixServer.prolixServer;

        //TODO Proper command-line parsing
        if (args.length == 1) {
            currentServer.setPort(Integer.valueOf(args[0]));
        }

        currentServer.run();

    }


}
