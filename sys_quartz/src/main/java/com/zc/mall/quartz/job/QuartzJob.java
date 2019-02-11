package com.zc.mall.quartz.job;

import com.zc.mall.mall.service.OrderGoodsCommentService;
import com.zc.mall.mall.service.OrderInfoService;
import com.zc.sys.common.util.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时器
 * @author zp
 *
 */
@Component
public class QuartzJob {
    private static Logger log = LoggerFactory.getLogger(QuartzJob.class);

    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private OrderGoodsCommentService orderGoodsCommentService;
    
    /**
     * 定时任务-订单支付超时处理-每10s执行一次
     */
    @Scheduled(fixedRate = 1000 * 10)
    public void autoStopDraftTrade() {
        orderInfoService.autoClose();
    }
    
    /**
     * 定时任务-订单默认评价/自动收货-每天0:30AM
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void autoDefaultComment() {
        log.info("定时任务-订单默认评价-每天0:30AM,本次执行时间:"+DateUtil.dateStr4(DateUtil.getNow()));
        orderGoodsCommentService.autoDefaultComment();
        log.info("定时任务-订单自动收货-每天0:30AM,本次执行时间:"+DateUtil.dateStr4(DateUtil.getNow()));
        orderInfoService.autoReceive();
    }
    
}
