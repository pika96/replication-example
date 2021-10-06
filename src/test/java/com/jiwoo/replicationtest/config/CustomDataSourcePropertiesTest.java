package com.jiwoo.replicationtest.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.jiwoo.replicationtest.config.CustomDataSourceProperties.Slave;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomDataSourcePropertiesTest {

    @Autowired
    private CustomDataSourceProperties customDataSourceProperties;

    @DisplayName("")
    @Test
    void name() {
        //given
        //when
        //then
        assertThat(customDataSourceProperties.getUrl()).isEqualTo("jdbc:mysql://13.125.200.56:8000/master?serverTimezone=Asia/Seoul");
        assertThat(customDataSourceProperties.getUsername()).isEqualTo("repl");
        assertThat(customDataSourceProperties.getPassword()).isEqualTo("1234");

        Map<String, Slave> slave = customDataSourceProperties.getSlave();

        assertThat(slave.get("slave1").getName()).isEqualTo("slave-1");
        assertThat(slave.get("slave1").getUrl()).isEqualTo("jdbc:mysql://3.35.171.81:8000/master?serverTimezone=Asia/Seoul");
        assertThat(slave.get("slave1").getUsername()).isEqualTo("slave1");
        assertThat(slave.get("slave1").getPassword()).isEqualTo("1234");
    }
}