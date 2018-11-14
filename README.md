v2.2版本，做了如下改动： 
       
     手写自己的plugin

v2.1版本，做了如下改动：

    1.引入注解，指定包名，自动扫描注解，将方法名与sql、返回值类型进行绑定。
    注解形式如下：
     public interface TestDAO {
   
       @SqlAnno("select * from test where id = ?")
       Test selectById(Integer id);
      }


v2.0版本相较与v1.0版本，做了如下改动：
    
    1. 由于返回值类型不固定，所以用MapperData存储，作为参数去传递
    
    2. 将一个query拆成3步，分别由ParameterHandler、StatementHandler、ResultSetHandler来处理
       1) ParameterHandler建立连接，获取PrepareStatement
       2）StatementHandler执行sql
       3) ResultSetHandler将ResultSet处理成我们想要的实体类
       
    3. 建立MyCachingExecutor，用来缓存结果，用到了装饰器模式