package org.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.blog.dto.CustomerDto;
import org.blog.entity.Customer;

public interface CustomerMapper extends BaseMapper<Customer> {
    // List<Category> getAllCustomer();
    Customer getCustomerById(@Param("id") Long id);
    Customer getCustomerByEmail(@Param("email") String email);
    int createCustomer(@Param("customer") CustomerDto customerDto);

}
