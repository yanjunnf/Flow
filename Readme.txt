1. 要支持的Action 列表
    a. 命令行action
    b. 读文件action
    c. 写文件action
    d. 发送http请求action(GET,PUT,DELETE,POST)
    e. 接收http请求action(GET,PUT,DELETE,POST)
    f. 接收JMS请求action
    g. 发送TCP/IP报文action
    h. 接收TCP/IP报文action
    j. 读取系统信息action(CPU, Memory等)
    k. 发送邮件action
    o. 解析文本action，比如正则取出文本某些内容
    
上述action，需要定义好输出是什么？

步骤(Step)的核心步骤：
    1. 输入
    2. 执行Action
    3. 校验执行结果
    4. 传递结果给下一个Step
    
封装(Wrapper)的核心步骤：
    1. 主要是针对JSON和HTML的操作
    2. 输入数据的包装
    3. 输出数据的包装

流(Flow)的核心概念：
    1. Flow由Step组成