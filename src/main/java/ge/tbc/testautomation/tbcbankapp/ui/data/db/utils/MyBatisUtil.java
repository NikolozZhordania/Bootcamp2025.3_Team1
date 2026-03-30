package ge.tbc.testautomation.tbcbankapp.ui.data.db.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class MyBatisUtil {
    private static SqlSessionFactory factory;

    static {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            Properties props = new Properties();
            props.setProperty("user.dir", System.getProperty("user.dir"));
            factory = new SqlSessionFactoryBuilder().build(reader, props);
        } catch (IOException e) {
            throw new RuntimeException(
                    "mybatis-config.xml not found in src/test/resources!", e);
        }
    }

    public static SqlSession getSqlSession() {
        return factory.openSession();
    }
}