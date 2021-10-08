package com.sapient.asde.batch5.vehicledataservice.service.pod_c.pjmb1299;

import java.io.InputStream;
import java.util.Map;

import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

/**
 * @author Mutharasan E mutharasan.e@publicissapient.com
 */
public interface VehicleUploadService {
    public Map store(InputStream file,String userId,String fileName) throws ServiceException;
}
