package com.auto.post.configuration.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 紧急停止日志实体，对应 ap_emergency_stop_log 表，用于记录批量暂停配置和取消任务的结果。
 */
@TableName("ap_emergency_stop_log")
public class EmergencyStopLogEntity {
    /** 日志主键。 */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 操作人名称。 */
    private String operatorName;
    /** 停止范围说明。 */
    private String scopeDesc;
    /** 受影响配置数量。 */
    private Integer affectedConfigCount;
    /** 被取消任务数量。 */
    private Integer cancelledTaskCount;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOperatorName() { return operatorName; }
    public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
    public String getScopeDesc() { return scopeDesc; }
    public void setScopeDesc(String scopeDesc) { this.scopeDesc = scopeDesc; }
    public Integer getAffectedConfigCount() { return affectedConfigCount; }
    public void setAffectedConfigCount(Integer affectedConfigCount) { this.affectedConfigCount = affectedConfigCount; }
    public Integer getCancelledTaskCount() { return cancelledTaskCount; }
    public void setCancelledTaskCount(Integer cancelledTaskCount) { this.cancelledTaskCount = cancelledTaskCount; }
}
