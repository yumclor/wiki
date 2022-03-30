package com.yum.wiki.result;


import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author Yum
 * @version 1.0
 */
@Alias("statisticRes")
public class StatisticRes {
    /**
     * 日期
     */
    private Date date;
    /**
     * 阅读总数
     */
    private int viewCount;
    /**
     * 点赞总数
     */
    private int voteCount;
    /**
     * 阅读增长数
     */
    private int viewIncrease;
    /**
     * 点赞增长数
     */
    private int voteIncrease;

    public StatisticRes() {
    }

    public StatisticRes(Date date, int viewCount, int voteCount, int viewIncrease, int voteIncrease) {
        this.date = date;
        this.viewCount = viewCount;
        this.voteCount = voteCount;
        this.viewIncrease = viewIncrease;
        this.voteIncrease = voteIncrease;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getViewIncrease() {
        return viewIncrease;
    }

    public void setViewIncrease(int viewIncrease) {
        this.viewIncrease = viewIncrease;
    }

    public int getVoteIncrease() {
        return voteIncrease;
    }

    public void setVoteIncrease(int voteIncrease) {
        this.voteIncrease = voteIncrease;
    }
}
