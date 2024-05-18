package cn.bugstack.chatbot.api.domain.zsxq;

import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import java.io.IOException;

/**
 * @author 小傅哥，微信：fustack
 * @description 知识星球 API 接口
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 封装知识星球 API 接口
 */
public interface IZsxqApi {

    /**
     * 根据group id 和 cookie 查询知识星球未回答的提问
     * @param groupId 知识星球id
     * @param cookie 用户cookie（没有做模拟登录）
     * @return 聚合后的查询结果
     * @throws IOException
     */
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    /**
     * 回答用户的提问
     * @param groupId
     * @param cookie
     * @param topicId 回答提问的id
     * @param text
     * @param silenced
     * @return 回答需要的数据不多，知道是否成功就可以了
     * @throws IOException
     */
    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;

}