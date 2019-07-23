package com.gopoop.bd.tsc.vo.req;

import com.gopoop.bd.tsc.jdbc.sql.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 郭速凯
 * @date 2019/7/22 11:34
 */
@Data
@ApiModel
public class SpiderPageRequest extends PageParam {

    @ApiModelProperty(notes = "爬虫id")
    private Long spiderId;


}
