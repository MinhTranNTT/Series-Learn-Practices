## <u>Technologies</u>

JDK version: 11

MySQL: version 5.0 and later

Mybatis

## <u>Guide</u>

## ***version 1***

- Create new project Spring Boot with version less than 3.0

- Update pom.xml

  ```
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-devtools</artifactId>
          <scope>runtime</scope>
          <optional>true</optional>
      </dependency>
      <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <optional>true</optional>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
      </dependency>
      <!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus -->
      <dependency>
          <groupId>com.baomidou</groupId>
          <artifactId>mybatis-plus</artifactId>
          <version>3.1.1</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
      <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>8.0.32</version>
      </dependency>
  
      <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
      <dependency>
          <groupId>com.alibaba</groupId>
          <artifactId>druid</artifactId>
          <version>1.0.11</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
      <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-log4j12</artifactId>
          <version>2.0.6</version>
          <type>pom</type>
          <scope>test</scope>
      </dependency>
      <!-- https://mvnrepository.com/artifact/junit/junit -->
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.13.2</version>
          <scope>test</scope>
      </dependency>
  </dependencies>
  ```

- Update log4j.properties

  ```
  log4j.rootLogger=DEBUG,A1
  log4j.appender.A1=org.apache.log4j.ConsoleAppender
  log4j.appender.A1.layout=org.apache.log4j.PatternLayout
  log4j.appender.A1.layout.ConversionPattern=[%t] [%c]-[%p] %m%n
  ```

- Update mybatis-config.xml

  ```
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/databasename?useUnicode=true&amp;                         characterEncoding=utf8&amp;autoReconnect=true&amp;allowMultiQueries=true&amp;useSSL=false"/>
                  <property name="username" value="root"/>
                  <property name="password" value="password"/>
              </dataSource>
          </environment>
      </environments>
      <mappers>
          <mapper resource="./mapper/UserMapper.xml"/>
      </mappers>
  </configuration>
  ```

where the mapper with resource tag is the mapper xml file. with path directory "src/main/resources/mapper/UserMapper.xml"

- Update Pojo: src/main/java/com/dragon/pojo/User.java

  ```
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public class User {
      private Long id;
      private String userName;
      private String password;
      private String name;
      private Integer age;
      private String email;
  }
  ```

- Update Mapper: src/main/java/com/dragon/mapper/UserMapper.java

  ```
  public interface UserMapper {
      List<User> findAll();
  }
  ```

- Update XML mapper: src/main/resources/mapper/UserMapper.xml

  ```
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.dragon.mapper.UserMapper">
      <select id="findAll" resultType="com.dragon.pojo.User">
          select * from tb_user
      </select>
  </mapper>
  ```

  - Create database 

  ```
  CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  -- 插入测试数据
  INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`) VALUES
  ('1', 'zhangsan', '123456', '张三', '18', 'test1@itcast.cn');
  INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`) VALUES
  ('2', 'lisi', '123456', '李四', '20', 'test2@itcast.cn');
  INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`) VALUES
  ('3', 'wangwu', '123456', '王五', '28', 'test3@itcast.cn');
  INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`) VALUES
  ('4', 'zhaoliu', '123456', '赵六', '21', 'test4@itcast.cn');
  INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`) VALUES
  ('5', 'sunqi', '123456', '孙七', '24', 'test5@itcast.cn');
  ```

- Create Test Connection: src/test/java/com/dragon/TestMybatis/TestMybatis.java

  ```
  public class TestMybatis {
      @Test
      public void testUserList() throws Exception{
          String resource = "mybatis-config.xml";
          InputStream inputStream = Resources.getResourceAsStream(resource);
          SqlSessionFactory sqlSessionFactory = new
                  SqlSessionFactoryBuilder().build(inputStream);
          SqlSession sqlSession = sqlSessionFactory.openSession();
          UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
          List<User> list = userMapper.findAll();
          for (User user : list) {
              System.out.println(user);
          }
      }
  }
  ```

***END VERSION 1***

############################################################################################################

