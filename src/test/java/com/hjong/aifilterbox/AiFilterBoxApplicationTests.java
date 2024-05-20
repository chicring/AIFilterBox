package com.hjong.aifilterbox;


import cn.hutool.dfa.WordTree;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class AiFilterBoxApplicationTests {

    @Test
    void contextLoads()   {
        WordTree tree = new WordTree();
        tree.addWord("拼多多");
        tree.addWord("微信");
        tree.addWord("配位");
        tree.addWord("驼鹿");
        tree.addWord("京东");

        System.out.println(tree.isMatch("试试"));
    }

}
