1. 要支持的Action 列表
    . 命令行action
    . 读文件action
    . 写文件action
    . 新建文件
    . 删除文件
    . 监听文件action
    . 发送http请求action(GET,PUT,DELETE,POST)
    . 接收http请求action(GET,PUT,DELETE,POST)
    . 接收JMS请求action
    . 发送TCP/IP报文action
    . 接收TCP/IP报文action
    . 读取系统信息action(CPU, Memory等)
    . 发送邮件action
    . 解析文本action，比如正则取出文本某些内容
    . 运行一个进程
    . gSOAP调用
    . 处理JSON对象action
    . 处理XML对象action
    
上述action，需要定义好输出是什么？

步骤(Step)的核心步骤：
    分为了两种：
        1. Action step
            . 执行Action
            . 传递结果给下一个Step
        2. Conditon step
            .校验输入
            .根据条件选择下一个step
        3. Loop step
            . 循环step
            . 设定循环次数和时间间隔
        4. Condition loop step
            . 条件循环step
            . 设定循环次数和时间间隔
            . 循环中每次校验action执行结果
    
封装(Wrapper)的核心步骤：
    1. 主要是针对JSON和HTML的操作
    2. 输入数据的包装
    3. 输出数据的包装

流(Flow)的核心概念：
    1. Flow由Step组成
    2. 状态以及不同状态的动作
    
流管理
    1. 添加流
    2. 删除流
    3. 查询流，名字，状态等
    4. 停止流
    5. 重新开始流
    6. 复制流