package com.lyb.lyblog.model.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Tags {
    @TableId(type = IdType.AUTO)
    int id;
    String title;
    String color;
}
