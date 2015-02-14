package com.tsftsf.prolix.mapper;

import com.tsftsf.prolix.domain.ProtocolElement;
import com.tsftsf.prolix.domain.ProtocolElementList;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tracy on 2/14/15.
 */
public class JSONMapper {

  static Logger logger = LoggerFactory.getLogger(JSONMapper.class);

  private ObjectMapper mapper = new ObjectMapper();

  public String toJSON(Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      logger.error(String.format("Unable to serialize object %s to string",obj.toString()),e);
      return null;
    }
  }

  public Object fromJSONString(String jsonString) {
    try {
      return mapper.readValue(jsonString,Object.class);
    } catch (Exception e) {
      logger.error(String.format("Unable to deserialize object string to object",jsonString),e);
      return null;
    }
  }

  public ProtocolElement protocolElementFromJSONString(String jsonString) {
    try {
      return mapper.readValue(jsonString,ProtocolElement.class);
    } catch (Exception e) {
      logger.error(String.format("Unable to deserialize ProtocolElement object string to ProtocolElement",jsonString),e);
      return null;
    }
  }

  public ProtocolElementList protocolElementListFromJSONString(String jsonString) {
    try {
      return mapper.readValue(jsonString,ProtocolElementList.class);
    } catch (Exception e) {
      logger.error(String.format("Unable to deserialize ProtocolElementList object string to ProtocolElementList",jsonString),e);
      return null;
    }
  }

}
