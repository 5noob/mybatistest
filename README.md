v2.0版本相较与v1.0版本，做了如下改动：
    
    1. 由于返回值类型不固定，所以用MapperData存储，作为参数去传递
    
    2. 将一个query拆成3步，分别由ParameterHandler、StatementHandler、ResultSetHandler来处理
       1) ParameterHandler建立连接，获取PrepareStatement
       2）StatementHandler执行sql
       3) ResultSetHandler将ResultSet处理成我们想要的实体类
       
    3. 建立MyCachingExecutor，用来缓存结果，用到了装饰器模式