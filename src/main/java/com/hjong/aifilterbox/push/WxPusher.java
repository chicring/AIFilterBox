package com.hjong.aifilterbox.push;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjong.aifilterbox.config.BeanConfig;
import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.entity.Option;
import com.hjong.aifilterbox.mapper.OptionMapper;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.hjong.aifilterbox.entity.Constant.*;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/

@Data
@Component
public class WxPusher implements Push{

//    @Resource
//    OptionMapper optionMapper;

//    private String appToken;
//    private String topicIds;
//    private String uids;

    @Resource
    BeanConfig beanConfig;



    @Override
    public void send(String title, String content) {

    }
//
//    @Override
//    public void modifySetting(List<Option> options) {
//
//    }
//
//    @Override
//    public void removeSetting() {
//        appToken = null;
//        topicIds = null;
//        uids = null;
//    }
//
//    private void doSetting(List<Option> options) {
//        for (Option option : options) {
//            switch (option.getKey()) {
//                case WXPUSHER_APP_TOKEN:
//                    appToken = option.getValue();
//                    break;
//                case WXPUSHER_TOPIC_IDS:
//                    topicIds = option.getValue();
//                    break;
//                case WXPUSHER_UIDS:
//                    uids = option.getValue();
//                    break;
//            }
//        }
//    }
}
