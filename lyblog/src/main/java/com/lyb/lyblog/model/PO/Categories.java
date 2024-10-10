package com.lyb.lyblog.model.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Optional;

@Data
@NoArgsConstructor
public class Categories {
    @TableId(type = IdType.AUTO)
    Integer id;
    String title;
    String color;

    public Categories(Categories categories) {
        Optional.ofNullable(categories).ifPresent(item -> {
            this.id = item.getId();
            this.title = item.getTitle();
            this.color = item.getColor();
        });
    }
}
