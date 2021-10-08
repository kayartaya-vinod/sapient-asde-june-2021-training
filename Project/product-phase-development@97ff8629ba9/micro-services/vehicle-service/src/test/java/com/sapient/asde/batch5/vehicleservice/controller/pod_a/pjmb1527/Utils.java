/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1527;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


public final class Utils {
  
  private Utils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Returns 
  *   {
  *     "content": {
  *         "brand": ["brand1", "brand2"]  
  *      },
  *     "ids": ["id1", "id2"],
  *     "images": ["img1", "img2"]
  *   }
  */
  public static Map<String, Object> getMatrix(){
    Map<String, Object> result = new LinkedHashMap<>();

    Map<String, Object> contentMap = new LinkedHashMap<>();

    contentMap.put("brand", Arrays.asList("brand1", "brand2"));

    result.put("content", contentMap);
    result.put("ids", Arrays.asList("id1", "id2"));
    result.put("images", Arrays.asList("img1", "img2"));
    return result;
  }


}
