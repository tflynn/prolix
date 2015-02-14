package com.tsftsf.prolix.domain;

import java.util.List;

/**
 * Created by tracy on 2/14/15.
 */
public class ProtocolElement {

  private String version;

  private String command;

  private List<Long> payload;

  public ProtocolElement() {

  }

  public ProtocolElement(String version, String command, List<Long> payload) {
    this.version = version;
    this.command = command;
    this.payload = payload;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public List<Long> getPayload() {
    return payload;
  }

  public void setPayload(List<Long> payload) {
    this.payload = payload;
  }


}
