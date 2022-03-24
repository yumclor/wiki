package com.yum.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yum.wiki.domain.Ebook;
import com.yum.wiki.domain.EbookExample;
import com.yum.wiki.mapper.EbookMapper;
import com.yum.wiki.request.EbookQueryReq;
import com.yum.wiki.request.EbookSaveReq;
import com.yum.wiki.request.EbookUpdateReq;
import com.yum.wiki.result.EbookQueryRes;
import com.yum.wiki.result.PageRes;
import com.yum.wiki.utils.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author Yum
 * @version 1.0
 */
@Service
public class EbookService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Autowired
    private EbookMapper ebookMapper;

    public PageRes<EbookQueryRes> list(EbookQueryReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        // 如果有name（标题）参数传递进来就模糊查询
        if(!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        // 分页(只对后面执行的第一条SQL有效)
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        // pagehelper还提供page对象
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        //pageInfo.getTotal();
        //pageInfo.getPages();
        /*ebooks.stream().forEach(item->{
            // BeanUtils.copyProperties(item,ebookRes);
            // 使用自己封装的CopyUtil 单体复制
            EbookQueryRes ebookRes = CopyUtil.copy(item, EbookQueryRes.class);
            resList.add(ebookRes);
        });*/
        // 使用自己封装的CopyUtil 列表复制
        List<EbookQueryRes> result = CopyUtil.copyList(ebookList, EbookQueryRes.class);
        PageRes<EbookQueryRes> pageRes = new PageRes<>();
        pageRes.setTotal(pageInfo.getTotal());
        pageRes.setList(result);
        return pageRes;
    }

    /**
     * 修改知识库
     */
    public void update(EbookUpdateReq req) {
        Ebook ebook = CopyUtil.copy(req,Ebook.class);
        ebookMapper.updateByPrimaryKey(ebook);
    }

    /**
     * 新增知识库
     */
    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req,Ebook.class);
        ebookMapper.insert(ebook);
    }
}
