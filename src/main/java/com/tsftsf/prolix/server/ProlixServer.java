package com.tsftsf.prolix.server;

import com.tsftsf.prolix.domain.ProtocolElement;
import com.tsftsf.prolix.domain.RandomNumberPoolTimed;
import com.tsftsf.prolix.domain.RandomNumberRun;
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

  private void run() {

    Spark.get("/randomNumberRun", (req, res) -> {
      RandomNumberRun randomNumberRun = RandomNumberPoolTimed.getInstance().get();
      return jsonMapper.toJSON(randomNumberRun);
    });

    Spark.get("/hello", (req, res) -> {
      return "hello world z";
      //return prolixServer.hello();
    });

  }
  public static void main(String[] args) {

    ProlixServer.prolixServer.run();

  }

   public String hello() {
     JSONMapper mapper = new JSONMapper();
     List<Long> payload = new ArrayList<Long>();
     payload.add(1L);
     payload.add(2L);
     ProtocolElement pe = new ProtocolElement("v1","command",payload);
     String expectedJsonString = "{\"version\":\"v1\",\"command\":\"command\",\"payload\":[1,2]}";
     String serializedJsonString = mapper.toJSON(pe);

     return serializedJsonString;
  }
}
