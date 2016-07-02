package com.tsftsf.prolix.mapper;

import com.tsftsf.prolix.domain.ProtocolElement;
import com.tsftsf.prolix.domain.ProtocolElementList;
import com.tsftsf.prolix.mapper.JSONMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by tracy on 2/14/15.
 */
public class JSONMapperTest {

  @Test
  public void testToJSONString() {
    JSONMapper mapper = new JSONMapper();
    List<Long> payload = new ArrayList<Long>();
    payload.add(1L);
    payload.add(2L);
    ProtocolElement pe = new ProtocolElement("v1","command",payload);
    String expectedJsonString = "{\"version\":\"v1\",\"command\":\"command\",\"payload\":[1,2]}";
    String serializedJsonString = mapper.toJSON(pe);
    //System.out.println(serializedJsonString);
    assertEquals("Serialized string matches expected", expectedJsonString,serializedJsonString);
  }

  @Test
  public void testFromJSON() {
    JSONMapper mapper = new JSONMapper();
    List<Long> payload = new ArrayList<Long>();
    payload.add(1L);
    payload.add(2L);
    ProtocolElement expectedPE = new ProtocolElement("v1","command",payload);
    String serializedJsonString = mapper.toJSON(expectedPE);
    ProtocolElement actualPE = (ProtocolElement) mapper.protocolElementFromJSONString(serializedJsonString);
    assertEquals("versions match",expectedPE.getVersion(),actualPE.getVersion());
    assertEquals("commands match",expectedPE.getCommand(),actualPE.getCommand());
    assertEquals("Payload(0) match", expectedPE.getPayload().get(0), actualPE.getPayload().get(0));
    assertEquals("Payload(1) match", expectedPE.getPayload().get(1), actualPE.getPayload().get(1));
  }

  @Test
  public void testListFromJSON() {
    JSONMapper mapper = new JSONMapper();
    List<Long> payload = new ArrayList<Long>();
    payload.add(1L);
    payload.add(2L);
    ProtocolElement pe1 = new ProtocolElement("v1","command1",payload);
    ProtocolElement pe2 = new ProtocolElement("v2","command2",payload);
    ProtocolElementList expectedPEList = new ProtocolElementList();
    expectedPEList.add(pe1).add(pe2);
    String serializedJsonString = mapper.toJSON(expectedPEList);
    //System.out.println(serializedJsonString);
    ProtocolElementList actualPEList = mapper.protocolElementListFromJSONString(serializedJsonString);
    ProtocolElement actualPE1 = actualPEList.get(0);
    ProtocolElement actualPE2 = actualPEList.get(1);

    assertEquals("versions match",pe1.getVersion(),actualPE1.getVersion());
    assertEquals("commands match",pe1.getCommand(),actualPE1.getCommand());
    assertEquals("Payload(0) match", pe1.getPayload().get(0), actualPE1.getPayload().get(0));
    assertEquals("Payload(1) match", pe1.getPayload().get(1), actualPE1.getPayload().get(1));

    assertEquals("versions match",pe2.getVersion(),actualPE2.getVersion());
    assertEquals("commands match",pe2.getCommand(),actualPE2.getCommand());
    assertEquals("Payload(0) match", pe2.getPayload().get(0), actualPE2.getPayload().get(0));
    assertEquals("Payload(1) match", pe2.getPayload().get(1), actualPE2.getPayload().get(1));
  }

}
