package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1156;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1154.VehicleComparisonService;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1143.GetVehicleByIdService;
import com.sapient.asde.batch5.vehicleservice.util.ExcelExporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shubham Chaudhari shubham.chaudhari@publicissapeint.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles/comparison/download")
class VehicleComaprisonDownloadController {

  @Autowired
  private GetVehicleByIdService getVehicleByIdService;
  @Autowired
  private VehicleComparisonService vehicleComparisonService;
  private static final String USER_ID = "userId";

  @GetMapping("/{id}")
    public void exportToExcel(HttpServletResponse response,@PathVariable String id,
    @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws IOException, ServiceException {

        if (claims == null) {
          log.info("GET /api/vehicles/comparison/download/" + id);
          log.info("UNAUTHORIZED, Authorization header may be missing or invalid.");
    
          response.setStatus(401);
          response.sendError(401,"UNAUTHORIZED, Authorization header may be missing or invalid.");
          return ;
        }
        log.info("GET /api/vehicles/comparison/download/" + id);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=comparison_matrix_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        String userId =claims.get(USER_ID).toString();
        VehicleComparison matrix = vehicleComparisonService.getComparisonMatrix(id, userId);
        if(matrix==null)
        {
          response.setStatus(404);
          response.sendError(404,"Comparison matrix not found"+" id : "+userId);
          return ;
        }
        List<String>idList=matrix.getVehicleIds();
        List<Vehicle>list=new ArrayList<>();
        for(String vId : idList)
        {
          list.add(getVehicleByIdService.getVehicleById(vId));
        }
        ExcelExporter excelExporter = new ExcelExporter(list);
        excelExporter.export(response);  
    }  
}