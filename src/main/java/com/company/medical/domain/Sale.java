package com.company.medical.domain;

import com.company.medical.domain.superdomain.SuperDomain;
import lombok.Data;

import java.util.Date;

@Data
public class Sale extends SuperDomain {
    private Long saleId;//药店id
    private String saleName;//药店名

    private String salePhone;//药店电话
    private Date createtime;//创建时间
    private Date updatetime;//修改时间
    //省略get/set方法

}