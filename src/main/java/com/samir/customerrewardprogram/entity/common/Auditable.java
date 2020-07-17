package com.samir.customerrewardprogram.entity.common;

import java.time.LocalDateTime;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Auditable {
	
	@CreatedDate
    @Column(updatable =  false)
    protected LocalDateTime createdDate;
}
