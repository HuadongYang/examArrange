package com.bishe.exam;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

public class GeneratorCodeConfig {



    public static void main(String[] args) {
        // 生成文件的输出目录
        String outputDir = "F:\\TempData";
        // 需要包含的表名（与exclude二选一配置）
        String[] include = {"setting"};
        // 父包名
        String parent = "com.bishe.exam";

        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        {
            config
                    .setOutputDir(outputDir)    // 生成路径
                    .setFileOverride(true)      // 文件覆盖
                    .setActiveRecord(false)     // 是否支持AR模式
                    .setEnableCache(false)      // XML 二级缓存
                    .setBaseResultMap(true)     // XML ResultMap
                    .setBaseColumnList(true)    // XML columList
                    .setAuthor("")
                    // 自定义文件命名，注意 %s 会自动填充表实体属性！
                    .setMapperName("%sDao")
                    .setXmlName("%sDao")
                    .setServiceName("%sService")
                    .setServiceImplName("%sServiceImpl")
                    .setControllerName("%sApi");
            //.setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I;
        }
        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        {
            dsConfig
                    .setUrl("jdbc:mysql://localhost:3306/exam?serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true")
                    .setUsername("root")
                    .setPassword("root")

                    .setDriverName("com.mysql.jdbc.Driver")
                    .setDbType(DbType.MYSQL)  // 设置数据库类型
                    .setTypeConvert(new MySqlTypeConvert() {
                        @Override
                        public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                            String t = fieldType.toLowerCase();
                            if (t.contains("bigint")) {
                                return DbColumnType.BIG_INTEGER;
                            }
                            System.out.println("转换类型：" + fieldType);
                            return super.processTypeConvert(config, fieldType);
                        }
                    });
        }
        //3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        {
            stConfig
                    .setColumnNaming(NamingStrategy.underline_to_camel)   //数据库表字段映射到实体的命名策略
                    .setNaming(NamingStrategy.underline_to_camel)   //数据库表映射到实体的命名策略
                    .setEntityBooleanColumnRemoveIsPrefix(true)
                    .setInclude(include)  // 生成的表
            //.setTablePrefix("tbl_")
            //.setCapitalMode(true)                           //全局大写命名
            ;
        }
        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        {
            pkConfig
                    .setParent(parent)
                    .setController("api")   // 默认web
                    .setMapper("dao")       // 这里是持久层包名，默认 mapper
                    .setXml("dao")          // 这里是xml文件名，默认 mapper.xml
                    .setEntity("domain");   // 这里是实体类包名，默认 entity
            //.setService("service")        // 默认service
            //.setEntity("beans")           // 这里是实体类包名，默认 entity
            ;
        }
        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        {
            ag
                    .setGlobalConfig(config)
                    .setDataSource(dsConfig)
                    .setStrategy(stConfig)
                    .setPackageInfo(pkConfig);
        }
        //6. 执行
        ag.execute();
    }
}
