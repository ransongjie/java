package com.xcrj.junit4me.generated.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 实体类
 *
 * @author root
 * @since 2022-12-04
 */
public class UtStudent implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键
	*/
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	* 学号
	*/
	private String code;
	/**
	* 年级，例如2021
	*/
	private String grade;
	/**
	* 姓名
	*/
	private String name;
	/**
	* 创建时间
	*/
	private LocalDateTime createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "UtStudent{" +
		", id=" + id +
		", code=" + code +
		", grade=" + grade +
		", name=" + name +
		", createTime=" + createTime +
		"}";
	}
}
