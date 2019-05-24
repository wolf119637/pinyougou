package test;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuoyao on 2019/5/12.
 */
public class GenerateTest {

//    public static void main(String[] args) throws Exception {
//        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/pinyougou_db?characterEncoding=utf-8", "root", "12345678");
//        Statement statement = root.createStatement();
//        boolean execute = statement.execute("select * from tb_user");
//        System.out.println(execute);
//        statement.close();
//        root.close();
//
//
//    }

    //@Test
    public void generate() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        // 指定 逆向工程配置文件
        File configFile = new File("./src/main/resources/generatorConfig.xml");


        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

    }
}
