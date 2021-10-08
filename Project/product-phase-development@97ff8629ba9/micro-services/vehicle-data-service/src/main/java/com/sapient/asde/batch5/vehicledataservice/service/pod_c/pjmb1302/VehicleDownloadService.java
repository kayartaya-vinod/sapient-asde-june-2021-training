package com.sapient.asde.batch5.vehicledataservice.service.pod_c.pjmb1302;

import java.util.List;

import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import java.io.Writer;

public interface VehicleDownloadService {
    public void getAllVehicles(Writer writer, String userId) throws ServiceException;

    public void getVehiclesById(List<String> id, Writer writer) throws ServiceException;
}
