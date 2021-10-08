/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/
package com.sapient.asde.batch5.vehicledataservice.service.pod_b.pjmb1453;

import java.util.List;

import com.sapient.asde.batch5.vehicledataservice.entity.VehicleUpload;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

public interface GetVehicleUploadedFilesService {

    public List<VehicleUpload> getVehicleUploadedFiles(String dealerId) throws ServiceException;

}
