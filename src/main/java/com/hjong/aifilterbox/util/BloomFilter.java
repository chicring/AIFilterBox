package com.hjong.aifilterbox.util;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import com.hjong.aifilterbox.mapper.MessageMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/

@Slf4j
@Component
public class BloomFilter implements InitializingBean {

    BitMapBloomFilter bitMapBloomFilter;

    @Resource
    MessageMapper messageMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        bitMapBloomFilter = new BitMapBloomFilter(2000);
        messageMapper.selectMessageIdList().forEach(bitMapBloomFilter::add);
    }

    public boolean contains(String messageId) {
        return bitMapBloomFilter.contains(messageId);
    }

    public void add(String messageId) {
        bitMapBloomFilter.add(messageId);
    }

    @Scheduled(cron = "0 30 0 * * ?")
    public synchronized void clear() throws Exception {
        log.info("清空布隆过滤器");
        bitMapBloomFilter = new BitMapBloomFilter(2000);
        log.info("加载近24小时的数据到布隆过滤器");
        messageMapper.selectMessageIdList().forEach(bitMapBloomFilter::add);
        log.info("清空布隆过滤器完成");
    }
}
