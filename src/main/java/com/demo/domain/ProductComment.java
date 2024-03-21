package com.demo.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
public class ProductComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commnet_seq;
	
	@ManyToOne
	@JoinColumn(name="pseq", nullable=false)
	private Product product;;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)
	private Member member;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@ColumnDefault("sysdate")
	@Column(updatable=false)
	private Date regdate;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(insertable=false)
	private Date modifydate;
}
