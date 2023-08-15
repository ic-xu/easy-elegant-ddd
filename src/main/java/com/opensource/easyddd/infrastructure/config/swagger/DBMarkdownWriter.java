package com.opensource.easyddd.infrastructure.config.swagger;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 生成数据库文档
 */
@Component
@ConditionalOnBean(DataSource.class)
public class DBMarkdownWriter implements ApplicationRunner {
    @Resource
    DataSource dataSourceMysql;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //模板引擎配置 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("doc/db")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.MD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        // 生成文档配置（包含以下自定义版本号、描述等配置连接），文档名称拼接：数据库名_描述_版本.扩展名
        Configuration config = Configuration.builder()
                .title("数据库文档")
                // 版本号
                .version("1.0.0")
                // 描述
                .description("数据库表结构设计文档")
                // 数据源
                .dataSource(dataSourceMysql)
                // 模板引擎配置
                .engineConfig(engineConfig)
                // 加载配置：想要生成的表、想要忽略的表
                .produceConfig(getProcessConfig())
                .build();
        // 执行生成
        new DocumentationExecute(config).execute();
    }

    /**
     * 配置想要生成的表+ 配置想要忽略的表
     *
     * @return 生成表配置
     */
    public static ProcessConfig getProcessConfig() {
        // 忽略表名
        List<String> ignoreTableName = Arrays.asList("");

        return ProcessConfig.builder()
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(ignoreTableName)
                .build();
    }


}
