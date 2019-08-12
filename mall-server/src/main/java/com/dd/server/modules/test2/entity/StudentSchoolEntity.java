package com.dd.server.modules.test2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 学生自主注册学校信息记录表
 * </p>
 *
 * @author Ivan
 * @since 2019-08-12
 */
@TableName("tb_student_school")
@ApiModel(value="StudentSchoolEntity对象", description="学生自主注册学校信息记录表")
public class StudentSchoolEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学生id")
    private Long studentId;

    @ApiModelProperty(value = "学校信息id")
    private Long schoolInfoId;

    @ApiModelProperty(value = "年级id")
    private Long gradeId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSchoolInfoId() {
        return schoolInfoId;
    }

    public void setSchoolInfoId(Long schoolInfoId) {
        this.schoolInfoId = schoolInfoId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "StudentSchoolEntity{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", schoolInfoId=" + schoolInfoId +
        ", gradeId=" + gradeId +
        ", createTime=" + createTime +
        "}";
    }
}
