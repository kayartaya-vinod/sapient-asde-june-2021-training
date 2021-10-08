package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1767;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.sapient.asde.batch5.vehicleservice.entity.Select;

@Slf4j
@Service
public class BrandAndModelServiceImpl implements BrandAndModelService{

    @Autowired
    VehicleRepository repo;
    
    /**
     * @Author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
     */
    @Override
    public List<Select> getBrandAndModel(String brand, String model,Integer page) throws ServiceException {
                Pageable pageable = PageRequest.of(page - 1, 100);
                List<Vehicle> ans = repo.findByBrand(brand,pageable);
                log.info("totla model = {} and brand={}",ans.size(),brand);
                Map<String, String> m = new HashMap<>();
                List<Select> result = new ArrayList<>();
                for (int i = 0; i < ans.size(); i++) {
                    if (!m.containsKey(ans.get(i).getModel() + "  Rs" +ans.get(i).getPrice().toString())){
                        m.put(ans.get(i).getModel() + "  Rs" + ans.get(i).getPrice().toString(), ans.get(i).getId());
                        Select obj = new Select();
                        obj.setLabel(ans.get(i).getModel() + "  Rs" + ans.get(i).getPrice().toString());
                        obj.setValue(ans.get(i).getId());
                        result.add(obj);
                    }
                }
                log.info("brand=given;model=notGiven returning m = {}",m.size());
                return result;
    }

    /**
     * @Author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
     */
    @Override
    public List<Select> getBrand() throws ServiceException{
        List<String> brands = repo.groupByBrand();
        List<Select> result = new ArrayList<>();
        for(int i=0;i<brands.size();i++){
            Select obj = new Select();
            obj.setLabel(brands.get(i));
            obj.setValue(brands.get(i));
            result.add(obj);
        }        
        log.info("brand=model=notGiven returning m = {}", brands.size());
        return result;
    }
     
}
