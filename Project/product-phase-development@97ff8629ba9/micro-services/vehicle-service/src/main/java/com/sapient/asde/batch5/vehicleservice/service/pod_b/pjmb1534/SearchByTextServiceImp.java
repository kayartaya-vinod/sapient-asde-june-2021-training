/**
 * @author Sakshi Yadav sakshi.yadav@publicissapient.com
 */

package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1534;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.repository.SearchAccessoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service("pjmb1534_SearchByTextServiceImpl")
public class SearchByTextServiceImp implements SearchByTextService {

  @Autowired
  SearchAccessoryRepository repo;

  @Cacheable(value ="AccessorySearch",unless="#result == null")
  @Override
  public List<VehicleAccessory> findAccessoryByText(String text, Integer page) throws ServiceException {
    return repo.searchByText(text, page * 12);
  }
}
