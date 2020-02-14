package com.lang.gmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 秒杀订单过期时间
 * </p>
 *
 * @author qll
 * @since 2020-01-16
 */
@ApiModel(value="CmsOrderSet对象", description="秒杀订单过期时间")
public class CmsOrderSet implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "秒杀过期时间")
    private Integer flashOrderOvertime;

    @ApiModelProperty(value = "正常订单过期时间")
    private Integer normalOrderOvertime;

    @ApiModelProperty(value = "确认收货时间")
    private Integer confirmOvertime;

    private Integer commentOvertime;

    private Integer finishOvertime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getFlashOrderOvertime() {
        return flashOrderOvertime;
    }

    public void setFlashOrderOvertime(Integer flashOrderOvertime) {
        this.flashOrderOvertime = flashOrderOvertime;
    }
    public Integer getNormalOrderOvertime() {
        return normalOrderOvertime;
    }

    public void setNormalOrderOvertime(Integer normalOrderOvertime) {
        this.normalOrderOvertime = normalOrderOvertime;
    }
    public Integer getConfirmOvertime() {
        return confirmOvertime;
    }

    public void setConfirmOvertime(Integer confirmOvertime) {
        this.confirmOvertime = confirmOvertime;
    }
    public Integer getCommentOvertime() {
        return commentOvertime;
    }

    public void setCommentOvertime(Integer commentOvertime) {
        this.commentOvertime = commentOvertime;
    }
    public Integer getFinishOvertime() {
        return finishOvertime;
    }

    public void setFinishOvertime(Integer finishOvertime) {
        this.finishOvertime = finishOvertime;
    }

    @Override
    public String toString() {
        return "CmsOrderSet{" +
            "id=" + id +
            ", flashOrderOvertime=" + flashOrderOvertime +
            ", normalOrderOvertime=" + normalOrderOvertime +
            ", confirmOvertime=" + confirmOvertime +
            ", commentOvertime=" + commentOvertime +
            ", finishOvertime=" + finishOvertime +
        "}";
    }
}
