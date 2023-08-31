package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // set get
@AllArgsConstructor // 全参构造
@NoArgsConstructor  // 无参构造
@TableName("commodity")
public class Commodity {
    @TableId(type = IdType.AUTO)
    public Integer id; // 身份/主键
    public String label; // 商品名称
    public String img; //商品图片
    public String price;//价格
    public String description;//描述
    public String soldnum;//售卖数量
    // set get
}
