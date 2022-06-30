package com.zoomcar.jdbi;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * since ${project.version}
 *
 * @Author AbhishekT on 16/05/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseDTO implements Serializable {
	private static final long serialVersionUID = -8927397155289863409L;
	private int id;
	private String createdBy;
	private String modifiedBy;
	private Date createdAt;
	private Date modifiedAt;
}
