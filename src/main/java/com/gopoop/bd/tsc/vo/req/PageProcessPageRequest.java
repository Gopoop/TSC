package com.gopoop.bd.tsc.vo.req;

import com.gopoop.bd.tsc.jdbc.sql.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 郭速凯
 * @date 2019/7/22 11:34
 */
@Data
@ApiModel
public class PageProcessPageRequest extends PageParam {

    @ApiModelProperty(notes = "爬虫id")
    private Long spiderId;
    @ApiModelProperty(notes = "是否展示")
    private Integer fieldIsShow;
    @ApiModelProperty(notes = "属性名称")
    private String attribute;
    @ApiModelProperty(notes = "开始创建时间")
    private Date startCreateTime;
    @ApiModelProperty(notes = "结束创建时间")
    private Date endCreateTime;

}
