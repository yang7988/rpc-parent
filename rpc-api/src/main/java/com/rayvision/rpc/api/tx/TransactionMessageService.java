package com.rayvision.rpc.api.tx;

import com.rayvision.rpc.common.pagination.PageBean;
import com.rayvision.rpc.common.exception.MessageBizException;
import com.rayvision.rpc.common.pagination.PageParam;

import java.util.Map;

public interface TransactionMessageService {

    /**
     * 预存储消息.
     */
    public int saveMessageWaitingConfirm(TransactionMessage TransactionMessage) throws MessageBizException;


    /**
     * 确认并发送消息.
     */
    public void confirmAndSendMessage(String messageId) throws MessageBizException;


    /**
     * 存储并发送消息.
     */
    public int saveAndSendMessage(TransactionMessage TransactionMessage) throws MessageBizException;


    /**
     * 直接发送消息.
     */
    public void directSendMessage(TransactionMessage TransactionMessage) throws MessageBizException;


    /**
     * 重发消息.
     */
    public void reSendMessage(TransactionMessage TransactionMessage) throws MessageBizException;


    /**
     * 根据messageId重发某条消息.
     */
    public void reSendMessageByMessageId(String messageId) throws MessageBizException;


    /**
     * 将消息标记为死亡消息.
     */
    public void setMessageToAreadlyDead(String messageId) throws MessageBizException;


    /**
     * 根据消息ID获取消息
     */
    public TransactionMessage getMessageByMessageId(String messageId) throws MessageBizException;

    /**
     * 根据消息ID删除消息
     */
    public void deleteMessageByMessageId(String messageId) throws MessageBizException;


    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException;

    /**
     * 获取分页数据
     */
    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) throws MessageBizException;


}
