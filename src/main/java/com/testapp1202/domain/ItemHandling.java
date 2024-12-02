package com.testapp1202.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="item_handlings")
@Getter @Setter @NoArgsConstructor
public class ItemHandling {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="item_handling_id")
	private Integer itemHandlingId;
    
  	@Column(name="unboxing_video_path")
	private String unboxingVideoPath;
    
  	@Column(name="item_weight")
	private double itemWeight;
    
  	@Column(name="rfid_tag")
	private String rfidTag;
    
	




}
