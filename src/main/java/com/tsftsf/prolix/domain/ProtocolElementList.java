package com.tsftsf.prolix.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tracy on 2/14/15.
 */
public class ProtocolElementList {

  private List<ProtocolElement> protocolElements = new ArrayList<ProtocolElement>();

  public ProtocolElementList() {}

  public ProtocolElementList(List<ProtocolElement> protocolElements) {
    this.protocolElements = protocolElements;
  }

  public List<ProtocolElement> add(ProtocolElement pe) {
    this.protocolElements.add(pe);
    return this.protocolElements;
  }

  public List<ProtocolElement> getProtocolElements() {
    return this.protocolElements;
  }

  public ProtocolElement get(int index) {
    return this.protocolElements.get(index);
  }

}
