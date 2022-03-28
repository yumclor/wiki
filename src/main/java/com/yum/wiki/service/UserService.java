package com.yum.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yum.wiki.domain.User;
import com.yum.wiki.domain.UserExample;
import com.yum.wiki.mapper.UserMapper;
import com.yum.wiki.request.UserQueryReq;
import com.yum.wiki.request.UserSaveReq;
import com.yum.wiki.request.UserUpdateReq;
import com.yum.wiki.result.PageRes;
import com.yum.wiki.result.UserQueryRes;
import com.yum.wiki.utils.CopyUtil;
import com.yum.wiki.utils.SnowFlakeUtil;
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
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    public PageRes<UserQueryRes> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getLoginName())) {
            criteria.andLoginNameLike("%" + req.getLoginName() + "%");
        }
        // 分页(只对后面执行的第一条SQL有效)
        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> userList = userMapper.selectByExample(userExample);
        // pagehelper还提供page对象
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        //pageInfo.getTotal();
        //pageInfo.getPages();
        /*users.stream().forEach(item->{
            // BeanUtils.copyProperties(item,userRes);
            // 使用自己封装的CopyUtil 单体复制
            UserQueryRes userRes = CopyUtil.copy(item, UserQueryRes.class);
            resList.add(userRes);
        });*/
        // 使用自己封装的CopyUtil 列表复制
        List<UserQueryRes> result = CopyUtil.copyList(userList, UserQueryRes.class);
        PageRes<UserQueryRes> pageRes = new PageRes<>();
        pageRes.setTotal(pageInfo.getTotal());
        pageRes.setList(result);
        return pageRes;
    }

    /**
     * 修改知识库
     */
    public void update(UserUpdateReq req) {
        User user = CopyUtil.copy(req,User.class);
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 新增知识库
     */
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req,User.class);
        user.setId(snowFlakeUtil.nextId());
        userMapper.insert(user);
    }

    /**
     * 根据ID删除知识库
     * @param id
     */
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
