package com.ural.readingisgood.orderservice.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseDaoModel {

    @Id
    @GeneratedValue
    private Long id;


    @CreatedBy
    @Column(name = "created_by"/*, nullable = false*/)
    protected String createdBy;

    @CreatedDate
    @Column(name = "create_date")
    protected Date createDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected Date lastModifiedDate;
}
