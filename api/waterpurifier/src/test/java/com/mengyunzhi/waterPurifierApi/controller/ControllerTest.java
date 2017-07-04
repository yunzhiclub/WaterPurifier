package com.mengyunzhi.waterPurifierApi.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by panjie on 17/5/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/asciidoc/snippets")
public abstract class ControllerTest {
    @Autowired
    protected MockMvc mockMvc;
}
