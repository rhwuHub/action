package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.entity.Label;
import com.blog.domain.entity.LabelArticle;
import com.blog.mapper.LabelArticleMapper;
import com.blog.mapper.LabelMapper;
import com.blog.service.ILabelArticleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LabelArticleServiceImpl  extends ServiceImpl<LabelArticleMapper, LabelArticle> implements ILabelArticleService {
@Resource
private LabelMapper labelMapper;
    @Override
    public List<Label> getLabelByArticleId(Integer id) {

        List<LabelArticle> list = this.lambdaQuery().eq(true, LabelArticle::getArticleId, id).list();
        List<Label> labelList = list.stream().map(new Function<LabelArticle, Label>() {
            @Override
            public Label apply(LabelArticle labelArticle) {
                return labelMapper.selectById(labelArticle.getLabelId());
            }

        }).collect(Collectors.toList());
return labelList;
    }
}
