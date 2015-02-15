package com.tsftsf.prolix.server;

import com.tsftsf.prolix.domain.ProtocolElement;
import com.tsftsf.prolix.domain.RandomNumberPoolTimed;
import com.tsftsf.prolix.domain.RandomNumberRun;
import com.tsftsf.prolix.domain.RandomNumberRunSet;
import com.tsftsf.prolix.mapper.JSONMapper;

import java.util.ArrayList;
import java.util.List;

//import spark.Spark;
import spark.Spark;

/**
 * Created by tracy on 2/14/15.
 */
public class ProlixServer {

  private static ProlixServer prolixServer = new ProlixServer();

  private static JSONMapper jsonMapper = new JSONMapper();

  private static RandomNumberPoolTimed randomNumberPoolTimed = RandomNumberPoolTimed.getInstance();

  private void run() {

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

    ProlixServer.prolixServer.run();

  }


}
